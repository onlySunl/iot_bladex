

package org.springblade.modules.iot.monitor.web.config.xss;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "org.springblade.iot.xss")
@Component
public class XssProperties {

  /** 开启xss */
  private Boolean enabled = true;

  /** 放行url */
  private List<String> skipUrl = new ArrayList<>();
}
