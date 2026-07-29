package org.springblade.core.easyexcel.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EasyExcel 自动配置
 *
 * @author Chill
 */
@Configuration
@EnableConfigurationProperties(EasyExcelProperties.class)
@ConditionalOnProperty(prefix = "blade.easyexcel", name = "enabled", havingValue = "true", matchIfMissing = true)
public class EasyExcelAutoConfiguration {

    /**
     * 默认配置
     */
    @Bean
    @ConditionalOnMissingBean
    public EasyExcelProperties easyExcelProperties() {
        return new EasyExcelProperties();
    }
}
