package org.springblade.core.databridge.model;

/**
 * 数据桥接连接器协议类型枚举。
 * <p>
 * 本枚举是 {@code blade-core-databridge} 对外宣称<b>本 starter 支持哪些协议</b>的清单，
 * 既用于 {@link ConnectorConfig#getType()} 描述配置归属哪种协议，也用于
 * {@code ConnectorRegistry} 把请求路由到对应的 {@code Sink} / {@code Source} 实现。
 * </p>
 *
 * <h3>设计原则（OCP 扩展点）</h3>
 * <ul>
 *   <li><b>新增协议</b>：在本枚举加新值 + 在 {@code sink/} 或 {@code source/} 包加对应实现，
 *       业务侧 0 改动（DataSource.sourceType 字典加一项即可）。</li>
 *   <li><b>命名规约</b>：全大写下划线，与业务侧 {@code def_dict.BRIDGE_DATA_SOURCE_TYPE}
 *       字典子项 1:1 对齐（用 {@link #valueOf(String)} 反查）。</li>
 *   <li><b>不带业务前缀</b>：本枚举不叫 {@code BridgeXxx}，因为 starter 不感知"桥接"业务概念，
 *       别的产品复用本 starter 时枚举语义依然成立。</li>
 * </ul>
 *
 * <h3>当前覆盖</h3>
 * <table>
 *   <tr><th>枚举值</th><th>Sink 支持</th><th>Source 支持</th><th>典型场景</th></tr>
 *   <tr><td>{@link #KAFKA}</td><td>✅</td><td>✅</td><td>消息总线 / 数据集成</td></tr>
 *   <tr><td>{@link #REDIS}</td><td>✅</td><td>—</td><td>缓存 / 流式队列（Stream/PubSub）</td></tr>
 *   <tr><td>{@link #ROCKETMQ}</td><td>✅</td><td>—</td><td>跨系统异步消息（自建 Apache + 阿里云）</td></tr>
 *   <tr><td>{@link #RABBITMQ}</td><td>✅</td><td>—</td><td>外部 RabbitMQ 集成（按 exchange 路由 routingKey）</td></tr>
 *   <tr><td>{@link #MYSQL}</td><td>✅</td><td>—</td><td>业务库直写</td></tr>
 *   <tr><td>{@link #HTTP}</td><td>✅</td><td>✅</td><td>RESTful API 集成</td></tr>
 *   <tr><td>{@link #WEBHOOK}</td><td>✅</td><td>—</td><td>HTTP + HMAC 签名（防伪 / 防重放）</td></tr>
 *   <tr><td>{@link #MQTT}</td><td>✅</td><td>✅</td><td>外部 IoT broker 互联</td></tr>
 * </table>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public enum ConnectorType {

    /**
     * Apache Kafka（kafka-clients 直连，不依赖 spring-kafka 自动装配）。
     */
    KAFKA,

    /**
     * Redis（Lettuce 直连；支持 STANDALONE / SENTINEL / CLUSTER 模式；写命令 LPUSH/RPUSH/XADD/PUBLISH/SET）。
     */
    REDIS,

    /**
     * Apache RocketMQ（rocketmq-client 5.x；自建 / 阿里云通过 access-channel 切换）。
     */
    ROCKETMQ,

    /**
     * RabbitMQ（amqp-client 5.x 直连；按 exchange + routingKey 路由；支持 direct/fanout/topic/headers）。
     */
    RABBITMQ,

    /**
     * MySQL（jdbc + HikariCP 连接池；按表 column 映射 INSERT / UPDATE / IGNORE）。
     */
    MYSQL,

    /**
     * 通用 HTTP（OkHttp 同步客户端；POST/PUT/PATCH 推送 JSON / 表单）。
     */
    HTTP,

    /**
     * WebHook（HTTP + HMAC-SHA256/512 签名 + 时间戳防重放）。
     */
    WEBHOOK,

    /**
     * 外部 MQTT broker（Eclipse Paho v3 客户端；topic 模板 + QoS 0/1/2）。
     */
    MQTT,

    // ====================================================================================
    // 时序数据库 / OLAP / NoSQL / 流平台扩展（v2 加入；驱动以 optional 依赖引入）
    // ====================================================================================

    /**
     * TDengine（taos-jdbcdriver；IoT 时序首选，原生超表 / 子表）。
     */
    TDENGINE,

    /**
     * ClickHouse（clickhouse-jdbc；列存 OLAP，写实时大宽表）。
     */
    CLICKHOUSE,

    /**
     * InfluxDB（v2 Java client + Line Protocol；时序 OSS 主流）。
     */
    INFLUXDB,

    /**
     * Apache IoTDB（jdbc / session SDK；Apache 顶级时序，边缘 + 云）。
     */
    IOTDB,

    /**
     * PostgreSQL（jdbc + HikariCP；通用 OLTP，比 MySQL 功能更全）。
     */
    POSTGRESQL,

    /**
     * MongoDB（mongodb-driver-sync；灵活 schema，存原始 JSON / 复杂业务对象）。
     */
    MONGODB,

    /**
     * Apache Pulsar（pulsar-client；Kafka 替代品，多租户原生 + tiered storage）。
     */
    PULSAR,

    // ====================================================================================
    // 国产数据库扩展（v2 增量；驱动以 optional 依赖引入，复用 AbstractJdbcSink）
    // ====================================================================================

    /**
     * 达梦数据库 DM（DmJdbcDriver；国产关系型 OLTP，金融 / 政企首选）。
     */
    DM,

    /**
     * 人大金仓 KingBase（kingbase8 driver；国产 OLTP，PG 兼容生态）。
     */
    KINGBASE;
}
