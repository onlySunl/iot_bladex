package org.springblade.modules.iot.api.thingmodel.factory;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.api.thingmodel.service.RemoteIotThingModelService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 物模型远程接口熔断降级工厂
 */
@Component
public class RemoteIotThingModelFallbackFactory implements FallbackFactory<RemoteIotThingModelService> {

    @Override
    public RemoteIotThingModelService create(Throwable cause) {
        return new RemoteIotThingModelService() {
            @Override
            public ThingModel getThingModelByProductKeyFromCache(String pk) {
                return null;
            }
        };
    }
}