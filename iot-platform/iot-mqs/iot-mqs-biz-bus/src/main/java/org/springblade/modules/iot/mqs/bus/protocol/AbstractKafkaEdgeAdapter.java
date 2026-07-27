package org.springblade.modules.iot.mqs.bus.protocol;

import org.springblade.modules.iot.bus.adapter.AbstractProtocolEdgeAdapter;
import org.springblade.modules.iot.entity.protocol.DeviceProtocolEvent;
import org.springblade.modules.iot.mqs.bus.dispatcher.SourceTopicHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka 边缘适配器抽象基类。子类只需声明协议类型 + {@code @TopicRoute}。
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
@Slf4j
public abstract class AbstractKafkaEdgeAdapter extends AbstractProtocolEdgeAdapter {

    @Override
    protected DeviceProtocolEvent doCanonicalize(Object rawSource) {
        if (!(rawSource instanceof String json)) {
            throw new IllegalArgumentException("[" + supports().getValue() + ".adapter] expects String JSON, got "
                + (rawSource == null ? "null" : rawSource.getClass().getName()));
        }
        return ProtocolKafkaPayloadParser.parse(json, SourceTopicHolder.current().orElse(null), supports());
    }
}
