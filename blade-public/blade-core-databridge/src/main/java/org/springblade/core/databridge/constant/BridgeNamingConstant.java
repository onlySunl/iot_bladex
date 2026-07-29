package org.springblade.core.databridge.constant;

/**
 * 桥接 starter 内部命名常量 ── 集中管理默认前缀,业务代码禁止硬编码字面量。
 * <p>所有前缀仅在<b>用户未填对应字段时作为兜底默认值</b>使用,用户值原样透传不做任何替换/清洗。
 *
 * @author mqttsnet
 */
public final class BridgeNamingConstant {

    /**
     * 出站 Sink 通用 client 标识前缀(Kafka clientId / MQTT clientId 等默认值,后接 identifier)。
     */
    public static final String SINK_CLIENT_PREFIX = "iot-bridge-";
    /**
     * RocketMQ Sink producer group 默认前缀(后接 identifier);RocketMQ 字符规约 {@code ^[%|a-zA-Z0-9_-]+$}。
     */
    public static final String ROCKETMQ_PRODUCER_GROUP_PREFIX = "PG_THINGLINKS_BRIDGE_";
    /**
     * MQTT Source(入站订阅)clientId 默认前缀(后接 identifier)。
     */
    public static final String MQTT_SOURCE_CLIENT_PREFIX = "iot-bridge-sub-";
    /**
     * Kafka Source(入站订阅)consumer client.id 前缀(后接 identifier)。
     */
    public static final String KAFKA_SOURCE_CLIENT_PREFIX = "databridge-source-";
    /**
     * Kafka Source poll 线程名前缀(后接 identifier)。
     */
    public static final String KAFKA_SOURCE_THREAD_PREFIX = "databridge-kafka-source-";
    /**
     * RabbitMQ Sink AMQP connection name 前缀(后接 identifier)。
     */
    public static final String RABBITMQ_CONNECTION_PREFIX = "databridge-rabbitmq-";
    /**
     * testConnection 临时 client 标识前缀(后接 timestamp / uuid)。
     */
    public static final String TEST_CONNECTION_PREFIX = "databridge-test-";
    /**
     * JDBC 系 sink(MySQL / TDengine / DM / ClickHouse / KingBase / PostgreSQL 等)HikariCP poolName 前缀。
     */
    public static final String JDBC_POOL_PREFIX = "databridge-";

    private BridgeNamingConstant() {
    }
}
