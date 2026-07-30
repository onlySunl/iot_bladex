package org.springblade.fastdfs.config;

import org.springblade.fastdfs.client.FastDfsClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * FastDFS 自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(FastDfsProperties.class)
@ConditionalOnProperty(prefix = "blade.fastdfs", name = "enabled", havingValue = "true", matchIfMissing = true)
public class FastDfsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FastDfsClient fastDfsClient(FastDfsProperties properties) {
        return new FastDfsClient(properties);
    }

}
