package org.springblade.modules.iot.api.virtualdevice.service;

import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springblade.modules.iot.api.virtualdevice.factory.RemoteIotVirtualDeviceFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 虚拟设备远程Feign调用接口，对齐 {@link org.springblade.modules.iot.api.virtualdevice.VirtualDeviceApi}
 */
@FeignClient(contextId = "remoteIotVirtualDeviceService",
        value = IotServiceNameConstants.IOT_VIRTUAL_DEVICE,
        fallbackFactory = RemoteIotVirtualDeviceFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotVirtualDeviceService {

    /**
     * 根据触发器表达式、状态查询虚拟设备列表
     */
    @GetMapping("/api/virtualDeviceApi/findByTriggerAndState")
    List<VirtualDevice> findByTriggerAndState(@RequestParam("trigger") String trigger,
                                              @RequestParam("state") String state);
}