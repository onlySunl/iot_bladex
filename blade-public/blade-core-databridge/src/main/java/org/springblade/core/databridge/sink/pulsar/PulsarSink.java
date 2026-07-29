package org.springblade.core.databridge.sink.pulsar;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;

import java.util.Map;

/**
 * Apache Pulsar 出站 Sink（pulsar-client；多租户原生 + tiered storage）。
 *
 * <h3>类签名约束（关键）</h3>
 * 本类所有 public / package-private 方法的 <b>参数类型 + 返回类型 + throws 类型</b>
 * 以及 <b>field 类型</b> 不直接引用 org.apache.pulsar 任何类，全部用 Object 兜底。
 * pulsar 类型仅出现在 <b>方法体局部变量</b> 中（含嵌套类的方法体）。
 * <p>
 * 这样保证 driver 不在 classpath 时（{@code <optional>true</optional>}），反射式扫描器
 * （如 thinglinks-databases 的 TenantLineAnnotationRegister 通过
 * Class.forName + getDeclaredMethods/getDeclaredFields 触发的 signature 解析）不会
 * 因为找不到 pulsar 类抛 NoClassDefFoundError。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "serviceUrl":      "pulsar://host:6650",
 *   "topic":           "persistent://tenant/namespace/topic",
 *   "producerName":    "iot-bridge",                // 可选
 *   "sendMode":        "ASYNC",                     // SYNC / ASYNC
 *   "compressionType": "LZ4",                       // NONE / LZ4 / ZLIB / ZSTD / SNAPPY
 *   "enableBatching":  true,
 *   "schemaType":      "BYTES"                      // 默认 BYTES，存原始 envelope
 * }
 * }</pre>
 *
 * <h3>credential_json 字段</h3>
 * <pre>{@code { "authToken": "..." }}      // JWT 认证；TLS 见 doc</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
@RequiredArgsConstructor
public class PulsarSink implements Sink {

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.PULSAR;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            // 用 Object 接住 ── 外层 method signature 不暴露 ProducerHolder（其字段是 pulsar 类型）
            Object holderObj = pool.getOrCreate(config.getIdentifier(), config, this::buildHolder);
            ProducerHolder holder = (ProducerHolder) holderObj;
            byte[] body = payload.getBody() == null ? new byte[0] : payload.getBody();

            @SuppressWarnings("unchecked")
            Producer<byte[]> producer = (Producer<byte[]>) holder.producer;
            TypedMessageBuilder<byte[]> mb = producer.newMessage()
                .value(body)
                .eventTime(payload.getTs());
            if (StrUtil.isNotBlank(payload.getRoutingKey())) {
                mb.key(payload.getRoutingKey());
            }
            if (payload.getHeaders() != null) {
                for (Map.Entry<String, String> e : payload.getHeaders().entrySet()) {
                    mb.property(e.getKey(), e.getValue());
                }
            }

            MessageId id = "SYNC".equalsIgnoreCase(holder.sendMode) ? mb.send() : mb.sendAsync().get();
            return SendResult.success(
                id == null ? null : id.toString(),
                System.currentTimeMillis() - start,
                Map.of("topic", holder.topic));
        } catch (Exception e) {
            log.warn("[PulsarSink] send failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            Object holderObj = pool.getOrCreate(config.getIdentifier(), config, this::buildHolder);
            ProducerHolder holder = (ProducerHolder) holderObj;
            // 探活：检查 producer 是否 connected
            return ((Producer<?>) holder.producer).isConnected();
        } catch (Exception e) {
            log.warn("[PulsarSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    /**
     * 构建 ProducerHolder。
     * <p>返回 Object，避免 method signature 引用 ProducerHolder（间接引用 pulsar 类） ──
     * driver 缺失时反射式扫描器不会抛 NoClassDefFoundError。
     */
    private Object buildHolder(ConnectorConfig config) {
        try {
            PulsarConnRaw conn = parseConnection(config);
            PulsarCredRaw cred = parseCredential(config);

            // 1. 构造 PulsarClient（含鉴权）
            org.apache.pulsar.client.api.ClientBuilder cb = PulsarClient.builder()
                .serviceUrl(conn.serviceUrl);
            if (StrUtil.isNotBlank(cred.authToken)) {
                cb.authentication(AuthenticationFactory.token(cred.authToken));
            }
            PulsarClient client = cb.build();

            // 2. 构造 Producer
            // 内联 compression 解析（CompressionType 不出现在外层 method signature）
            CompressionType compression = CompressionType.LZ4;
            if (StrUtil.isNotBlank(conn.compressionType)) {
                try {
                    compression = CompressionType.valueOf(conn.compressionType.toUpperCase());
                } catch (Exception ignore) { /* fallback LZ4 */ }
            }
            org.apache.pulsar.client.api.ProducerBuilder<byte[]> pb = client.newProducer(Schema.BYTES)
                .topic(conn.topic)
                .enableBatching(conn.enableBatching == null ? Boolean.TRUE : conn.enableBatching)
                .compressionType(compression);
            if (StrUtil.isNotBlank(conn.producerName)) {
                pb.producerName(conn.producerName);
            }
            Producer<byte[]> producer = pb.create();

            log.info("[PulsarSink] building producer identifier={} topic={}",
                config.getIdentifier(), conn.topic);
            return new ProducerHolder(client, producer, conn.topic,
                StrUtil.nullToDefault(conn.sendMode, "ASYNC"));
        } catch (Exception e) {
            // 不加前缀包装,e.getMessage() 透传 raw cause(PulsarClientException 自带分类信息)
            throw new RuntimeException(e);
        }
    }

    private PulsarConnRaw parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new PulsarConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), PulsarConnRaw.class);
    }

    private PulsarCredRaw parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new PulsarCredRaw()
            : JsonUtil.parse(config.getCredentialJson(), PulsarCredRaw.class);
    }

    public static class PulsarConnRaw {
        public String serviceUrl;
        public String topic;
        public String producerName;
        public String sendMode;
        public String compressionType;
        public Boolean enableBatching;
        public String schemaType;
    }

    public static class PulsarCredRaw {
        public String authToken;
        public String tlsCert;
        public String tlsKey;
    }

    /**
     * 连接池里持有的 Producer + 关联资源。
     * <p><b>字段类型用 Object 而非 PulsarClient / Producer</b>，避免 field signature
     * 引用 pulsar 类型 ── 反射扫描器加载本类时不会触发 pulsar 类解析。
     * pool eviction 时通过 AutoCloseable.close() 在方法体内 cast 后释放。
     */
    public static class ProducerHolder implements AutoCloseable {
        public final Object client;       // PulsarClient
        public final Object producer;     // Producer<byte[]>
        public final String topic;
        public final String sendMode;

        public ProducerHolder(Object client, Object producer, String topic, String sendMode) {
            this.client = client;
            this.producer = producer;
            this.topic = topic;
            this.sendMode = sendMode;
        }

        @Override
        public void close() throws Exception {
            // method body 内 cast，pulsar 类型不出现在 method signature
            try {
                ((Producer<?>) producer).close();
            } catch (Exception ignore) {
            }
            try {
                ((PulsarClient) client).close();
            } catch (Exception ignore) {
            }
        }
    }
}
