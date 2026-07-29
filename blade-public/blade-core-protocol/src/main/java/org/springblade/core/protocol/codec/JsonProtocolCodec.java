package org.springblade.core.protocol.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springblade.core.protocol.model.DeviceMessage;
import org.springblade.core.protocol.model.PropertyMessage;
import org.springblade.core.protocol.model.EventMessage;
import org.springblade.core.protocol.model.ServiceCallMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * JSON 协议编解码器
 *
 * @author Chill
 */
@Component
public class JsonProtocolCodec implements ProtocolCodec {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] encode(DeviceMessage message) throws Exception {
        return objectMapper.writeValueAsBytes(message);
    }

    @Override
    public DeviceMessage decode(byte[] data) throws Exception {
        Map<String, Object> map = objectMapper.readValue(data, Map.class);
        String messageType = (String) map.get("messageType");

        DeviceMessage message;
        if ("PROPERTY".equals(messageType)) {
            message = objectMapper.readValue(data, PropertyMessage.class);
        } else if ("EVENT".equals(messageType)) {
            message = objectMapper.readValue(data, EventMessage.class);
        } else if ("SERVICE".equals(messageType)) {
            message = objectMapper.readValue(data, ServiceCallMessage.class);
        } else {
            message = objectMapper.readValue(data, DeviceMessage.class);
        }

        return message;
    }

    @Override
    public String getProtocolType() {
        return "json";
    }
}
