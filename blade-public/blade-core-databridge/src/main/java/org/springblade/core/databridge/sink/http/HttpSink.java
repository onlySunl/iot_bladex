package org.springblade.core.databridge.sink.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 通用 HTTP 出站 Sink（OkHttp 同步客户端）。
 * <p>支持 POST / PUT / PATCH 推送 byte[] body；自动在 {@code ConnectionPoolManager} 中复用 OkHttpClient。</p>
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "url":         "https://api.example.com/iot",  // 必填
 *   "method":      "POST",                          // POST/PUT/PATCH
 *   "headers":     { "X-Source": "blade" },    // 静态请求头
 *   "queryParams": { "src": "iot" },                // URL 参数
 *   "contentType": "application/json"               // 默认 application/json
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段（鉴权三选一）</h3>
 * <pre>{@code
 * {
 *   "bearerToken":   "...",
 *   "basicUsername": "...", "basicPassword": "..."
 * }
 * }</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "connectTimeoutMs": 2000,
 *   "readTimeoutMs":    5000,
 *   "writeTimeoutMs":   5000
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class HttpSink implements Sink {

    private static final MediaType DEFAULT_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.HTTP;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            HttpConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.url)) {
                throw new IllegalArgumentException("[HttpSink] missing url");
            }
            HttpCredConfig cred = parseCredential(config);

            OkHttpClient client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);

            Request request = buildRequest(payload, conn, cred);
            try (Response resp = client.newCall(request).execute()) {
                int code = resp.code();
                String responseBody = resp.body() == null ? "" : resp.body().string();

                Map<String, Object> attrs = new HashMap<>();
                attrs.put("httpStatus", code);
                attrs.put("responseBody", StrUtil.maxLength(responseBody, 1024));   // 截断防过大日志
                attrs.put("contentLength", resp.body() == null ? 0 : resp.body().contentLength());

                if (resp.isSuccessful()) {
                    return SendResult.success(resp.header("X-Request-Id"),
                        System.currentTimeMillis() - start, attrs);
                }
                // raw 透传 ── HTTP 状态码 + 响应体原文(最多 2000 字符,跟 trace.error_msg 对齐)。
                // 状态码前缀是 HTTP 协议必要标识,不算包装。
                String errMsg = "HTTP " + code + " " + StrUtil.maxLength(responseBody, 2000);
                return SendResult.fail(String.valueOf(code), new RuntimeException(errMsg),
                    System.currentTimeMillis() - start);
            }
        } catch (Exception e) {
            log.warn("[HttpSink] send failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            HttpConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.url)) {
                return false;
            }
            OkHttpClient client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            // OPTIONS / HEAD 探活；很多 API 不支持 OPTIONS，用 HEAD 兜底
            Request head = new Request.Builder().url(conn.url).head().build();
            try (Response r = client.newCall(head).execute()) {
                return r.code() < 500;   // 4xx 也算"连得上"，只是权限/路径问题
            }
        } catch (Exception e) {
            log.warn("[HttpSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    // ============================== 内部：build Request ==============================

    private Request buildRequest(ConnectorPayload payload, HttpConnConfig conn, HttpCredConfig cred) {
        // URL + queryParams
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(conn.url)).newBuilder();
        if (MapUtil.isNotEmpty(conn.queryParams)) {
            conn.queryParams.forEach(urlBuilder::addQueryParameter);
        }

        Request.Builder rb = new Request.Builder().url(urlBuilder.build());

        // headers (config 静态 + payload 动态)
        if (MapUtil.isNotEmpty(conn.headers)) {
            conn.headers.forEach(rb::addHeader);
        }
        if (CollUtil.isNotEmpty(payload.getHeaders())) {
            payload.getHeaders().forEach((k, v) -> {
                if (v != null) {
                    rb.addHeader(k, v);
                }
            });
        }

        // 鉴权
        if (StrUtil.isNotBlank(cred.bearerToken)) {
            rb.addHeader("Authorization", "Bearer " + cred.bearerToken);
        } else if (StrUtil.isNotBlank(cred.basicUsername)) {
            String basic = okhttp3.Credentials.basic(
                cred.basicUsername, StrUtil.nullToDefault(cred.basicPassword, ""));
            rb.addHeader("Authorization", basic);
        }

        // body
        MediaType ct = StrUtil.isBlank(conn.contentType)
            ? DEFAULT_CONTENT_TYPE
            : MediaType.parse(conn.contentType);
        RequestBody body = RequestBody.create(payload.getBody() == null ? new byte[0] : payload.getBody(), ct);

        String method = StrUtil.isBlank(conn.method) ? "POST" : conn.method.toUpperCase();
        return switch (method) {
            case "POST" -> rb.post(body).build();
            case "PUT" -> rb.put(body).build();
            case "PATCH" -> rb.patch(body).build();
            default -> throw new UnsupportedOperationException("[HttpSink] unsupported method: " + method);
        };
    }

    // ============================== 内部：build OkHttpClient ==============================

    private OkHttpClient buildClient(ConnectorConfig config) {
        HttpExtraConfig extra = parseExtra(config);
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.connectTimeout(extra.connectTimeoutMs == null ? 2000 : extra.connectTimeoutMs, TimeUnit.MILLISECONDS);
        b.readTimeout(extra.readTimeoutMs == null ? 5000 : extra.readTimeoutMs, TimeUnit.MILLISECONDS);
        b.writeTimeout(extra.writeTimeoutMs == null ? 5000 : extra.writeTimeoutMs, TimeUnit.MILLISECONDS);
        log.info("[HttpSink] building client identifier={} timeouts(c/r/w)={}/{}/{}ms",
            config.getIdentifier(),
            extra.connectTimeoutMs, extra.readTimeoutMs, extra.writeTimeoutMs);
        return b.build();
    }

    // ============================== 内部：JSON 解析 ==============================

    private HttpConnConfig parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new HttpConnConfig()
            : JsonUtil.parse(config.getConnectionJson(), HttpConnConfig.class);
    }

    private HttpCredConfig parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new HttpCredConfig()
            : JsonUtil.parse(config.getCredentialJson(), HttpCredConfig.class);
    }

    private HttpExtraConfig parseExtra(ConnectorConfig config) {
        return StrUtil.isBlank(config.getExtraConfigJson())
            ? new HttpExtraConfig()
            : JsonUtil.parse(config.getExtraConfigJson(), HttpExtraConfig.class);
    }

    // ============================== 内部 POJO ==============================

    public static class HttpConnConfig {
        public String url;
        public String method;
        public Map<String, String> headers;
        public Map<String, String> queryParams;
        public String contentType;
    }

    public static class HttpCredConfig {
        public String bearerToken;
        public String basicUsername;
        public String basicPassword;
    }

    public static class HttpExtraConfig {
        public Long connectTimeoutMs;
        public Long readTimeoutMs;
        public Long writeTimeoutMs;
    }
}
