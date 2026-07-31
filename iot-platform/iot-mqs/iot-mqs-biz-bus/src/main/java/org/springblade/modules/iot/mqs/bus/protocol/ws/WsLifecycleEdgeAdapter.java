package org.springblade.modules.iot.mqs.bus.protocol.ws;

import org.springblade.common.iot.mq.KafkaConsumerTopicConstant;
import org.springblade.modules.iot.bus.route.TopicRoute;
import org.springblade.modules.iot.enumeration.bus.DispatchGroupEnum;
import org.springblade.modules.iot.enumeration.bus.MatchModeEnum;
import org.springblade.modules.iot.mqs.bus.protocol.AbstractKafkaEdgeAdapter;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springframework.stereotype.Component;

/**
 * WebSocket 生命周期 adapter,CONNECT/DISCONNECT/CLOSE/KICKED/PING,走全套管道。
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
@Component
@TopicRoute(
    value = {
        KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_CLIENT_CONNECTED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_CLIENT_DISCONNECTED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_SERVER_DISCONNECTED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_DEVICE_KICKED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsWebSocket.IOT_WEBSOCKET_PING_REQ_TOPIC
    },
    mode = MatchModeEnum.EXACT,
    group = DispatchGroupEnum.DEVICE_LIFECYCLE
)
public class WsLifecycleEdgeAdapter extends AbstractKafkaEdgeAdapter {
    @Override
    public ProtocolTypeEnum supports() {
        return ProtocolTypeEnum.WEBSOCKET;
    }
}
