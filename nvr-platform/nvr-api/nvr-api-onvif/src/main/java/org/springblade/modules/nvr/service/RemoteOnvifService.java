package org.springblade.modules.nvr.service;

import org.springblade.core.tool.api.R;
import org.springblade.modules.nvr.common.constants.SecurityConstants;
import org.springblade.modules.nvr.common.constants.ServiceNameConstants;
import org.springblade.modules.nvr.domain.OnvifDevice;
import org.springblade.modules.nvr.domain.WSOnvifDevice;

import org.springblade.modules.nvr.factory.RemoteOnvifFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * onvif 服务
 *
 * @FileName RemoteOnvifService
 * @Description
 * @Author fengcheng
 * @date 2026-04-10
 **/
@FeignClient(contextId = "remoteOnvifService",
        value = ServiceNameConstants.ONVIF_SERVICE,
        fallbackFactory = RemoteOnvifFallbackFactory.class,
        url= ServiceNameConstants.SERVICE_URL
)
public interface RemoteOnvifService {

    /**
     * 验证登录onvif设备
     *
     * @param onvifDevice 设备信息
     * @param source      请求来源
     * @return
     */
    @PostMapping("/api/onvif/login")
    R<OnvifDevice> login(@RequestBody WSOnvifDevice onvifDevice);

    /**
     * 开始云台控制
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @param direction 方向
     * @param speed 速度
     * @param source 请求来源
     * @return
     */
    @GetMapping("/api/onvif/startPtzControl/{deviceIp}")
    R<Void> startPtzControl(@PathVariable("deviceIp") String deviceIp,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("direction") String direction,
                             @RequestParam(value = "speed", defaultValue = "50") Integer speed);

    /**
     * 停止云台控制
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping("/api/onvif/stopPtzControl/{deviceIp}")
    R<Void> stopPtzControl(@PathVariable("deviceIp") String deviceIp,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password);

    /**
     * 获取预置点列表
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping("/api/onvif/getPresets/{deviceIp}")
    R<List<Map<String, Object>>> getPresets(@PathVariable("deviceIp") String deviceIp,
                                             @RequestParam("username") String username,
                                             @RequestParam("password") String password);

    /**
     * 设置预置点
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @param presetIndex 预置点索引
     * @param presetName 预置点名称
     * @return
     */
    @GetMapping("/api/onvif/setPreset/{deviceIp}")
    R<Void> setPreset(@PathVariable("deviceIp") String deviceIp,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "presetIndex", required = false) Integer presetIndex,
                      @RequestParam(value = "presetName", required = false) String presetName);

    /**
     * 调用预置点
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @param presetIndex 预置点索引
     * @param speed 速度
     * @return
     */
    @GetMapping("/api/onvif/gotoPreset/{deviceIp}")
    R<Void> gotoPreset(@PathVariable("deviceIp") String deviceIp,
                       @RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("presetIndex") Integer presetIndex,
                       @RequestParam(value = "speed", defaultValue = "50") Integer speed);

    /**
     * 删除预置点
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @param presetIndex 预置点索引
     * @return
     */
    @GetMapping("/api/onvif/removePreset/{deviceIp}")
    R<Void> removePreset(@PathVariable("deviceIp") String deviceIp,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("presetIndex") Integer presetIndex);

    /**
     * 灯光控制
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @param on true为开灯，false为关灯
     * @return
     */
    @GetMapping("/api/onvif/controlLight/{deviceIp}")
    R<Void> controlLight(@PathVariable("deviceIp") String deviceIp,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("on") boolean on);

    /**
     * 雨刷控制
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @param on true为开雨刷，false为关雨刷
     * @return
     */
    @GetMapping("/api/onvif/controlWiper/{deviceIp}")
    R<Void> controlWiper(@PathVariable("deviceIp") String deviceIp,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("on") boolean on);

    /**
     * 查询录像文件
     *
     * @param deviceIp  设备IP
     * @param username  用户名
     * @param password  密码
     * @param startTime 开始时间，格式：yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间，格式：yyyy-MM-dd HH:mm:ss
     * @return 录像文件列表
     */
    @GetMapping("/api/onvif/queryRecord")
    R<Object> queryRecord(@RequestParam("deviceIp") String deviceIp,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("startTime") String startTime,
                           @RequestParam("endTime") String endTime);

    /**
     * 获取回放地址
     *
     * @param deviceIp 设备IP
     * @param username 用户名
     * @param password 密码
     * @param recordingToken 录制令牌
     * @param trackToken 轨道令牌
     * @return 回放地址
     */
    @GetMapping("/api/onvif/getReplayUri")
    R<String> getReplayUri(@RequestParam("deviceIp") String deviceIp,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("recordingToken") String recordingToken,
                            @RequestParam("trackToken") String trackToken);
}
