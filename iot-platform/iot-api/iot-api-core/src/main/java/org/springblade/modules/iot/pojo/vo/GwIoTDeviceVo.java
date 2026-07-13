

package org.springblade.modules.iot.pojo.vo;

import lombok.Data;

@Data
public class GwIoTDeviceVo {
  private String iotId;
  private String productKey;
  private String deviceId;
  private String deviceName;
  private String productName;
  private Boolean state;
  private Long onlineTime;
  private String ext1; // 从站地址
  private String signalStrength; // CSQ信号强度
  private String deviceTag; // 设备标签
  private String deviceAddress; // 设备地址
  private String photoUrl;
}
