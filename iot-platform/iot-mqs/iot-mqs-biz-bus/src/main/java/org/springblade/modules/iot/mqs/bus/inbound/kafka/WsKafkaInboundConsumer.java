package org.springblade.modules.iot.mqs.bus.inbound.kafka;

import java.util.List;

import org.springblade.modules.iot.common.mq.ConsumerGroupConstant;
import org.springblade.modules.iot.common.mq.KafkaConsumerTopicConstant;
import org.springblade.modules.iot.mqs.bus.dispatcher.BusPipelineDispatcher;
import org.springblade.modules.iot.mqs.bus.stats.BusStatsService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * WebSocket 协议 Kafka 入站消费者.
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
@Component
public class WsKafkaInboundConsumer extends AbstractProtocolKafkaInboundConsumer {

    private static final String CONSUMER_GROUP = ConsumerGroupConstant.IOT_CONSUMER_GROUP_PREFIX + "BUS_WEBSOCKET";
    private static final String PROTOCOL_NAME = "WEBSOCKET";

    public WsKafkaInboundConsumer(BusPipelineDispatcher dispatcher, BusStatsService statsService) {
        super(dispatcher, statsService);
    }

    @Override
    protected String protocolName() {
        return PROTOCOL_NAME;
    }

    @KafkaListener(
        topics = {
            KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_CLIENT_CONNECTED_TOPIC,
            KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_CLIENT_DISCONNECTED_TOPIC,
            KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_SERVER_DISCONNECTED_TOPIC,
            KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_DEVICE_KICKED_TOPIC,
            KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_DISTRIBUTION_ERROR_TOPIC,
            KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_DISTRIBUTION_COMPLETED_TOPIC,
            KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_PING_REQ_TOPIC
        },
        groupId = CONSUMER_GROUP,
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void onBatch(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        handleBatch(records, ack);
    }
}
