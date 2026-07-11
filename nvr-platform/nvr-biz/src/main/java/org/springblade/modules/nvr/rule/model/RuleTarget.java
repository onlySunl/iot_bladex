package org.springblade.modules.nvr.rule.model;

import lombok.Data;

/**
 * 规则转发目标
 */
@Data
public class RuleTarget {
    /**
     * 转发类型: HTTP/MQTT/KAFKA/LOG 等
     */
    private String type;
    /**
     * 转发配置（JSON）
     */
    private String config;
}
