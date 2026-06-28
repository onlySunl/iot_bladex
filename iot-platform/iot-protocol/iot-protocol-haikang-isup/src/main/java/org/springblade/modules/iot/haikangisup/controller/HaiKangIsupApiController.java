package org.springblade.modules.iot.haikangisup.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.Result;
import org.springblade.modules.iot.haikangisup.callback.FRegisterCallBack;
import org.springblade.modules.iot.haikangisup.HaiKangIsupDeviceInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupPresetInfo;
import org.springblade.modules.iot.haikangisup.service.IHaiKangIsupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 海康ISUP API Controller
 *
 * @Author
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class HaiKangIsupApiController {

	private final IHaiKangIsupService haiKangIsupService;

	/**
	 * 获取设备登录的用户ID
	 */
	public Result<Integer> getUserId(String ip) {
		return Result.data(FRegisterCallBack.lUserIDMap.get(ip));
	}

	/**
	 * 获取设备信息
	 */
	public Result<HaiKangIsupDeviceInfo> getDevInfo(String ip) {
		Integer lUserID = FRegisterCallBack.lUserIDMap.get(ip);
		if (lUserID == null) {
			return Result.fail("设备未登录");
		}
		return Result.data(haiKangIsupService.getDevInfo(lUserID));
	}

	/**
	 * 开始播放
	 */
	public Result<Void> startPlay(String rtpServerParam) {
		haiKangIsupService.startPlay(rtpServerParam);
		return Result.success();
	}

	/**
	 * 停止播放
	 */
	public Result<Void> stopPlay(Long id) {
		haiKangIsupService.stopPlay(id);
		return Result.success();
	}

	/**
	 * 开始回放
	 */
	public Result<Void> startPlayback(String rtpServerParam) {
		haiKangIsupService.startPlayback(rtpServerParam);
		return Result.success();
	}

	/**
	 * 停止回放
	 */
	public Result<Void> stopPlayback(Long id) {
		haiKangIsupService.stopPlayback(id);
		return Result.success();
	}

	/**
	 * 开始云台控制
	 */
	public Result<Void> startPtz(Long deviceId, Integer channelId, int PTZCmd, int speed) {
		haiKangIsupService.startPtz(deviceId, channelId, PTZCmd, speed);
		return Result.success();
	}

	/**
	 * 结束云台控制
	 */
	public Result<Void> endPtz(Long deviceId, Integer channelId, int PTZCmd, int speed) {
		haiKangIsupService.endPtz(deviceId, channelId, PTZCmd, speed);
		return Result.success();
	}

	/**
	 * 设置预置点
	 */
	public Result<Void> setPreset(Long deviceId, Integer channelId, int presetIndex) {
		haiKangIsupService.setPreset(deviceId, channelId, presetIndex);
		return Result.success();
	}

	/**
	 * 清除预置点
	 */
	public Result<Void> clearPreset(Long deviceId, Integer channelId, int presetIndex) {
		haiKangIsupService.clearPreset(deviceId, channelId, presetIndex);
		return Result.success();
	}

	/**
	 * 调用预置点
	 */
	public Result<Void> gotoPreset(Long deviceId, Integer channelId, int presetIndex) {
		haiKangIsupService.gotoPreset(deviceId, channelId, presetIndex);
		return Result.success();
	}

	/**
	 * 辅助设备控制
	 */
	public Result<Void> cameraAuxControl(Long deviceId, Integer channelId, String operation, boolean isStart) {
		haiKangIsupService.cameraAuxControl(deviceId, channelId, operation, isStart);
		return Result.success();
	}

	/**
	 * 巡航控制
	 */
	public Result<Void> cruiseControl(Long deviceId, Integer channelId, String operation, Integer param) {
		haiKangIsupService.cruiseControl(deviceId, channelId, operation, param);
		return Result.success();
	}

	/**
	 * 获取预置点列表
	 */
	public Result<List<HaiKangIsupPresetInfo>> getPresetList(Long deviceId, Integer channelId) {
		return Result.data(haiKangIsupService.getPresetList(deviceId, channelId));
	}

	/**
	 * 查询录像
	 */
	public Result<ArrayList<HashMap<String, Object>>> getRecMonth(Long deviceId, Integer channelId, String startTime, String endTime) {
		return Result.data(haiKangIsupService.queryRecord(deviceId, channelId, startTime, endTime));
	}

	/**
	 * 重启设备
	 */
	public Result<Void> restartDevice(Long deviceId) {
		haiKangIsupService.restartDevice(deviceId);
		return Result.success();
	}

	/**
	 * 获取设备时间
	 */
	public Result<String> getDevTime(Long deviceId) {
		return Result.data(haiKangIsupService.getDevTime(deviceId));
	}

	/**
	 * 设置设备时间
	 */
	public Result<Void> setDevTime(Long deviceId, String time) {
		haiKangIsupService.setDevTime(deviceId, time);
		return Result.success();
	}

	/**
	 * 设置移动侦测区域
	 */
	public Result<HashMap<String, Object>> setHaiKangIsupMotionArea(Long deviceId, Integer channelId, HashMap<String, Object> motionAreaConfig) {
		if (channelId == null) {
			channelId = 1;
		}
		return Result.data(haiKangIsupService.setHaiKangIsupMotionArea(deviceId, channelId, motionAreaConfig));
	}
}
