package org.springblade.modules.iot.common.mq;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * Kafka send result handler — implements ProducerListener to be wired into KafkaTemplate.
 */
public class KafkaSendResultHandler implements ProducerListener<String, String> {

    @Override
    public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
        // no-op stub
    }

    @Override
    public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata,
                        Exception exception) {
        // no-op stub
    }
}
