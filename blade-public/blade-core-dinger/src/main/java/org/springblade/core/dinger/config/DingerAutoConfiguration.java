package org.springblade.core.dinger.config;

import org.springblade.core.dinger.client.DingerClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 钉钉自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(DingerProperties.class)
@ConditionalOnProperty(prefix = "blade.dinger", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DingerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DingerClient dingerClient(DingerProperties properties) {
        return new DingerClient(properties);
    }

}
