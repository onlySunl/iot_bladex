

package org.springblade.modules.iot.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IoTDeviceHistoryBO {

  private int id;
  private String deviceId;
  private String deviceName;
  private String productKey;
  private String coordinate;
  private Long firstOnlineTime;
  private String creater;
  private Long deleteTime;
  private Long createTime;
}
