package org.springblade.core.rocketmq.template;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * BladeX RocketMQ 模板
 *
 * @author Chill
 */
@Slf4j
@Component
public class BladeRocketMqTemplate {

    private final RocketMQTemplate rocketMQTemplate;

    public BladeRocketMqTemplate(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /**
     * 同步发送消息
     *
     * @param topic Topic
     * @param data  数据
     */
    public void send(String topic, Object data) {
        rocketMQTemplate.convertAndSend(topic, data);
        log.debug("同步发送消息到 Topic: {}", topic);
    }

    /**
     * 同步发送消息（带Tag）
     *
     * @param topic Topic
     * @param tag   Tag
     * @param data  数据
     */
    public void send(String topic, String tag, Object data) {
        String destination = topic + ":" + tag;
        rocketMQTemplate.convertAndSend(destination, data);
        log.debug("同步发送消息到 Topic: {}, Tag: {}", topic, tag);
    }

    /**
     * 异步发送消息
     *
     * @param topic    Topic
     * @param data     数据
     * @param callback 回调
     */
    public void asyncSend(String topic, Object data, SendCallback callback) {
        Message<?> message = MessageBuilder.withPayload(data).build();
        rocketMQTemplate.asyncSend(topic, message, new org.apache.rocketmq.spring.core.RocketMQTemplate.DefaultSendCallback() {
            @Override
            public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
                log.debug("异步发送消息成功: {}", sendResult.getMsgId());
                if (callback != null) {
                    callback.onSuccess(sendResult.getMsgId());
                }
            }

            @Override
            public void onException(Throwable e) {
                log.error("异步发送消息失败", e);
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    /**
     * 发送顺序消息
     *
     * @param topic Topic
     * @param data  数据
     * @param hashKey 哈希Key
     */
    public void sendOrderly(String topic, Object data, String hashKey) {
        rocketMQTemplate.syncSendOrderly(topic, data, hashKey);
        log.debug("发送顺序消息到 Topic: {}, HashKey: {}", topic, hashKey);
    }

    /**
     * 发送延迟消息
     *
     * @param topic      Topic
     * @param data       数据
     * @param delayLevel 延迟级别
     */
    public void sendDelay(String topic, Object data, int delayLevel) {
        Message<?> message = MessageBuilder.withPayload(data).build();
        rocketMQTemplate.syncSend(topic, message, 3000, delayLevel);
        log.debug("发送延迟消息到 Topic: {}, DelayLevel: {}", topic, delayLevel);
    }

    /**
     * 发送回调接口
     */
    public interface SendCallback {
        /**
         * 发送成功
         *
         * @param msgId 消息ID
         */
        void onSuccess(String msgId);

        /**
         * 发送失败
         *
         * @param e 异常
         */
        void onFailure(Throwable e);
    }

}
