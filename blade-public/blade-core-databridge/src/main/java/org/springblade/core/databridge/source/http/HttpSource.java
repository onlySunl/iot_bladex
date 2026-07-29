package org.springblade.core.databridge.source.http;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SourceMessage;
import org.springblade.core.databridge.spi.Source;
import lombok.extern.slf4j.Slf4j;

/**
 * HTTP 入站 Source（push 模式 / Servlet 入口由业务侧 Controller 提供）。
 * <p>
 * 与 KafkaSource / MqttSource 不同，HTTP push 没有"客户端去拉"的语义 ── 第三方系统主动 POST
 * 数据进来。所以本类<b>不启动后台线程</b>，仅做"handler 注册表"：
 * </p>
 * <ol>
 *   <li>业务侧调 {@link #start(ConnectorConfig, Consumer)} 注册一个 handler，按 identifier 索引</li>
 *   <li>业务侧 Controller（如 {@code BridgeIngressOpenAnyUserController}）收到 HTTP POST 后，
 *       根据 URL 中的 sourceCode 拿到 identifier，然后调 {@link #ingest(String, SourceMessage)}
 *       触发对应 handler</li>
 * </ol>
 * <p>这样 starter 不依赖 Spring MVC，业务侧自由选择 Controller 实现（Servlet / WebFlux / 自定义）。</p>
 *
 * <h3>connectionJson 字段（仅文档化，starter 不用）</h3>
 * <pre>{@code
 * {
 *   "endpointPath":  "/api/anyUser/bridge/ingress/{sourceCode}",
 *   "method":        "POST",
 *   "verifySignature": true        // 业务侧 Controller 实现
 * }
 * }</pre>
 *
 * <h3>用法</h3>
 * <pre>{@code
 * // 业务侧 SubscriptionSourceManager 启动时调
 * httpSource.start(config, handler);
 *
 * // 业务侧 Controller 收到 HTTP 请求后
 * @PostMapping("/anyUser/bridge/ingress/{sourceCode}")
 * public R<Void> ingress(@PathVariable String sourceCode, @RequestBody byte[] body,
 *                        @RequestHeader Map<String, String> headers) {
 *     SourceMessage msg = SourceMessage.builder()
 *         .body(body).headers(headers).ts(System.currentTimeMillis()).build();
 *     httpSource.ingest(sourceCode, msg);   // ⭐ 触发回调
 *     return R.success();
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
public class HttpSource implements Source {

    /**
     * identifier → handler 注册表。
     */
    private final Map<String, Consumer<SourceMessage>> handlers = new ConcurrentHashMap<>();

    @Override
    public ConnectorType supports() {
        return ConnectorType.HTTP;
    }

    @Override
    public void start(ConnectorConfig config, Consumer<SourceMessage> handler) {
        String id = config.getIdentifier();
        Consumer<SourceMessage> prev = handlers.put(id, handler);
        if (prev != null) {
            log.info("[HttpSource] handler replaced identifier={}", id);
        } else {
            log.info("[HttpSource] handler registered identifier={}", id);
        }
    }

    @Override
    public void stop(String identifier) {
        Consumer<SourceMessage> removed = handlers.remove(identifier);
        if (removed != null) {
            log.info("[HttpSource] handler removed identifier={}", identifier);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        // HTTP push 没有"连接"概念，端点是否可达由业务侧自测；这里只检查 handler 是否注册
        return handlers.containsKey(config.getIdentifier());
    }

    /**
     * 业务侧 Controller 收到请求后调用本方法触发回调。
     * <p>未注册的 identifier 调用会被忽略并打 warn 日志（便于排查路由错误）。
     *
     * @param identifier 与 start 时的 {@link ConnectorConfig#getIdentifier()} 相同
     * @param message    封装好的 SourceMessage（业务侧 Controller 从 HTTP body+headers 构造）
     * @return true=已触发对应 handler；false=未注册 handler
     */
    public boolean ingest(String identifier, SourceMessage message) {
        Consumer<SourceMessage> handler = handlers.get(identifier);
        if (handler == null) {
            log.warn("[HttpSource] ingest called for unregistered identifier={}, dropped", identifier);
            return false;
        }
        try {
            handler.accept(message);
            return true;
        } catch (Exception e) {
            log.warn("[HttpSource] handler threw identifier={} cause={}", identifier, e.getMessage());
            return false;
        }
    }

    /**
     * 内省：当前已注册多少个 handler。监控 / 调试用。
     */
    public int registeredCount() {
        return handlers.size();
    }
}
