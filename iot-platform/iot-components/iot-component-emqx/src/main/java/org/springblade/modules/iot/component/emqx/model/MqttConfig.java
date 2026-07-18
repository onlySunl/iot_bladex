

package org.springblade.modules.iot.component.emqx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@Configuration
@ConfigurationProperties(prefix = "emqx")
@NoArgsConstructor
@AllArgsConstructor
public class MqttConfig {

    private String host;

    private int port;

    private boolean ssl;

    private String topics;

    private int authPort;
}
