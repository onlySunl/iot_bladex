package org.springblade.modules.iot.message.actor;

import akka.actor.ActorRef;
import org.springblade.modules.iot.message.core.MqProducer;

public class AkkaProducer<T> implements MqProducer<T> {
    private final ActorRef messageBus;

    public AkkaProducer(ActorRef messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void publish(String topic, T msg) {
        messageBus.tell(new PublishMessage<>(topic, msg), ActorRef.noSender());
    }
}
