package org.springblade.core.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.lang.Nullable;

/**
 * Kafka 消息发送结果通用日志回调。
 * 由 {@code KafkaProducerAutoConfiguration} 自动 attach 到 {@code thingLinksKafkaTemplate}。
 *
 * @author mqttsnet
 */
@Slf4j
public class KafkaSendResultHandler implements ProducerListener<String, String> {

    @Override
    public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
        // 成功回调降为 debug:高 TPS 主链路下逐条 INFO 会形成日志洪峰,反成瓶颈
        if (log.isDebugEnabled()) {
            log.debug("[kafka.send.ok] topic={} partition={} offset={}", producerRecord.topic(), recordMetadata.partition(), recordMetadata.offset());
        }
    }

    @Override
    public void onError(ProducerRecord<String, String> producerRecord,
                        @Nullable RecordMetadata recordMetadata, Exception exception) {
        log.error("[kafka.send.fail] topic={} key={} err={}", producerRecord.topic(), producerRecord.key(), exception.getMessage(), exception);
    }
}
