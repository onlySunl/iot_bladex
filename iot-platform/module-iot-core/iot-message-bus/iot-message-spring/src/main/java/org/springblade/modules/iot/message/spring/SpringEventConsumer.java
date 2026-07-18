

package org.springblade.modules.iot.message.spring;

import org.springblade.modules.iot.message.core.ConsumerHandler;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpringEventConsumer<T> implements MqConsumer<T>, ReceiveMessage<T> {

    private final Map<String, List<ConsumerHandler<T>>> handlers = new ConcurrentHashMap<>();

    @Override
    public void consume(String topic, ConsumerHandler<T> handler) {
        List<ConsumerHandler<T>> handlerList = handlers.getOrDefault(topic, new ArrayList<>());
        // 如果handlerList不存在handler, 则添加handler
        if (!handlerList.contains(handler)) {
            handlerList.add(handler);
            handlers.put(topic, handlerList);
        }
    }

    @EventListener
    @Async // 异步处理消息
    public void onMessage(SpringEventMessage<T> event) {
        String topic = event.getTopic();
        List<ConsumerHandler<T>> handlerList = handlers.get(topic);
        if (handlerList != null) {
            for (ConsumerHandler<T> handler : handlerList) {
                handler.handler(event.getMessage());
            }
        }
    }

}