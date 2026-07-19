package org.springblade.modules.iot.api.alert.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 发送短信请求 DTO
 */
@Data
public class SendSmsRequest implements Serializable {
    /** 模板参数 */
    private Map<String, Object> templateParam;
    /** 模板ID */
    private String templateId;
    /** 短信配置 */
    private SmsConfig smsConfig;
}
