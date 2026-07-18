

package org.springblade.modules.iot.message.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * kafka配置
 * @author clickear
 */
@ConfigurationProperties(prefix = "iot.event-bus.kafka")
@Data
public class KafkaProperties {

    /**
     * kafka boostrapServers地址
     */
    private String boostrapServers = "localhost:9092";

}
