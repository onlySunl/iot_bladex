package org.springblade.modules.iot.protocol.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.protocol.common.message.DeviceMessage;
import org.springblade.modules.iot.protocol.common.message.EventMessage;
import org.springblade.modules.iot.protocol.common.message.PropertyMessage;
import org.springblade.modules.iot.protocol.common.protocol.ProtocolCodec;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * JSON 协议编解码器
 * <p>将设备消息序列化为 JSON 字节数组，或从 JSON 字节数组反序列化为设备消息</p>
 */
@Slf4j
@Component
public class JsonProtocolCodec implements ProtocolCodec {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public byte[] encode(DeviceMessage message) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(message);
        } catch (Exception e) {
            log.error("JSON 编码失败: deviceId={}", message.getDeviceId(), e);
            return new byte[0];
        }
    }

    @Override
    public DeviceMessage decode(byte[] data) {
        try {
            Map<String, Object> map = OBJECT_MAPPER.readValue(data, Map.class);
            String type = (String) map.get("type");
            if ("property".equals(type)) {
                return OBJECT_MAPPER.readValue(data, PropertyMessage.class);
            } else if ("event".equals(type)) {
                return OBJECT_MAPPER.readValue(data, EventMessage.class);
            }
            return OBJECT_MAPPER.readValue(data, DeviceMessage.class);
        } catch (Exception e) {
            log.error("JSON 解码失败", e);
            return null;
        }
    }

    @Override
    public String getCodecType() {
        return "json";
    }
}
