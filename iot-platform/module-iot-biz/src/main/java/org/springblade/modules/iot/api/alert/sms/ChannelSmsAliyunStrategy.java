package org.springblade.modules.iot.api.alert.sms;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.ChannelSmsStrategy;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 阿里云短信策略实现
 * 需要引入阿里云短信 SDK: com.aliyun:dysmsapi20170525
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "config.alert.sms-provider", havingValue = "aliyun", matchIfMissing = true)
public class ChannelSmsAliyunStrategy implements ChannelSmsStrategy {
    private static final String ALI_END_POINT = "dysmsapi.aliyuncs.com";

    @Override
    public void sendSms(Map<String, Object> templateParam, String templateId, SmsConfig smsConfig) {
        // TODO: 需要引入阿里云 SDK 后实现
        // com.aliyun:dysmsapi20170525
        log.warn("[SMS-Aliyun] 阿里云短信发送未实现，需要引入 dysmsapi SDK。templateId={}", templateId);
        throw ServiceExceptionUtil.exception(ErrorCodeConstants.SMS_SEND_FAILED);
    }

    @Override
    public String createSmsTemplate(String templateContent, Long templateId, SmsConfig smsConfig) {
        // TODO: 需要引入阿里云 SDK 后实现
        log.warn("[SMS-Aliyun] 阿里云短信模板创建未实现。templateId={}", templateId);
        throw ServiceExceptionUtil.exception(ErrorCodeConstants.SMS_TEMPLATE_CREATE_FAILED);
    }

    @Override
    public String updateSmsTemplate(String templateContent, String templateCode, SmsConfig smsConfig) {
        // TODO: 需要引入阿里云 SDK 后实现
        log.warn("[SMS-Aliyun] 阿里云短信模板更新未实现。templateCode={}", templateCode);
        throw ServiceExceptionUtil.exception(ErrorCodeConstants.SMS_TEMPLATE_UPDATE_FAILED);
    }

    @Override
    public Integer querySmsTemplateStatus(SmsConfig smsConfig, String templateCode) {
        // TODO: 需要引入阿里云 SDK 后实现
        log.warn("[SMS-Aliyun] 阿里云短信模板状态查询未实现。templateCode={}", templateCode);
        return null;
    }

    @Override
    public void deleteSmsTemplate(String templateCode, SmsConfig smsConfig) {
        // TODO: 需要引入阿里云 SDK 后实现
        log.warn("[SMS-Aliyun] 阿里云短信模板删除未实现。templateCode={}", templateCode);
    }

    /**
     * 构建模板规则（从原代码迁移，待 SDK 引入后启用）
     */
    private String buildTemplateRule(String templateContent) {
        Map<String, String> rule = new HashMap<>();
        // 解析模板中的占位符
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
        Matcher matcher = pattern.matcher(templateContent);
        while (matcher.find()) {
            String placeholder = matcher.group(1);
            rule.put(placeholder, inferTemplateParamType(placeholder));
        }
        return JsonUtils.toJsonString(rule);
    }

    /**
     * 推断模板参数类型
     */
    private String inferTemplateParamType(String placeholder) {
        String lowerPlaceholder = placeholder.toLowerCase();

        // 时间类型
        if (lowerPlaceholder.contains("time") || lowerPlaceholder.contains("date")
                || lowerPlaceholder.contains("day") || lowerPlaceholder.contains("year")
                || lowerPlaceholder.contains("month") || lowerPlaceholder.contains("week")
                || lowerPlaceholder.contains("hour") || lowerPlaceholder.contains("minute")
                || lowerPlaceholder.contains("second")) {
            return "date";
        }

        // 金额/数量类型
        if (lowerPlaceholder.contains("money") || lowerPlaceholder.contains("amount")
                || lowerPlaceholder.contains("price") || lowerPlaceholder.contains("fee")
                || lowerPlaceholder.contains("count") || lowerPlaceholder.contains("num")
                || lowerPlaceholder.contains("quantity")) {
            return "amount";
        }

        // 电话号码类型
        if (lowerPlaceholder.contains("phone") || lowerPlaceholder.contains("tel")
                || lowerPlaceholder.contains("mobile") || lowerPlaceholder.contains("telephone")) {
            return "phone";
        }

        // 默认类型
        return "others";
    }
}
