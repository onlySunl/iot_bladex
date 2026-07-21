package org.springblade.modules.iot.api.component.service;

import org.springblade.modules.iot.api.component.dto.ComponentInfo;
import org.springblade.modules.iot.api.component.factory.RemoteIotComponentFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IOT组件远程Feign调用接口，对齐 {@link org.springblade.modules.iot.api.component.ComponentApi}
 *
 * @FileName RemoteIotComponentService
 * @Description 跨服务获取组件信息
 * @Author fengcheng
 * @date 2026-03-28
 **/
@FeignClient(contextId = "remoteIotComponentService",
        value = IotServiceNameConstants.IOT_COMPONENT,
        fallbackFactory = RemoteIotComponentFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotComponentService {

    /**
     * 根据组件类型获取组件信息
     */
    @GetMapping("/api/componentApi/getInfo")
    ComponentInfo getInfo(@RequestParam("type") String type);
}