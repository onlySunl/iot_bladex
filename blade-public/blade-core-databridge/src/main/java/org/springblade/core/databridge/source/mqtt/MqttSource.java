package org.springblade.core.databridge.source.mqtt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.constant.BridgeNamingConstant;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SourceMessage;
import org.springblade.core.databridge.spi.Source;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT 入站 Source（subscribe + 异步回调）。
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "broker":      "tcp://host:1883",
 *   "clientId":    "iot-bridge-sub-12345",
 *   "topicFilter": "in/+/data",                  // 支持 MQTT 通配符 + / #
 *   "qos":         1,
 *   "username":    "..."
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段</h3>
 * <pre>{@code { "password": "...", "caCert": "..." }}</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "keepAlive":          60,
 *   "connectTimeout":     30,
 *   "cleanSession":       true,
 *   "automaticReconnect": true
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
public class MqttSource implements Source {

    private final Map<String, MqttClient> running = new ConcurrentHashMap<>();

    @Override
    public ConnectorType supports() {
        return ConnectorType.MQTT;
    }

    @Override
    public synchronized void start(ConnectorConfig config, Consumer<SourceMessage> handler) {
        String id = config.getIdentifier();
        if (running.containsKey(id)) {
            log.info("[MqttSource] already running, skip start identifier={}", id);
            return;
        }
        try {
            MqttSourceConnConfig conn = parseConnection(config);
            MqttSourceCredConfig cred = parseCredential(config);
            MqttSourceExtraConfig extra = parseExtra(config);

            if (StrUtil.isBlank(conn.broker) || StrUtil.isBlank(conn.topicFilter)) {
                throw new IllegalArgumentException("[MqttSource] missing broker or topicFilter");
            }

            String clientId = StrUtil.isBlank(conn.clientId)
                ? BridgeNamingConstant.MQTT_SOURCE_CLIENT_PREFIX + id
                : conn.clientId;
            MqttClient client = new MqttClient(conn.broker, clientId, new MemoryPersistence());

            MqttConnectOptions opts = new MqttConnectOptions();
            if (StrUtil.isNotBlank(conn.username)) {
                opts.setUserName(conn.username);
            }
            if (StrUtil.isNotBlank(cred.password)) {
                opts.setPassword(cred.password.toCharArray());
            }
            opts.setKeepAliveInterval(extra.keepAlive == null ? 60 : extra.keepAlive);
            opts.setConnectionTimeout(extra.connectTimeout == null ? 30 : extra.connectTimeout);
            opts.setCleanSession(extra.cleanSession == null || extra.cleanSession);
            opts.setAutomaticReconnect(extra.automaticReconnect == null || extra.automaticReconnect);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    log.warn("[MqttSource] connection lost identifier={}: {}",
                        id, cause == null ? "n/a" : cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    try {
                        handler.accept(toSourceMessage(topic, message));
                    } catch (Exception e) {
                        log.warn("[MqttSource] handler threw, continuing identifier={} topic={} cause={}",
                            id, topic, e.getMessage());
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Source 端只接收，不需要处理 delivery
                }
            });

            client.connect(opts);
            client.subscribe(conn.topicFilter, conn.qos == null ? 1 : conn.qos);
            running.put(id, client);
            log.info("[MqttSource] started identifier={} broker={} topicFilter={}",
                id, conn.broker, conn.topicFilter);
        } catch (Exception e) {
            log.error("[MqttSource] start failed identifier={}: {}", id, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void stop(String identifier) {
        MqttClient client = running.remove(identifier);
        if (client == null) {
            return;
        }
        try {
            if (client.isConnected()) {
                client.disconnect();
            }
            client.close();
            log.info("[MqttSource] stopped identifier={}", identifier);
        } catch (Exception e) {
            log.warn("[MqttSource] stop error identifier={}: {}", identifier, e.getMessage());
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            MqttSourceConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.broker)) {
                return false;
            }
            MqttSourceCredConfig cred = parseCredential(config);

            String clientId = BridgeNamingConstant.TEST_CONNECTION_PREFIX + System.currentTimeMillis();
            try (MqttClient probe = new MqttClient(conn.broker, clientId, new MemoryPersistence())) {
                MqttConnectOptions opts = new MqttConnectOptions();
                if (StrUtil.isNotBlank(conn.username)) {
                    opts.setUserName(conn.username);
                }
                if (StrUtil.isNotBlank(cred.password)) {
                    opts.setPassword(cred.password.toCharArray());
                }
                opts.setConnectionTimeout(3);
                probe.connect(opts);
                boolean ok = probe.isConnected();
                probe.disconnect();
                return ok;
            }
        } catch (Exception e) {
            log.warn("[MqttSource] testConnection failed identifier={} cause={}",
                config.getIdentifier(), e.getMessage());
            return false;
        }
    }

    // ============================== 内部 ==============================

    private SourceMessage toSourceMessage(String topic, MqttMessage message) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("topic", topic);
        attrs.put("qos", message.getQos());
        attrs.put("duplicate", message.isDuplicate());
        attrs.put("retained", message.isRetained());

        // QoS > 0 才有 native messageId；否则走 SourceMessage 的雪花默认
        SourceMessage.SourceMessageBuilder b = SourceMessage.builder()
            .body(message.getPayload())
            .routingKey(topic)
            .ts(System.currentTimeMillis())
            .attributes(attrs);
        if (message.getQos() > 0 && message.getId() > 0) {
            b.sourceMessageId("mqtt-" + message.getId());
        }
        return b.build();
    }

    private MqttSourceConnConfig parseConnection(ConnectorConfig c) {
        return StrUtil.isBlank(c.getConnectionJson())
            ? new MqttSourceConnConfig()
            : JsonUtil.parse(c.getConnectionJson(), MqttSourceConnConfig.class);
    }

    private MqttSourceCredConfig parseCredential(ConnectorConfig c) {
        return StrUtil.isBlank(c.getCredentialJson())
            ? new MqttSourceCredConfig()
            : JsonUtil.parse(c.getCredentialJson(), MqttSourceCredConfig.class);
    }

    private MqttSourceExtraConfig parseExtra(ConnectorConfig c) {
        return StrUtil.isBlank(c.getExtraConfigJson())
            ? new MqttSourceExtraConfig()
            : JsonUtil.parse(c.getExtraConfigJson(), MqttSourceExtraConfig.class);
    }

    public static class MqttSourceConnConfig {
        public String broker;
        public String clientId;
        public String topicFilter;
        public Integer qos;
        public String username;
    }

    public static class MqttSourceCredConfig {
        public String password;
        public String caCert;
    }

    public static class MqttSourceExtraConfig {
        public Integer keepAlive;
        public Integer connectTimeout;
        public Boolean cleanSession;
        public Boolean automaticReconnect;
    }
}
