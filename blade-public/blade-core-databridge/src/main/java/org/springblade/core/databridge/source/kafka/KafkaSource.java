package org.springblade.core.databridge.source.kafka;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;

/**
 * Kafka 入站 Source（pull 模式 + 后台 poll 线程）。
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "bootstrapServers": "host:9092",
 *   "groupId":          "thinglinks-bridge-12345",
 *   "topics":           "iot-events,user-events",   // 多个用逗号分隔
 *   "autoOffsetReset":  "latest"                    // earliest / latest
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段（同 KafkaSink）</h3>
 * <pre>{@code
 * { "saslUsername": "...", "saslPassword": "...", "saslMechanism": "PLAIN" }
 * }</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "pollTimeoutMs":  500,        // 单次 poll 等待
 *   "maxPollRecords": 500,
 *   "enableAutoCommit": true      // 业务自管 offset 时设 false
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
public class KafkaSource implements Source {

    private final Map<String, KafkaPollWorker> running = new ConcurrentHashMap<>();

    @Override
    public ConnectorType supports() {
        return ConnectorType.KAFKA;
    }

    @Override
    public synchronized void start(ConnectorConfig config, Consumer<SourceMessage> handler) {
        String id = config.getIdentifier();
        if (running.containsKey(id)) {
            log.info("[KafkaSource] already running, skip start identifier={}", id);
            return;
        }
        KafkaPollWorker worker = new KafkaPollWorker(config, handler);
        worker.setName(BridgeNamingConstant.KAFKA_SOURCE_THREAD_PREFIX + id);
        worker.setDaemon(true);
        worker.start();
        running.put(id, worker);
        log.info("[KafkaSource] started identifier={}", id);
    }

    @Override
    public synchronized void stop(String identifier) {
        KafkaPollWorker worker = running.remove(identifier);
        if (worker != null) {
            worker.shutdown();
            log.info("[KafkaSource] stopped identifier={}", identifier);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try (KafkaConsumer<byte[], byte[]> probe = buildConsumer(config)) {
            probe.listTopics(Duration.ofSeconds(3));
            return true;
        } catch (Exception e) {
            log.warn("[KafkaSource] testConnection failed identifier={} cause={}",
                config.getIdentifier(), e.getMessage());
            return false;
        }
    }

    // ============================== 内部：build Consumer ==============================

    private KafkaConsumer<byte[], byte[]> buildConsumer(ConnectorConfig config) {
        KafkaSourceConnConfig conn = parseConnection(config);
        KafkaSourceCredConfig cred = parseCredential(config);
        KafkaSourceExtraConfig extra = parseExtra(config);

        if (StrUtil.isBlank(conn.bootstrapServers) || StrUtil.isBlank(conn.topics)
            || StrUtil.isBlank(conn.groupId)) {
            throw new IllegalArgumentException(
                "[KafkaSource] connectionJson missing bootstrapServers / topics / groupId");
        }

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, conn.bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, conn.groupId);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG,
            BridgeNamingConstant.KAFKA_SOURCE_CLIENT_PREFIX + config.getIdentifier());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
            StrUtil.nullToDefault(conn.autoOffsetReset, "latest"));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
            extra.enableAutoCommit == null ? true : extra.enableAutoCommit);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
            extra.maxPollRecords == null ? 500 : extra.maxPollRecords);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());

        // SASL
        if (StrUtil.isNotBlank(cred.saslUsername)) {
            props.put("security.protocol", "SASL_PLAINTEXT");
            props.put("sasl.mechanism", StrUtil.nullToDefault(cred.saslMechanism, "PLAIN"));
            String loginModule = "PLAIN".equalsIgnoreCase(cred.saslMechanism)
                ? "org.apache.kafka.common.security.plain.PlainLoginModule"
                : "org.apache.kafka.common.security.scram.ScramLoginModule";
            props.put("sasl.jaas.config", loginModule + " required username=\""
                + cred.saslUsername + "\" password=\""
                + StrUtil.nullToDefault(cred.saslPassword, "") + "\";");
        }

        return new KafkaConsumer<>(props);
    }

    private KafkaSourceConnConfig parseConnection(ConnectorConfig c) {
        return StrUtil.isBlank(c.getConnectionJson())
            ? new KafkaSourceConnConfig()
            : JsonUtil.parse(c.getConnectionJson(), KafkaSourceConnConfig.class);
    }

    private KafkaSourceCredConfig parseCredential(ConnectorConfig c) {
        return StrUtil.isBlank(c.getCredentialJson())
            ? new KafkaSourceCredConfig()
            : JsonUtil.parse(c.getCredentialJson(), KafkaSourceCredConfig.class);
    }

    private KafkaSourceExtraConfig parseExtra(ConnectorConfig c) {
        return StrUtil.isBlank(c.getExtraConfigJson())
            ? new KafkaSourceExtraConfig()
            : JsonUtil.parse(c.getExtraConfigJson(), KafkaSourceExtraConfig.class);
    }

    // ============================== 内部：后台 poll 线程 ==============================

    public static class KafkaSourceConnConfig {
        public String bootstrapServers;
        public String groupId;
        public String topics;
        public String autoOffsetReset;
    }

    // ============================== 内部 POJO ==============================

    public static class KafkaSourceCredConfig {
        public String saslUsername;
        public String saslPassword;
        public String saslMechanism;
    }

    public static class KafkaSourceExtraConfig {
        public Long pollTimeoutMs;
        public Integer maxPollRecords;
        public Boolean enableAutoCommit;
    }

    private class KafkaPollWorker extends Thread {
        private final ConnectorConfig config;
        private final Consumer<SourceMessage> handler;
        private final long pollTimeoutMs;
        private volatile boolean running = true;

        KafkaPollWorker(ConnectorConfig config, Consumer<SourceMessage> handler) {
            this.config = config;
            this.handler = handler;
            KafkaSourceExtraConfig extra = parseExtra(config);
            this.pollTimeoutMs = extra.pollTimeoutMs == null ? 500L : extra.pollTimeoutMs;
        }

        @Override
        public void run() {
            try (KafkaConsumer<byte[], byte[]> consumer = buildConsumer(config)) {
                KafkaSourceConnConfig conn = parseConnection(config);
                consumer.subscribe(Arrays.asList(conn.topics.split(",")));
                while (running) {
                    try {
                        ConsumerRecords<byte[], byte[]> records = consumer.poll(Duration.ofMillis(pollTimeoutMs));
                        for (ConsumerRecord<byte[], byte[]> r : records) {
                            try {
                                handler.accept(toSourceMessage(r));
                            } catch (Exception e) {
                                log.warn("[KafkaSource] handler threw, continuing identifier={} topic={} offset={} cause={}",
                                    config.getIdentifier(), r.topic(), r.offset(), e.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        if (running) {
                            log.warn("[KafkaSource] poll error identifier={} cause={}",
                                config.getIdentifier(), e.getMessage());
                            // 防 tight loop（短暂 sleep 后重试）
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error("[KafkaSource] fatal poll thread exit identifier={}", config.getIdentifier(), e);
            }
        }

        void shutdown() {
            running = false;
            this.interrupt();
        }

        private SourceMessage toSourceMessage(ConsumerRecord<byte[], byte[]> r) {
            Map<String, String> headers = new HashMap<>();
            if (r.headers() != null) {
                for (Header h : r.headers()) {
                    if (h.value() != null) {
                        headers.put(h.key(), new String(h.value(), StandardCharsets.UTF_8));
                    }
                }
            }
            Map<String, Object> attrs = new HashMap<>();
            attrs.put("topic", r.topic());
            attrs.put("partition", r.partition());
            attrs.put("offset", r.offset());

            return SourceMessage.builder()
                .body(r.value())
                .headers(headers)
                .routingKey(r.key() == null ? null : new String(r.key(), StandardCharsets.UTF_8))
                .ts(r.timestamp())
                .sourceMessageId(r.topic() + "-" + r.partition() + "-" + r.offset())
                .attributes(attrs)
                .build();
        }
    }
}
