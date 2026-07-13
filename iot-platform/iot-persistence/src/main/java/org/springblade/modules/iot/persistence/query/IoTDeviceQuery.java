

package org.springblade.modules.iot.persistence.query;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/1/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IoTDeviceQuery {

  private String iotId;
  private String extDeviceId;
  private String deviceId;
  private String gwProductKey;
  private String productKey;
  private String thirdPlatform;

  public boolean emptyParams() {
    return StrUtil.isBlank(getIotId())
        && StrUtil.isBlank(getExtDeviceId())
        && StrUtil.isBlank(getDeviceId())
        && StrUtil.isBlank(getGwProductKey())
        && StrUtil.isBlank(productKey)
        && StrUtil.isBlank(thirdPlatform);
  }
}
