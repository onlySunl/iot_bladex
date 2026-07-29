package org.springblade.core.databridge.sink.kafka;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.collection.CollUtil;
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
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.ByteArraySerializer;

/**
 * Kafka 出站 Sink。
 * <p>
 * 用 {@code kafka-clients} 的原生 {@link KafkaProducer}，<b>不依赖 spring-kafka 自动装配</b>，
 * 避免与业务侧已有的 Spring Kafka 配置冲突。
 * </p>
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "bootstrapServers": "host1:9092,host2:9092",   // 必填
 *   "clientId":         "thinglinks-bridge-12345", // 可选，默认按 identifier 拼
 *   "topic":            "iot-out",                 // 必填
 *   "useTls":           false                      // 可选
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段（SASL/TLS）</h3>
 * <pre>{@code
 * {
 *   "saslUsername":  "...",
 *   "saslPassword":  "...",
 *   "saslMechanism": "PLAIN" | "SCRAM-SHA-256" | "SCRAM-SHA-512"
 * }
 * }</pre>
 *
 * <h3>extraConfigJson 字段（性能调参）</h3>
 * <pre>{@code
 * {
 *   "acks":            "1",         // 0 / 1 / all
 *   "compressionType": "snappy",    // none / gzip / snappy / lz4 / zstd
 *   "retries":         3,
 *   "lingerMs":        0,
 *   "batchSize":       16384,
 *   "bufferMemory":    33554432,
 *   "sendTimeoutMs":   3000         // ⭐ 自定义 send().get() 超时（默认 3s）
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class KafkaSink implements Sink {

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.KAFKA;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            KafkaConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.bootstrapServers) || StrUtil.isBlank(conn.topic)) {
                throw new IllegalArgumentException("[KafkaSink] connectionJson missing bootstrapServers or topic");
            }
            KafkaExtraConfig extra = parseExtra(config);

            KafkaProducer<byte[], byte[]> producer = pool.getOrCreate(
                config.getIdentifier(), config, this::buildProducer);

            byte[] key = StrUtil.isBlank(payload.getRoutingKey())
                ? null
                : payload.getRoutingKey().getBytes(StandardCharsets.UTF_8);
            ProducerRecord<byte[], byte[]> record = new ProducerRecord<>(
                conn.topic, null, payload.getTs() > 0 ? payload.getTs() : null,
                key, payload.getBody());

            // payload.headers → kafka record headers
            if (CollUtil.isNotEmpty(payload.getHeaders())) {
                payload.getHeaders().forEach((hk, hv) ->
                    record.headers().add(hk, hv == null ? null : hv.getBytes(StandardCharsets.UTF_8)));
            }

            RecordMetadata m = producer.send(record).get(extra.sendTimeoutMs, TimeUnit.MILLISECONDS);

            String messageId = m.topic() + "-" + m.partition() + "-" + m.offset();
            Map<String, Object> attrs = new HashMap<>();
            attrs.put("partition", m.partition());
            attrs.put("offset", m.offset());
            attrs.put("topic", m.topic());
            return SendResult.success(messageId, System.currentTimeMillis() - start, attrs);

        } catch (Exception e) {
            // 透传完整 cause chain ── Kafka 的 ExecutionException 经常把 TimeoutException
            // 或 NetworkException 藏在 getCause() 里;e 整体传给 logger 自动打 stack trace。
            log.warn("[KafkaSink] send failed identifier={} cause={}", config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            KafkaConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.bootstrapServers) || StrUtil.isBlank(conn.topic)) {
                return false;
            }
            KafkaProducer<byte[], byte[]> producer = pool.getOrCreate(
                config.getIdentifier(), config, this::buildProducer);
            // 探活：拉一次 topic 的 partition 元数据（带短超时由 producer 自身配置控制）
            List<PartitionInfo> partitions = producer.partitionsFor(conn.topic);
            return CollUtil.isNotEmpty(partitions);
        } catch (Exception e) {
            log.warn("[KafkaSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    // ============================== 内部：建 KafkaProducer ==============================

    private KafkaProducer<byte[], byte[]> buildProducer(ConnectorConfig config) {
        KafkaConnConfig conn = parseConnection(config);
        KafkaCredConfig cred = parseCredential(config);
        KafkaExtraConfig extra = parseExtra(config);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, conn.bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG,
            StrUtil.isBlank(conn.clientId)
                ? BridgeNamingConstant.SINK_CLIENT_PREFIX + config.getIdentifier()
                : conn.clientId);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        // 性能调参（用户可覆盖；缺省走 producer 默认）
        if (StrUtil.isNotBlank(extra.acks)) {
            props.put(ProducerConfig.ACKS_CONFIG, extra.acks);
        }
        if (StrUtil.isNotBlank(extra.compressionType)) {
            props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, extra.compressionType);
        }
        if (extra.retries != null) {
            props.put(ProducerConfig.RETRIES_CONFIG, extra.retries);
        }
        if (extra.lingerMs != null) {
            props.put(ProducerConfig.LINGER_MS_CONFIG, extra.lingerMs);
        }
        if (extra.batchSize != null) {
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, extra.batchSize);
        }
        if (extra.bufferMemory != null) {
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, extra.bufferMemory);
        }

        // SASL / TLS
        if (Boolean.TRUE.equals(conn.useTls)) {
            props.put("security.protocol", "SASL_SSL");
        } else if (StrUtil.isNotBlank(cred.saslUsername)) {
            props.put("security.protocol", "SASL_PLAINTEXT");
        }
        if (StrUtil.isNotBlank(cred.saslMechanism)) {
            props.put("sasl.mechanism", cred.saslMechanism);
        }
        if (StrUtil.isNotBlank(cred.saslUsername)) {
            props.put("sasl.jaas.config", buildSaslJaasConfig(cred));
        }

        log.info("[KafkaSink] building producer identifier={} bootstrap={}",
            config.getIdentifier(), conn.bootstrapServers);
        return new KafkaProducer<>(props);
    }

    private String buildSaslJaasConfig(KafkaCredConfig cred) {
        String loginModule = "PLAIN".equalsIgnoreCase(cred.saslMechanism)
            ? "org.apache.kafka.common.security.plain.PlainLoginModule"
            : "org.apache.kafka.common.security.scram.ScramLoginModule";
        return loginModule + " required username=\"" + cred.saslUsername + "\" password=\""
            + (cred.saslPassword == null ? "" : cred.saslPassword) + "\";";
    }

    // ============================== 内部：解析 JSON 配置 ==============================

    private KafkaConnConfig parseConnection(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getConnectionJson())) {
            return new KafkaConnConfig();
        }
        return JsonUtil.parse(config.getConnectionJson(), KafkaConnConfig.class);
    }

    private KafkaCredConfig parseCredential(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return new KafkaCredConfig();
        }
        return JsonUtil.parse(config.getCredentialJson(), KafkaCredConfig.class);
    }

    private KafkaExtraConfig parseExtra(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getExtraConfigJson())) {
            return new KafkaExtraConfig();
        }
        KafkaExtraConfig extra = JsonUtil.parse(config.getExtraConfigJson(), KafkaExtraConfig.class);
        return extra == null ? new KafkaExtraConfig() : extra;
    }

    // ============================== 内部 POJO（typed JSON 视图）==============================

    /**
     * connectionJson POJO 视图。
     */
    public static class KafkaConnConfig {
        public String bootstrapServers;
        public String clientId;
        public String topic;
        public Boolean useTls;
    }

    /**
     * credentialJson POJO 视图。
     */
    public static class KafkaCredConfig {
        public String saslUsername;
        public String saslPassword;
        public String saslMechanism;
    }

    /**
     * extraConfigJson POJO 视图（性能调参，所有字段可选）。
     */
    public static class KafkaExtraConfig {
        public String acks;
        public String compressionType;
        public Integer retries;
        public Integer lingerMs;
        public Integer batchSize;
        public Long bufferMemory;
        /**
         * send().get() 同步等待 ack 超时（毫秒）── 跨公网 / cold start 余量,内网正常 ms 级返回。
         */
        public long sendTimeoutMs = 10_000L;
    }
}
