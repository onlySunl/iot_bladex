

package org.springblade.modules.iot.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/2/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IoTDeviceOfflineThesholdBO {

  private String productKey;
  private String platform;
  private int offlineThreshold;
}
