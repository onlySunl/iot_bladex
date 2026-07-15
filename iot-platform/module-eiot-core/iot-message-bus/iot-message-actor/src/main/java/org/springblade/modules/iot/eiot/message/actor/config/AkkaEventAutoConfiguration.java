package org.springblade.modules.iot.message.actor.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.springblade.modules.iot.common.thing.ComponentMessage;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.message.actor.AkkaConsumer;
import org.springblade.modules.iot.message.actor.AkkaProducer;
import org.springblade.modules.iot.message.actor.actor.MessageBusActor;
import org.springblade.modules.iot.message.actor.spring.SpringExtensionProvider;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springblade.modules.iot.message.core.MqProducer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaEventAutoConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        Config dispatcherConfig = ConfigFactory.parseResources("my-dispatcher.conf");
        Config combinedConfig = dispatcherConfig.withFallback(ConfigFactory.load());
        ActorSystem system = ActorSystem.create("IoTMessageBusSystem", combinedConfig);
        SpringExtensionProvider.getInstance().get(system).initialize(applicationContext);
        return system;
    }

    @Bean
    public ActorRef messageBus(ActorSystem actorSystem) {
        return actorSystem.actorOf(Props.create(MessageBusActor.class), "messageBus");
    }

    @Bean
    public MqProducer<ThingModelMessage> producer(ActorRef messageBus) {
        return new AkkaProducer<>(messageBus);
    }

    @Bean
    public MqConsumer<ThingModelMessage> consumer(ActorRef messageBus, ActorSystem actorSystem) {
        return new AkkaConsumer<>(messageBus, actorSystem);
    }

    @Bean
    public MqProducer<ComponentMessage> componentMessageProducer(ActorRef messageBus) {
        return new AkkaProducer<>(messageBus);
    }

    @Bean
    public MqConsumer<ComponentMessage> componentMessageConsumer(ActorRef messageBus, ActorSystem actorSystem) {
        return new AkkaConsumer<>(messageBus, actorSystem);
    }
}
