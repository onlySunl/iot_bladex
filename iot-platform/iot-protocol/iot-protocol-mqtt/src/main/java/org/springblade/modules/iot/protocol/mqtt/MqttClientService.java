package org.springblade.modules.iot.protocol.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springblade.modules.iot.protocol.common.message.DeviceMessage;
import org.springblade.modules.iot.protocol.common.protocol.ProtocolCodec;
import org.springblade.modules.iot.protocol.common.protocol.ProtocolType;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * MQTT 客户端服务 - 封装 Eclipse Paho MQTT 客户端
 *
 * @author blade-iot
 */
@Slf4j
public class MqttClientService implements MqttCallbackExtended {

    private MqttAsyncClient client;
    private final MqttConnectionConfig config;
    private final Map<String, Consumer<byte[]>> topicHandlers = new ConcurrentHashMap<>();
    private Consumer<DeviceMessage> messageHandler;
    private ProtocolCodec codec;

    public MqttClientService(MqttConnectionConfig config) {
        this.config = config;
    }

    /**
     * 设置消息处理器
     */
    public void setMessageHandler(Consumer<DeviceMessage> handler) {
        this.messageHandler = handler;
    }

    /**
     * 设置协议编解码器
     */
    public void setCodec(ProtocolCodec codec) {
        this.codec = codec;
    }

    /**
     * 连接 MQTT Broker
     */
    public void connect() throws MqttException {
        client = new MqttAsyncClient(config.getBrokerUrl(), config.getClientId(), new MemoryPersistence());
        client.setCallback(this);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(config.isCleanSession());
        options.setConnectionTimeout(config.getConnectionTimeout());
        options.setKeepAliveInterval(config.getKeepAliveInterval());
        options.setAutomaticReconnect(config.isAutomaticReconnect());

        if (config.getUsername() != null) {
            options.setUserName(config.getUsername());
        }
        if (config.getPassword() != null) {
            options.setPassword(config.getPassword().toCharArray());
        }

        log.info("[MQTT] 连接 Broker: {}, clientId: {}", config.getBrokerUrl(), config.getClientId());
        client.connect(options);
    }

    /**
     * 订阅主题
     */
    public void subscribe(String topic, Consumer<byte[]> handler) throws MqttException {
        topicHandlers.put(topic, handler);
        if (client != null && client.isConnected()) {
            client.subscribe(topic, config.getDefaultQos());
            log.info("[MQTT] 订阅主题: {}", topic);
        }
    }

    /**
     * 发布消息
     */
    public void publish(String topic, byte[] payload, int qos, boolean retained) throws MqttException {
        if (client == null || !client.isConnected()) {
            log.warn("[MQTT] 客户端未连接，无法发布消息到: {}", topic);
            return;
        }
        MqttMessage message = new MqttMessage(payload);
        message.setQos(qos);
        message.setRetained(retained);
        client.publish(topic, message);
    }

    /**
     * 发布设备消息
     */
    public void publishDeviceMessage(String topic, DeviceMessage message) throws MqttException {
        if (codec != null) {
            byte[] payload = codec.encode(message);
            publish(topic, payload, config.getDefaultQos(), false);
        }
    }

    /**
     * 断开连接
     */
    public void disconnect() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
            log.info("[MQTT] 已断开连接: {}", config.getBrokerUrl());
        }
    }

    /**
     * 是否已连接
     */
    public boolean isConnected() {
        return client != null && client.isConnected();
    }

    // ========== MqttCallbackExtended ==========

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        log.info("[MQTT] {}完成, serverURI: {}", reconnect ? "重连" : "连接", serverURI);
        // 重新订阅所有主题
        for (String topic : topicHandlers.keySet()) {
            try {
                client.subscribe(topic, config.getDefaultQos());
            } catch (MqttException e) {
                log.error("[MQTT] 重新订阅失败: {}", topic, e);
            }
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.warn("[MQTT] 连接丢失: {}", cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        byte[] payload = mqttMessage.getPayload();
        // 调用主题处理器
        Consumer<byte[]> handler = topicHandlers.get(topic);
        if (handler != null) {
            handler.accept(payload);
        }
        // 调用通用消息处理器
        if (messageHandler != null && codec != null) {
            List<DeviceMessage> messages = codec.decode(payload, topic);
            for (DeviceMessage msg : messages) {
                messageHandler.accept(msg);
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // 消息发布完成
    }
}
