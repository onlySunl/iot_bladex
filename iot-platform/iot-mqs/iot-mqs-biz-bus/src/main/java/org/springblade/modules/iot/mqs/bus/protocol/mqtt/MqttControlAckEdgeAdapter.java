package org.springblade.modules.iot.mqs.bus.protocol.mqtt;

import org.springblade.modules.iot.bus.route.TopicRoute;
import org.springblade.modules.iot.common.mq.KafkaConsumerTopicConstant;
import org.springblade.modules.iot.enumeration.bus.DispatchGroupEnum;
import org.springblade.modules.iot.enumeration.bus.MatchModeEnum;
import org.springblade.modules.iot.mqs.bus.protocol.AbstractKafkaEdgeAdapter;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springframework.stereotype.Component;

/**
 * MQTT 控制信令 adapter,SUBSCRIBE / UNSUBSCRIBE 等控制 ack,跳过 CORE 直接 POST。
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
@Component
@TopicRoute(
    value = {
        KafkaConsumerTopicConstant.Mqs.MqsMqtt.IOT_MQTT_SUBSCRIPTION_ACKED_TOPIC,
        KafkaConsumerTopicConstant.Mqs.MqsMqtt.IOT_MQTT_UNSUBSCRIPTION_ACKED_TOPIC
    },
    mode = MatchModeEnum.EXACT,
    group = DispatchGroupEnum.CONTROL_ACK
)
public class MqttControlAckEdgeAdapter extends AbstractKafkaEdgeAdapter {
    @Override
    public ProtocolTypeEnum supports() {
        return ProtocolTypeEnum.MQTT;
    }
}
