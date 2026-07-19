package org.springblade.modules.iot.api.device;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.device.dto.*;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.thing.ThingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 设备对外API控制器
 * 提供Feign远程调用入口，所有方法与 {@link org.springblade.modules.iot.api.device.service.RemoteIotDeviceService} 一一对应
 *
 * @author EnjoyIot
 */
@RestController
@RequestMapping("/deviceApi")
@Tag(name = "设备对外API", description = "设备缓存、注册、鉴权、子设备、物模型调用接口")
public class DeviceApiController extends BladeController {

    @Resource
    private DeviceApi deviceApi;

    @GetMapping("/getDeviceByPkDnByCache")
    @Operation(summary = "根据产品key+设备DN从缓存获取设备信息")
    public DeviceInfo getDeviceByPkDnByCache(
            @Parameter(description = "产品key") @RequestParam String pk,
            @Parameter(description = "设备DN") @RequestParam String dn
    ) {
        return deviceApi.getDeviceByPkDnByCache(pk, dn);
    }

    @GetMapping("/getDeviceInfoFromCache")
    @Operation(summary = "根据设备ID从缓存获取设备信息")
    public DeviceInfo getDeviceInfoFromCache(@Parameter(description = "设备ID") @RequestParam Long deviceId) {
        return deviceApi.getDeviceInfoFromCache(deviceId);
    }

    @PostMapping("/registerDevice")
    @Operation(summary = "设备注册")
    public DeviceInfo registerDevice(@RequestBody RegisterDevice registerDevice) {
        return deviceApi.registerDevice(registerDevice);
    }

    @PostMapping("/auth")
    @Operation(summary = "设备鉴权")
    public CommonResult<DeviceInfo> auth(@RequestBody DeviceAuth deviceAuth) {
        return deviceApi.auth(deviceAuth);
    }

    @GetMapping("/getPropertiesFromCache")
    @Operation(summary = "根据设备ID获取缓存物模型属性")
    public Map<String, DevicePropertyCache> getPropertiesFromCache(@Parameter(description = "设备ID") @RequestParam Long deviceId) {
        return deviceApi.getPropertiesFromCache(deviceId);
    }

    @PostMapping("/updateDeviceLastTimeCache")
    @Operation(summary = "更新设备最后通讯时间缓存")
    public void updateDeviceLastTimeCache(
            @Parameter(description = "设备ID") @RequestParam Long deviceId,
            @Parameter(description = "时间戳") @RequestParam long lastTime
    ) {
        deviceApi.updateDeviceLastTimeCache(deviceId, lastTime);
    }

    @PostMapping("/updateDeviceState")
    @Operation(summary = "更新设备在线状态")
    public Boolean updateDeviceState(
            @Parameter(description = "设备ID") @RequestParam Long deviceId,
            @Parameter(description = "是否在线") @RequestParam boolean online
    ) {
        return deviceApi.updateDeviceState(deviceId, online);
    }

    @PostMapping("/savePropertiesCache")
    @Operation(summary = "保存设备物模型属性缓存")
    public void savePropertiesCache(
            @Parameter(description = "设备ID") @RequestParam Long deviceId,
            @RequestBody Map<String, DevicePropertyCache> properties
    ) {
        deviceApi.savePropertiesCache(deviceId, properties);
    }

    @PostMapping("/clearPropertiesCache")
    @Operation(summary = "根据产品key清空该产品下所有设备属性缓存")
    public void clearPropertiesCache(@Parameter(description = "产品key") @RequestParam String productKey) {
        deviceApi.clearPropertiesCache(productKey);
    }

    @GetMapping("/getDeviceConfigById")
    @Operation(summary = "根据设备ID获取设备配置")
    public DeviceConfig getDeviceConfig(@Parameter(description = "设备ID") @RequestParam Long deviceId) {
        return deviceApi.getDeviceConfig(deviceId);
    }

    @GetMapping("/getDeviceConfigByPkDn")
    @Operation(summary = "根据产品key+DN获取设备配置")
    public DeviceConfig getDeviceConfig(
            @Parameter(description = "产品key") @RequestParam String productKey,
            @Parameter(description = "设备DN") @RequestParam String dn
    ) {
        return deviceApi.getDeviceConfig(productKey, dn);
    }

    @PostMapping("/invokeThingService")
    @Operation(summary = "调用设备物模型服务")
    public void invoke(@RequestBody ThingService<?> service) {
        deviceApi.invoke(service);
    }

    @GetMapping("/getSubDevicesByProductKeAndDeviceName")
    @Operation(summary = "根据产品key、父设备DN查询子设备列表")
    public List<DeviceInfo> getSubDevicesByProductKeAndDeviceName(
            @Parameter(description = "产品key") @RequestParam String pk,
            @Parameter(description = "父设备DN") @RequestParam String dn
    ) {
        return deviceApi.getSubDevicesByProductKeAndDeviceName(pk, dn);
    }

    @PostMapping("/deregisterSubDevice")
    @Operation(summary = "注销子设备")
    public Boolean deregisterSubDevice(
            @Parameter(description = "父产品key") @RequestParam String pk,
            @Parameter(description = "父设备DN") @RequestParam String dn,
            @Parameter(description = "子设备型号") @RequestParam String model,
            @Parameter(description = "子产品key") @RequestParam String subPkDeregister,
            @Parameter(description = "子设备DN") @RequestParam String subDnDeregister
    ) {
        return deviceApi.deregisterSubDevice(pk, dn, model, subPkDeregister, subDnDeregister);
    }
}