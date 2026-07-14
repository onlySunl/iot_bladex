

package org.springblade.modules.iot.persistence.entity.vo;

import java.util.List;
import org.springblade.modules.iot.pojo.entity.IoTDeviceGeoFence;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备围栏 @Author gitee.com/NexIoT
 *
 * @since 2023/8/5 8:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGeoFenceVO extends IoTDeviceGeoFence {

  /** 围栏状态 0.启用 1.停用 */

  private int deviceNum;

    private List<String> queryUserList;
}
