package org.springblade.modules.iot.api.device.service;

import org.springblade.modules.iot.api.device.dto.*;
import org.springblade.modules.iot.api.device.factory.RemoteIotDeviceFallbackFactory;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.thing.ThingService;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * IOT设备远程Feign调用接口，对齐 {@link DeviceApi} 全部能力
 *
 * @FileName RemoteIotDeviceService
 * @Description 跨服务调用iot设备相关缓存/注册/鉴权/子设备/属性配置接口
 * @Author fengcheng
 * @date 2026-03-28
 **/
@FeignClient(contextId = "remoteIotDeviceService",
        value = IotServiceNameConstants.IOT_DEVICE,
        fallbackFactory = RemoteIotDeviceFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotDeviceService {

    /**
     * 根据产品key、设备dn从缓存获取设备信息
     */
    @GetMapping("/api/deviceApi/getDeviceByPkDnByCache")
    DeviceInfo getDeviceByPkDnByCache(@RequestParam("pk") String pk, @RequestParam("dn") String dn);

    /**
     * 根据设备ID从缓存获取设备信息
     */
    @GetMapping("/api/deviceApi/getDeviceInfoFromCache")
    DeviceInfo getDeviceInfoFromCache(@RequestParam("deviceId") Long deviceId);

    /**
     * 设备注册
     */
    @PostMapping("/api/deviceApi/registerDevice")
    DeviceInfo registerDevice(@RequestBody RegisterDevice registerDevice);

    /**
     * 设备鉴权
     */
    @PostMapping("/api/deviceApi/auth")
    CommonResult<DeviceInfo> auth(@RequestBody DeviceAuth deviceAuth);

    /**
     * 根据设备ID获取缓存属性Map
     */
    @GetMapping("/api/deviceApi/getPropertiesFromCache")
    Map<String, DevicePropertyCache> getPropertiesFromCache(@RequestParam("deviceId") Long deviceId);

    /**
     * 更新设备最后通讯时间缓存
     */
    @PostMapping("/api/deviceApi/updateDeviceLastTimeCache")
    void updateDeviceLastTimeCache(@RequestParam("deviceId") Long deviceId, @RequestParam("lastTime") long lastTime);

    /**
     * 更新设备在线状态
     */
    @PostMapping("/api/deviceApi/updateDeviceState")
    Boolean updateDeviceState(@RequestParam("deviceId") Long deviceId, @RequestParam("online") boolean online);

    /**
     * 保存设备属性缓存
     */
    @PostMapping("/api/deviceApi/savePropertiesCache")
    void savePropertiesCache(@RequestParam("deviceId") Long deviceId, @RequestBody Map<String, DevicePropertyCache> properties);

    /**
     * 根据产品key清空所有设备属性缓存
     */
    @PostMapping("/api/deviceApi/clearPropertiesCache")
    void clearPropertiesCache(@RequestParam("productKey") String productKey);

    /**
     * 根据设备ID获取设备配置
     */
    @GetMapping("/api/deviceApi/getDeviceConfigById")
    DeviceConfig getDeviceConfig(@RequestParam("deviceId") Long deviceId);

    /**
     * 根据产品key、dn获取设备配置
     */
    @GetMapping("/api/deviceApi/getDeviceConfigByPkDn")
    DeviceConfig getDeviceConfig(@RequestParam("productKey") String productKey, @RequestParam("dn") String dn);

    /**
     * 调用设备物模型服务
     */
    @PostMapping("/api/deviceApi/invokeThingService")
    void invoke(@RequestBody ThingService<?> service);

    /**
     * 根据产品key、父设备dn查询子设备列表
     */
    @GetMapping("/api/deviceApi/getSubDevicesByProductKeAndDeviceName")
    List<DeviceInfo> getSubDevicesByProductKeAndDeviceName(@RequestParam("pk") String pk, @RequestParam("dn") String dn);

    /**
     * 注销子设备
     */
    @PostMapping("/api/deviceApi/deregisterSubDevice")
    Boolean deregisterSubDevice(
            @RequestParam("pk") String pk,
            @RequestParam("dn") String dn,
            @RequestParam("model") String model,
            @RequestParam("subPkDeregister") String subPkDeregister,
            @RequestParam("subDeregister") String subDnDeregister
    );
}