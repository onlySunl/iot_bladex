

package org.springblade.modules.iot.message.spring;

import org.springblade.modules.iot.message.core.MqProducer;
import org.springframework.context.ApplicationEventPublisher;

public class SpringEventProducer<T> implements MqProducer<T> {

    private final ApplicationEventPublisher eventPublisher;

    public SpringEventProducer(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publish(String topic, T msg) {
        // 使用Spring Event发布消息
        // 这里我们将topic和消息包装成一个事件对象
        SpringEventMessage<T> event = new SpringEventMessage<>(topic, msg);
        eventPublisher.publishEvent(event);
    }
}