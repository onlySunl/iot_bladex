package org.springblade.modules.iot.api.alert.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * TTS语音呼叫请求 DTO
 */
@Data
public class CallByTtsRequest implements Serializable {
    /** 模板参数 */
    private Map<String, Object> templateParam;
    /** 模板ID */
    private String templateId;
    /** 语音配置 */
    private VmsConfig vmsConfig;
}
