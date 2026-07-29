package org.springblade.modules.iot.mqs.bus.protocol.tcp;

import org.springblade.modules.iot.bus.route.TopicRoute;
import org.springblade.modules.iot.common.mq.KafkaConsumerTopicConstant;
import org.springblade.modules.iot.enumeration.bus.DispatchGroupEnum;
import org.springblade.modules.iot.enumeration.bus.MatchModeEnum;
import org.springblade.modules.iot.mqs.bus.protocol.AbstractKafkaEdgeAdapter;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springframework.stereotype.Component;

/**
 * TCP 生命周期 adapter,CONNECT/DISCONNECT/CLOSE/KICKED/PING,走全套管道。
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
@Component
@TopicRoute(
    value = {
        KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_CLIENT_CONNECTED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_CLIENT_DISCONNECTED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_SERVER_DISCONNECTED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_DEVICE_KICKED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_PING_REQ_TOPIC
    },
    mode = MatchModeEnum.EXACT,
    group = DispatchGroupEnum.DEVICE_LIFECYCLE
)
public class TcpLifecycleEdgeAdapter extends AbstractKafkaEdgeAdapter {
    @Override
    public ProtocolTypeEnum supports() {
        return ProtocolTypeEnum.TCP;
    }
}
