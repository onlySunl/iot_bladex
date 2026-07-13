

package org.springblade.modules.iot.persistence.dto;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/7/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceScanDTO {

  private String productKey;
  private String qrcode;

  public boolean isEmpty() {
    return StrUtil.isEmpty(getProductKey()) || StrUtil.isEmpty(getQrcode());
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ResultScanDTO {

    private String imei;
    private String deviceId;
  }
}
