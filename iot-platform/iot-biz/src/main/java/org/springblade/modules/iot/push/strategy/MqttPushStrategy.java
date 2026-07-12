package org.springblade.modules.iot.push.strategy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springblade.modules.iot.protocol.common.message.DeviceMessage;
import org.springblade.modules.iot.push.PushStrategy;

import java.util.Map;

/**
 * MQTT 推送策略 - 将设备数据发布到指定 MQTT Broker
 *
 * @author blade-iot
 */
@Slf4j
public class MqttPushStrategy implements PushStrategy {

    private MqttAsyncClient client;
    private String topic;
    private int qos = 1;
    private boolean available = false;

    @Override
    public String getType() {
        return "MQTT";
    }

    @Override
    public void init(String config) {
        try {
            Map<String, Object> cfg = JSON.parseObject(config, Map.class);
            String brokerUrl = (String) cfg.get("brokerUrl");
            String clientId = (String) cfg.get("clientId");
            this.topic = (String) cfg.get("topic");
            this.qos = cfg.containsKey("qos") ? ((Number) cfg.get("qos")).intValue() : 1;

            client = new MqttAsyncClient(brokerUrl, clientId, new MemoryPersistence());
            client.connect().waitForCompletion(5000);
            available = true;
            log.info("[MQTT推送] 初始化完成, broker: {}, topic: {}", brokerUrl, topic);
        } catch (Exception e) {
            log.error("[MQTT推送] 初始化失败", e);
            available = false;
        }
    }

    @Override
    public boolean push(DeviceMessage message) {
        if (!available || client == null || !client.isConnected()) {
            return false;
        }
        try {
            String payload = JSON.toJSONString(message);
            MqttMessage msg = new MqttMessage(payload.getBytes());
            msg.setQos(qos);
            client.publish(topic, msg);
            return true;
        } catch (Exception e) {
            log.error("[MQTT推送] 推送失败, topic: {}", topic, e);
            return false;
        }
    }

    @Override
    public void close() {
        try {
            if (client != null && client.isConnected()) {
                client.disconnect();
                client.close();
            }
        } catch (Exception e) {
            log.error("[MQTT推送] 关闭失败", e);
        }
        available = false;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }
}
