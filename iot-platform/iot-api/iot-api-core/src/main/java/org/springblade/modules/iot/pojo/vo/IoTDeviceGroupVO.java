

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceGroup;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGroupVO extends IoTDeviceGroup implements Serializable {


  /** 分组ID，非自增 */
  private Long id;







  /** 设备列表 */
  private List<IoTDeviceVO> instanceVOS;

  /** 分组子集 */
  private List<IoTDeviceGroupVO> children;
}
