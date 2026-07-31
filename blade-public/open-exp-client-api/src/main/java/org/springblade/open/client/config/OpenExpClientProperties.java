package org.springblade.open.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 开放平台客户端配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.open.exp-client")
public class OpenExpClientProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * API 基础地址
     */
    private String baseUrl = "https://api.example.com";

    /**
     * 应用Key
     */
    private String appKey;

    /**
     * 应用Secret
     */
    private String appSecret;

    /**
     * 连接超时时间（毫秒）
     */
    private Integer connectTimeout = 5000;

    /**
     * 读取超时时间（毫秒）
     */
    private Integer readTimeout = 10000;

    /**
     * 签名算法
     */
    private String signAlgorithm = "HmacSHA256";

}
