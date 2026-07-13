

package org.springblade.modules.iot.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/4/19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IoTDeviceSubscribeBO {

  private String msgType;
  private String url;
  private String topic;
}
