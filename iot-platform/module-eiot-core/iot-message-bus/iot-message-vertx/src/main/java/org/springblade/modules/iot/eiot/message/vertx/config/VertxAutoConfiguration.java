package org.springblade.modules.iot.message.vertx.config;

import org.springblade.modules.iot.common.thing.ComponentMessage;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springblade.modules.iot.message.vertx.VertxConsumer;
import org.springblade.modules.iot.message.vertx.VertxProducer;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 暂时还没测试,所以未开启
//@ConditionalOnProperty(name = "eiot.message.producer-type", havingValue = "vertx-event")
public class VertxAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "vertx")
    public VertxOptions vertxOptions() {
        return new VertxOptions();
    }

    @Bean
    public Vertx vertx() {
        VertxOptions vertxOptions = vertxOptions();
        return Vertx.vertx(vertxOptions);
    }

    @Bean
    public MqProducer<ThingModelMessage> getThingModelMessageProducer() {
        return new VertxProducer<>(ThingModelMessage.class);
    }

    @Bean
    public MqConsumer<ThingModelMessage> getThingModelMessageConsumer() {
        return new VertxConsumer<>(ThingModelMessage.class);
    }

    @Bean
    public MqProducer<ComponentMessage> componentMessageProducer() {
        return new VertxProducer<>(ComponentMessage.class);
    }

    @Bean
    public MqConsumer<ComponentMessage> componentMessageConsumer() {
        return new VertxConsumer<>(ComponentMessage.class);
    }
}
