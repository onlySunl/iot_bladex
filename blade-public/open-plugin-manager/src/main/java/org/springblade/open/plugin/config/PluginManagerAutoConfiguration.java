package org.springblade.open.plugin.config;

import org.springblade.open.plugin.manager.PluginManager;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 插件管理器自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(PluginManagerProperties.class)
@ConditionalOnProperty(prefix = "blade.open.plugin", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PluginManagerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PluginManager pluginManager(PluginManagerProperties properties) {
        return new PluginManager(properties);
    }

}
