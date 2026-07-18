

package org.springblade.modules.iot.message.spring;

import lombok.Getter;

@Getter
public class SpringEventMessage<T> {
    private final String topic;
    private final T message;

    public SpringEventMessage(String topic, T message) {
        this.topic = topic;
        this.message = message;
    }
}