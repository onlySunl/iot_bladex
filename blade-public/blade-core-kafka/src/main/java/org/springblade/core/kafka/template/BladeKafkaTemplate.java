package org.springblade.core.kafka.template;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

/**
 * BladeX Kafka 模板
 *
 * @author Chill
 */
@Component
public class BladeKafkaTemplate {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BladeKafkaTemplate(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 发送消息
     *
     * @param topic Topic
     * @param data  数据
     */
    public void send(String topic, Object data) {
        kafkaTemplate.send(topic, data);
    }

    /**
     * 发送消息
     *
     * @param topic Topic
     * @param key   Key
     * @param data  数据
     */
    public void send(String topic, String key, Object data) {
        kafkaTemplate.send(topic, key, data);
    }

    /**
     * 发送消息并处理回调
     *
     * @param topic    Topic
     * @param data     数据
     * @param callback 回调
     */
    public void send(String topic, Object data, KafkaCallback callback) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, data);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                callback.onSuccess(result);
            } else {
                callback.onFailure(ex);
            }
        });
    }

    /**
     * 发送消息并处理回调
     *
     * @param topic    Topic
     * @param key      Key
     * @param data     数据
     * @param callback 回调
     */
    public void send(String topic, String key, Object data, KafkaCallback callback) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, data);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                callback.onSuccess(result);
            } else {
                callback.onFailure(ex);
            }
        });
    }

    /**
     * Kafka 回调接口
     */
    public interface KafkaCallback {
        /**
         * 发送成功
         *
         * @param result 发送结果
         */
        void onSuccess(SendResult<String, Object> result);

        /**
         * 发送失败
         *
         * @param ex 异常
         */
        void onFailure(Throwable ex);
    }

}
