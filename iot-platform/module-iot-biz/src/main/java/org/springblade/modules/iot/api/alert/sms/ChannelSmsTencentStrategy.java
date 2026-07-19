package org.springblade.modules.iot.api.alert.sms;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.ChannelSmsStrategy;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@ConditionalOnProperty(name = "config.alert.sms-provider", havingValue = "tencent")
public class ChannelSmsTencentStrategy implements ChannelSmsStrategy {

    @Override
    public void sendSms(Map<String, Object> templateParam, String templateId, SmsConfig smsConfig) {
        // todo
    }

    /**
     * 创建腾讯云短信模板
     */
    @Override
    public String createSmsTemplate(String templateContent, Long templateId, SmsConfig smsConfig) {
        // todo
        return null;
    }

    /**
     * 更新腾讯云短信模板
     */
    @Override
    public String updateSmsTemplate(String templateContent, String templateCode, SmsConfig smsConfig) {
        // todo
        return null;
    }

    /**
     * 查询腾讯云短信模板状态
     */
    @Override
    public Integer querySmsTemplateStatus(SmsConfig smsConfig, String templateCode) {
        // todo
        return null;
    }

    /**
     * 删除腾讯云短信模板
     */
    @Override
    public void deleteSmsTemplate(String templateCode, SmsConfig smsConfig) {
        // todo
    }
}
