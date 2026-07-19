package org.springblade.modules.iot.api.alert;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 默认短信策略实现（空实现）
 * 当没有自定义 SMS 服务商实现时，使用此默认实现，仅打印日志
 * 用户可通过实现 ChannelSmsStrategy 接口并提供自己的 Bean 来覆盖此默认实现
 */
@Slf4j
@Component
@ConditionalOnMissingBean(ChannelSmsStrategy.class)
public class DefaultChannelSmsStrategy implements ChannelSmsStrategy {

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
}
