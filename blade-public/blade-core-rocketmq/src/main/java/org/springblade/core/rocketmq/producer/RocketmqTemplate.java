package org.springblade.core.rocketmq.producer;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.rocketmq.listener.MessageHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Thinglinks RocketMQ Producer 适配器（Adapter Pattern）。
 * <p>
 * 业务侧 Producer 的<b>唯一入口</b>：包装原生 {@link RocketMQTemplate}，发送前自动把
 * {@link ContextUtil#getLocalMap()} 整体序列化为 JSON 字符串，注入到 RocketMQ 消息头
 * {@link MessageHeaders#LOCAL_MAP}。Consumer 端 {@code AbstractTenantAwareRocketmqListener}
 * 反序列化恢复，保证 traceId / tenantId / userId / tenantBasePoolName 等业务上下文
 * 在异步消费线程里依然可用、切库依然正确。
 * </p>
 *
 * <h3>跨厂商兼容（Apache RocketMQ + 阿里云 RocketMQ）</h3>
 * <ul>
 *   <li><b>默认</b>自建 Apache RocketMQ 5.x（{@code access-channel: LOCAL}）。</li>
 *   <li>切阿里云：仅在 nacos {@code rocketmq.yml} 把 {@code ROCKETMQ_ACCESS_CHANNEL=CLOUD}
 *       并填上 {@code namespace / access-key / secret-key}，<b>本类代码零改动</b>。</li>
 *   <li>本类只调 {@link RocketMQTemplate} 原生 API，框架根据 access-channel 自动选择
 *       {@code DefaultMQProducer}（自建）或 {@code ONSProducer}（阿里云），调用方无感知。</li>
 * </ul>
 *
 * <h3>设计原则</h3>
 * <ol>
 *   <li><b>整体传 LocalMap</b>：单 header 包揽全部上下文，未来 ContextUtil 增字段
 *       0 改 starter / 0 改业务（详见 {@link MessageHeaders#LOCAL_MAP} 类注释）。</li>
 *   <li><b>体积保护</b>：序列化结果超过 {@link MessageHeaders#MAX_LOCAL_MAP_SIZE_BYTES}
 *       仅打 warn 日志不阻断发送（异常情况业务侧自行修）。</li>
 *   <li><b>序列化失败容忍</b>：JSON 序列化抛异常时降级为 {@code "{}"} 空 header，
 *       不影响主消息发送（上下文丢了下游会自然恢复成默认值）。</li>
 *   <li><b>逃生通道</b>：{@link #getRaw()} 暴露原生 Template，覆盖事务消息 / 批量消息
 *       等本类未包装的高级场景。</li>
 * </ol>
 *
 * <h3>常见用法</h3>
 * <pre>{@code
 * @Autowired
 * private RocketmqTemplate rocketmqTemplate;
 *
 * // 同步发送（destination 格式 "topic" 或 "topic:tag"）
 * SendResult sr = rocketmqTemplate.syncSend(
 *     RocketmqTopicConstant.Bridge.DEVICE_EVENT + ":" + actionType.name(),
 *     envelope);
 *
 * // 异步发送
 * rocketmqTemplate.asyncSend(topic, payload, new SendCallback() {
 *     public void onSuccess(SendResult r) { ... }
 *     public void onException(Throwable e) { ... }
 * });
 *
 * // 顺序发送（按 hashKey 路由到固定队列）
 * rocketmqTemplate.syncSendOrderly(topic, payload, deviceIdentification);
 *
 * // 延时消息（delayLevel 1-18：1s/5s/10s/30s/1m/2m/3m/4m/5m/6m/7m/8m/9m/10m/20m/30m/1h/2h）
 * rocketmqTemplate.syncSendDelay(topic, payload, 3);   // 10 秒后投递
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class RocketmqTemplate {

    /**
     * 原生 RocketMQTemplate 引用。
     * <p>由 {@code rocketmq-spring-boot-starter} 自动装配；本类只通过其公开 API 发送，
     * 不感知底层是 {@code DefaultMQProducer}（Apache）还是 {@code ONSProducer}（阿里云）。
     */
    private final RocketMQTemplate raw;

    // ============================== 同步发送 ==============================

    /**
     * 同步发送（最常用）。
     *
     * @param destination 目标，格式 {@code topic} 或 {@code topic:tag}
     * @param payload     业务对象（自动 JSON 序列化为 RocketMQ 消息体）
     * @return 发送结果
     */
    public SendResult syncSend(String destination, Object payload) {
        return raw.syncSend(destination, wrap(payload));
    }

    /**
     * 同步发送（自定义超时）。
     */
    public SendResult syncSend(String destination, Object payload, long timeout) {
        return raw.syncSend(destination, wrap(payload), timeout);
    }

    /**
     * 同步延时发送。
     *
     * @param delayLevel RocketMQ 内置延时级别（1-18）：
     *                   1=1s 2=5s 3=10s 4=30s 5=1m 6=2m 7=3m 8=4m 9=5m
     *                   10=6m 11=7m 12=8m 13=9m 14=10m 15=20m 16=30m 17=1h 18=2h
     */
    public SendResult syncSendDelay(String destination, Object payload, int delayLevel) {
        return raw.syncSend(destination, wrap(payload),
            raw.getProducer().getSendMsgTimeout(), delayLevel);
    }

    // ============================== 异步发送 ==============================

    /**
     * 异步发送。
     */
    public void asyncSend(String destination, Object payload, SendCallback sendCallback) {
        raw.asyncSend(destination, wrap(payload), sendCallback);
    }

    /**
     * 异步发送（自定义超时）。
     */
    public void asyncSend(String destination, Object payload, SendCallback sendCallback, long timeout) {
        raw.asyncSend(destination, wrap(payload), sendCallback, timeout);
    }

    /**
     * 异步延时发送。
     */
    public void asyncSendDelay(String destination, Object payload, SendCallback sendCallback, int delayLevel) {
        raw.asyncSend(destination, wrap(payload), sendCallback,
            raw.getProducer().getSendMsgTimeout(), delayLevel);
    }

    // ============================== 单向发送（fire-and-forget）==============================

    /**
     * 单向发送：最高性能，但不可靠（无 ack）。
     * <p>仅用于日志类、可丢失的统计上报；桥接业务必须用 sync/async 保证可靠性。
     */
    public void sendOneWay(String destination, Object payload) {
        raw.sendOneWay(destination, wrap(payload));
    }

    // ============================== 顺序发送（hashKey 路由固定队列）==============================

    /**
     * 同步顺序发送：保证同一 hashKey 的消息进入同一 MessageQueue，消费时按发送顺序处理。
     *
     * @param hashKey 路由键（一般用 deviceId / orderId 等业务唯一标识）
     */
    public SendResult syncSendOrderly(String destination, Object payload, String hashKey) {
        return raw.syncSendOrderly(destination, wrap(payload), hashKey);
    }

    public SendResult syncSendOrderly(String destination, Object payload, String hashKey, long timeout) {
        return raw.syncSendOrderly(destination, wrap(payload), hashKey, timeout);
    }

    public void asyncSendOrderly(String destination, Object payload, String hashKey, SendCallback sendCallback) {
        raw.asyncSendOrderly(destination, wrap(payload), hashKey, sendCallback);
    }

    public void asyncSendOrderly(String destination, Object payload, String hashKey, SendCallback sendCallback, long timeout) {
        raw.asyncSendOrderly(destination, wrap(payload), hashKey, sendCallback, timeout);
    }

    public void sendOneWayOrderly(String destination, Object payload, String hashKey) {
        raw.sendOneWayOrderly(destination, wrap(payload), hashKey);
    }

    // ============================== 逃生通道 ==============================

    /**
     * 取原生 Template，用于本类未包装的高级场景：
     * <ul>
     *   <li>事务消息 {@code RocketMQTemplate.sendMessageInTransaction(...)}</li>
     *   <li>批量消息 {@code syncSend(String, Collection<Message>)}</li>
     *   <li>自定义 {@link Message} headers 后由调用方手工 send（此时如需上下文透传，
     *       请自行 {@link #buildContextHeaderMessage(Object)} 构造或在 message 上 setHeader
     *       {@link MessageHeaders#LOCAL_MAP}）</li>
     * </ul>
     * <p>⚠️ 直接走 raw 发送<b>不会自动注入 LocalMap header</b>；若需透传上下文，请用
     * {@link #buildContextHeaderMessage(Object)} 先构造再发送。
     */
    public RocketMQTemplate getRaw() {
        return raw;
    }

    /**
     * 构造一条已注入 LocalMap header 的 Spring Message，供逃生通道场景使用。
     * <p>典型场景：业务侧需要在 Message 上塞业务自定义 header，又想保留上下文透传。
     */
    public Message<?> buildContextHeaderMessage(Object payload) {
        return wrap(payload);
    }

    // ============================== 内部：构建带 header 的 Message ==============================

    /**
     * 把 payload 封装成 {@link Message}，注入 LocalMap JSON header。
     * <ul>
     *   <li>payload 已是 {@link Message} → 复用其原 headers，追加 LocalMap header</li>
     *   <li>否则 → 直接 build 新 Message（仅含 LocalMap header）</li>
     * </ul>
     */
    private Message<?> wrap(Object payload) {
        String localMapJson = serializeLocalMap();
        if (payload instanceof Message) {
            Message<?> origin = (Message<?>) payload;
            return MessageBuilder.fromMessage(origin)
                .setHeader(MessageHeaders.LOCAL_MAP, localMapJson)
                .build();
        }
        return MessageBuilder.withPayload(payload)
            .setHeader(MessageHeaders.LOCAL_MAP, localMapJson)
            .build();
    }

    /**
     * 把 ContextUtil.getLocalMap() 序列化为 JSON 字符串。
     * <ul>
     *   <li>map 为空 → {@code "{}"}</li>
     *   <li>序列化失败 → {@code "{}"} + warn 日志（不阻断发送）</li>
     *   <li>体积超 {@link MessageHeaders#MAX_LOCAL_MAP_SIZE_BYTES} → warn 日志</li>
     * </ul>
     */
    private String serializeLocalMap() {
        Map<String, String> ctx;
        try {
            ctx = ContextUtil.getLocalMap();
        } catch (Throwable t) {
            log.warn("[rocketmq] read ContextUtil.getLocalMap() failed, "
                + "header will be empty: {}", t.getMessage());
            return StrUtil.EMPTY_JSON;
        }
        if (CollUtil.isEmpty(ctx)) {
            return StrUtil.EMPTY_JSON;
        }
        // 防御性 copy：避免序列化期间被其它线程修改（ThreadLocal 一般不会，留个保险）
        Map<String, String> snapshot = new HashMap<>(ctx);
        String json;
        try {
            json = JsonUtil.toJson(snapshot);
        } catch (Throwable t) {
            log.warn("[rocketmq] serialize ContextUtil.getLocalMap() failed, "
                + "header will be empty: {}", t.getMessage());
            return StrUtil.EMPTY_JSON;
        }
        if (json == null) {
            return StrUtil.EMPTY_JSON;
        }
        // 体积保护：仅 warn，不阻断
        int byteSize = json.getBytes(StandardCharsets.UTF_8).length;
        if (byteSize > MessageHeaders.MAX_LOCAL_MAP_SIZE_BYTES) {
            log.warn("[rocketmq] LocalMap header size {} bytes exceeds threshold {} bytes, "
                    + "consider trimming ContextUtil.getLocalMap() (keys={})",
                byteSize, MessageHeaders.MAX_LOCAL_MAP_SIZE_BYTES, snapshot.keySet());
        }
        return json;
    }
}
