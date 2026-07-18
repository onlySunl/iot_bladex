

package org.springblade.modules.iot.message.spring.config;

import org.springblade.modules.iot.common.thing.ComponentMessage;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springblade.modules.iot.message.spring.SpringEventConsumer;
import org.springblade.modules.iot.message.spring.SpringEventProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ConditionalOnProperty(name = "eiot.message.producer-type", havingValue = "spring-event", matchIfMissing = true)
public class SpringEventAutoConfiguration {

//    @ConditionalOnMissingBean
    @Bean
    public MqProducer<ThingModelMessage> producer(ApplicationEventPublisher eventPublisher) {
        return new SpringEventProducer<>(eventPublisher);
    }

//    @ConditionalOnMissingBean
    @Bean
    public MqConsumer<ThingModelMessage> consumer() {
        return new SpringEventConsumer<>();
    }


//    @ConditionalOnMissingBean
    @Bean
    public MqProducer<ComponentMessage> componentMessageProducer(ApplicationEventPublisher eventPublisher) {
        return new SpringEventProducer<>(eventPublisher);
    }

//    @ConditionalOnMissingBean(value = )
    @Bean
    public MqConsumer<ComponentMessage> componentMessageConsumer() {
        return new SpringEventConsumer<>();
    }
}