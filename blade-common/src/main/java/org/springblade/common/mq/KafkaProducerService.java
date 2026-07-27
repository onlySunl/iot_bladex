package org.springblade.common.mq;

import org.springframework.stereotype.Service;

/**
 * Kafka 生产者服务包装类
 */
@Service
public class KafkaProducerService {
    
    /**
     * 发送消息
     */
    public void sendMessage(String topic, Object message) {
        // TODO: 实现 Kafka 发送逻辑
    }
    
    /**
     * 发送消息到指定分区
     */
    public void sendMessage(String topic, Integer partition, Object message) {
        // TODO: 实现 Kafka 分区发送逻辑
    }
}
