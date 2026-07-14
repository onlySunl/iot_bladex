

package org.springblade.modules.iot.persistence.entity.bo;

import lombok.Data;

@Data
public class IoTGwDeviceBO {

  /** 当前要添加的设备 */
  private String productKey;

  private String creatorId;

  /** 对应网关产品Key */
  private String gwProductKey;
}
