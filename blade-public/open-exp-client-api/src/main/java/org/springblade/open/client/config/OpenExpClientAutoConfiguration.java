package org.springblade.open.client.config;

import org.springblade.open.client.api.OpenExpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 开放平台客户端自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(OpenExpClientProperties.class)
@ConditionalOnProperty(prefix = "blade.open.exp-client", name = "enabled", havingValue = "true", matchIfMissing = true)
public class OpenExpClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenExpClient openExpClient(OpenExpClientProperties properties) {
        return new OpenExpClient(properties);
    }

}
