package org.springblade.modules.iot.api.component.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.modules.iot.api.component.dto.ComponentInfo;
import org.springblade.modules.iot.api.component.service.RemoteIotComponentService;
import org.springblade.modules.iot.api.device.dto.*;
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
public class RemoteIotComponentFallbackFactory implements FallbackFactory<RemoteIotComponentService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteIotComponentFallbackFactory.class);

    @Override
    public RemoteIotComponentService create(Throwable throwable) {

        return new RemoteIotComponentService() {

            @Override
            public ComponentInfo getInfo(String type) {
                return null;
            }
        };
    }
}
