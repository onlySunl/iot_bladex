package org.springblade.modules.iot.message.actor;

import akka.actor.ActorRef;

// 订阅消息
public class SubscribeMessage {
    private final String topic;
    private final ActorRef subscriber;

    public SubscribeMessage(String topic, ActorRef subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
    }

    public String getTopic() {
        return topic;
    }

    public ActorRef getSubscriber() {
        return subscriber;
    }
}
