package org.springblade.core.databridge.autoconfigure;

import java.util.List;

import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.registry.ConnectorRegistry;
import org.springblade.core.databridge.serializer.AvroSerializer;
import org.springblade.core.databridge.serializer.BinarySerializer;
import org.springblade.core.databridge.serializer.JsonSerializer;
import org.springblade.core.databridge.serializer.StringSerializer;
import org.springblade.core.databridge.sink.clickhouse.ClickHouseSink;
import org.springblade.core.databridge.sink.dm.DmSink;
import org.springblade.core.databridge.sink.http.HttpSink;
import org.springblade.core.databridge.sink.influxdb.InfluxDbSink;
import org.springblade.core.databridge.sink.iotdb.IoTDbSink;
import org.springblade.core.databridge.sink.kafka.KafkaSink;
import org.springblade.core.databridge.sink.kingbase.KingBaseSink;
import org.springblade.core.databridge.sink.mongodb.MongoDbSink;
import org.springblade.core.databridge.sink.mqtt.MqttSink;
import org.springblade.core.databridge.sink.mysql.MysqlSink;
import org.springblade.core.databridge.sink.postgresql.PostgreSqlSink;
import org.springblade.core.databridge.sink.pulsar.PulsarSink;
import org.springblade.core.databridge.sink.rabbitmq.RabbitmqSink;
import org.springblade.core.databridge.sink.redis.RedisSink;
import org.springblade.core.databridge.sink.rocketmq.RocketmqSink;
import org.springblade.core.databridge.sink.tdengine.TDengineSink;
import org.springblade.core.databridge.sink.webhook.WebhookSink;
import org.springblade.core.databridge.source.http.HttpSource;
import org.springblade.core.databridge.source.kafka.KafkaSource;
import org.springblade.core.databridge.source.mqtt.MqttSource;
import org.springblade.core.databridge.spi.Serializer;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.Source;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * Thinglinks Databridge Starter 自动配置入口。
 * <p>
 * 业务侧服务（rule-biz / mqs-biz 等）只需在 pom 引入本 starter，
 * 启动时本类会装配以下 Bean：
 * </p>
 * <ul>
 *   <li>{@link ConnectionPoolManager}：通用连接池（任意 String key Caffeine 缓存）</li>
 *   <li>4 个 {@link Serializer}：JSON / STRING / BINARY / AVRO</li>
 *   <li>8 个 {@link Sink}：Kafka / Redis / RocketMQ / RabbitMQ / MySQL / HTTP / WebHook / MQTT</li>
 *   <li>3 个 {@link Source}：Kafka / MQTT / HTTP</li>
 *   <li>{@link ConnectorRegistry}：聚合上述 Sink/Source/Serializer 的 O(1) 查询表</li>
 * </ul>
 *
 * <h3>禁用方式</h3>
 * <p>设置 {@code blade.databridge.enabled=false}（缺省 true）可一键禁用整个 starter。
 *
 * <h3>定制扩展</h3>
 * 全部 Bean 都用 {@link ConditionalOnMissingBean}：业务侧若需替换默认实现（如自定义 KafkaSink），
 * 在自己的 @Configuration 里声明同类型 Bean 即可覆盖，本 starter 自动让位（OCP 闭环）。
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
@AutoConfiguration
@ConditionalOnProperty(prefix = "blade.databridge", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DatabridgeAutoConfiguration {

    public DatabridgeAutoConfiguration() {
        log.info("[Databridge] starter loading: 8 sinks + 3 sources + 4 serializers + pool + registry");
    }

    // ============================== 基础设施 ==============================

    @Bean
    @ConditionalOnMissingBean
    public ConnectionPoolManager databridgeConnectionPoolManager() {
        return new ConnectionPoolManager();
    }

    // ============================== Serializer × 4 ==============================

    @Bean
    @ConditionalOnMissingBean(name = "jsonSerializer")
    public JsonSerializer jsonSerializer() {
        return new JsonSerializer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "stringSerializer")
    public StringSerializer stringSerializer() {
        return new StringSerializer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "binarySerializer")
    public BinarySerializer binarySerializer() {
        return new BinarySerializer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "avroSerializer")
    public AvroSerializer avroSerializer() {
        return new AvroSerializer();
    }

    // ============================== Sink × 7 ==============================

    @Bean
    @ConditionalOnMissingBean(name = "kafkaSink")
    public KafkaSink kafkaSink(ConnectionPoolManager pool) {
        return new KafkaSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisSink")
    public RedisSink redisSink(ConnectionPoolManager pool) {
        return new RedisSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "rocketmqSink")
    public RocketmqSink rocketmqSink(ConnectionPoolManager pool) {
        return new RocketmqSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "rabbitmqSink")
    public RabbitmqSink rabbitmqSink(ConnectionPoolManager pool) {
        return new RabbitmqSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "mysqlSink")
    public MysqlSink mysqlSink(ConnectionPoolManager pool) {
        return new MysqlSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "httpSink")
    public HttpSink httpSink(ConnectionPoolManager pool) {
        return new HttpSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "webhookSink")
    public WebhookSink webhookSink(ConnectionPoolManager pool) {
        return new WebhookSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "mqttSink")
    public MqttSink mqttSink(ConnectionPoolManager pool) {
        return new MqttSink(pool);
    }

    // ============================== Sink v2 扩展 × 7（按驱动 ConditionalOnClass 装配，缺驱动时 Bean 不注册） ==============================

    @Bean
    @ConditionalOnMissingBean(name = "postgreSqlSink")
    @ConditionalOnClass(name = "org.postgresql.Driver")
    public PostgreSqlSink postgreSqlSink(ConnectionPoolManager pool) {
        return new PostgreSqlSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "clickHouseSink")
    @ConditionalOnClass(name = "com.clickhouse.jdbc.ClickHouseDriver")
    public ClickHouseSink clickHouseSink(ConnectionPoolManager pool) {
        return new ClickHouseSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "tDengineSink")
    @ConditionalOnClass(name = "com.taosdata.jdbc.rs.RestfulDriver")
    public TDengineSink tDengineSink(ConnectionPoolManager pool) {
        return new TDengineSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "ioTDbSink")
    @ConditionalOnClass(name = "org.apache.iotdb.jdbc.IoTDBDriver")
    public IoTDbSink ioTDbSink(ConnectionPoolManager pool) {
        return new IoTDbSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "mongoDbSink")
    @ConditionalOnClass(name = "com.mongodb.client.MongoClients")
    public MongoDbSink mongoDbSink(ConnectionPoolManager pool) {
        return new MongoDbSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "influxDbSink")
    @ConditionalOnClass(name = "com.influxdb.client.InfluxDBClientFactory")
    public InfluxDbSink influxDbSink(ConnectionPoolManager pool) {
        return new InfluxDbSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "pulsarSink")
    @ConditionalOnClass(name = "org.apache.pulsar.client.api.PulsarClient")
    public PulsarSink pulsarSink(ConnectionPoolManager pool) {
        return new PulsarSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "dmSink")
    @ConditionalOnClass(name = "dm.jdbc.driver.DmDriver")
    public DmSink dmSink(ConnectionPoolManager pool) {
        return new DmSink(pool);
    }

    @Bean
    @ConditionalOnMissingBean(name = "kingBaseSink")
    @ConditionalOnClass(name = "com.kingbase8.Driver")
    public KingBaseSink kingBaseSink(ConnectionPoolManager pool) {
        return new KingBaseSink(pool);
    }

    // ============================== Source × 3 ==============================

    @Bean
    @ConditionalOnMissingBean(name = "kafkaSource")
    public KafkaSource kafkaSource() {
        return new KafkaSource();
    }

    @Bean
    @ConditionalOnMissingBean(name = "mqttSource")
    public MqttSource mqttSource() {
        return new MqttSource();
    }

    @Bean
    @ConditionalOnMissingBean(name = "httpSource")
    public HttpSource httpSource() {
        return new HttpSource();
    }

    // ============================== Registry（聚合）==============================

    @Bean
    @ConditionalOnMissingBean
    public ConnectorRegistry connectorRegistry(List<Sink> sinks,
                                               List<Source> sources,
                                               List<Serializer> serializers) {
        return new ConnectorRegistry(sinks, sources, serializers);
    }
}
