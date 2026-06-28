package org.springblade.modules.iot.haikangisup.callback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.config.HaiKangIsupConfig;
import org.springblade.modules.iot.haikangisup.enums.HaiKangIsupAlarmTypeConstants;
import org.springblade.modules.iot.haikangisup.service.cms.HCISUPCMS;
import org.springblade.modules.iot.haikangisup.utils.CommonMethod;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 海康ISUP设备注册回调
 *
 * @author fengcheng
 */
@Slf4j
@Component
public class FRegisterCallBack implements HCISUPCMS.DEVICE_REGISTER_CB {

	public static final ConcurrentHashMap<Integer, String> deviceIdMap = new ConcurrentHashMap<>(16);

	public static final ConcurrentHashMap<String, Integer> lUserIDMap = new ConcurrentHashMap<>(16);

	public static final CopyOnWriteArrayList<Device> deviceList = new CopyOnWriteArrayList<>();

	private HaiKangIsupConfig haiKangIsupConfig;

	public FRegisterCallBack(HaiKangIsupConfig haiKangIsupConfig) {
		this.haiKangIsupConfig = haiKangIsupConfig;
	}

	@Override
	public boolean invoke(int lUserID, int dwDataType, com.sun.jna.Pointer pOutBuffer, int dwOutLen, com.sun.jna.Pointer pInBuffer, int dwInLen, com.sun.jna.Pointer pUser) {
		log.info("设备注册状态回调: {}, lUserID: {}", dwDataType, lUserID);
		HCISUPCMS.NET_EHOME_DEV_REG_INFO_V12 strDevRegInfo = new HCISUPCMS.NET_EHOME_DEV_REG_INFO_V12();
		com.sun.jna.Pointer pDevRegInfo = strDevRegInfo.getPointer();

		switch (dwDataType) {
			case HCISUPCMS.EHOME_REGISTER_TYPE.ENUM_DEV_ON: {
				strDevRegInfo.write();
				pDevRegInfo.write(0, pOutBuffer.getByteArray(0, strDevRegInfo.size()), 0, strDevRegInfo.size());
				strDevRegInfo.read();

				HCISUPCMS.NET_EHOME_SERVER_INFO_V50 strEhomeServerInfo = new HCISUPCMS.NET_EHOME_SERVER_INFO_V50();
				strEhomeServerInfo.read();
				byte[] byCmsIP = new byte[0];
				byCmsIP = haiKangIsupConfig.getAlarmServer().getIp().getBytes();
				System.arraycopy(byCmsIP, 0, strEhomeServerInfo.struUDPAlarmSever.szIP, 0, byCmsIP.length);
				System.arraycopy(byCmsIP, 0, strEhomeServerInfo.struTCPAlarmSever.szIP, 0, byCmsIP.length);

				strEhomeServerInfo.dwAlarmServerType = haiKangIsupConfig.getAlarmServer().getType();
				strEhomeServerInfo.struTCPAlarmSever.wPort = (short) (int) haiKangIsupConfig.getAlarmServer().getTcpPort();
				strEhomeServerInfo.struUDPAlarmSever.wPort = (short) (int) haiKangIsupConfig.getAlarmServer().getUdpPort();

				byte[] byClouldAccessKey = "test".getBytes();
				System.arraycopy(byClouldAccessKey, 0, strEhomeServerInfo.byClouldAccessKey, 0, byClouldAccessKey.length);
				byte[] byClouldSecretKey = "12345".getBytes();
				System.arraycopy(byClouldSecretKey, 0, strEhomeServerInfo.byClouldSecretKey, 0, byClouldSecretKey.length);
				strEhomeServerInfo.dwClouldPoolId = 1;

				byte[] bySSIP = new byte[0];
				bySSIP = haiKangIsupConfig.getPicServer().getIp().getBytes();
				System.arraycopy(bySSIP, 0, strEhomeServerInfo.struPictureSever.szIP, 0, bySSIP.length);
				strEhomeServerInfo.struPictureSever.wPort = (short) (int) haiKangIsupConfig.getPicServer().getPort();
				strEhomeServerInfo.dwPicServerType = haiKangIsupConfig.getPicServer().getType();
				strEhomeServerInfo.write();
				dwInLen = strEhomeServerInfo.size();
				pInBuffer.write(0, strEhomeServerInfo.getPointer().getByteArray(0, dwInLen), 0, dwInLen);

				String deviceId = new String(strDevRegInfo.struRegInfo.byDeviceID).trim();
				String ip = new String(strDevRegInfo.struRegInfo.struDevAdd.szIP).trim();
				log.info("设备上线, DeviceID: {}, LoginID: {}", deviceId, lUserID);

				Device device = new Device();
				device.setDeviceId("haikang_isup_" + deviceId);
				device.setIp(ip);
				device.setLUserID(lUserID);

				boolean exists = deviceList.stream()
					.anyMatch(d -> d.getDeviceId().equals(deviceId) && d.getIp().equals(ip));

				if (!exists) {
					deviceList.add(device);
				}

				deviceIdMap.put(lUserID, ip);
				lUserIDMap.put(ip, lUserID);
				return true;
			}

			case HCISUPCMS.EHOME_REGISTER_TYPE.ENUM_DEV_AUTH: {
				strDevRegInfo.write();
				pDevRegInfo.write(0, pOutBuffer.getByteArray(0, strDevRegInfo.size()), 0, strDevRegInfo.size());
				strDevRegInfo.read();
				String szEHomeKey = haiKangIsupConfig.getIsupKey();
				byte[] bs = szEHomeKey.getBytes();
				pInBuffer.write(0, bs, 0, szEHomeKey.length());
				log.info("Ehome5.0设备认证回调 Device auth, DeviceID is: {}", new String(strDevRegInfo.struRegInfo.byDeviceID).trim());
				break;
			}

			case HCISUPCMS.EHOME_REGISTER_TYPE.ENUM_DEV_SESSIONKEY: {
				log.info("Ehome5.0设备Sessionkey回调");
				strDevRegInfo.write();
				pDevRegInfo.write(0, pOutBuffer.getByteArray(0, strDevRegInfo.size()), 0, strDevRegInfo.size());
				strDevRegInfo.read();
				HCISUPCMS.NET_EHOME_DEV_SESSIONKEY struSessionKey = new HCISUPCMS.NET_EHOME_DEV_SESSIONKEY();
				System.arraycopy(strDevRegInfo.struRegInfo.byDeviceID, 0, struSessionKey.sDeviceID, 0, strDevRegInfo.struRegInfo.byDeviceID.length);
				System.arraycopy(strDevRegInfo.struRegInfo.bySessionKey, 0, struSessionKey.sSessionKey, 0, strDevRegInfo.struRegInfo.bySessionKey.length);
				struSessionKey.write();
				com.sun.jna.Pointer pSessionKey = struSessionKey.getPointer();
				break;
			}

			case HCISUPCMS.EHOME_REGISTER_TYPE.ENUM_DEV_OFF: {
				log.info("设备下线回调 Device off, lUserID is: {}", lUserID);
				String ip = deviceIdMap.remove(lUserID);
				if (ip != null) {
					lUserIDMap.remove(ip);
				}
				deviceList.removeIf(d -> Objects.equals(d.getIp(), ip));
				break;
			}

			default:
				log.warn("FRegisterCallBack unknown type: {}", dwDataType);
				break;
		}
		return true;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Device implements Serializable {
		private String deviceId;
		private String ip;
		private Integer lUserID;
	}
}
