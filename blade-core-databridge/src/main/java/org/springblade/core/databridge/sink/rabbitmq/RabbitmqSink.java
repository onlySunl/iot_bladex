package org.springblade.core.databridge.sink.rabbitmq;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.rabbitmq.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.constant.BridgeNamingConstant;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RabbitMQ 出站 Sink（com.rabbitmq:amqp-client 直连）。
 * <p>
 * 用原生 AMQP 客户端，<b>不依赖 spring-rabbit autoconfig</b>，避免与业务侧已有的 Spring AMQP 配置冲突。
 * 支持 direct / fanout / topic / headers 四种 exchange 类型；按 routingKey 路由。
 * </p>
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "host":         "host",                     // 必填
 *   "port":         5672,                       // 默认 5672；TLS 默认 5671
 *   "virtualHost":  "/",                        // 默认 "/"
 *   "exchangeName": "iot-out-exchange",         // 必填
 *   "exchangeType": "direct",                   // direct/fanout/topic/headers，默认 direct
 *   "routingKey":   "device.${routingKey}",     // 支持 ${routingKey} ${ts} ${header.X} 占位符
 *   "useTls":       false                       // 默认 false
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段</h3>
 * <pre>{@code
 * {
 *   "username": "guest",
 *   "password": "guest"
 * }
 * }</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "deliveryMode":         2,           // 1=non-persistent / 2=persistent（落盘，broker 重启不丢）
 *   "publisherConfirms":    true,        // 启用 publisher confirms（高可靠）
 *   "channelMax":           100,         // 单连接最大 channel 数
 *   "connectionTimeoutMs":  5000,        // 连接建立超时
 *   "requestedHeartbeat":   30           // 心跳间隔（秒）
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class RabbitmqSink implements Sink {

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.RABBITMQ;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            RmqConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.host) || StrUtil.isBlank(conn.exchangeName)) {
                throw new IllegalArgumentException("[RabbitmqSink] missing host or exchangeName");
            }
            RmqExtraConfig extra = parseExtra(config);

            RabbitmqHolder holder = pool.getOrCreate(config.getIdentifier(), config, this::buildHolder);

            // 渲染 routingKey（支持占位符）
            String routingKey = renderRoutingKey(conn.routingKey, payload);

            // 构造 AMQP basic properties
            AMQP.BasicProperties.Builder propBuilder = new AMQP.BasicProperties.Builder()
                .deliveryMode(extra.deliveryMode == null ? 2 : extra.deliveryMode)
                .timestamp(payload.getTs() > 0 ? new Date(payload.getTs()) : new Date())
                .contentType("application/octet-stream");
            if (CollUtil.isNotEmpty(payload.getHeaders())) {
                Map<String, Object> hdrs = new HashMap<>(payload.getHeaders());
                propBuilder.headers(hdrs);
            }

            byte[] body = payload.getBody() == null ? new byte[0] : payload.getBody();

            // 同步发送：Channel 在多线程下不安全，使用 synchronized 保护
            synchronized (holder.channel) {
                holder.channel.basicPublish(conn.exchangeName, routingKey, propBuilder.build(), body);
                if (Boolean.TRUE.equals(extra.publisherConfirms)) {
                    long confirmTimeoutMs = extra.confirmTimeoutMs == null ? 3000L : extra.confirmTimeoutMs;
                    boolean acked = holder.channel.waitForConfirms(confirmTimeoutMs);
                    if (!acked) {
                        // waitForConfirms 返回 false = broker nack 或 confirmTimeoutMs 内未 ack。
                        // RabbitMQ 协议无原生 exception 表达,这是协议级 raw 描述。
                        return SendResult.fail(new RuntimeException(
                                "publisher confirm not acked within " + confirmTimeoutMs +
                                    "ms (exchange=" + conn.exchangeName + " routingKey=" + routingKey + ")"),
                            System.currentTimeMillis() - start);
                    }
                }
            }

            Map<String, Object> attrs = new HashMap<>();
            attrs.put("exchange", conn.exchangeName);
            attrs.put("routingKey", routingKey);
            attrs.put("deliveryMode", extra.deliveryMode == null ? 2 : extra.deliveryMode);

            // RabbitMQ basic.publish 没有原生 messageId 返回，用 AMQP messageId 字段（如有）
            return SendResult.success(null, System.currentTimeMillis() - start, attrs);
        } catch (Exception e) {
            // RabbitMQ 的 IOException / TimeoutException 经常套了 ShutdownSignalException
            log.warn("[RabbitmqSink] send failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            RabbitmqHolder holder = pool.getOrCreate(config.getIdentifier(), config, this::buildHolder);
            return holder.connection.isOpen() && holder.channel.isOpen();
        } catch (Exception e) {
            log.warn("[RabbitmqSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    // ============================== 内部：build Connection + Channel ==============================

    private RabbitmqHolder buildHolder(ConnectorConfig config) {
        try {
            RmqConnConfig conn = parseConnection(config);
            RmqCredConfig cred = parseCredential(config);
            RmqExtraConfig extra = parseExtra(config);

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(conn.host);
            factory.setPort(conn.port == null
                ? (Boolean.TRUE.equals(conn.useTls) ? 5671 : 5672)
                : conn.port);
            factory.setVirtualHost(StrUtil.nullToDefault(conn.virtualHost, "/"));
            if (StrUtil.isNotBlank(cred.username)) {
                factory.setUsername(cred.username);
            }
            if (StrUtil.isNotBlank(cred.password)) {
                factory.setPassword(cred.password);
            }
            if (extra.connectionTimeoutMs != null) {
                factory.setConnectionTimeout(extra.connectionTimeoutMs);
            }
            if (extra.requestedHeartbeat != null) {
                factory.setRequestedHeartbeat(extra.requestedHeartbeat);
            }
            if (extra.channelMax != null) {
                factory.setRequestedChannelMax(extra.channelMax);
            }
            if (Boolean.TRUE.equals(conn.useTls)) {
                factory.useSslProtocol();
            }
            factory.setAutomaticRecoveryEnabled(true);   // 自动重连

            Connection connection = factory.newConnection(BridgeNamingConstant.RABBITMQ_CONNECTION_PREFIX + config.getIdentifier());
            Channel channel = connection.createChannel();

            // 自动声明 exchange（幂等：已存在则不报错）
            String exType = StrUtil.nullToDefault(conn.exchangeType, "direct").toLowerCase();
            BuiltinExchangeType type = BuiltinExchangeType.valueOf(exType.toUpperCase());
            channel.exchangeDeclare(conn.exchangeName, type, true, false, null);

            if (Boolean.TRUE.equals(extra.publisherConfirms)) {
                channel.confirmSelect();
            }

            log.info("[RabbitmqSink] connected identifier={} host={} exchange={} type={}",
                config.getIdentifier(), conn.host, conn.exchangeName, exType);
            return new RabbitmqHolder(connection, channel);
        } catch (Exception e) {
            // 不加前缀包装,e.getMessage() 透传 raw cause
            throw new RuntimeException(e);
        }
    }

    private String renderRoutingKey(String template, ConnectorPayload payload) {
        if (StrUtil.isBlank(template)) {
            return StrUtil.nullToDefault(payload.getRoutingKey(), "");
        }
        // 仅支持 ${routingKey} ${ts} ${header.X} 三类占位符
        String result = template;
        if (result.contains("${routingKey}")) {
            result = result.replace("${routingKey}", StrUtil.nullToDefault(payload.getRoutingKey(), ""));
        }
        if (result.contains("${ts}")) {
            result = result.replace("${ts}", String.valueOf(payload.getTs()));
        }
        // ${header.X} 简易替换
        Matcher m = Pattern.compile("\\$\\{header\\.([^}]+)}").matcher(result);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String hv = StrUtil.nullToDefault(payload.header(m.group(1)), "");
            m.appendReplacement(sb, Matcher.quoteReplacement(hv));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private RmqConnConfig parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new RmqConnConfig()
            : JsonUtil.parse(config.getConnectionJson(), RmqConnConfig.class);
    }

    private RmqCredConfig parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new RmqCredConfig()
            : JsonUtil.parse(config.getCredentialJson(), RmqCredConfig.class);
    }

    private RmqExtraConfig parseExtra(ConnectorConfig config) {
        return StrUtil.isBlank(config.getExtraConfigJson())
            ? new RmqExtraConfig()
            : JsonUtil.parse(config.getExtraConfigJson(), RmqExtraConfig.class);
    }

    // ============================== 内部 POJO ==============================

    public static class RmqConnConfig {
        public String host;
        public Integer port;
        public String virtualHost;
        public String exchangeName;
        public String exchangeType;
        public String routingKey;
        public Boolean useTls;
    }

    public static class RmqCredConfig {
        public String username;
        public String password;
    }

    public static class RmqExtraConfig {
        public Integer deliveryMode;
        public Boolean publisherConfirms;
        public Integer channelMax;
        public Integer connectionTimeoutMs;
        public Integer requestedHeartbeat;
        public Long confirmTimeoutMs;
    }

    /**
     * 持有 Connection + Channel；ConnectionPoolManager 淘汰时自动 close。
     */
    record RabbitmqHolder(Connection connection, Channel channel) implements AutoCloseable {

        @Override
        public void close() {
            try {
                if (channel != null && channel.isOpen()) {
                    channel.close();
                }
            } catch (Exception ignored) {
            }
            try {
                if (connection != null && connection.isOpen()) {
                    connection.close();
                }
            } catch (Exception ignored) {
            }
        }
    }
}
