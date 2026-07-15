

package org.springblade.modules.iot.message.rocketmq.config;


import org.springblade.modules.iot.common.thing.ComponentMessage;
import org.springblade.modules.iot.message.rocketmq.RocketMqConsumer;
import org.springblade.modules.iot.message.rocketmq.RocketMqProducer;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rocketmq自动配置类
 * @author yitian
 */
@Configuration
@ConditionalOnProperty(prefix = "eiot.message", name = "producer-type", havingValue = "rocketmq")
public class RocketMqAutoConfiguration {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Value("${rocketmq.producer.group}")
    private String group;

    @Bean
    public MqProducer<ThingModelMessage> getThingModelMessageProducer() {
        return new RocketMqProducer<>(nameServer, group);
    }

    @Bean
    public MqConsumer<ThingModelMessage> getThingModelMessageConsumer() {
        return new RocketMqConsumer<>(nameServer, ThingModelMessage.class);
    }

    @Bean
    public MqProducer<ComponentMessage> componentMessageProducer() {
        return new RocketMqProducer<>(nameServer, group);
    }

    @Bean
    public MqConsumer<ComponentMessage> componentMessageConsumer() {
        return new RocketMqConsumer<>(nameServer, ComponentMessage.class);
    }


}
