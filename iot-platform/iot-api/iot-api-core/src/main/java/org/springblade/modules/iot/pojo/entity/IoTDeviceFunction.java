

package org.springblade.modules.iot.pojo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IoTDeviceFunction implements Serializable {

  /** 功能标识 */
  private String id;

  /** 功能名称 */
  private String name;

  /** 是否是配置 */
  private boolean config;

  /** 描述 */
  private String description;

  /** 功能来源 */
  private String source;

  /** 输入 */
  private String inputs;
}
