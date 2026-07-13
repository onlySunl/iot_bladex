

package org.springblade.modules.iot.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/2/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IoTDeviceLogMaxStorageTime {

  private String productKey;
  private String platform;
  private int days;
}
