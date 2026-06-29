package org.springblade.modules.iot.haikangisup.controller;


import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.haikangisup.HaiKangIsupCameraInfo;
import org.springblade.modules.iot.haikangisup.HaiKangIsupUpgradeRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadRequest;
import org.springblade.modules.iot.haikangisup.HaikangIsupRecordDownloadResponse;
import org.springblade.modules.iot.haikangisup.callBack.FRegisterCallBack;
import org.springblade.modules.iot.haikangisup.haikang.IHaiKangIsupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 海康isup Controller
 *
 * @FileName HaiKangIsupController
 * @Description
 * @Author fengcheng
 * @date 2026-03-28
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("/device")
public class HaiKangIsupController extends BladeController {

    @Autowired
    private IHaiKangIsupService haiKangIsupService;

    /**
     * 获取设备列表
     */
    @GetMapping("/list")
    public R deviceList() {
        return R.data(FRegisterCallBack.deviceList);
    }

    /**
     * 海康设备查询录像
     *
     * @param deviceId  设备id
     * @param channelId 通道id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping("/getRecMonth/{deviceId}/{channelId}")
    public R<ArrayList<HashMap<String, Object>>> getRecMonth(@PathVariable("deviceId") Long deviceId,
                                                             @PathVariable("channelId") Integer channelId,
                                                             @NotBlank(message = "开始时间不能为空") String startTime,
                                                             @NotBlank(message = "结束时间不能为空") String endTime) {
        return R.data(haiKangIsupService.queryRecord(deviceId, channelId, startTime, endTime));
    }

    /**
     * 重启海康设备
     *
     * @param deviceId 设备id
     * @return
     */
    @GetMapping("/rebootHaiKangDevice/{deviceId}")
    public R<Boolean> rebootHaiKangDevice(@PathVariable("deviceId") Long deviceId) {
        log.info("重启海康设备 - deviceId:{}", deviceId);
        haiKangIsupService.restartDevice(deviceId);
        return R.data(true);
    }

    /**
     * 获取海康设备时间
     *
     * @param deviceId 设备id
     * @return
     */
    @GetMapping("/getHaiKangDevTime/{deviceId}")
    public R<String> getHaiKangDevTime(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康设备时间 - deviceId:{}", deviceId);
        return R.data(haiKangIsupService.getDevTime(deviceId));
    }

    /**
     * 设置海康设备时间
     *
     * @param deviceId 设备id
     * @param time     时间，格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    @GetMapping("/setHaiKangDevTime/{deviceId}")
    public R<Boolean> setHaiKangDevTime(@PathVariable("deviceId") Long deviceId, String time) {
        log.info("设置海康设备时间 - deviceId:{}, time:{}", deviceId, time);
        haiKangIsupService.setDevTime(deviceId, time);
        return R.data(true);
    }

    /**
     * 海康设备抓图并保存
     *
     * @param deviceId     设备id
     * @param channelId    通道id
     * @param snapshotType 抓图类型
     * @return
     */
    @PostMapping("/captureAndSave/{deviceId}/{channelId}")
    public R<Long> captureAndSave(@PathVariable Long deviceId, @PathVariable Integer channelId, String snapshotType) throws IOException {
        if (snapshotType == null || snapshotType.isEmpty()) {
            snapshotType = "manual";
        }
        log.info("海康设备抓图并保存 - deviceId:{}, channelId:{}, snapshotType:{}", deviceId, channelId, snapshotType);
        if (channelId == null) {
            return R.fail("channelId参数不能为空");
        }
        return R.data(haiKangIsupService.captureAndSave(deviceId, channelId, snapshotType));
    }

    /**
     * 海康设备录像下载
     */
    @PostMapping("/downloadRecord")
    public R<HaikangIsupRecordDownloadResponse> downloadRecord(@RequestBody HaikangIsupRecordDownloadRequest request) {
        log.info("海康设备录像下载 - request: {}", request);
        return R.data(haiKangIsupService.downloadRecord(request));
    }

    /**
     * 海康设备录像直接下载到用户电脑
     */
    @PostMapping("/downloadRecordDirect")
    public ResponseEntity<Resource> downloadRecordDirect(@RequestBody HaikangIsupRecordDownloadRequest request) throws Exception {
        log.info("海康设备录像直接下载到用户电脑 - request: {}", request);
        File file = haiKangIsupService.downloadRecordFile(request);
        log.info("海康设备录像文件下载完成 - fileName: {}", file.getName());
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

    /**
     * 获取海康设备信息
     */
    @GetMapping("/getHaiKangIsupDeviceInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupDeviceInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP设备信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupDeviceInfo(deviceId));
    }

    /**
     * 获取海康存储信息
     */
    @GetMapping("/getHaiKangIsupStorageInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupStorageInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP存储信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupStorageInfo(deviceId));
    }

    /**
     * 获取海康SD卡信息
     */
    @GetMapping("/getHaiKangIsupSDCardInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupSDCardInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP SD卡信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupSDCardInfo(deviceId));
    }

    /**
     * 获取海康码率信息
     */
    @GetMapping("/getHaiKangIsupBitrateInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupBitrateInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP码率信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupBitrateInfo(deviceId));
    }

    /**
     * 获取海康网络状态信息
     */
    @GetMapping("/getHaiKangIsupNetworkStatusInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupNetworkStatusInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP网络状态信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupNetworkStatusInfo(deviceId));
    }

    /**
     * 获取海康软件版本信息
     */
    @GetMapping("/getHaiKangIsupSoftwareVersionInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupSoftwareVersionInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP软件版本信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupSoftwareVersionInfo(deviceId));
    }

    /**
     * 获取海康电源状态信息
     */
    @GetMapping("/getHaiKangIsupPowerStateInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupPowerStateInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP电源状态信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupPowerStateInfo(deviceId));
    }

    /**
     * 获取海康摄像头属性信息
     */
    @GetMapping("/getHaiKangIsupCameraInfo/{deviceId}")
    public R<HaiKangIsupCameraInfo> getHaiKangIsupCameraInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP摄像头属性信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupCameraInfo(deviceId));
    }

    /**
     * 获取海康系统参数
     */
    @GetMapping("/getHaiKangIsupSystemParam/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupSystemParam(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP系统参数 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupSystemParam(deviceId));
    }

    /**
     * 获取海康视频参数
     */
    @GetMapping("/getHaiKangIsupVideoParam/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupVideoParam(@PathVariable("deviceId") Long deviceId, String streamType) {
        Integer channelId = 1;
        log.info("获取海康ISUP视频参数 - deviceId:{}, channelId:{}, streamType:{}", deviceId, channelId, streamType);
        return R.data(haiKangIsupService.getHaiKangIsupVideoParam(deviceId, channelId, streamType));
    }

    /**
     * 获取海康系统状态信息
     */
    @GetMapping("/getHaiKangIsupSystemStatus/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupSystemStatus(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP系统状态信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupSystemStatus(deviceId));
    }

    /**
     * 获取海康设备信息（XML格式）
     */
    @GetMapping("/getHaiKangIsupDeviceInfoXml/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupDeviceInfoXml(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP设备信息（XML） - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupDeviceInfoXml(deviceId));
    }

    /**
     * 海康设备远程升级
     */
    @PostMapping("/upgradeHaiKangIsupDevice")
    public R<HashMap<String, Object>> upgradeHaiKangIsupDevice(@RequestBody HaiKangIsupUpgradeRequest request) {
        log.info("海康ISUP设备升级 - request: {}", request);
        return R.data(haiKangIsupService.upgradeHaiKangIsupDevice(request));
    }

    /**
     * 获取设备配置信息
     */
    @GetMapping("/getHaiKangIsupDeviceConfig/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupDeviceConfig(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP设备配置 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupDeviceConfig(deviceId));
    }

    /**
     * 设置设备配置信息
     */
    @PostMapping("/setHaiKangIsupDeviceConfig/{deviceId}")
    public R<HashMap<String, Object>> setHaiKangIsupDeviceConfig(@PathVariable("deviceId") Long deviceId, @RequestBody HashMap<String, Object> config) {
        log.info("设置海康ISUP设备配置 - deviceId: {}, config: {}", deviceId, config);
        return R.data(haiKangIsupService.setHaiKangIsupDeviceConfig(deviceId, config));
    }

    /**
     * 获取设备详细信息
     */
    @GetMapping("/getHaiKangIsupDeviceDetail/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupDeviceDetail(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP设备详细信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupDeviceDetail(deviceId));
    }

    /**
     * 获取设备版本信息
     */
    @GetMapping("/getHaiKangIsupVersionInfo/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupVersionInfo(@PathVariable("deviceId") Long deviceId) {
        log.info("获取海康ISUP设备版本信息 - deviceId: {}", deviceId);
        return R.data(haiKangIsupService.getHaiKangIsupVersionInfo(deviceId));
    }



    /**
     * 获取移动侦测区域参数
     */
    @GetMapping("/getHaiKangIsupMotionArea/{deviceId}")
    public R<HashMap<String, Object>> getHaiKangIsupMotionArea(@PathVariable("deviceId") Long deviceId, 
                                                                  Integer channelId) {
        if (channelId == null) {
            channelId = 1;
        }
        log.info("获取海康ISUP移动侦测区域参数 - deviceId: {}, channelId: {}", deviceId, channelId);
        return R.data(haiKangIsupService.getHaiKangIsupMotionArea(deviceId, channelId));
    }

    /**
     * 设置移动侦测区域参数
     */
    @PostMapping("/setHaiKangIsupMotionArea/{deviceId}")
    public R<HashMap<String, Object>> setHaiKangIsupMotionArea(@PathVariable("deviceId") Long deviceId, 
                                                                  Integer channelId,
                                                                  @RequestBody HashMap<String, Object> motionAreaConfig) {
        if (channelId == null) {
            channelId = 1;
        }
        log.info("设置海康ISUP移动侦测区域参数 - deviceId: {}, channelId: {}", deviceId, channelId);
        return R.data(haiKangIsupService.setHaiKangIsupMotionArea(deviceId, channelId, motionAreaConfig));
    }
}
