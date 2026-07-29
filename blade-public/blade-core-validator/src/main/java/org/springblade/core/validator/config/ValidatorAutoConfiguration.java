package org.springblade.core.validator.config;

import org.springblade.core.validator.BladeValidator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 验证器自动配置
 *
 * @author Chill
 */
@AutoConfiguration
public class ValidatorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    @ConditionalOnMissingBean
    public BladeValidator bladeValidator(LocalValidatorFactoryBean validator) {
        return new BladeValidator(validator);
    }

}
