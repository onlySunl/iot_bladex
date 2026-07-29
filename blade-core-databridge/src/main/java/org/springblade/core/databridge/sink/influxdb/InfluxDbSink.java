package org.springblade.core.databridge.sink.influxdb;

import cn.hutool.core.util.StrUtil;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;

/**
 * InfluxDB v2 出站 Sink（influxdb-client-java；Line Protocol 写入）。
 *
 * <h3>类签名约束（关键）</h3>
 * 本类所有 public / package-private 方法的 <b>参数类型 + 返回类型 + throws 类型</b>
 * 不直接引用 com.influxdb 任何类，全部用 Object 兜底。influxdb-client 类型仅出现在
 * <b>方法体局部变量</b> 中。这样保证 driver 不在 classpath 时
 * （{@code <optional>true</optional>}），反射式扫描器（如 thinglinks-databases 的
 * TenantLineAnnotationRegister 通过 Class.forName + getDeclaredMethods() 触发的
 * method signature 解析）不会抛 NoClassDefFoundError。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "url":             "http://host:8086",
 *   "org":             "iot-org",
 *   "bucket":          "device_metrics",
 *   "measurement":     "device_data",
 *   "tagsMapping":     { "productId": "${header.X-Product}", "deviceId": "${routingKey}" },
 *   "fieldsMapping":   { "value": "${body}" },
 *   "timestampField":  null,                    // 默认从 payload.ts 取
 *   "apiVersion":      "V2"                     // V1 / V2
 * }
 * }</pre>
 *
 * <h3>credential_json 字段</h3>
 * <pre>{@code { "token": "..." }}     // v2 优先；v1 用 username + password</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
@RequiredArgsConstructor
public class InfluxDbSink implements Sink {

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.INFLUXDB;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            // 用 Object 接住 ── method 内部 cast，外层 method signature 不暴露 InfluxDBClient
            Object clientObj = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            InfluxDBClient client = (InfluxDBClient) clientObj;
            InfluxConnRaw conn = parseConnection(config);
            WriteApiBlocking write = client.getWriteApiBlocking();

            Point point = Point.measurement(StrUtil.nullToDefault(conn.measurement, "device_data"));
            // tags
            if (conn.tagsMapping != null) {
                for (Map.Entry<String, String> e : conn.tagsMapping.entrySet()) {
                    point.addTag(e.getKey(), String.valueOf(renderTemplate(e.getValue(), payload)));
                }
            }
            // fields
            if (conn.fieldsMapping != null) {
                for (Map.Entry<String, String> e : conn.fieldsMapping.entrySet()) {
                    Object v = renderTemplate(e.getValue(), payload);
                    if (v == null) {
                        continue;
                    }
                    if (v instanceof Number) {
                        point.addField(e.getKey(), (Number) v);
                    } else if (v instanceof Boolean) {
                        point.addField(e.getKey(), (Boolean) v);
                    } else {
                        point.addField(e.getKey(), v.toString());
                    }
                }
            }
            // timestamp（默认从 payload.ts 取，单位毫秒）
            point.time(Instant.ofEpochMilli(payload.getTs()), WritePrecision.MS);

            write.writePoint(conn.bucket, conn.org, point);

            return SendResult.success(
                null,
                System.currentTimeMillis() - start,
                Map.of("measurement", conn.measurement, "bucket", conn.bucket));
        } catch (Exception e) {
            log.warn("[InfluxDbSink] send failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            Object clientObj = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            // ping() 返回 healthy 即认为连接通
            return ((InfluxDBClient) clientObj).ping();
        } catch (Exception e) {
            log.warn("[InfluxDbSink] testConnection failed identifier={} cause={}",
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
     * 构建 InfluxDBClient。
     * <p>返回 Object，避免 method signature 引用 com.influxdb.client.InfluxDBClient ──
     * driver 缺失时反射式扫描器不会抛 NoClassDefFoundError。
     */
    private Object buildClient(ConnectorConfig config) {
        InfluxConnRaw conn = parseConnection(config);
        InfluxCredRaw cred = parseCredential(config);
        log.info("[InfluxDbSink] building client identifier={} url={} org={} bucket={}",
            config.getIdentifier(), conn.url, conn.org, conn.bucket);
        if (StrUtil.isNotBlank(cred.token)) {
            return InfluxDBClientFactory.create(conn.url, cred.token.toCharArray(), conn.org, conn.bucket);
        }
        // v1 fallback: username + password
        return InfluxDBClientFactory.create(conn.url, cred.username, cred.password == null ? null : cred.password.toCharArray());
    }

    private InfluxConnRaw parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new InfluxConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), InfluxConnRaw.class);
    }

    private InfluxCredRaw parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new InfluxCredRaw()
            : JsonUtil.parse(config.getCredentialJson(), InfluxCredRaw.class);
    }

    /**
     * 占位符渲染（与 AbstractJdbcSink 同款，简化版）。
     */
    private Object renderTemplate(String expr, ConnectorPayload payload) {
        if (StrUtil.isBlank(expr)) {
            return null;
        }
        if (!expr.startsWith("${") || !expr.endsWith("}")) {
            return expr;
        }
        String key = expr.substring(2, expr.length() - 1);
        if ("body".equals(key)) {
            return payload.getBody() == null ? null : new String(payload.getBody(), StandardCharsets.UTF_8);
        }
        if ("routingKey".equals(key)) {
            return payload.getRoutingKey();
        }
        if ("ts".equals(key)) {
            return payload.getTs();
        }
        if (key.startsWith("header.")) {
            return payload.header(key.substring("header.".length()));
        }
        return null;
    }

    public static class InfluxConnRaw {
        public String url;
        public String org;
        public String bucket;
        public String measurement;
        public Map<String, String> tagsMapping;
        public Map<String, String> fieldsMapping;
        public String timestampField;
        public String apiVersion;
    }

    public static class InfluxCredRaw {
        public String token;
        public String username;
        public String password;
    }
}
