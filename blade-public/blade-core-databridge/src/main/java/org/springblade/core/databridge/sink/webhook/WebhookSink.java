package org.springblade.core.databridge.sink.webhook;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * WebHook 出站 Sink ── HTTP 协议 + HMAC 签名 + 时间戳防重放。
 * <p>与 {@link org.springblade.core.databridge.sink.http.HttpSink} 区别：</p>
 * <ul>
 *   <li>WebHook <b>必须</b> 配 secretKey + signMethod，每条请求自动算签名塞 header</li>
 *   <li>同时塞当前时间戳 header（接收方校验时间窗口防重放）</li>
 *   <li>接收方按 {@code HMAC-SHA256(secretKey, timestamp + body)} 验签</li>
 * </ul>
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "url":     "https://thirdparty.example.com/webhook",
 *   "headers": { "X-Source": "thinglinks" },
 *   "contentType": "application/json"
 * }
 * }</pre>
 *
 * <h3>credentialJson 字段（必填 secretKey）</h3>
 * <pre>{@code { "secretKey": "..." }}</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "signMethod":          "HMAC_SHA256",   // HMAC_SHA1 / HMAC_SHA256 / HMAC_SHA512
 *   "signEncoding":        "HEX",            // HEX / BASE64
 *   "signHeaderName":      "X-Signature",
 *   "timestampHeaderName": "X-Timestamp",
 *   "connectTimeoutMs":    2000,
 *   "readTimeoutMs":       5000
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class WebhookSink implements Sink {

    private static final MediaType DEFAULT_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.WEBHOOK;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            WebhookConnConfig conn = parseConnection(config);
            WebhookCredConfig cred = parseCredential(config);
            WebhookExtraConfig extra = parseExtra(config);

            if (StrUtil.isBlank(conn.url) || StrUtil.isBlank(cred.secretKey)) {
                throw new IllegalArgumentException("[WebhookSink] missing url or secretKey");
            }

            byte[] body = payload.getBody() == null ? new byte[0] : payload.getBody();
            long timestamp = System.currentTimeMillis();
            String signature = computeSignature(cred.secretKey, timestamp, body, extra);

            OkHttpClient client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            Request.Builder rb = new Request.Builder()
                .url(conn.url)
                .post(RequestBody.create(body,
                    StrUtil.isBlank(conn.contentType)
                        ? DEFAULT_CONTENT_TYPE
                        : MediaType.parse(conn.contentType)));

            // 静态 header
            if (CollUtil.isNotEmpty(conn.headers)) {
                conn.headers.forEach(rb::addHeader);
            }
            // 动态 payload header
            if (CollUtil.isNotEmpty(payload.getHeaders())) {
                payload.getHeaders().forEach((k, v) -> {
                    if (v != null) {
                        rb.addHeader(k, v);
                    }
                });
            }
            // 签名 + 时间戳
            rb.addHeader(StrUtil.nullToDefault(extra.signHeaderName, "X-Signature"), signature);
            rb.addHeader(StrUtil.nullToDefault(extra.timestampHeaderName, "X-Timestamp"),
                String.valueOf(timestamp));

            try (Response resp = client.newCall(rb.build()).execute()) {
                int code = resp.code();
                String responseBody = resp.body() == null ? "" : resp.body().string();
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("httpStatus", code);
                attrs.put("responseBody", StrUtil.maxLength(responseBody, 2048));
                attrs.put("signedAt", timestamp);

                if (resp.isSuccessful()) {
                    return SendResult.success(resp.header("X-Request-Id"),
                        System.currentTimeMillis() - start, attrs);
                }
                // raw 透传 ── HTTP 状态码 + 响应体原文(最多 2000 字符,跟 trace.error_msg 对齐)。
                // 状态码前缀是协议必要标识,不算包装(webhook server 没有 exception 概念)。
                return SendResult.fail(String.valueOf(code),
                    new RuntimeException("HTTP " + code + " " + StrUtil.maxLength(responseBody, 2000)),
                    System.currentTimeMillis() - start);
            }
        } catch (Exception e) {
            log.warn("[WebhookSink] send failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            WebhookConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.url)) {
                return false;
            }
            OkHttpClient client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            try (Response r = client.newCall(new Request.Builder().url(conn.url).head().build()).execute()) {
                return r.code() < 500;
            }
        } catch (Exception e) {
            log.warn("[WebhookSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    // ============================== 内部：HMAC 签名 ==============================

    private String computeSignature(String secretKey, long timestamp, byte[] body, WebhookExtraConfig extra) {
        String method = StrUtil.isBlank(extra.signMethod) ? "HMAC_SHA256" : extra.signMethod.toUpperCase();
        HmacAlgorithm algo;
        switch (method) {
            case "HMAC_SHA1":
                algo = HmacAlgorithm.HmacSHA1;
                break;
            case "HMAC_SHA256":
                algo = HmacAlgorithm.HmacSHA256;
                break;
            case "HMAC_SHA512":
                algo = HmacAlgorithm.HmacSHA512;
                break;
            default:
                throw new UnsupportedOperationException("[WebhookSink] unsupported signMethod: " + method);
        }
        // 签名内容：timestamp + body（接收方按相同规则验证）
        byte[] data = (timestamp + new String(body, StandardCharsets.UTF_8))
            .getBytes(StandardCharsets.UTF_8);
        byte[] digest = new HMac(algo, secretKey.getBytes(StandardCharsets.UTF_8)).digest(data);

        String encoding = StrUtil.isBlank(extra.signEncoding) ? "HEX" : extra.signEncoding.toUpperCase();
        return "BASE64".equals(encoding) ? Base64.encode(digest) : HexUtil.encodeHexStr(digest);
    }

    // ============================== 内部：build OkHttpClient ==============================

    private OkHttpClient buildClient(ConnectorConfig config) {
        WebhookExtraConfig extra = parseExtra(config);
        return new OkHttpClient.Builder()
            .connectTimeout(extra.connectTimeoutMs == null ? 2000 : extra.connectTimeoutMs, TimeUnit.MILLISECONDS)
            .readTimeout(extra.readTimeoutMs == null ? 5000 : extra.readTimeoutMs, TimeUnit.MILLISECONDS)
            .writeTimeout(extra.writeTimeoutMs == null ? 5000 : extra.writeTimeoutMs, TimeUnit.MILLISECONDS)
            .build();
    }

    private WebhookConnConfig parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new WebhookConnConfig()
            : JsonUtil.parse(config.getConnectionJson(), WebhookConnConfig.class);
    }

    private WebhookCredConfig parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new WebhookCredConfig()
            : JsonUtil.parse(config.getCredentialJson(), WebhookCredConfig.class);
    }

    private WebhookExtraConfig parseExtra(ConnectorConfig config) {
        return StrUtil.isBlank(config.getExtraConfigJson())
            ? new WebhookExtraConfig()
            : JsonUtil.parse(config.getExtraConfigJson(), WebhookExtraConfig.class);
    }

    public static class WebhookConnConfig {
        public String url;
        public Map<String, String> headers;
        public String contentType;
    }

    public static class WebhookCredConfig {
        public String secretKey;
    }

    public static class WebhookExtraConfig {
        public String signMethod;
        public String signEncoding;
        public String signHeaderName;
        public String timestampHeaderName;
        public Long connectTimeoutMs;
        public Long readTimeoutMs;
        public Long writeTimeoutMs;
    }
}
