

package org.springblade.modules.iot.pojo.entity;

import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_function")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class IoTDeviceFunction extends CustomBaseEntity {

  /** 功能标识 */

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
