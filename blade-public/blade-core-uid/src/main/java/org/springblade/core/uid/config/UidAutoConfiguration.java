package org.springblade.core.uid.config;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.uid.UidGenerator;
import org.springblade.core.uid.generator.SnowflakeUidGenerator;
import org.springblade.core.uid.generator.UuidUidGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * UID 自动配置
 *
 * @author Chill
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(UidProperties.class)
@ConditionalOnProperty(prefix = "blade.uid", name = "enabled", havingValue = "true", matchIfMissing = true)
public class UidAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UidGenerator uidGenerator(UidProperties properties) {
        // 如果使用随机工作机器ID
        if (properties.isRandomWorkerId() && "snowflake".equals(properties.getType())) {
            int maxWorkerId = properties.getMaxWorkerId();
            long randomWorkerId = ThreadLocalRandom.current().nextLong(0, maxWorkerId + 1);
            properties.setWorkerId(randomWorkerId);
            log.info("使用随机工作机器ID: {}", randomWorkerId);
        }

        String type = properties.getType();
        log.info("初始化 UID 生成器, 类型: {}", type);

        switch (type.toLowerCase()) {
            case "uuid":
                return new UuidUidGenerator();
            case "snowflake":
            default:
                return new SnowflakeUidGenerator(properties);
        }
    }
}
