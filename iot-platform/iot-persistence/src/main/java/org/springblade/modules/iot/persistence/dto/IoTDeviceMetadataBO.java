

package org.springblade.modules.iot.persistence.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IoTDeviceMetadataBO implements Serializable {

  private String companyNo;
  private String creatorId;
  private String orgId;
  private String classifiedId;
  private String transportProtocol;
  private String point;
}
