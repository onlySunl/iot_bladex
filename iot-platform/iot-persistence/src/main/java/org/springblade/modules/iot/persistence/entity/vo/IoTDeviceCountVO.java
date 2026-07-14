

package org.springblade.modules.iot.persistence.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceCountVO {

  private static final long serialVersionUID = 1L;
  private String application;
  private String devNum;
}
