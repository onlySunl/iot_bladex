package org.springblade.core.rocketmq.config;

import org.springblade.core.rocketmq.template.BladeRocketMqTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * RocketMQ 自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(RocketMqProperties.class)
@ConditionalOnProperty(prefix = "blade.rocketmq", name = "enabled", havingValue = "true", matchIfMissing = true)
public class RocketMqAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BladeRocketMqTemplate bladeRocketMqTemplate(org.apache.rocketmq.spring.core.RocketMQTemplate rocketMQTemplate) {
        return new BladeRocketMqTemplate(rocketMQTemplate);
    }

}
