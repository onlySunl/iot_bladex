package org.springblade.modules.iot.api.virtualdevice.factory;


import cn.hutool.core.collection.ListUtil;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springblade.modules.iot.api.virtualdevice.service.RemoteIotVirtualDeviceService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 虚拟设备远程接口熔断降级工厂
 */
@Component
public class RemoteIotVirtualDeviceFallbackFactory implements FallbackFactory<RemoteIotVirtualDeviceService> {

    @Override
    public RemoteIotVirtualDeviceService create(Throwable cause) {
        return new RemoteIotVirtualDeviceService() {
            @Override
            public List<VirtualDevice> findByTriggerAndState(String trigger, String state) {
                // 熔断返回空列表，避免空指针
                return ListUtil.empty();
            }
        };
    }
}