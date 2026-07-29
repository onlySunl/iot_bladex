package org.springblade.modules.iot.base.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * IoT 基础自动配置
 *
 * @author Chill
 */
@AutoConfiguration
@EnableConfigurationProperties(IotBaseProperties.class)
@ComponentScan("org.springblade.modules.iot.base")
public class IotBaseAutoConfiguration {

}
