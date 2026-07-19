package org.springblade.modules.iot.api.device.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.modules.iot.api.device.dto.*;
import org.springblade.modules.iot.api.device.service.RemoteIotDeviceService;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.thing.ThingService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 视频监控设备服务降级处理
 *
 * @FileName RemoteQsDeviceFallbackFactory
 * @Description
 * @Author fengcheng
 * @date 2026-03-28
 **/
@Component
public class RemoteIotDeviceFallbackFactory implements FallbackFactory<RemoteIotDeviceService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteIotDeviceFallbackFactory.class);

    @Override
    public RemoteIotDeviceService create(Throwable throwable) {
        log.error("视频监控设备服务调用失败:{}", throwable.getMessage());

        return new RemoteIotDeviceService() {

            @Override
            public DeviceInfo getDeviceByPkDnByCache(String pk, String dn) {
                return null;
            }

            @Override
            public DeviceInfo getDeviceInfoFromCache(Long deviceId) {
                return null;
            }

            @Override
            public DeviceInfo registerDevice(RegisterDevice registerDevice) {
                return null;
            }

            @Override
            public CommonResult<DeviceInfo> auth(DeviceAuth deviceAuth) {
                return null;
            }

            @Override
            public Map<String, DevicePropertyCache> getPropertiesFromCache(Long deviceId) {
                return null;
            }

            @Override
            public void updateDeviceLastTimeCache(Long deviceId, long lastTime) {

            }

            @Override
            public Boolean updateDeviceState(Long deviceId, boolean online) {
                return null;
            }

            @Override
            public void savePropertiesCache(Long deviceId, Map<String, DevicePropertyCache> properties) {

            }

            @Override
            public void clearPropertiesCache(String productKey) {

            }

            @Override
            public DeviceConfig getDeviceConfig(Long deviceId) {
                return null;
            }

            @Override
            public DeviceConfig getDeviceConfig(String productKey, String dn) {
                return null;
            }

            @Override
            public void invoke(ThingService<?> service) {

            }

            @Override
            public List<DeviceInfo> getSubDevicesByProductKeAndDeviceName(String pk, String dn) {
                return null;
            }

            @Override
            public Boolean deregisterSubDevice(String pk, String dn, String model, String subPkDeregister, String subDnDeregister) {
                return null;
            }
        };
    }
}
