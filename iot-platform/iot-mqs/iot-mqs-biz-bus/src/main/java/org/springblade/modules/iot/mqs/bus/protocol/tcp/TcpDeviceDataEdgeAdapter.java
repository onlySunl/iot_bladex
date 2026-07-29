package org.springblade.modules.iot.mqs.bus.protocol.tcp;

import org.springblade.modules.iot.bus.route.TopicRoute;
import org.springblade.modules.iot.common.mq.KafkaConsumerTopicConstant;
import org.springblade.modules.iot.enumeration.bus.DispatchGroupEnum;
import org.springblade.modules.iot.enumeration.bus.MatchModeEnum;
import org.springblade.modules.iot.mqs.bus.protocol.AbstractKafkaEdgeAdapter;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springframework.stereotype.Component;

/**
 * TCP 设备主数据 adapter ── 走 PRE→CORE→POST 全套管道。
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
@Component
@TopicRoute(
    value = KafkaConsumerTopicConstant.Mqs.MqsTcp.IOT_TCP_DISTRIBUTION_COMPLETED_TOPIC,
    mode = MatchModeEnum.EXACT,
    group = DispatchGroupEnum.DEVICE_DATA
)
public class TcpDeviceDataEdgeAdapter extends AbstractKafkaEdgeAdapter {
    @Override
    public ProtocolTypeEnum supports() {
        return ProtocolTypeEnum.TCP;
    }
}
