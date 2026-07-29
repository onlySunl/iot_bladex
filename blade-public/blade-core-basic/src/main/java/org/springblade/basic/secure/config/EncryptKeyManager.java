package org.springblade.basic.secure.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 动态密钥管理器
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/8/3
 */
@Slf4j
@Component
public class EncryptKeyManager {
    public enum Algorithm {AES, SM4}

    public record KeyConfig(String key, String iv) {
    }

    private final Map<Algorithm, KeyConfig> configStore = new EnumMap<>(Algorithm.class);

    public EncryptKeyManager(EncryptKeyProperties properties) {
        // 初始化AES配置
        if (properties.getAes() != null) {
            configStore.put(Algorithm.AES,
                    new KeyConfig(properties.getAes().getKey(), properties.getAes().getIv()));
        }

        // 初始化SM4配置
        if (properties.getSm4() != null) {
            configStore.put(Algorithm.SM4,
                    new KeyConfig(properties.getSm4().getKey(), properties.getSm4().getIv()));
        }

        log.info("密钥配置加载完成: {}", configStore.keySet());
    }

    public KeyConfig getConfig(Algorithm algorithm) {
        return Optional.ofNullable(configStore.get(algorithm))
                .orElseThrow(() -> new IllegalArgumentException("未配置的算法: " + algorithm));
    }
}
