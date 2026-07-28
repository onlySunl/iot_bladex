package org.springblade.core.databridge.config;

import org.springblade.core.databridge.registry.ConnectorRegistry;
import org.springblade.core.databridge.registry.DefaultConnectorRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据桥接配置
 *
 * @author Chill
 */
@Configuration
public class DatabridgeConfiguration {

    @Bean
    public ConnectorRegistry connectorRegistry() {
        return new DefaultConnectorRegistry();
    }
}
