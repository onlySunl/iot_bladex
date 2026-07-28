package org.springblade.basic.secure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 加密密钥配置
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/8/3
 */
@Data
@Component
@ConfigurationProperties(prefix = "thinglinks.security.algorithms")
public class EncryptKeyProperties {
    /**
     * AES算法配置
     */
    private AesConfig aes;

    /**
     * SM4算法配置
     */
    private Sm4Config sm4;

    @Data
    public static class AesConfig {
        /**
         * AES密钥（16/24/32字节）
         */
        private String key;
        /**
         * 初始化向量（16字节）
         */
        private String iv;

        /**
         * 默认CBC
         */
        private String mode = "CBC";
    }

    @Data
    public static class Sm4Config {
        /**
         * SM4密钥（16字节）
         */
        private String key;
        /**
         * 初始化向量（16字节）
         */
        private String iv;

        /**
         * 默认CBC
         */
        private String mode = "CBC";
    }
}
