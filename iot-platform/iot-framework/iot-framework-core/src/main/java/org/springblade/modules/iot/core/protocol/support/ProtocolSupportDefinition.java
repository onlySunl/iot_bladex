

package org.springblade.modules.iot.core.protocol.support;

import java.util.Map;
import java.util.Set;
import lombok.Data;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/9 19:11
 */
@Data
public class ProtocolSupportDefinition {

  private String id;
  private String name;
  private String description;
  private String provider;
  private String type;
  private byte state;
  private Map<String, Object> configuration;
  private Set<String> supportMethods;

  public boolean supportMethod(ProtocolCodecSupport.CodecMethod method) {
    if (supportMethods == null) {
      return false;
    }
    if (supportMethods.contains(method.name())) {
      return true;
    }
    return false;
  }
}
