package org.springblade.core.chatgpt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ChatGPT 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.chatgpt")
public class ChatGptProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * API 地址
     */
    private String apiUrl = "https://api.openai.com/v1";

    /**
     * API Key
     */
    private String apiKey;

    /**
     * 模型名称
     */
    private String model = "gpt-3.5-turbo";

    /**
     * 最大Token数
     */
    private Integer maxTokens = 2048;

    /**
     * 温度参数
     */
    private Double temperature = 0.7;

    /**
     * 连接超时时间（毫秒）
     */
    private Integer connectTimeout = 30000;

    /**
     * 读取超时时间（毫秒）
     */
    private Integer readTimeout = 60000;

}
