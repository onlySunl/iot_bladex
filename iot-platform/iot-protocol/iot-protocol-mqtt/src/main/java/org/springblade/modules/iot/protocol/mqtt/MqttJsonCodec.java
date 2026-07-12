package org.springblade.modules.iot.protocol.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.protocol.common.message.DeviceMessage;
import org.springblade.modules.iot.protocol.common.message.PropertyMessage;
import org.springblade.modules.iot.protocol.common.protocol.ProtocolCodec;
import org.springblade.modules.iot.protocol.common.protocol.ProtocolType;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * MQTT 协议编解码器 - 标准 JSON 格式
 *
 * Topic 约定:
 *   上行: /sys/{productId}/{deviceId}/thing/property/post
 *   下行: /sys/{productId}/{deviceId}/thing/service/{serviceKey}
 *
 * @author blade-iot
 */
@Slf4j
public class MqttJsonCodec implements ProtocolCodec {

    @Override
    public ProtocolType getProtocolType() {
        return ProtocolType.MQTT;
    }

    @Override
    public List<DeviceMessage> decode(byte[] payload, String topic) {
        List<DeviceMessage> messages = new ArrayList<>();
        try {
            String json = new String(payload, StandardCharsets.UTF_8);
            JSONObject obj = JSON.parseObject(json);

            PropertyMessage msg = new PropertyMessage();
            msg.setMessageId(obj.getString("messageId"));
            msg.setTimestamp(obj.getLong("timestamp"));
            msg.setProperties(obj.getJSONObject("params") != null
                    ? obj.getJSONObject("params").getInnerMap()
                    : Collections.emptyMap());

            // 从 topic 中解析 productId 和 deviceId
            if (topic != null) {
                String[] parts = topic.split("/");
                if (parts.length >= 4) {
                    msg.setProductId(parts[1]);
                    msg.setDeviceId(parts[2]);
                }
            }

            messages.add(msg);
        } catch (Exception e) {
            log.error("[MQTT Codec] 解码失败, topic: {}", topic, e);
        }
        return messages;
    }

    @Override
    public byte[] encode(DeviceMessage message) {
        JSONObject obj = new JSONObject();
        obj.put("messageId", message.getMessageId());
        obj.put("timestamp", message.getTimestamp());

        if (message instanceof PropertyMessage) {
            obj.put("method", "thing.service.property");
            obj.put("params", ((PropertyMessage) message).getProperties());
        }

        return obj.toJSONString().getBytes(StandardCharsets.UTF_8);
    }
}
