package org.springblade.modules.iot.mqs.bus.protocol.tcp;

import org.springblade.common.iot.mq.KafkaConsumerTopicConstant;
import org.springblade.modules.iot.bus.route.TopicRoute;
import org.springblade.modules.iot.enumeration.bus.DispatchGroupEnum;
import org.springblade.modules.iot.enumeration.bus.MatchModeEnum;
import org.springblade.modules.iot.mqs.bus.protocol.AbstractKafkaEdgeAdapter;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springframework.stereotype.Component;

/**
 * TCP 控制信令 adapter ── SUBSCRIBE/UNSUBSCRIBE,跳过 CORE。
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
@Component
@TopicRoute(
    value = {
        KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_SUBSCRIPTION_ACKED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_UNSUBSCRIPTION_ACKED_TOPIC
    },
    mode = MatchModeEnum.EXACT,
    group = DispatchGroupEnum.CONTROL_ACK
)
public class TcpControlAckEdgeAdapter extends AbstractKafkaEdgeAdapter {
    @Override
    public ProtocolTypeEnum supports() {
        return ProtocolTypeEnum.TCP;
    }
}
