package org.springblade.modules.iot.service;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.constants.SecurityConstants;
import org.springblade.modules.iot.common.constants.ServiceNameConstants;
import org.springblade.modules.iot.common.domain.RtpServerParam;
import org.springblade.modules.iot.domain.DahuaDevice;
import org.springblade.modules.iot.domain.LoginDevice;
import org.springblade.modules.iot.domain.DahuaRecordDownloadRequest;
import org.springblade.modules.iot.domain.DahuaRecordDownloadResponse;
import org.springblade.modules.iot.factory.RemoteDaHuaFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 大华sdk 服务
 *
 * @FileName RemoteDaHuaService
 * @Description
 * @Author fengcheng
 * @date 2026-03-30
 **/
@FeignClient(contextId = "remoteDaHuaService", value = ServiceNameConstants.DAHUA_SERVICE, fallbackFactory = RemoteDaHuaFallbackFactory.class)
public interface RemoteDaHuaService {

    /**
     * 登录设备
     *
     * @param loginDevice 大华设备登录信息
     * 
     */
    @PostMapping(value = "/api/dahua/loginDevice")
    public R<Void> loginDevice(@RequestBody LoginDevice loginDevice);

    /**
     * 查询是否登录
     *
     * @param ip     设备ip
     * 
     * @return
     */
    @PostMapping(value = "/api/dahua/isUserId/{ip}")
    R<Boolean> isUserId(@PathVariable String ip);

    /**
     * 大华设备获取时间
     *
     * @param ip     设备ip
     * 
     * @return
     */
    @PostMapping(value = "/api/dahua/getTime/{ip}")
    R<String> getTime(@PathVariable String ip);

    /**
     * 大华设备设置时间
     *
     * @param id     设备id
     * @param date   日期时间
     * @param type   类型
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/setTime/{id}")
    R<Boolean> setTime(@PathVariable Long id, @RequestParam String date, @RequestParam boolean type);

    /**
     * 大华设备重启
     *
     * @param id     设备id
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/reboot/{id}")
    R<Boolean> reboot(@PathVariable Long id);

    /**
     * 获取大华主动上线设备
     *
     * @param ip     设备ip
     * 
     * @return
     */
    @PostMapping(value = "/api/dahua/getDahuaDevice/{ip}")
    R<DahuaDevice> getDahuaDevice(@PathVariable String ip);

    /**
     * 退出登录
     *
     * @param ip     设备ip
     * 
     * @return
     */
    @PostMapping(value = "/api/dahua/logoutDevice/{ip}")
    R<Boolean> logoutDevice(@PathVariable String ip);

    /**
     * 开始播放
     *
     * @param rtpServerParam 播放参数
     * 
     */
    @PostMapping(value = "/api/dahua/startPlay")
    R<Void> startPlay(@RequestBody RtpServerParam rtpServerParam);

    /**
     * 停止播放
     *
     * @param id     设备id
     * 
     */
    @GetMapping(value = "/api/dahua/stopPlay/{id}")
    R<Void> stopPlay(@PathVariable Long id);

    /**
     * 大华设备云台控制（开始）
     *
     * @param id        设备id
     * @param channelId 通道id
     * @param direction 方向
     * @param speed     速度
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/ptzControlUpStart/{id}/{channelId}")
    R<Boolean> ptzControlUpStart(@PathVariable Long id, @PathVariable int channelId, @RequestParam String direction, @RequestParam Integer speed);

    /**
     * 大华设备云台控制（停止）
     *
     * @param id        设备id
     * @param channelId 通道id
     * @param direction 方向
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/ptzControlUpEnd/{id}/{channelId}")
    R<Boolean> ptzControlUpEnd(@PathVariable Long id, @PathVariable int channelId, @RequestParam String direction);

    /**
     * 大华设备获取预置点列表
     *
     * @param id        设备id
     * @param channelId 通道id
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/getPresetList/{id}/{channelId}")
    R<ArrayList<HashMap<String, Object>>> getPresetList(@PathVariable Long id, @PathVariable int channelId);

    /**
     * 大华设备设置预置点
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param presetIndex 预置点号
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/setPreset/{id}/{channelId}")
    R<Void> setPreset(@PathVariable Long id, @PathVariable int channelId, @RequestParam int presetIndex);

    /**
     * 大华删除设置预置点
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param presetIndex 预置点号
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/delPreset/{id}/{channelId}")
    R<Void> delPreset(@PathVariable Long id, @PathVariable int channelId, @RequestParam int presetIndex);

    /**
     * 大华调用设置预置点
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param presetIndex 预置点号
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/invokePreset/{id}/{channelId}")
    R<Void> invokePreset(@PathVariable Long id, @PathVariable int channelId, @RequestParam int presetIndex);

    /**
     * 灯光控制
     *
     * @param id        设备id
     * @param channelId 通道id
     * @param action    0-关,1-开
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/controlLight/{id}/{channelId}")
    R<Void> controlLight(@PathVariable Long id, @PathVariable int channelId, @RequestParam int action);

    /**
     * 雨刷控制
     *
     * @param id        设备id
     * @param channelId 通道id
     * @param action    0-关,1-开
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/controlWiper/{id}/{channelId}")
    R<Void> controlWiper(@PathVariable Long id, @PathVariable int channelId, @RequestParam int action);

    /**
     * 开始点间巡航
     *
     * @param id         设备id
     * @param channelId 通道id
     * @param tourIndex 巡航线路号
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/startTour/{id}/{channelId}")
    R<Void> startTour(@PathVariable Long id, @PathVariable int channelId, @RequestParam int tourIndex);

    /**
     * 停止点间巡航
     *
     * @param id        设备id
     * @param channelId 通道id
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/stopTour/{id}/{channelId}")
    R<Void> stopTour(@PathVariable Long id, @PathVariable int channelId);

    /**
     * 添加预置点到巡航线路
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param tourIndex   巡航线路号
     * @param presetIndex 预置点号
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/addPresetToTour/{id}/{channelId}")
    R<Void> addPresetToTour(@PathVariable Long id, @PathVariable int channelId, @RequestParam int tourIndex, @RequestParam int presetIndex);

    /**
     * 从巡航线路删除预置点
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param tourIndex   巡航线路号
     * @param presetIndex 预置点号
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/removePresetFromTour/{id}/{channelId}")
    R<Void> removePresetFromTour(@PathVariable Long id, @PathVariable int channelId, @RequestParam int tourIndex, @RequestParam int presetIndex);

    /**
     * 清除巡航线路
     *
     * @param id         设备id
     * @param channelId 通道id
     * @param tourIndex 巡航线路号
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/clearTour/{id}/{channelId}")
    R<Void> clearTour(@PathVariable Long id, @PathVariable int channelId, @RequestParam int tourIndex);

    /**
     * 大华设备查询录像
     *
     * @param id         设备id
     * @param channelId  通道id
     * @param startTime  开始时间
     * @param endTime    结束时间
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/queryRecord/{id}/{channelId}")
    R<ArrayList<HashMap<String, Object>>> queryRecord(@PathVariable Long id, @PathVariable int channelId, @RequestParam String startTime, @RequestParam String endTime);

    /**
     * 大华设备开始录像回放
     *
     * @param rtpServerParam 回放参数
     * 
     * @return
     */
    @PostMapping(value = "/api/dahua/startPlayback")
    R<Void> startPlayback(@RequestBody RtpServerParam rtpServerParam);

    /**
     * 大华设备停止录像回放
     *
     * @param id     设备id
     * 
     * @return
     */
    @GetMapping(value = "/api/dahua/stopPlayback/{id}")
    R<Void> stopPlayback(@PathVariable Long id);

    /**
     * 大华设备抓图并保存
     *
     * @param id           设备id
     * @param channelId    通道id
     * @param snapshotType 抓图类型
     * 
     * @return 抓图记录id
     */
    @PostMapping(value = "/api/dahua/captureAndSave/{id}/{channelId}")
    R<Long> captureAndSave(@PathVariable Long id, @PathVariable int channelId, @RequestParam String snapshotType);

    /**
     * 大华设备录像下载
     *
     * @param request 下载请求
     * 
     * @return 下载响应
     */
    @PostMapping(value = "/api/dahua/downloadRecord")
    R<DahuaRecordDownloadResponse> downloadRecord(@RequestBody DahuaRecordDownloadRequest request);
}
