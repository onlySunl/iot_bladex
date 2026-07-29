package org.springblade.core.databridge.sink.rocketmq;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.RPCHook;

/**
 * RocketMQ 出站 Sink。
 * <p>用 {@code rocketmq-client} 原生 {@link DefaultMQProducer}。同时兼容自建 Apache RocketMQ
 * 与阿里云 RocketMQ ── 阿里云填 accessKey/secretKey 即自动走 {@link AclClientRPCHook} 鉴权。</p>
 *
 * <h3>connectionJson 字段</h3>
 * <pre>{@code
 * {
 *   "nameServer":         "host:9876;host2:9876",  // 必填；阿里云填 endpoint
 *   "topic":              "iot-out",               // 必填
 *   "tag":                "${routingKey}",         // 可空；支持 ${routingKey} 占位符
 *   "producerGroup":      "PG_BRIDGE_12345",       // 可空；缺省按 identifier 拼
 *   "vipChannelEnabled":  false                    // 默认 false；详见下文 VIP 通道说明
 * }
 * }</pre>
 *
 * <h3>VIP 通道(vipChannelEnabled)</h3>
 * <ul>
 *   <li>默认 <b>false</b>(NULL 也按 false 处理)</li>
 *   <li>true 时 producer 走 fastListenPort = brokerPort - 2(默认 10909);
 *       false 时直接走 brokerPort(默认 10911)</li>
 *   <li>RocketMQ 5.x 官方建议关闭 VIP 通道:容器 / NAT 环境下 fastListenPort 经常被漏掉,
 *       producer 卡在 10909 三次握手 → send 全超时</li>
 *   <li>仅当你明确知道 broker 部署环境的 fastListenPort 已正确开放时才置 true</li>
 * </ul>
 *
 * <h3>credentialJson 字段（阿里云）</h3>
 * <pre>{@code { "accessKey": "...", "secretKey": "..." }}</pre>
 *
 * <h3>extraConfigJson 字段</h3>
 * <pre>{@code
 * {
 *   "sendMsgTimeout":            3000,
 *   "retryTimesWhenSendFailed":  2
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
public class RocketmqSink implements Sink {

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.ROCKETMQ;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        // testSink 场景用一次性 producer,绕过 pool 缓存(避免 testConnection 时 topic 未建
        // pool 缓存"无路由"导致后续 send 持续超时);其它场景复用 pool。
        boolean ephemeral = config.getIdentifier() != null
            && config.getIdentifier().startsWith("testSink-");
        DefaultMQProducer producer = null;
        // 详细 timing 拆解,任一步骤异常慢都能从日志直接定位
        long tBuild = 0, tRefresh = 0, tSend = 0;
        // topic 提前到 try 外,catch 内直接读;避免重新 parseConnection 再次抛异常吞掉根因
        String topic = "?";
        try {
            RmqConnConfig conn = parseConnection(config);
            topic = StrUtil.nullToDefault(conn.topic, "?");
            if (StrUtil.isBlank(conn.nameServer) || StrUtil.isBlank(conn.topic)) {
                throw new IllegalArgumentException("[RocketmqSink] missing nameServer or topic");
            }

            long t0 = System.currentTimeMillis();
            producer = ephemeral
                ? buildProducer(config)
                : pool.getOrCreate(config.getIdentifier(), config, this::buildProducer);
            tBuild = System.currentTimeMillis() - t0;

            // 显式触发一次路由拉取(producer.start 后 lazy,跨公网首次 send 路径慢)。
            // 路由 / broker 问题让 RocketMQ client 自己抛 MQClientException,外层 catch 兜底。
            long t1 = System.currentTimeMillis();
            refreshTopicRoute(producer, conn.topic);
            tRefresh = System.currentTimeMillis() - t1;
            logTopicRoute(producer, conn.topic);

            String tag = StrUtil.isBlank(conn.tag)
                ? null
                : conn.tag.replace("${routingKey}", StrUtil.nullToDefault(payload.getRoutingKey(), ""));

            Message message = new Message(conn.topic, tag,
                payload.getRoutingKey(),
                payload.getBody() == null ? new byte[0] : payload.getBody());

            // payload.headers → rocketmq message user properties
            if (CollUtil.isNotEmpty(payload.getHeaders())) {
                payload.getHeaders().forEach((hk, hv) -> {
                    if (hv != null) {
                        message.putUserProperty(hk, hv);
                    }
                });
            }

            // doSend 返回 Map(msgId / attrs),避免本方法直接引用 RocketMQ 的 SendResult
            // (本类的返回类型 org.springblade.core.databridge.model.SendResult 与之同名,
            //  Java 不允许双 import,提到 helper 方法封装)
            long t2 = System.currentTimeMillis();
            Map<String, Object> sentInfo = doSend(producer, message);
            tSend = System.currentTimeMillis() - t2;

            log.info("[RocketmqSink] send ok identifier={} topic={} timing(ms): build={} refresh={} send={}", config.getIdentifier(), conn.topic, tBuild, tRefresh, tSend);

            return SendResult.success(
                (String) sentInfo.get("msgId"),
                System.currentTimeMillis() - start,
                (Map<String, Object>) sentInfo.get("attrs"));
        } catch (Exception e) {
            // 直接透传 RocketMQ 原始错误 ── 不做任何包装。
            // 前端 / 日志看到的就是 client 真实抛出的内容,便于直接对照 RocketMQ FAQ 排查。
            // 拼整个 cause chain ── MQClientException 外层 "Send [N] times, still failed" 经常吞根因,
            // 真凶(RemotingConnectException/RemotingTimeoutException)在 e.getCause() 里。
            String rawMsg = SinkErrors.causeChain(e);
            // 完整 stack trace 进 IDE 控制台(方便复制 / 二次定位 client 内部哪条 socket 失败)
            log.warn("[RocketmqSink] send failed identifier={} topic={} timing(ms): build={} refresh={} send={} cause={}",
                config.getIdentifier(), topic, tBuild, tRefresh, tSend, rawMsg, e);

            // 诊断提示作为辅助日志单独输出 ── 不混入主错误信息,便于运维快速定位常见根因。
            String hint = diagnoseHint(rawMsg, topic);
            if (StrUtil.isNotBlank(hint)) {
                log.warn("[RocketmqSink] send hint topic={}: {}", topic, hint);
            }
            return SendResult.fail(e, System.currentTimeMillis() - start);
        } finally {
            // 一次性 producer 必须 shutdown,否则每次 testSink 都会留下一个 producer 实例
            if (ephemeral && producer != null) {
                try {
                    producer.shutdown();
                } catch (Exception ignore) { /* shutdown 异常不影响外层 */ }
            }
        }
    }

    /**
     * 调 RocketMQ producer.send 并把关键字段提到 Map ── 隔离 RocketMQ 的 SendResult 与
     * 本工程 model.SendResult 同名冲突,主调用方不必使用全限定名;helper 内部用 var
     * 类型推断,也避免内联全限定名。
     */
    private Map<String, Object> doSend(DefaultMQProducer producer, Message message) throws Exception {
        var sr = producer.send(message);
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("queueId", sr.getMessageQueue() == null ? null : sr.getMessageQueue().getQueueId());
        attrs.put("brokerName", sr.getMessageQueue() == null ? null : sr.getMessageQueue().getBrokerName());
        attrs.put("sendStatus", sr.getSendStatus().name());

        Map<String, Object> wrapped = new HashMap<>();
        wrapped.put("msgId", sr.getMsgId());
        wrapped.put("attrs", attrs);
        return wrapped;
    }

    /**
     * 拉一次 topic 路由 + 解析 broker 真实地址 log 出来(纯客观事实,无解读)。
     */
    private void logTopicRoute(DefaultMQProducer producer, String topic) {
        try {
            List<MessageQueue> mqs = producer.fetchPublishMessageQueues(topic);
            if (CollUtil.isEmpty(mqs)) {
                log.warn("[RocketmqSink] topic '{}' has 0 message queues", topic);
                return;
            }
            String brokerName = mqs.get(0).getBrokerName();
            String brokerAddr = resolveBrokerAddress(producer, brokerName);
            log.info("[RocketmqSink] topic '{}' route: queues={}, brokerName={}, brokerAddr={}",
                topic, mqs.size(), brokerName, brokerAddr);
        } catch (Exception e) {
            log.warn("[RocketmqSink] fetchPublishMessageQueues failed for topic '{}': {}",
                topic, e.getMessage());
        }
    }

    /**
     * 通过反射读 RocketMQ client 内部状态,把 NameServer 返回给 producer 的真实 broker
     * 物理地址(ip:port)解出来。反射 API 在 RocketMQ 4.x / 5.x 都稳定。
     * <p>反射失败返回 "(unknown)",不影响主流程。
     */
    private String resolveBrokerAddress(DefaultMQProducer producer, String brokerName) {
        try {
            Object factory = getMQClientFactory(producer);
            if (factory == null) {
                return "(unknown: factory null)";
            }
            Method findBroker = factory.getClass()
                .getMethod("findBrokerAddressInPublish", String.class);
            Object addr = findBroker.invoke(factory, brokerName);
            return addr == null ? "(unknown: broker not in publish table)" : addr.toString();
        } catch (Throwable t) {
            return "(unknown: " + t.getClass().getSimpleName() + ")";
        }
    }

    /**
     * 反射拿 producer 内部的 MQClientInstance(rocketmq-client 的 client factory)。
     * <p>这是 publish/route 各种内部状态的入口对象。reflective API 在 4.x / 5.x 稳定。
     */
    private Object getMQClientFactory(DefaultMQProducer producer) throws Exception {
        Method getImpl = producer.getClass().getDeclaredMethod("getDefaultMQProducerImpl");
        getImpl.setAccessible(true);
        Object impl = getImpl.invoke(producer);
        Method getFactory = impl.getClass().getMethod("getmQClientFactory");
        return getFactory.invoke(impl);
    }

    /**
     * 强制刷新 topic 路由表 + brokerAddrTable。
     * <p>RocketMQ producer 的 {@code fetchPublishMessageQueues} 仅返回 queue 列表,
     * <b>不更新 brokerAddrTable</b>(那是 send 路径或 30s 心跳更新的)。
     * 这导致 testSink 启动一次性 producer 后,主动调 logTopicRoute 显示
     * "broker not in publish table" ── 真实地址尚未被 client 缓存。
     * <p>本方法显式调 {@code MQClientInstance.updateTopicRouteInfoFromNameServer(topic)}
     * 强制同步刷新,使后续 send 立即拿到 broker 地址,并便于诊断。
     *
     * @return true = 路由刷新成功;false = 反射失败或 NameServer 拉取失败
     */
    private boolean refreshTopicRoute(DefaultMQProducer producer, String topic) {
        try {
            Object factory = getMQClientFactory(producer);
            if (factory == null) {
                return false;
            }
            Method update = factory.getClass()
                .getMethod("updateTopicRouteInfoFromNameServer", String.class);
            Object ret = update.invoke(factory, topic);
            return Boolean.TRUE.equals(ret);
        } catch (Throwable t) {
            log.warn("[RocketmqSink] refreshTopicRoute failed topic={} cause={}",
                topic,
                SinkErrors.causeChain(t), t);
            return false;
        }
    }

    /**
     * TCP 探活 broker 地址(ip:port),用 1.5s 超时。
     * <p>用于 testConnection 阶段提前发现 broker.conf {@code brokerIP1} 配错的情况
     * (NameServer 可达 + broker 注册成功,但 broker 暴露的地址 producer 网络层连不上)。
     *
     * @return true = TCP 端口可达;false = 连接拒绝/超时/解析失败
     */
    private boolean tcpPing(String addr, int timeoutMs) {
        if (StrUtil.isBlank(addr) || !addr.contains(":")) {
            return false;
        }
        String[] parts = addr.split(":");
        if (parts.length != 2) {
            return false;
        }
        int port;
        try {
            port = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            return false;
        }
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(parts[0].trim(), port), timeoutMs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据 RocketMQ 原始错误返回诊断提示(纯辅助 log,不参与主错误信息)。
     * <p>区分常见三类:
     * <ul>
     *   <li>topic 不存在 ── No route info / Can not find Message Queue / TOPIC_NOT_EXIST</li>
     *   <li>send 超时 ── sendDefaultImpl call timeout / RemotingTimeoutException</li>
     *   <li>多 broker 重试都失败 ── Send [N] times still failed</li>
     * </ul>
     * 不匹配返回空串,调用方据此决定是否额外 log。
     */
    private String diagnoseHint(String rawMsg, String topic) {
        if (rawMsg == null) {
            return "";
        }
        if (rawMsg.contains("No route info of this topic")
            || rawMsg.contains("Can not find Message Queue for this topic")
            || rawMsg.contains("TOPIC_NOT_EXIST")) {
            return "topic '" + topic + "' 未在 broker 上创建。修复:mqadmin updateTopic -t " + topic;
        }
        if (rawMsg.contains("sendDefaultImpl call timeout")
            || rawMsg.contains("RemotingTimeoutException")) {
            return "send 超时,常见根因:broker.conf 的 brokerIP1 不是 producer 可达地址 " +
                "(典型如 docker 内网 IP 172.x.x.x);用 `mqadmin clusterList` 看 broker 注册的 #Addr 是否对。";
        }
        if (rawMsg.contains("Send [") && rawMsg.contains("times, still failed")) {
            return "broker 多次拒绝,常见根因:topic 路由对了,但 broker 端口未开放 / autoCreateTopicEnable=false " +
                "且 topic 实际未建。优先检查 mqadmin topicList 与防火墙 10911 端口。";
        }
        return "";
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            RmqConnConfig conn = parseConnection(config);
            if (StrUtil.isBlank(conn.nameServer) || StrUtil.isBlank(conn.topic)) {
                return false;
            }
            // producer.start() 成功 = NameServer 连通 + 鉴权通过(若 ACL),
            // 这是连接层连通性的核心判定;buildProducer 内部已 start。
            DefaultMQProducer producer = pool.getOrCreate(
                config.getIdentifier(), config, this::buildProducer);

            try {
                List<MessageQueue> mqs = producer.fetchPublishMessageQueues(conn.topic);

                // ⭐ NameServer 可达 + topic 已创建后,继续验证 broker 网络可达性。
                // 关键:用户常踩坑是 broker.conf 的 brokerIP1 配错 ── NameServer 返回的
                // broker 地址 producer 网络层根本连不上。这种情况下 fetchPublishMessageQueues
                // 会成功(只问 NameServer),但 send 必然 timeout。
                // 这里主动刷路由 + TCP ping broker,提前暴露,而不是让用户在 testSink 才发现。
                if (CollUtil.isNotEmpty(mqs)) {
                    refreshTopicRoute(producer, conn.topic);
                    String brokerName = mqs.get(0).getBrokerName();
                    String brokerAddr = resolveBrokerAddress(producer, brokerName);
                    if (brokerAddr != null && brokerAddr.contains(":")
                        && !brokerAddr.startsWith("(unknown")) {
                        boolean reachable = tcpPing(brokerAddr, 1500);
                        if (!reachable) {
                            log.warn("[RocketmqSink] testConnection failed identifier={} ns={} topic={} " +
                                    "brokerName={} brokerAddr={} cause=tcp unreachable",
                                config.getIdentifier(), conn.nameServer, conn.topic, brokerName, brokerAddr);
                            // hint(辅助提示,与主结果分离)
                            log.warn("[RocketmqSink] testConnection hint: broker registered addr '{}' " +
                                "is unreachable from this host. Likely broker.conf brokerIP1 is wrong " +
                                "(e.g. docker internal IP).", brokerAddr);
                            return false;
                        }
                        log.info("[RocketmqSink] testConnection passed identifier={} ns={} topic={} " +
                                "brokerName={} brokerAddr={} tcp=ok",
                            config.getIdentifier(), conn.nameServer, conn.topic, brokerName, brokerAddr);
                    } else {
                        log.info("[RocketmqSink] testConnection passed identifier={} ns={} topic={} " +
                                "brokerAddr={}",
                            config.getIdentifier(), conn.nameServer, conn.topic, brokerAddr);
                    }
                }
                return true;
            } catch (Exception topicEx) {
                String msg = topicEx.getMessage() == null ? "" : topicEx.getMessage();
                if (msg.contains("Can not find Message Queue for this topic")
                    || msg.contains("No route info of this topic")
                    || msg.contains("TOPIC_NOT_EXIST")) {
                    log.info("[RocketmqSink] testConnection passed identifier={} ns={} topic={} " +
                            "(NameServer reachable, topic not yet created on broker)",
                        config.getIdentifier(), conn.nameServer, conn.topic);
                    return true;
                }
                log.warn("[RocketmqSink] testConnection failed identifier={} cause={}",
                    config.getIdentifier(), msg);
                return false;
            }
        } catch (Exception e) {
            log.warn("[RocketmqSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    // ============================== 内部：build Producer ==============================

    private DefaultMQProducer buildProducer(ConnectorConfig config) {
        RmqConnConfig conn = parseConnection(config);
        RmqCredConfig cred = parseCredential(config);
        RmqExtraConfig extra = parseExtra(config);

        String group = StrUtil.isBlank(conn.producerGroup)
            ? BridgeNamingConstant.ROCKETMQ_PRODUCER_GROUP_PREFIX + config.getIdentifier()
            : conn.producerGroup;

        DefaultMQProducer producer;
        if (StrUtil.isNotBlank(cred.accessKey) && StrUtil.isNotBlank(cred.secretKey)) {
            // 阿里云 / ACL 自建场景
            RPCHook hook = new AclClientRPCHook(new SessionCredentials(cred.accessKey, cred.secretKey));
            producer = new DefaultMQProducer(group, hook);
        } else {
            producer = new DefaultMQProducer(group);
        }
        producer.setNamesrvAddr(conn.nameServer);
        // sendMsgTimeout:testSink 给 15s 宽松值兼容跨公网测试;production 默认 10s
        // 覆盖跨公网 / pool eviction 后重建首次启动。用户可用 extra.sendMsgTimeout 覆盖。
        boolean isTestSink = config.getIdentifier() != null
            && config.getIdentifier().startsWith("testSink-");
        int defaultTimeout = isTestSink ? 15000 : 10000;
        producer.setSendMsgTimeout(extra.sendMsgTimeout == null ? defaultTimeout : extra.sendMsgTimeout);
        producer.setRetryTimesWhenSendFailed(extra.retryTimesWhenSendFailed == null ? 2 : extra.retryTimesWhenSendFailed);
        // mqClientApiTimeout:跟 sendMsgTimeout 对齐,避免"上层 send 给了 10s
        // 但底层 netty RPC 3s 就放弃"造成诡异 RemotingTimeoutException。
        producer.setMqClientApiTimeout(extra.sendMsgTimeout == null ? defaultTimeout : extra.sendMsgTimeout);
        // VIP 通道:从连接参数 conn.vipChannelEnabled 读取(用户在表单可视化配置)。
        // 默认 false ── RocketMQ 5.x 官方已不推荐 VIP 通道(broker fastListenPort 在容器/云
        // 环境下经常被防火墙或 NAT 漏掉,造成"NameServer 路由 OK + send 全部超时"的诡异现象)。
        producer.setVipChannelEnabled(Boolean.TRUE.equals(conn.vipChannelEnabled));
        try {
            producer.start();
            log.info("[RocketmqSink] producer started identifier={} ns={} topic={}",
                config.getIdentifier(), conn.nameServer, conn.topic);
            return producer;
        } catch (Exception e) {
            // 不加前缀包装,e.getMessage() 透传 raw cause(MQClientException 含 ResponseCode + 原始描述)
            throw new RuntimeException(e);
        }
    }

    private RmqConnConfig parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new RmqConnConfig()
            : JsonUtil.parse(config.getConnectionJson(), RmqConnConfig.class);
    }

    private RmqCredConfig parseCredential(ConnectorConfig config) {
        return StrUtil.isBlank(config.getCredentialJson())
            ? new RmqCredConfig()
            : JsonUtil.parse(config.getCredentialJson(), RmqCredConfig.class);
    }

    private RmqExtraConfig parseExtra(ConnectorConfig config) {
        return StrUtil.isBlank(config.getExtraConfigJson())
            ? new RmqExtraConfig()
            : JsonUtil.parse(config.getExtraConfigJson(), RmqExtraConfig.class);
    }

    public static class RmqConnConfig {
        public String nameServer;
        public String topic;
        public String tag;
        public String producerGroup;
        /**
         * 是否启用 VIP 通道(走 fastListenPort = brokerPort - 2,默认 10909)。
         * <p>
         * <b>默认 false</b>(若 NULL 也按 false 处理)。
         * RocketMQ 5.x 官方建议关闭 VIP 通道:
         * <ul>
         *   <li>容器/云环境下 fastListenPort 经常被 NAT 漏掉,producer 卡在 10909
         *       三次握手 → send 超时</li>
         *   <li>NameServer 路由只返回 brokerPort(默认 10911),Dashboard / mqadmin 也走 10911</li>
         *   <li>主端口 10911 已能承载所有功能,VIP 通道是历史包袱</li>
         * </ul>
         * 仅当你明确知道 broker 部署环境的 fastListenPort 配置且开放时才置 true。
         */
        public Boolean vipChannelEnabled;
    }

    public static class RmqCredConfig {
        public String accessKey;
        public String secretKey;
    }

    public static class RmqExtraConfig {
        public Integer sendMsgTimeout;
        public Integer retryTimesWhenSendFailed;
    }
}
