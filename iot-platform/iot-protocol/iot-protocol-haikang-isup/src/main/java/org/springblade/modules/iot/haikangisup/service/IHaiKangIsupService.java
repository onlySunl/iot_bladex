package org.springblade.modules.iot.haikangisup.service;

import org.springblade.modules.iot.haikangisup.HaiKangIsupCameraInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupDeviceInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupPresetInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupUpgradeRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 海康ISUP服务接口
 *
 * @author fengcheng
 */
public interface IHaiKangIsupService {

	/**
	 * 获取设备信息
	 */
	HaiKangIsupDeviceInfo getDevInfo(Integer lUserID);

	/**
	 * 开始播放
	 */
	void startPlay(Long deviceId, Integer channelId, String streamType);

	/**
	 * 停止播放
	 */
	void stopPlay(Long id);

	/**
	 * 开始云台控制
	 */
	void startPtz(Long deviceId, Integer channelId, int ptzCmd, int speed);

	/**
	 * 结束云台控制
	 */
	void endPtz(Long deviceId, Integer channelId, int ptzCmd, int speed);

	/**
	 * 设置预置点
	 */
	void setPreset(Long deviceId, Integer channelId, int presetIndex);

	/**
	 * 清除预置点
	 */
	void clearPreset(Long deviceId, Integer channelId, int presetIndex);

	/**
	 * 调用预置点
	 */
	void gotoPreset(Long deviceId, Integer channelId, int presetIndex);

	/**
	 * 辅助设备控制
	 */
	void cameraAuxControl(Long deviceId, Integer channelId, String operation, boolean isStart);

	/**
	 * 巡航控制
	 */
	void cruiseControl(Long deviceId, Integer channelId, String operation, Integer param);

	/**
	 * 获取预置点列表
	 */
	List<HaiKangIsupPresetInfo> getPresetList(Long deviceId, Integer channelId);

	/**
	 * 查询录像
	 */
	ArrayList<HashMap<String, Object>> queryRecord(Long deviceId, Integer channelId, String startTime, String endTime);

	/**
	 * 开始回放
	 */
	void startPlayback(Long deviceId, Integer channelId, String startTime, String endTime);

	/**
	 * 停止回放
	 */
	void stopPlayback(Long id);

	/**
	 * 重启设备
	 */
	void restartDevice(Long deviceId);

	/**
	 * 获取设备时间
	 */
	String getDevTime(Long deviceId);

	/**
	 * 设置设备时间
	 */
	void setDevTime(Long deviceId, String time);

	/**
	 * 抓图并保存
	 */
	Long captureAndSave(Long deviceId, int channelId, String snapshotType) throws IOException;

	/**
	 * 录像下载
	 */
	HaikangIsupRecordDownloadResponse downloadRecord(HaikangIsupRecordDownloadRequest request);

	/**
	 * 录像文件下载
	 */
	File downloadRecordFile(HaikangIsupRecordDownloadRequest request) throws Exception;

	/**
	 * 获取设备信息
	 */
	HashMap<String, Object> getHaiKangIsupDeviceInfo(Long deviceId);

	/**
	 * 获取存储信息
	 */
	HashMap<String, Object> getHaiKangIsupStorageInfo(Long deviceId);

	/**
	 * 获取SD卡信息
	 */
	HashMap<String, Object> getHaiKangIsupSDCardInfo(Long deviceId);

	/**
	 * 获取码率信息
	 */
	HashMap<String, Object> getHaiKangIsupBitrateInfo(Long deviceId);

	/**
	 * 获取网络状态
	 */
	HashMap<String, Object> getHaiKangIsupNetworkStatusInfo(Long deviceId);

	/**
	 * 获取软件版本
	 */
	HashMap<String, Object> getHaiKangIsupSoftwareVersionInfo(Long deviceId);

	/**
	 * 获取电源状态
	 */
	HashMap<String, Object> getHaiKangIsupPowerStateInfo(Long deviceId);

	/**
	 * 获取摄像头信息
	 */
	HaiKangIsupCameraInfo getHaiKangIsupCameraInfo(Long deviceId);

	/**
	 * 获取系统参数
	 */
	HashMap<String, Object> getHaiKangIsupSystemParam(Long deviceId);

	/**
	 * 获取视频参数
	 */
	HashMap<String, Object> getHaiKangIsupVideoParam(Long deviceId, Integer channelId, String streamType);

	/**
	 * 获取系统状态
	 */
	HashMap<String, Object> getHaiKangIsupSystemStatus(Long deviceId);

	/**
	 * 获取设备信息(XML)
	 */
	HashMap<String, Object> getHaiKangIsupDeviceInfoXml(Long deviceId);

	/**
	 * 远程升级
	 */
	HashMap<String, Object> upgradeHaiKangIsupDevice(HaiKangIsupUpgradeRequest request);

	/**
	 * 获取设备配置
	 */
	HashMap<String, Object> getHaiKangIsupDeviceConfig(Long deviceId);

	/**
	 * 设置设备配置
	 */
	HashMap<String, Object> setHaiKangIsupDeviceConfig(Long deviceId, HashMap<String, Object> config);

	/**
	 * 获取设备详情
	 */
	HashMap<String, Object> getHaiKangIsupDeviceDetail(Long deviceId);

	/**
	 * 获取版本信息
	 */
	HashMap<String, Object> getHaiKangIsupVersionInfo(Long deviceId);

	/**
	 * 获取移动侦测区域
	 */
	HashMap<String, Object> getHaiKangIsupMotionArea(Long deviceId, Integer channelId);

	/**
	 * 设置移动侦测区域
	 */
	HashMap<String, Object> setHaiKangIsupMotionArea(Long deviceId, Integer channelId, HashMap<String, Object> motionAreaConfig);
}
