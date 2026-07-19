package org.springblade.modules.iot.api.alert.factory;

import org.springblade.modules.iot.api.alert.dto.SendSmsRequest;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springblade.modules.iot.api.alert.service.RemoteIotChannelSmsService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 短信渠道接口熔断降级工厂
 */
@Component
public class RemoteIotChannelSmsFallbackFactory implements FallbackFactory<RemoteIotChannelSmsService> {

    @Override
    public RemoteIotChannelSmsService create(Throwable cause) {
        return new RemoteIotChannelSmsService() {
            @Override
            public void sendSms(SendSmsRequest request) {}

            @Override
            public String createSmsTemplate(String templateContent, Long templateId, SmsConfig smsConfig) {
                return null;
            }

            @Override
            public String updateSmsTemplate(String templateContent, String templateCode, SmsConfig smsConfig) {
                return null;
            }

            @Override
            public Integer querySmsTemplateStatus(SmsConfig smsConfig, String templateCode) {
                return null;
            }

            @Override
            public void deleteSmsTemplate(String templateCode, SmsConfig smsConfig) {}
        };
    }
}