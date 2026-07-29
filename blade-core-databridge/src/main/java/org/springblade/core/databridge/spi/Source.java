package org.springblade.core.databridge.spi;

import java.util.function.Consumer;

import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SourceMessage;

/**
 * 数据入站 Source 接口（Strategy Pattern）。
 * <p>
 * 与 {@link Sink} 对称，本接口定义了"从外部系统拉/收消息并回调给业务侧"的统一动作。
 * 当前覆盖 Kafka（pull）/ MQTT（subscribe）/ HTTP（push 入口）三种模式。
 * </p>
 *
 * <h3>OCP 边界</h3>
 * <ul>
 *   <li>仅产出通用 {@link SourceMessage}（byte[] + Map + 雪花 ID），不感知业务字段</li>
 *   <li>实现侧不做字段映射 / 业务路由 ── 那是业务侧 SubscriptionSourceManager 的职责</li>
 *   <li>新增协议 = 加一个 Source 实现 + ConnectorType 枚举值</li>
 * </ul>
 *
 * <h3>生命周期 & 线程模型</h3>
 * <ul>
 *   <li>{@link #start(ConnectorConfig, Consumer)} 启动后台拉取/订阅线程，立即返回（不阻塞调用方）</li>
 *   <li>handler 回调可能在<b>非业务请求线程</b>触发：业务侧需自行处理 ContextUtil 上下文（一般在 handler 内部用 try/finally 清理）</li>
 *   <li>{@link #stop(String)} 必须能优雅关闭（断开连接 / commit offset / 等待 in-flight 消息消费完）</li>
 *   <li>实现内部应维护 {@code Map<identifier, ClientResource>}，支持多个配置同时拉取</li>
 * </ul>
 *
 * <h3>实现样例（Kafka pull 模式）</h3>
 * <pre>{@code
 * @Component
 * public class KafkaSource implements Source {
 *     private final Map<String, KafkaConsumerThread> running = new ConcurrentHashMap<>();
 *
 *     @Override public ConnectorType supports() { return ConnectorType.KAFKA; }
 *
 *     @Override
 *     public void start(ConnectorConfig config, Consumer<SourceMessage> handler) {
 *         String id = config.getIdentifier();
 *         if (running.containsKey(id)) return;  // 幂等
 *
 *         KafkaConsumer<byte[], byte[]> consumer = buildConsumer(config);
 *         consumer.subscribe(parseTopics(config));
 *
 *         KafkaConsumerThread thread = new KafkaConsumerThread(id, consumer, handler);
 *         thread.start();
 *         running.put(id, thread);
 *     }
 *
 *     @Override
 *     public void stop(String identifier) {
 *         KafkaConsumerThread t = running.remove(identifier);
 *         if (t != null) t.shutdown();
 *     }
 *
 *     @Override public boolean testConnection(ConnectorConfig config) { ... }
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public interface Source {

    /**
     * 本 Source 支持的协议类型。
     */
    ConnectorType supports();

    /**
     * 启动拉取/订阅。立即返回（不阻塞），后台异步驱动 handler 回调。
     * <p><b>实现要求</b>：
     * <ul>
     *   <li><b>幂等</b>：同一 {@code identifier} 重复 start 应该 no-op 或重启已有任务</li>
     *   <li><b>异步</b>：方法应在毫秒级返回；建立连接 / 订阅 topic 等动作放后台线程</li>
     *   <li><b>handler 调用</b>：每收到一条消息封装成 {@link SourceMessage}（sourceMessageId 默认雪花，
     *       但 Kafka/MQTT 等有原生 ID 时显式覆盖）后调 {@code handler.accept(msg)}；
     *       handler 抛异常应捕获 + warn 日志，不能让单条消息异常中断 source</li>
     *   <li><b>错误重连</b>：网络断开等 transient 错误自动重试（指数退避）</li>
     * </ul>
     *
     * @param config  连接配置
     * @param handler 业务侧消费回调；本接口不约束 handler 的执行线程
     */
    void start(ConnectorConfig config, Consumer<SourceMessage> handler);

    /**
     * 停止指定 identifier 对应的 Source。
     * <p><b>实现要求</b>：
     * <ul>
     *   <li><b>幂等</b>：未 start 或已 stop 的 identifier 调用应是 no-op</li>
     *   <li><b>优雅</b>：等待 in-flight 消息处理完 / commit offset / 关连接，超时强制断开</li>
     * </ul>
     *
     * @param identifier 与 start 时 {@code config.getIdentifier()} 相同的标识
     */
    void stop(String identifier);

    /**
     * 测试连接（同 {@link Sink#testConnection(ConnectorConfig)}）。
     */
    boolean testConnection(ConnectorConfig config);
}
