package org.springblade.modules.iot.haikangisup.callback;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.Result;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.modules.iot.haikangisup.config.HaiKangIsupConfig;
import org.springblade.modules.iot.haikangisup.service.alarm.HCISUPAlarm;
import org.springblade.modules.iot.haikangisup.service.alarm.HCISUPAlarm.EHomeMsgCallBack;
import org.springblade.modules.iot.haikangisup.utils.XmlParserUtils;
import org.springblade.modules.iot.haikangisup.xml.EventNotificationAlert;
import org.springblade.modules.iot.haikangisup.xml.model.AlarmEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 海康 ISUP 报警消息回调
 */
@Slf4j
@Component
public class EHomeMsgCallBack implements EHomeMsgCallBack {

	private final HaiKangIsupConfig haiKangIsupConfig;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public EHomeMsgCallBack(HaiKangIsupConfig haiKangIsupConfig) {
		this.haiKangIsupConfig = haiKangIsupConfig;
	}

	@Override
	public boolean invoke(int iHandle, HCISUPAlarm.NET_EHOME_ALARM_MSG pAlarmMsg, Pointer pUser) {
		Boolean alarmStorage = haiKangIsupConfig.getAlarmServer().getAlarmStorage();
		if (alarmStorage == null || !alarmStorage) {
			log.debug("收到报警信息，报警存储功能未开启，跳过处理");
			return true;
		}

		// 先解析XML判断是否是关键报警
		EventNotificationAlert event = null;
		if (pAlarmMsg.dwXmlBufLen != 0) {
			HCISUPAlarm.BYTE_ARRAY strXMLData = new HCISUPAlarm.BYTE_ARRAY(pAlarmMsg.dwXmlBufLen);
			strXMLData.write();
			Pointer pPlateInfo = strXMLData.getPointer();
			pPlateInfo.write(0, pAlarmMsg.pXmlBuf.getByteArray(0, strXMLData.size()), 0, strXMLData.size());
			strXMLData.read();
			String strXML = new String(strXMLData.byValue).trim();
			event = XmlParserUtils.parseXmlToObject(strXML, EventNotificationAlert.class);
		}

		// 过滤垃圾报警，只保留关键的安全报警
		boolean isCriticalAlarm = isCriticalAlarm(pAlarmMsg.dwAlarmType, event);
		if (!isCriticalAlarm) {
			log.debug("非关键报警，跳过处理, 报警类型:{}", pAlarmMsg.dwAlarmType);
			return true;
		}

		log.info("收到报警信息，报警类型：{}, dwAlarmInfoLen: {}, dwXmlBufLen: {}, dwHttpUrlLen: {}",
			pAlarmMsg.dwAlarmType, pAlarmMsg.dwAlarmInfoLen, pAlarmMsg.dwXmlBufLen, pAlarmMsg.dwHttpUrlLen);

		if (event != null) {
			log.info("从 pXmlBuf 解析报警事件，开始保存到数据库");
			saveAlarmToDatabase(event.getDeviceID(), event.getIpAddress(), event, null, null, null);
		}

		processAlarmData(pAlarmMsg);
		return true;
	}

	private void processAlarmData(HCISUPAlarm.NET_EHOME_ALARM_MSG pAlarmMsg) {
		int dwAlarmType = pAlarmMsg.dwAlarmType;
		if (pAlarmMsg.pHttpUrl != Pointer.NULL) {
			dwAlarmType = HCISUPAlarm.EHOME_ISAPI_ALARM;
		}

		log.info("处理报警类型：{}", dwAlarmType);

		switch (dwAlarmType) {
			case HCISUPAlarm.EHOME_ALARM:
				processEhomeAlarm(pAlarmMsg);
				break;
			case HCISUPAlarm.EHOME_ISAPI_ALARM:
				processEhomeIsapiAlarm(pAlarmMsg);
				break;
			case HCISUPAlarm.EHOME_ALARM_HEATMAP_REPORT:
				log.info("热图报告报警");
				processBasicAlarm(pAlarmMsg, "heatmap");
				break;
			case HCISUPAlarm.EHOME_ALARM_FACESNAP_REPORT:
				log.info("人脸抓拍报警");
				processBasicAlarm(pAlarmMsg, "facesnap");
				break;
			case HCISUPAlarm.EHOME_ALARM_GPS:
				log.info("GPS报警");
				processBasicAlarm(pAlarmMsg, "gps");
				break;
			case HCISUPAlarm.EHOME_ALARM_CID_REPORT:
				log.info("CID报告报警");
				processBasicAlarm(pAlarmMsg, "cid");
				break;
			case HCISUPAlarm.EHOME_ALARM_NOTICE_PICURL:
				log.info("图片URL通知报警");
				processAlarmWithPicUrl(pAlarmMsg);
				break;
			case HCISUPAlarm.EHOME_ALARM_NOTIFY_FAIL:
				log.info("通知失败报警");
				processBasicAlarm(pAlarmMsg, "notify_fail");
				break;
			case HCISUPAlarm.EHOME_ALARM_ACS:
				log.info("门禁报警");
				processBasicAlarm(pAlarmMsg, "acs");
				break;
			case HCISUPAlarm.EHOME_ALARM_WIRELESS_INFO:
				log.info("无线信息报警");
				processBasicAlarm(pAlarmMsg, "wireless");
				break;
			case HCISUPAlarm.EHOME_ALARM_MPDCDATA:
				log.info("MPDC数据报警");
				processBasicAlarm(pAlarmMsg, "mpdc");
				break;
			case HCISUPAlarm.EHOME_ALARM_QRCODE:
				log.info("二维码报警");
				processBasicAlarm(pAlarmMsg, "qrcode");
				break;
			case HCISUPAlarm.EHOME_ALARM_FACETEMP:
				log.info("人脸测温报警");
				processBasicAlarm(pAlarmMsg, "facetemp");
				break;
			default:
				log.warn("未知报警类型：{}，尝试通用处理", dwAlarmType);
				processBasicAlarm(pAlarmMsg, "unknown");
				break;
		}
	}

	private void processAlarmWithPicUrl(HCISUPAlarm.NET_EHOME_ALARM_MSG pAlarmMsg) {
		try {
			String imageUrl = "";
			if (pAlarmMsg.pHttpUrl != Pointer.NULL && pAlarmMsg.dwHttpUrlLen > 0) {
				HCISUPAlarm.BYTE_ARRAY urlData = new HCISUPAlarm.BYTE_ARRAY(pAlarmMsg.dwHttpUrlLen);
				urlData.write();
				Pointer pUrl = urlData.getPointer();
				pUrl.write(0, pAlarmMsg.pHttpUrl.getByteArray(0, urlData.size()), 0, urlData.size());
				urlData.read();
				imageUrl = new String(urlData.byValue).trim();
				log.info("获取到图片URL：{}", imageUrl);
			}

			String deviceId = new String(pAlarmMsg.sSerialNumber).trim();

			saveAlarmToDatabase(deviceId, null, null, 0, pAlarmMsg.dwAlarmType, null, imageUrl);
		} catch (Exception e) {
			log.error("处理图片URL报警失败", e);
		}
	}

	private void processBasicAlarm(HCISUPAlarm.NET_EHOME_ALARM_MSG pAlarmMsg, String type) {
		try {
			String deviceId = new String(pAlarmMsg.sSerialNumber).trim();

			if (pAlarmMsg.pXmlBuf != Pointer.NULL && pAlarmMsg.dwXmlBufLen > 0) {
				HCISUPAlarm.BYTE_ARRAY xmlData = new HCISUPAlarm.BYTE_ARRAY(pAlarmMsg.dwXmlBufLen);
				xmlData.write();
				Pointer pXml = xmlData.getPointer();
				pXml.write(0, pAlarmMsg.pXmlBuf.getByteArray(0, xmlData.size()), 0, xmlData.size());
				xmlData.read();
				String strXML = new String(xmlData.byValue).trim();
				log.info("{}报警的XML数据：{}", type, strXML);

				EventNotificationAlert event = XmlParserUtils.parseXmlToObject(strXML, EventNotificationAlert.class);
				if (event != null) {
					saveAlarmToDatabase(event.getDeviceID(), event.getIpAddress(), event, null, pAlarmMsg.dwAlarmType, null);
					return;
				}
			}

			if (pAlarmMsg.pAlarmInfo != Pointer.NULL) {
				HCISUPAlarm.NET_EHOME_ALARM_INFO ehomeAlarmInfo = new HCISUPAlarm.NET_EHOME_ALARM_INFO();
				ehomeAlarmInfo.write();
				Pointer pEhomeAlarmInfo = ehomeAlarmInfo.getPointer();
				pEhomeAlarmInfo.write(0, pAlarmMsg.pAlarmInfo.getByteArray(0, ehomeAlarmInfo.size()), 0, ehomeAlarmInfo.size());
				ehomeAlarmInfo.read();

				if (deviceId.isEmpty()) {
					deviceId = Native.toString(ehomeAlarmInfo.szDeviceID);
				}
				int channel = ehomeAlarmInfo.dwVideoChannel;

				if (pAlarmMsg.pXmlBuf != Pointer.NULL && pAlarmMsg.dwXmlBufLen > 0) {
					HCISUPAlarm.BYTE_ARRAY xmlData = new HCISUPAlarm.BYTE_ARRAY(pAlarmMsg.dwXmlBufLen);
					xmlData.write();
					Pointer pXml = xmlData.getPointer();
					pXml.write(0, pAlarmMsg.pXmlBuf.getByteArray(0, xmlData.size()), 0, xmlData.size());
					xmlData.read();
					String strXML = new String(xmlData.byValue).trim();
					log.info("{}报警的XML数据：{}", type, strXML);

					EventNotificationAlert event = XmlParserUtils.parseXmlToObject(strXML, EventNotificationAlert.class);
					saveAlarmToDatabase(deviceId, null, event, channel, pAlarmMsg.dwAlarmType, ehomeAlarmInfo);
				} else {
					saveAlarmToDatabase(deviceId, null, null, channel, pAlarmMsg.dwAlarmType, ehomeAlarmInfo);
				}
			} else {
				saveAlarmToDatabase(deviceId, null, null, 0, pAlarmMsg.dwAlarmType, null);
			}
		} catch (Exception e) {
			log.error("处理{}报警失败", type, e);
		}
	}

	private void processEhomeAlarm(HCISUPAlarm.NET_EHOME_ALARM_MSG pAlarmMsg) {
		try {
			HCISUPAlarm.NET_EHOME_ALARM_INFO ehomeAlarmInfo = new HCISUPAlarm.NET_EHOME_ALARM_INFO();
			ehomeAlarmInfo.write();
			Pointer pEhomeAlarmInfo = ehomeAlarmInfo.getPointer();
			pEhomeAlarmInfo.write(0, pAlarmMsg.pAlarmInfo.getByteArray(0, ehomeAlarmInfo.size()), 0, ehomeAlarmInfo.size());
			ehomeAlarmInfo.read();

			String deviceId = Native.toString(ehomeAlarmInfo.szDeviceID);
			int alarmType = ehomeAlarmInfo.dwAlarmType;
			int channel = ehomeAlarmInfo.dwVideoChannel;

			log.info("基础报警 - 设备ID:{}, 报警类型:{}, 通道:{}", deviceId, alarmType, channel);

			saveAlarmToDatabase(deviceId, null, null, channel, alarmType, ehomeAlarmInfo);
		} catch (Exception e) {
			log.error("处理基础报警失败", e);
		}
	}

	private void processEhomeIsapiAlarm(HCISUPAlarm.NET_EHOME_ALARM_MSG pAlarmMsg) {
		if (pAlarmMsg.pHttpUrl != Pointer.NULL && pAlarmMsg.dwHttpUrlLen > 0) {
			log.info("ISAPI报警，URL不为空，暂不处理分离数据");
			processAlarmWithPicUrl(pAlarmMsg);
			return;
		}

		if (pAlarmMsg.pAlarmInfo == Pointer.NULL) {
			return;
		}

		try {
			HCISUPAlarm.NET_EHOME_ALARM_ISAPI_INFO strIsapiInfo = new HCISUPAlarm.NET_EHOME_ALARM_ISAPI_INFO();
			strIsapiInfo.write();
			Pointer pIsapiInfo = strIsapiInfo.getPointer();
			pIsapiInfo.write(0, pAlarmMsg.pAlarmInfo.getByteArray(0, strIsapiInfo.size()), 0, strIsapiInfo.size());
			strIsapiInfo.read();

			if (strIsapiInfo.pAlarmData != Pointer.NULL && strIsapiInfo.dwAlarmDataLen > 0 && strIsapiInfo.byDataType != 0) {
				HCISUPAlarm.BYTE_ARRAY m_strIsapiData = new HCISUPAlarm.BYTE_ARRAY(strIsapiInfo.dwAlarmDataLen);
				m_strIsapiData.write();
				Pointer pPlateInfo = m_strIsapiData.getPointer();
				pPlateInfo.write(0, strIsapiInfo.pAlarmData.getByteArray(0, m_strIsapiData.size()), 0, m_strIsapiData.size());
				m_strIsapiData.read();

				String xmlContent = new String(m_strIsapiData.byValue).trim();
				log.info("ISAPI报警XML:{}", xmlContent);

				EventNotificationAlert event = XmlParserUtils.parseXmlToObject(xmlContent, EventNotificationAlert.class);
				if (event != null) {
					log.info("解析到报警事件 - 设备ID:{}, 设备IP:{}, 事件类型:{}, 通道:{}",
						event.getDeviceID(), event.getIpAddress(), event.getEventType(), event.getChannelID());
					saveAlarmToDatabase(event.getDeviceID(), event.getIpAddress(), event, null, 0, null);
				}
			}
		} catch (Exception e) {
			log.error("处理ISAPI报警失败", e);
		}
	}

	private void saveAlarmToDatabase(String deviceId, String deviceIp, EventNotificationAlert event,
									Integer channel, Integer alarmType, HCISUPAlarm.NET_EHOME_ALARM_INFO ehomeAlarmInfo) {
		saveAlarmToDatabase(deviceId, deviceIp, event, channel, alarmType, ehomeAlarmInfo, null);
	}

	private void saveAlarmToDatabase(String deviceId, String deviceIp, EventNotificationAlert event,
									Integer channel, Integer alarmType, HCISUPAlarm.NET_EHOME_ALARM_INFO ehomeAlarmInfo, String imageUrl) {
		try {
			log.info("保存报警到数据库 - 设备ID:{}, 设备IP:{}, 通道:{}, 报警类型:{}", deviceId, deviceIp, channel, alarmType);
		} catch (Exception e) {
			log.error("保存报警到数据库失败", e);
		}
	}

	/**
	 * 判断是否是关键的安全报警
	 */
	private boolean isCriticalAlarm(int alarmType, EventNotificationAlert event) {
		// 先检查event中的报警类型
		if (event != null && event.getEventType() != null) {
			String eventType = event.getEventType().toLowerCase();
			switch (eventType) {
				case "vmd":
				case "motion_detection":
				case "shelteralarm":
				case "video_cover":
				case "linedetection":
				case "cross_line_detection":
				case "fielddetection":
				case "area_intrusion":
				case "enter_area":
				case "leave_area":
				case "object_removal":
				case "removedetection":
				case "object_leave":
				case "parkingdetection":
				case "facedetection":
				case "face_detection":
					return true;
			}
		}

		// 检查基础报警类型
		switch (alarmType) {
			case 1: // EHOME_ALARM - 基础报警
			case 3: // EHOME_ALARM_FACESNAP_REPORT - 人脸抓拍
			case 13: // EHOME_ISAPI_ALARM - ISAPI报警
			case 6: // EHOME_ALARM_NOTICE_PICURL - 图片URL通知
				return true;
			default:
				return false;
		}
	}
}
