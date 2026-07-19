package org.springblade.modules.iot.api.alert.factory;

import org.springblade.modules.iot.api.alert.dto.VmsConfig;
import org.springblade.modules.iot.api.alert.service.RemoteIotChannelVmsService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 语音渠道熔断降级工厂
 */
@Component
public class RemoteIotChannelVmsFallbackFactory implements FallbackFactory<RemoteIotChannelVmsService> {

    @Override
    public RemoteIotChannelVmsService create(Throwable cause) {
        return new RemoteIotChannelVmsService() {
            @Override
            public void callByTts(Map<String, Object> templateParam, String templateId, VmsConfig vmsConfig) {
                // 熔断无操作
            }
        };
    }
}