package org.springblade.modules.iot.config;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.ChannelSmsStrategy;
import org.springblade.modules.iot.api.alert.ChannelVmsStrategy;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springblade.modules.iot.api.alert.dto.VmsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * IoT 告警通道默认配置
 * 提供 SMS 和 VMS 的默认空实现，避免启动时因找不到 Bean 而失败
 * 用户可通过定义自己的 @Bean 来覆盖这些默认实现
 */
@Slf4j
@Configuration
public class IotAlertConfiguration {

    /**
     * 默认短信策略（空实现）
     */
    @Bean
    public ChannelSmsStrategy channelSmsStrategy() {
        return new ChannelSmsStrategy() {
            @Override
            public void sendSms(Map<String, Object> templateParam, String templateId, SmsConfig smsConfig) {
                log.warn("[SMS] 未配置短信服务商实现，跳过发送。templateId={}, params={}", templateId, templateParam);
            }

            @Override
            public String createSmsTemplate(String templateContent, Long templateId, SmsConfig smsConfig) {
                log.warn("[SMS] 未配置短信服务商实现，跳过创建模板。templateId={}", templateId);
                return null;
            }

            @Override
            public String updateSmsTemplate(String templateContent, String templateCode, SmsConfig smsConfig) {
                log.warn("[SMS] 未配置短信服务商实现，跳过更新模板。templateCode={}", templateCode);
                return null;
            }

            @Override
            public Integer querySmsTemplateStatus(SmsConfig smsConfig, String templateCode) {
                log.warn("[SMS] 未配置短信服务商实现，跳过查询模板状态。templateCode={}", templateCode);
                return null;
            }

            @Override
            public void deleteSmsTemplate(String templateCode, SmsConfig smsConfig) {
                log.warn("[SMS] 未配置短信服务商实现，跳过删除模板。templateCode={}", templateCode);
            }
        };
    }

    /**
     * 默认语音通知策略（空实现）
     */
    @Bean
    public ChannelVmsStrategy channelVmsStrategy() {
        return new ChannelVmsStrategy() {
            @Override
            public void callByTts(Map<String, Object> templateParam, String templateId, VmsConfig vmsConfig) {
                log.warn("[VMS] 未配置语音通知服务商实现，跳过呼叫。templateId={}, params={}", templateId, templateParam);
            }
        };
    }
}
