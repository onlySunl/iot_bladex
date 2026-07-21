package org.springblade.modules.iot.api.thingmodel.service;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.api.thingmodel.factory.RemoteIotThingModelFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 物模型远程Feign调用接口，对齐 {@link org.springblade.modules.iot.api.thingmodel.ThingModelApi}
 */
@FeignClient(contextId = "remoteIotThingModelService",
        value = IotServiceNameConstants.IOT_THING_MODE,
        fallbackFactory = RemoteIotThingModelFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotThingModelService {

    /** 根据产品Key从缓存获取物模型 */
    @GetMapping("/api/inner/thingModelApi/getThingModelByProductKeyFromCache")
    ThingModel getThingModelByProductKeyFromCache(@RequestParam("pk") String pk);
}