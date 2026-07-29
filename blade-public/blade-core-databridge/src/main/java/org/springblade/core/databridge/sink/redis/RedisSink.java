package org.springblade.core.databridge.sink.redis;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Redis 出站 Sink（Lettuce 直连）。
 * <p>
 * 支持 STANDALONE / SENTINEL / CLUSTER 三种部署模式，按 connectionJson 的 {@code mode} 字段路由。
 * 写入命令支持 LPUSH / RPUSH / XADD / PUBLISH / SET。
 * </p>
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "mode":          "STANDALONE",     // STANDALONE / SENTINEL / CLUSTER
 *   "host":          "127.0.0.1",      // STANDALONE 必填
 *   "port":          6379,
 *   "db":            0,
 *   "sentinels":     "host1:26379,host2:26379",   // SENTINEL 必填
 *   "masterName":    "mymaster",                  // SENTINEL 必填
 *   "clusterNodes":  "host1:7000,host2:7000",     // CLUSTER 必填
 *   "command":       "LPUSH",          // ⭐ 写入命令：LPUSH/RPUSH/XADD/PUBLISH/SET
 *   "keyTemplate":   "iot:${routingKey}"          // 可含 ${routingKey} 占位符
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段</h3>
 * <pre>{@code { "password": "..." }}</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "ttlSeconds":     3600,            // SET 命令 TTL；0 表示无过期
 *   "connectTimeout": 2000             // 连接超时 (ms)
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class RedisSink implements Sink {

    private static final Pattern PLACEHOLDER = Pattern.compile("\\$\\{([^}]+)}");

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.REDIS;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            RedisConnConfig conn = parseConnection(config);
            RedisExtraConfig extra = parseExtra(config);
            if (StrUtil.isBlank(conn.command) || StrUtil.isBlank(conn.keyTemplate)) {
                throw new IllegalArgumentException("[RedisSink] connectionJson missing command or keyTemplate");
            }

            String key = renderKey(conn.keyTemplate, payload);
            byte[] value = payload.getBody() == null ? new byte[0] : payload.getBody();

            RedisHolder holder = pool.getOrCreate(config.getIdentifier(), config, this::buildHolder);
            String responseId = executeCommand(holder, conn.command.toUpperCase(), key, value, extra);

            return SendResult.success(responseId, System.currentTimeMillis() - start, Map.of("key", key));
        } catch (Exception e) {
            // Lettuce 的 RedisCommandExecutionException / RedisConnectionException 经常套 cause
            log.warn("[RedisSink] send failed identifier={} cause={}", config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            RedisHolder holder = pool.getOrCreate(config.getIdentifier(), config, this::buildHolder);
            String pong = holder.cluster
                ? holder.clusterCmd.ping()
                : holder.cmd.ping();
            return "PONG".equalsIgnoreCase(pong);
        } catch (Exception e) {
            log.warn("[RedisSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    // ============================== 内部：command 执行 ==============================

    private String executeCommand(RedisHolder holder, String cmd, String key, byte[] value, RedisExtraConfig extra) {
        switch (cmd) {
            case "LPUSH":
                return String.valueOf(holder.cluster
                    ? holder.clusterCmd.lpush(key, value)
                    : holder.cmd.lpush(key, value));
            case "RPUSH":
                return String.valueOf(holder.cluster
                    ? holder.clusterCmd.rpush(key, value)
                    : holder.cmd.rpush(key, value));
            case "XADD": {
                Map<String, byte[]> bodyMap = Map.of("payload", value);
                return holder.cluster
                    ? holder.clusterCmd.xadd(key, bodyMap)
                    : holder.cmd.xadd(key, bodyMap);
            }
            case "PUBLISH":
                return String.valueOf(holder.cluster
                    ? holder.clusterCmd.publish(key, value)
                    : holder.cmd.publish(key, value));
            case "SET": {
                String ok;
                if (extra.ttlSeconds != null && extra.ttlSeconds > 0) {
                    SetArgs args = SetArgs.Builder.ex(extra.ttlSeconds);
                    ok = holder.cluster
                        ? holder.clusterCmd.set(key, value, args)
                        : holder.cmd.set(key, value, args);
                } else {
                    ok = holder.cluster
                        ? holder.clusterCmd.set(key, value)
                        : holder.cmd.set(key, value);
                }
                return ok;
            }
            default:
                throw new UnsupportedOperationException("[RedisSink] unsupported command: " + cmd);
        }
    }

    private String renderKey(String template, ConnectorPayload payload) {
        if (template == null) {
            return "";
        }
        Matcher m = PLACEHOLDER.matcher(template);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String placeholder = m.group(1);
            String replacement = switch (placeholder) {
                case "routingKey" -> StrUtil.nullToDefault(payload.getRoutingKey(), "");
                case "ts" -> String.valueOf(payload.getTs());
                default -> StrUtil.nullToDefault(payload.header(placeholder), "");
            };
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // ============================== 内部：build Lettuce Client ==============================

    private RedisHolder buildHolder(ConnectorConfig config) {
        RedisConnConfig conn = parseConnection(config);
        RedisCredConfig cred = parseCredential(config);
        RedisExtraConfig extra = parseExtra(config);

        String mode = StrUtil.isBlank(conn.mode) ? "STANDALONE" : conn.mode.toUpperCase();
        log.info("[RedisSink] building client identifier={} mode={}", config.getIdentifier(), mode);

        switch (mode) {
            case "STANDALONE":
                return buildStandalone(conn, cred, extra);
            case "SENTINEL":
                return buildSentinel(conn, cred, extra);
            case "CLUSTER":
                return buildCluster(conn, cred, extra);
            default:
                throw new UnsupportedOperationException("[RedisSink] unsupported mode: " + mode);
        }
    }

    private RedisHolder buildStandalone(RedisConnConfig conn, RedisCredConfig cred, RedisExtraConfig extra) {
        RedisURI.Builder b = RedisURI.builder()
            .withHost(StrUtil.nullToDefault(conn.host, "127.0.0.1"))
            .withPort(conn.port == null ? 6379 : conn.port)
            .withDatabase(conn.db == null ? 0 : conn.db);
        if (StrUtil.isNotBlank(cred.password)) {
            b.withPassword(cred.password.toCharArray());
        }
        RedisClient client = RedisClient.create(b.build());
        StatefulRedisConnection<String, byte[]> connection = client.connect(stringByteCodec());
        return new RedisHolder(client, null, connection, null, connection.sync(), null, false);
    }

    private RedisHolder buildSentinel(RedisConnConfig conn, RedisCredConfig cred, RedisExtraConfig extra) {
        if (StrUtil.isBlank(conn.sentinels) || StrUtil.isBlank(conn.masterName)) {
            throw new IllegalArgumentException("[RedisSink] SENTINEL mode requires sentinels + masterName");
        }
        RedisURI.Builder b = RedisURI.Builder.sentinel(
            conn.sentinels.split(",")[0].split(":")[0],
            Integer.parseInt(conn.sentinels.split(",")[0].split(":")[1]),
            conn.masterName);
        for (String s : conn.sentinels.split(",")) {
            String[] hp = s.split(":");
            b.withSentinel(hp[0], Integer.parseInt(hp[1]));
        }
        b.withDatabase(conn.db == null ? 0 : conn.db);
        if (StrUtil.isNotBlank(cred.password)) {
            b.withPassword(cred.password.toCharArray());
        }
        RedisClient client = RedisClient.create(b.build());
        StatefulRedisConnection<String, byte[]> connection = client.connect(stringByteCodec());
        return new RedisHolder(client, null, connection, null, connection.sync(), null, false);
    }

    private RedisHolder buildCluster(RedisConnConfig conn, RedisCredConfig cred, RedisExtraConfig extra) {
        if (StrUtil.isBlank(conn.clusterNodes)) {
            throw new IllegalArgumentException("[RedisSink] CLUSTER mode requires clusterNodes");
        }
        List<RedisURI> uris = new java.util.ArrayList<>();
        for (String node : conn.clusterNodes.split(",")) {
            String[] hp = node.split(":");
            RedisURI.Builder b = RedisURI.Builder.redis(hp[0], Integer.parseInt(hp[1]));
            if (StrUtil.isNotBlank(cred.password)) {
                b.withPassword(cred.password.toCharArray());
            }
            uris.add(b.build());
        }
        RedisClusterClient client = RedisClusterClient.create(uris);
        StatefulRedisClusterConnection<String, byte[]> connection = client.connect(stringByteCodec());
        return new RedisHolder(null, client, null, connection, null, connection.sync(), true);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private RedisCodec<String, byte[]> stringByteCodec() {
        return RedisCodec.of(StringCodec.UTF8, ByteArrayCodec.INSTANCE);
    }

    // ============================== 内部：JSON 解析 ==============================

    private RedisConnConfig parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new RedisConnConfig()
            : JsonUtil.parse(config.getConnectionJson(), RedisConnConfig.class);
    }

    private RedisCredConfig parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new RedisCredConfig()
            : JsonUtil.parse(config.getCredentialJson(), RedisCredConfig.class);
    }

    private RedisExtraConfig parseExtra(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getExtraConfigJson())) {
            return new RedisExtraConfig();
        }
        RedisExtraConfig extra = JsonUtil.parse(config.getExtraConfigJson(), RedisExtraConfig.class);
        return extra == null ? new RedisExtraConfig() : extra;
    }

    // ============================== 内部 POJO ==============================

    public static class RedisConnConfig {
        public String mode;
        public String host;
        public Integer port;
        public Integer db;
        public String sentinels;
        public String masterName;
        public String clusterNodes;
        public String command;
        public String keyTemplate;
    }

    public static class RedisCredConfig {
        public String password;
    }

    public static class RedisExtraConfig {
        public Integer ttlSeconds;
        public Integer connectTimeout;
    }

    /**
     * 持有 Redis 客户端 + sync 命令对象（standalone/sentinel/cluster 二选一）。
     * <p>实现 AutoCloseable 让 ConnectionPoolManager 淘汰时自动关。
     */
    record RedisHolder(RedisClient standaloneClient, RedisClusterClient clusterClient,
                       StatefulRedisConnection<String, byte[]> standaloneConn,
                       StatefulRedisClusterConnection<String, byte[]> clusterConn,
                       RedisCommands<String, byte[]> cmd,
                       RedisAdvancedClusterCommands<String, byte[]> clusterCmd,
                       boolean cluster) implements AutoCloseable {

        @Override
        public void close() {
            try {
                if (standaloneConn != null) {
                    standaloneConn.close();
                }
            } catch (Exception ignored) {
            }
            try {
                if (clusterConn != null) {
                    clusterConn.close();
                }
            } catch (Exception ignored) {
            }
            try {
                if (standaloneClient != null) {
                    standaloneClient.shutdown();
                }
            } catch (Exception ignored) {
            }
            try {
                if (clusterClient != null) {
                    clusterClient.shutdown();
                }
            } catch (Exception ignored) {
            }
        }
    }
}
