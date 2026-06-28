package org.springblade.modules.iot.haikangisup.controller;

import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.Result;
import org.springblade.modules.iot.haikangisup.HaiKangIsupCameraInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupUpgradeRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadResponse;
import org.springblade.modules.iot.haikangisup.callback.FRegisterCallBack;
import org.springblade.modules.iot.haikangisup.service.IHaiKangIsupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 海康ISUP设备控制器
 *
 * @author fengcheng
 */
@Slf4j
@RestController
@RequestMapping("/device/haikang-isup")
@AllArgsConstructor
@Tag(name = "海康ISUP设备管理", description = "海康ISUP设备相关接口")
public class HaiKangIsupController {

	@Autowired
	private IHaiKangIsupService haiKangIsupService;

	/**
	 * 获取设备列表
	 */
	@GetMapping("/list")
	@Operation(summary = "获取设备列表")
	public Result<ArrayList<HashMap<String, Object>>> deviceList() {
		return Result.data(FRegisterCallBack.deviceList);
	}

	/**
	 * 查询录像
	 */
	@GetMapping("/getRecMonth/{deviceId}/{channelId}")
	@ApiLog("查询录像")
	@Operation(summary = "查询录像")
	public Result<ArrayList<HashMap<String, Object>>> getRecMonth(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId,
		@Parameter(description = "通道ID") @PathVariable("channelId") Integer channelId,
		@Parameter(description = "开始时间") String startTime,
		@Parameter(description = "结束时间") String endTime) {
		return Result.data(haiKangIsupService.queryRecord(deviceId, channelId, startTime, endTime));
	}

	/**
	 * 重启设备
	 */
	@GetMapping("/rebootHaiKangDevice/{deviceId}")
	@ApiLog("重启海康设备")
	@Operation(summary = "重启海康设备")
	public Result<Boolean> rebootHaiKangDevice(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		log.info("重启海康设备 - deviceId:{}", deviceId);
		haiKangIsupService.restartDevice(deviceId);
		return Result.data(true);
	}

	/**
	 * 获取设备时间
	 */
	@GetMapping("/getHaiKangDevTime/{deviceId}")
	@ApiLog("获取海康设备时间")
	@Operation(summary = "获取海康设备时间")
	public Result<String> getHaiKangDevTime(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		log.info("获取海康设备时间 - deviceId:{}", deviceId);
		return Result.data(haiKangIsupService.getDevTime(deviceId));
	}

	/**
	 * 设置设备时间
	 */
	@GetMapping("/setHaiKangDevTime/{deviceId}")
	@ApiLog("设置海康设备时间")
	@Operation(summary = "设置海康设备时间")
	public Result<Boolean> setHaiKangDevTime(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId,
		@Parameter(description = "时间") String time) {
		log.info("设置海康设备时间 - deviceId:{}, time:{}", deviceId, time);
		haiKangIsupService.setDevTime(deviceId, time);
		return Result.data(true);
	}

	/**
	 * 抓图并保存
	 */
	@PostMapping("/captureAndSave/{deviceId}/{channelId}")
	@ApiLog("海康设备抓图")
	@Operation(summary = "海康设备抓图并保存")
	public Result<Long> captureAndSave(
		@Parameter(description = "设备ID") @PathVariable Long deviceId,
		@Parameter(description = "通道ID") @PathVariable Integer channelId,
		@Parameter(description = "抓图类型") String snapshotType) throws IOException {
		if (snapshotType == null || snapshotType.isEmpty()) {
			snapshotType = "manual";
		}
		log.info("海康设备抓图并保存 - deviceId:{}, channelId:{}, snapshotType:{}", deviceId, channelId, snapshotType);
		return Result.data(haiKangIsupService.captureAndSave(deviceId, channelId, snapshotType));
	}

	/**
	 * 录像下载
	 */
	@PostMapping("/downloadRecord")
	@ApiLog("海康设备录像下载")
	@Operation(summary = "海康设备录像下载")
	public Result<HaikangIsupRecordDownloadResponse> downloadRecord(@RequestBody HaikangIsupRecordDownloadRequest request) {
		log.info("海康设备录像下载 - request: {}", request);
		return Result.data(haiKangIsupService.downloadRecord(request));
	}

	/**
	 * 录像直接下载
	 */
	@PostMapping("/downloadRecordDirect")
	@ApiLog("海康设备录像直接下载")
	@Operation(summary = "海康设备录像直接下载到用户电脑")
	public ResponseEntity<Resource> downloadRecordDirect(@RequestBody HaikangIsupRecordDownloadRequest request) throws Exception {
		log.info("海康设备录像直接下载 - request: {}", request);
		File file = haiKangIsupService.downloadRecordFile(request);
		log.info("海康设备录像文件下载完成 - fileName: {}", file.getName());
		Resource resource = new FileSystemResource(file);
		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
			.body(resource);
	}

	/**
	 * 获取设备信息
	 */
	@GetMapping("/getHaiKangIsupDeviceInfo/{deviceId}")
	@ApiLog("获取海康ISUP设备信息")
	@Operation(summary = "获取海康ISUP设备信息")
	public Result<HashMap<String, Object>> getHaiKangIsupDeviceInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupDeviceInfo(deviceId));
	}

	/**
	 * 获取存储信息
	 */
	@GetMapping("/getHaiKangIsupStorageInfo/{deviceId}")
	@ApiLog("获取海康ISUP存储信息")
	@Operation(summary = "获取海康ISUP存储信息")
	public Result<HashMap<String, Object>> getHaiKangIsupStorageInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupStorageInfo(deviceId));
	}

	/**
	 * 获取SD卡信息
	 */
	@GetMapping("/getHaiKangIsupSDCardInfo/{deviceId}")
	@ApiLog("获取海康ISUP SD卡信息")
	@Operation(summary = "获取海康ISUP SD卡信息")
	public Result<HashMap<String, Object>> getHaiKangIsupSDCardInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupSDCardInfo(deviceId));
	}

	/**
	 * 获取码率信息
	 */
	@GetMapping("/getHaiKangIsupBitrateInfo/{deviceId}")
	@ApiLog("获取海康ISUP码率信息")
	@Operation(summary = "获取海康ISUP码率信息")
	public Result<HashMap<String, Object>> getHaiKangIsupBitrateInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupBitrateInfo(deviceId));
	}

	/**
	 * 获取网络状态信息
	 */
	@GetMapping("/getHaiKangIsupNetworkStatusInfo/{deviceId}")
	@ApiLog("获取海康ISUP网络状态信息")
	@Operation(summary = "获取海康ISUP网络状态信息")
	public Result<HashMap<String, Object>> getHaiKangIsupNetworkStatusInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupNetworkStatusInfo(deviceId));
	}

	/**
	 * 获取软件版本信息
	 */
	@GetMapping("/getHaiKangIsupSoftwareVersionInfo/{deviceId}")
	@ApiLog("获取海康ISUP软件版本信息")
	@Operation(summary = "获取海康ISUP软件版本信息")
	public Result<HashMap<String, Object>> getHaiKangIsupSoftwareVersionInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupSoftwareVersionInfo(deviceId));
	}

	/**
	 * 获取电源状态信息
	 */
	@GetMapping("/getHaiKangIsupPowerStateInfo/{deviceId}")
	@ApiLog("获取海康ISUP电源状态信息")
	@Operation(summary = "获取海康ISUP电源状态信息")
	public Result<HashMap<String, Object>> getHaiKangIsupPowerStateInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupPowerStateInfo(deviceId));
	}

	/**
	 * 获取摄像头属性信息
	 */
	@GetMapping("/getHaiKangIsupCameraInfo/{deviceId}")
	@ApiLog("获取海康ISUP摄像头属性信息")
	@Operation(summary = "获取海康ISUP摄像头属性信息")
	public Result<HaiKangIsupCameraInfo> getHaiKangIsupCameraInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupCameraInfo(deviceId));
	}

	/**
	 * 获取系统参数
	 */
	@GetMapping("/getHaiKangIsupSystemParam/{deviceId}")
	@ApiLog("获取海康ISUP系统参数")
	@Operation(summary = "获取海康ISUP系统参数")
	public Result<HashMap<String, Object>> getHaiKangIsupSystemParam(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupSystemParam(deviceId));
	}

	/**
	 * 获取视频参数
	 */
	@GetMapping("/getHaiKangIsupVideoParam/{deviceId}")
	@ApiLog("获取海康ISUP视频参数")
	@Operation(summary = "获取海康ISUP视频参数")
	public Result<HashMap<String, Object>> getHaiKangIsupVideoParam(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId,
		@Parameter(description = "码流类型") String streamType) {
		Integer channelId = 1;
		return Result.data(haiKangIsupService.getHaiKangIsupVideoParam(deviceId, channelId, streamType));
	}

	/**
	 * 获取系统状态信息
	 */
	@GetMapping("/getHaiKangIsupSystemStatus/{deviceId}")
	@ApiLog("获取海康ISUP系统状态信息")
	@Operation(summary = "获取海康ISUP系统状态信息")
	public Result<HashMap<String, Object>> getHaiKangIsupSystemStatus(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupSystemStatus(deviceId));
	}

	/**
	 * 获取设备信息（XML格式）
	 */
	@GetMapping("/getHaiKangIsupDeviceInfoXml/{deviceId}")
	@ApiLog("获取海康ISUP设备信息XML")
	@Operation(summary = "获取海康ISUP设备信息（XML格式）")
	public Result<HashMap<String, Object>> getHaiKangIsupDeviceInfoXml(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupDeviceInfoXml(deviceId));
	}

	/**
	 * 远程升级
	 */
	@PostMapping("/upgradeHaiKangIsupDevice")
	@ApiLog("海康ISUP设备升级")
	@Operation(summary = "海康ISUP设备远程升级")
	public Result<HashMap<String, Object>> upgradeHaiKangIsupDevice(@RequestBody HaiKangIsupUpgradeRequest request) {
		log.info("海康ISUP设备升级 - request: {}", request);
		return Result.data(haiKangIsupService.upgradeHaiKangIsupDevice(request));
	}

	/**
	 * 获取设备配置信息
	 */
	@GetMapping("/getHaiKangIsupDeviceConfig/{deviceId}")
	@ApiLog("获取海康ISUP设备配置")
	@Operation(summary = "获取海康ISUP设备配置")
	public Result<HashMap<String, Object>> getHaiKangIsupDeviceConfig(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupDeviceConfig(deviceId));
	}

	/**
	 * 设置设备配置信息
	 */
	@PostMapping("/setHaiKangIsupDeviceConfig/{deviceId}")
	@ApiLog("设置海康ISUP设备配置")
	@Operation(summary = "设置海康ISUP设备配置")
	public Result<HashMap<String, Object>> setHaiKangIsupDeviceConfig(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId,
		@RequestBody HashMap<String, Object> config) {
		log.info("设置海康ISUP设备配置 - deviceId: {}, config: {}", deviceId, config);
		return Result.data(haiKangIsupService.setHaiKangIsupDeviceConfig(deviceId, config));
	}

	/**
	 * 获取设备详细信息
	 */
	@GetMapping("/getHaiKangIsupDeviceDetail/{deviceId}")
	@ApiLog("获取海康ISUP设备详细信息")
	@Operation(summary = "获取海康ISUP设备详细信息")
	public Result<HashMap<String, Object>> getHaiKangIsupDeviceDetail(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupDeviceDetail(deviceId));
	}

	/**
	 * 获取设备版本信息
	 */
	@GetMapping("/getHaiKangIsupVersionInfo/{deviceId}")
	@ApiLog("获取海康ISUP设备版本信息")
	@Operation(summary = "获取海康ISUP设备版本信息")
	public Result<HashMap<String, Object>> getHaiKangIsupVersionInfo(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId) {
		return Result.data(haiKangIsupService.getHaiKangIsupVersionInfo(deviceId));
	}

	/**
	 * 获取移动侦测区域参数
	 */
	@GetMapping("/getHaiKangIsupMotionArea/{deviceId}")
	@ApiLog("获取海康ISUP移动侦测区域参数")
	@Operation(summary = "获取海康ISUP移动侦测区域参数")
	public Result<HashMap<String, Object>> getHaiKangIsupMotionArea(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId,
		@Parameter(description = "通道ID") Integer channelId) {
		if (channelId == null) {
			channelId = 1;
		}
		return Result.data(haiKangIsupService.getHaiKangIsupMotionArea(deviceId, channelId));
	}

	/**
	 * 设置移动侦测区域参数
	 */
	@PostMapping("/setHaiKangIsupMotionArea/{deviceId}")
	@ApiLog("设置海康ISUP移动侦测区域参数")
	@Operation(summary = "设置海康ISUP移动侦测区域参数")
	public Result<HashMap<String, Object>> setHaiKangIsupMotionArea(
		@Parameter(description = "设备ID") @PathVariable("deviceId") Long deviceId,
		@Parameter(description = "通道ID") Integer channelId,
		@RequestBody HashMap<String, Object> motionAreaConfig) {
		if (channelId == null) {
			channelId = 1;
		}
		log.info("设置海康ISUP移动侦测区域参数 - deviceId: {}, channelId: {}", deviceId, channelId);
		return Result.data(haiKangIsupService.setHaiKangIsupMotionArea(deviceId, channelId, motionAreaConfig));
	}
}
