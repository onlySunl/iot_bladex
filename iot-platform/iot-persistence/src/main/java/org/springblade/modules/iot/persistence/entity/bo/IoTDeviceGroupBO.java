

package org.springblade.modules.iot.persistence.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGroupBO {

  private static final long serialVersionUID = 1L;

  /** 分组ID，非自增 */
  private Long id;

  /** 设备id */
  private String[] devIds;

  /** 分组名称 */
  private String groupName;

  /** 分组标识 */
  private String groupCode;

  /** 群组描述 */
  private String groupDescribe;

  /** 父id */
  private Long parentId;
}
