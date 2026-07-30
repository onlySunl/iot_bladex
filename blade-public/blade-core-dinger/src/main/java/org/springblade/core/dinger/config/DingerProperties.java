package org.springblade.core.dinger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 钉钉配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.dinger")
public class DingerProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * 应用Key
     */
    private String appKey;

    /**
     * 应用Secret
     */
    private String appSecret;

    /**
     * 企业ID
     */
    private String corpId;

    /**
     * AgentId
     */
    private String agentId;

    /**
     * 回调签名密钥
     */
    private String aesKey;

    /**
     * 机器人Webhook地址
     */
    private String webhookUrl;

    /**
     * 机器人签名密钥
     */
    private String robotSecret;

}
