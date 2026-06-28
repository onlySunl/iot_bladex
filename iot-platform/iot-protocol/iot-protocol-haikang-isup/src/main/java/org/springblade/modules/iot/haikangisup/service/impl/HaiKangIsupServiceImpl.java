package org.springblade.modules.iot.haikangisup.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.Result;
import org.springblade.modules.iot.haikangisup.HaiKangIsupCameraInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupDeviceInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupPresetInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupUpgradeRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadResponse;
import org.springblade.modules.iot.haikangisup.callback.FRegisterCallBack;
import org.springblade.modules.iot.haikangisup.constants.HaiKangIsupAlarmTypeConstants;
import org.springblade.modules.iot.haikangisup.enums.HCIsupCameraAuxEnum;
import org.springblade.modules.iot.haikangisup.enums.HCIsupCruiseControlEnum;
import org.springblade.modules.iot.haikangisup.enums.HCIsupPresetControlEnum;
import org.springblade.modules.iot.haikangisup.service.IHaiKangIsupService;
import org.springblade.modules.iot.haikangisup.service.IHaikangIsupMediaStreamService;
import org.springblade.modules.iot.haikangisup.service.cms.CmsService;
import org.springblade.modules.iot.haikangisup.service.cms.HCISUPCMS;
import org.springblade.modules.iot.haikangisup.utils.CommonMethod;
import org.springblade.modules.iot.haikangisup.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 海康ISUP服务实现
 *
 * @author fengcheng
 */
@Slf4j
@Service
public class HaiKangIsupServiceImpl implements IHaiKangIsupService {

	@Autowired(required = false)
	private IHaikangIsupMediaStreamService mediaStreamService;

	@Value("${blade.iot.haikang-isup.file-path:./uploads}")
	private String filePath;

	@Value("${blade.iot.haikang-isup.file-domain:}")
	private String fileDomain;

	@Value("${blade.iot.haikang-isup.file-prefix:}")
	private String filePrefix;

	@Override
	public HaiKangIsupDeviceInfo getDevInfo(Integer lUserID) {
		if (CmsService.hCEhomeCMS == null) {
			return null;
		}
		boolean bRet;
		HCISUPCMS.NET_EHOME_DEVICE_INFO ehomeDeviceInfo = new HCISUPCMS.NET_EHOME_DEVICE_INFO();
		ehomeDeviceInfo.read();
		ehomeDeviceInfo.dwSize = ehomeDeviceInfo.size();
		ehomeDeviceInfo.write();

		HCISUPCMS.NET_EHOME_CONFIG strEhomeCfd = new HCISUPCMS.NET_EHOME_CONFIG();
		strEhomeCfd.pCondBuf = null;
		strEhomeCfd.dwCondSize = 0;
		strEhomeCfd.pOutBuf = ehomeDeviceInfo.getPointer();
		strEhomeCfd.dwOutSize = ehomeDeviceInfo.size();
		strEhomeCfd.pInBuf = null;
		strEhomeCfd.dwInSize = 0;
		strEhomeCfd.write();

		bRet = CmsService.hCEhomeCMS.NET_ECMS_GetDevConfig(lUserID, 1, strEhomeCfd.getPointer(), strEhomeCfd.size());
		if (!bRet) {
			int dwErr = CmsService.hCEhomeCMS.NET_ECMS_GetLastError();
			log.error("获取设备信息失败，Error:{}", dwErr);
			return null;
		} else {
			ehomeDeviceInfo.read();
			HaiKangIsupDeviceInfo deviceInfo = new HaiKangIsupDeviceInfo();
			deviceInfo.setDwChannelNumber(ehomeDeviceInfo.dwChannelNumber);
			deviceInfo.setDwChannelAmount(ehomeDeviceInfo.dwChannelAmount);
			deviceInfo.setDwDevType(ehomeDeviceInfo.dwDevType);
			deviceInfo.setDwDiskNumber(ehomeDeviceInfo.dwDiskNumber);
			deviceInfo.setSSerialNumber(CommonUtil.parseHikvisionString(ehomeDeviceInfo.sSerialNumber));
			deviceInfo.setDwAlarmOutPortNum(ehomeDeviceInfo.dwAlarmInPortNum);
			deviceInfo.setDwAlarmOutAmount(ehomeDeviceInfo.dwAlarmOutAmount);
			deviceInfo.setDwStartChannel(ehomeDeviceInfo.dwStartChannel);
			deviceInfo.setDwAudioChanNum(ehomeDeviceInfo.dwAudioChanNum);
			deviceInfo.setDwMaxDigitChannelNum(ehomeDeviceInfo.dwMaxDigitChannelNum);
			deviceInfo.setDwSupportZeroChan(ehomeDeviceInfo.dwSupportZeroChan);
			deviceInfo.setDwStartZeroChan(ehomeDeviceInfo.dwStartZeroChan);
			deviceInfo.setDwSmartType(ehomeDeviceInfo.dwSmartType);
			deviceInfo.setDwAudioEncType(ehomeDeviceInfo.dwAudioEncType);
			deviceInfo.setSSIMCardSN(CommonUtil.parseHikvisionString(ehomeDeviceInfo.sSIMCardSN));
			deviceInfo.setSSIMCardPhoneNum(CommonUtil.parseHikvisionString(ehomeDeviceInfo.sSIMCardPhoneNum));
			return deviceInfo;
		}
	}

	@Override
	public void startPlay(Long deviceId, Integer channelId, String streamType) {
		log.info("开始播放 - deviceId:{}, channelId:{}, streamType:{}", deviceId, channelId, streamType);
	}

	@Override
	public void stopPlay(Long id) {
		log.info("停止播放 - id:{}", id);
	}

	@Override
	public void startPtz(Long deviceId, Integer channelId, int ptzCmd, int speed) {
		log.info("开始云台控制 - deviceId:{}, channelId:{}, ptzCmd:{}, speed:{}", deviceId, channelId, ptzCmd, speed);
		Integer lUserID = FRegisterCallBack.lUserIDMap.get(deviceId.toString());
		if (lUserID == null) {
			log.error("未找到用户信息, deviceId:{}", deviceId);
			return;
		}
		executePtzControl(lUserID, channelId, ptzCmd, 0, speed, null);
	}

	@Override
	public void endPtz(Long deviceId, Integer channelId, int ptzCmd, int speed) {
		log.info("结束云台控制 - deviceId:{}, channelId:{}, ptzCmd:{}, speed:{}", deviceId, channelId, ptzCmd, speed);
		Integer lUserID = FRegisterCallBack.lUserIDMap.get(deviceId.toString());
		if (lUserID == null) {
			log.error("未找到用户信息, deviceId:{}", deviceId);
			return;
		}
		executePtzControl(lUserID, channelId, ptzCmd, 1, speed, null);
	}

	private void executePtzControl(Integer lUserID, Integer channelId, int ptzCmd, int action, int speed, Integer param) {
		if (CmsService.hCEhomeCMS == null) {
			return;
		}
		HCISUPCMS.NET_EHOME_REMOTE_CTRL_PARAM net_ehome_remote_ctrl_param = new HCISUPCMS.NET_EHOME_REMOTE_CTRL_PARAM();
		HCISUPCMS.NET_EHOME_PTZ_PARAM net_ehome_ptz_param = new HCISUPCMS.NET_EHOME_PTZ_PARAM();
		net_ehome_ptz_param.read();
		net_ehome_ptz_param.dwSize = net_ehome_ptz_param.size();
		net_ehome_ptz_param.byPTZCmd = (byte) ptzCmd;
		net_ehome_ptz_param.byAction = (byte) action;
		net_ehome_ptz_param.bySpeed = (byte) speed;
		net_ehome_ptz_param.write();
		net_ehome_remote_ctrl_param.read();
		net_ehome_remote_ctrl_param.dwSize = net_ehome_remote_ctrl_param.size();
		net_ehome_remote_ctrl_param.lpInbuffer = net_ehome_ptz_param.getPointer();
		net_ehome_remote_ctrl_param.dwInBufferSize = net_ehome_ptz_param.size();

		net_ehome_remote_ctrl_param.lpCondBuffer = com.sun.jna.Memory.POINTER_SIZE;
		net_ehome_remote_ctrl_param.dwCondBufferSize = 4;
		net_ehome_remote_ctrl_param.write();

		boolean b_ptz = CmsService.hCEhomeCMS.NET_ECMS_RemoteControl(lUserID, HCISUPCMS.NET_EHOME_PTZ_CTRL, net_ehome_remote_ctrl_param);
		if (!b_ptz) {
			int iErr = CmsService.hCEhomeCMS.NET_ECMS_GetLastError();
			log.error("云台控制失败，错误：{}", iErr);
		}
	}

	@Override
	public void setPreset(Long deviceId, Integer channelId, int presetIndex) {
		log.info("设置预置点 - deviceId:{}, channelId:{}, presetIndex:{}", deviceId, channelId, presetIndex);
	}

	@Override
	public void clearPreset(Long deviceId, Integer channelId, int presetIndex) {
		log.info("清除预置点 - deviceId:{}, channelId:{}, presetIndex:{}", deviceId, channelId, presetIndex);
	}

	@Override
	public void gotoPreset(Long deviceId, Integer channelId, int presetIndex) {
		log.info("调用预置点 - deviceId:{}, channelId:{}, presetIndex:{}", deviceId, channelId, presetIndex);
	}

	@Override
	public void cameraAuxControl(Long deviceId, Integer channelId, String operation, boolean isStart) {
		log.info("辅助设备控制 - deviceId:{}, channelId:{}, operation:{}, isStart:{}", deviceId, channelId, operation, isStart);
	}

	@Override
	public void cruiseControl(Long deviceId, Integer channelId, String operation, Integer param) {
		log.info("巡航控制 - deviceId:{}, channelId:{}, operation:{}, param:{}", deviceId, channelId, operation, param);
	}

	@Override
	public List<HaiKangIsupPresetInfo> getPresetList(Long deviceId, Integer channelId) {
		log.info("获取预置点列表 - deviceId:{}, channelId:{}", deviceId, channelId);
		return new ArrayList<>();
	}

	@Override
	public ArrayList<HashMap<String, Object>> queryRecord(Long deviceId, Integer channelId, String startTime, String endTime) {
		log.info("查询录像 - deviceId:{}, channelId:{}, startTime:{}, endTime:{}", deviceId, channelId, startTime, endTime);
		return new ArrayList<>();
	}

	@Override
	public void startPlayback(Long deviceId, Integer channelId, String startTime, String endTime) {
		log.info("开始回放 - deviceId:{}, channelId:{}, startTime:{}, endTime:{}", deviceId, channelId, startTime, endTime);
	}

	@Override
	public void stopPlayback(Long id) {
		log.info("停止回放 - id:{}", id);
	}

	@Override
	public void restartDevice(Long deviceId) {
		log.info("重启设备 - deviceId:{}", deviceId);
	}

	@Override
	public String getDevTime(Long deviceId) {
		log.info("获取设备时间 - deviceId:{}", deviceId);
		return "";
	}

	@Override
	public void setDevTime(Long deviceId, String time) {
		log.info("设置设备时间 - deviceId:{}, time:{}", deviceId, time);
	}

	@Override
	public Long captureAndSave(Long deviceId, int channelId, String snapshotType) throws IOException {
		log.info("抓图并保存 - deviceId:{}, channelId:{}, snapshotType:{}", deviceId, channelId, snapshotType);
		return 0L;
	}

	@Override
	public HaikangIsupRecordDownloadResponse downloadRecord(HaikangIsupRecordDownloadRequest request) {
		log.info("录像下载 - request:{}", request);
		return new HaikangIsupRecordDownloadResponse();
	}

	@Override
	public File downloadRecordFile(HaikangIsupRecordDownloadRequest request) throws Exception {
		log.info("录像文件下载 - request:{}", request);
		return null;
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupDeviceInfo(Long deviceId) {
		log.info("获取设备信息 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupStorageInfo(Long deviceId) {
		log.info("获取存储信息 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupSDCardInfo(Long deviceId) {
		log.info("获取SD卡信息 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupBitrateInfo(Long deviceId) {
		log.info("获取码率信息 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupNetworkStatusInfo(Long deviceId) {
		log.info("获取网络状态 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupSoftwareVersionInfo(Long deviceId) {
		log.info("获取软件版本 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupPowerStateInfo(Long deviceId) {
		log.info("获取电源状态 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HaiKangIsupCameraInfo getHaiKangIsupCameraInfo(Long deviceId) {
		log.info("获取摄像头信息 - deviceId:{}", deviceId);
		return new HaiKangIsupCameraInfo();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupSystemParam(Long deviceId) {
		log.info("获取系统参数 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupVideoParam(Long deviceId, Integer channelId, String streamType) {
		log.info("获取视频参数 - deviceId:{}, channelId:{}, streamType:{}", deviceId, channelId, streamType);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupSystemStatus(Long deviceId) {
		log.info("获取系统状态 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupDeviceInfoXml(Long deviceId) {
		log.info("获取设备信息XML - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> upgradeHaiKangIsupDevice(HaiKangIsupUpgradeRequest request) {
		log.info("远程升级 - request:{}", request);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupDeviceConfig(Long deviceId) {
		log.info("获取设备配置 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> setHaiKangIsupDeviceConfig(Long deviceId, HashMap<String, Object> config) {
		log.info("设置设备配置 - deviceId:{}, config:{}", deviceId, config);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupDeviceDetail(Long deviceId) {
		log.info("获取设备详情 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupVersionInfo(Long deviceId) {
		log.info("获取版本信息 - deviceId:{}", deviceId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> getHaiKangIsupMotionArea(Long deviceId, Integer channelId) {
		log.info("获取移动侦测区域 - deviceId:{}, channelId:{}", deviceId, channelId);
		return new HashMap<>();
	}

	@Override
	public HashMap<String, Object> setHaiKangIsupMotionArea(Long deviceId, Integer channelId, HashMap<String, Object> motionAreaConfig) {
		log.info("设置移动侦测区域 - deviceId:{}, channelId:{}, config:{}", deviceId, channelId, motionAreaConfig);
		return new HashMap<>();
	}
}
