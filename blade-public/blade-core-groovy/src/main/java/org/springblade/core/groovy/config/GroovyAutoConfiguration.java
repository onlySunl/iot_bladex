package org.springblade.core.groovy.config;

import org.springblade.core.groovy.engine.GroovyScriptEngine;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Groovy 自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(GroovyProperties.class)
@ConditionalOnProperty(prefix = "blade.groovy", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GroovyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GroovyScriptEngine groovyScriptEngine(GroovyProperties properties) {
        return new GroovyScriptEngine(properties);
    }

}
