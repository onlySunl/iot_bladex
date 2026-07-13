

package org.springblade.modules.iot.protocol.http.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * HTTP 协议配置属性
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/2
 */
@ConfigurationProperties(prefix = "http.protocol")
@Data
public class HttpProperties {

  /** 是否启用HTTP协议模块 */
  private boolean enabled = true;
}
