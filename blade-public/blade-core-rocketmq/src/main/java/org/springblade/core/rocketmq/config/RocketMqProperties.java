package org.springblade.core.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RocketMQ 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.rocketmq")
public class RocketMqProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * NameServer 地址
     */
    private String nameServer = "localhost:9876";

    /**
     * 生产者组
     */
    private String producerGroup = "blade-producer-group";

    /**
     * 发送超时时间（毫秒）
     */
    private Integer sendTimeout = 3000;

    /**
     * 重试次数
     */
    private Integer retryTimes = 2;

    /**
     * 消费者组
     */
    private String consumerGroup = "blade-consumer-group";

    /**
     * 线程数
     */
    private Integer threadNum = 20;

}
