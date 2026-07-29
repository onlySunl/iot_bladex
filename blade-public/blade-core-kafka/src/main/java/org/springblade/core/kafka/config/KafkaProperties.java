package org.springblade.core.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Kafka 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.kafka")
public class KafkaProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * Bootstrap Servers
     */
    private String bootstrapServers = "localhost:9092";

    /**
     * 生产者配置
     */
    private Producer producer = new Producer();

    /**
     * 消费者配置
     */
    private Consumer consumer = new Consumer();

    @Data
    public static class Producer {
        /**
         * 重试次数
         */
        private Integer retries = 3;

        /**
         * 批量大小
         */
        private Integer batchSize = 16384;

        /**
         * Linger 毫秒
         */
        private Integer lingerMs = 1;

        /**
         * Buffer 内存
         */
        private Integer bufferMemory = 33554432;

        /**
         * Key 序列化器
         */
        private String keySerializer = "org.apache.kafka.common.serialization.StringSerializer";

        /**
         * Value 序列化器
         */
        private String valueSerializer = "org.apache.kafka.common.serialization.StringSerializer";
    }

    @Data
    public static class Consumer {
        /**
         * Group ID
         */
        private String groupId = "blade-group";

        /**
         * 自动提交
         */
        private Boolean enableAutoCommit = true;

        /**
         * 自动提交间隔
         */
        private Integer autoCommitIntervalMs = 1000;

        /**
         * Key 反序列化器
         */
        private String keyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";

        /**
         * Value 反序列化器
         */
        private String valueDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";

        /**
         * 自动偏移重置
         */
        private String autoOffsetReset = "latest";
    }

}
