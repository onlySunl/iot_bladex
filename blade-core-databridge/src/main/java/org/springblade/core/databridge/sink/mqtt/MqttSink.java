package org.springblade.core.databridge.sink.mqtt;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.constant.BridgeNamingConstant;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 外部 MQTT Broker 出站 Sink（Eclipse Paho v3）。
 * <p>典型场景：把平台数据推到第三方 MQTT 系统（其它 IoT 平台 / 企业 EMQ broker 等）。</p>
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "broker":        "tcp://host:1883",          // 或 ssl://host:8883
 *   "clientId":      "thinglinks-bridge-12345",  // 可选
 *   "topicTemplate": "out/${routingKey}",        // 必填，支持 ${routingKey} ${header.XXX} ${ts}
 *   "qos":           1,                           // 0 / 1 / 2
 *   "retained":      false,
 *   "username":      "..."
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段</h3>
 * <pre>{@code
 * {
 *   "password": "...",
 *   "caCert":   "..."     // PEM 文本（TLS 场景）
 * }
 * }</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "keepAlive":     60,        // 秒
 *   "connectTimeout": 30,       // 秒
 *   "cleanSession":  true,
 *   "automaticReconnect": true
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class MqttSink implements Sink {

    private static final Pattern PLACEHOLDER = Pattern.compile("\\$\\{([^}]+)}");

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.MQTT;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            MqttConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.broker) || StrUtil.isBlank(conn.topicTemplate)) {
                throw new IllegalArgumentException("[MqttSink] missing broker or topicTemplate");
            }

            MqttClient client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);

            String topic = renderTopic(conn.topicTemplate, payload);
            MqttMessage message = new MqttMessage(payload.getBody() == null ? new byte[0] : payload.getBody());
            message.setQos(conn.qos == null ? 1 : conn.qos);
            message.setRetained(Boolean.TRUE.equals(conn.retained));

            MqttDeliveryToken token = client.getTopic(topic).publish(message);
            token.waitForCompletion(5000L);    // 最多等 5 秒 ack

            Map<String, Object> attrs = new HashMap<>();
            attrs.put("topic", topic);
            attrs.put("qos", message.getQos());
            attrs.put("messageId", token.getMessageId());

            return SendResult.success(String.valueOf(token.getMessageId()),
                System.currentTimeMillis() - start, attrs);
        } catch (Exception e) {
            // MqttException 内含 reason code,但 cause 还可能套 socket 层异常
            log.warn("[MqttSink] send failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            MqttClient client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            return client.isConnected();
        } catch (Exception e) {
            log.warn("[MqttSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    // ============================== 内部：build MqttClient ==============================

    private MqttClient buildClient(ConnectorConfig config) {
        try {
            MqttConnConfig conn = parseConnection(config);
            MqttCredConfig cred = parseCredential(config);
            MqttExtraConfig extra = parseExtra(config);

            String clientId = StrUtil.isBlank(conn.clientId)
                ? BridgeNamingConstant.SINK_CLIENT_PREFIX + config.getIdentifier()
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

            client.connect(opts);
            log.info("[MqttSink] connected identifier={} broker={} clientId={}",
                config.getIdentifier(), conn.broker, clientId);
            return client;
        } catch (Exception e) {
            // 不加前缀包装,e.getMessage() 透传 raw cause(MqttException 含 reason code + 原始描述)
            throw new RuntimeException(e);
        }
    }

    private String renderTopic(String template, ConnectorPayload payload) {
        Matcher m = PLACEHOLDER.matcher(template);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String placeholder = m.group(1);
            String replacement;
            if ("routingKey".equals(placeholder)) {
                replacement = StrUtil.nullToDefault(payload.getRoutingKey(), "");
            } else if ("ts".equals(placeholder)) {
                replacement = String.valueOf(payload.getTs());
            } else if (placeholder.startsWith("header.")) {
                replacement = StrUtil.nullToDefault(
                    payload.header(placeholder.substring("header.".length())), "");
            } else {
                replacement = "";
            }
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private MqttConnConfig parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new MqttConnConfig()
            : JsonUtil.parse(config.getConnectionJson(), MqttConnConfig.class);
    }

    private MqttCredConfig parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new MqttCredConfig()
            : JsonUtil.parse(config.getCredentialJson(), MqttCredConfig.class);
    }

    private MqttExtraConfig parseExtra(ConnectorConfig config) {
        return StrUtil.isBlank(config.getExtraConfigJson())
            ? new MqttExtraConfig()
            : JsonUtil.parse(config.getExtraConfigJson(), MqttExtraConfig.class);
    }

    public static class MqttConnConfig {
        public String broker;
        public String clientId;
        public String topicTemplate;
        public Integer qos;
        public Boolean retained;
        public String username;
    }

    public static class MqttCredConfig {
        public String password;
        public String caCert;
    }

    public static class MqttExtraConfig {
        public Integer keepAlive;
        public Integer connectTimeout;
        public Boolean cleanSession;
        public Boolean automaticReconnect;
    }
}
