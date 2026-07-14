

package org.springblade.modules.iot.core.protocol.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springblade.modules.iot.common.support.ProtocolSupportDefinition;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/9 21:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProtocolDecodeRequest {

  private ProtocolSupportDefinition definition;
  // 原始消息
  private String payload;
  // 上下文
  private Object context;

  public ProtocolDecodeRequest(ProtocolSupportDefinition definition, String payload) {
    this.definition = definition;
    this.payload = payload;
  }
}
