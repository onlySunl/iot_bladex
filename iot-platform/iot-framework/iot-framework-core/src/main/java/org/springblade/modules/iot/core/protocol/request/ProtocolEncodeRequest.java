

package org.springblade.modules.iot.core.protocol.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springblade.modules.iot.core.protocol.support.ProtocolSupportDefinition;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/9 21:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProtocolEncodeRequest {

  private ProtocolSupportDefinition definition;
  private String payload;
  private Object context;

  public ProtocolEncodeRequest(ProtocolSupportDefinition definition, String payload) {
    this.definition = definition;
    this.payload = payload;
  }
}
