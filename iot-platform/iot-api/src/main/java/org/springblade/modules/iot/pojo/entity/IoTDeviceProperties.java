/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_properties")
@Data
@Builder
public class IoTDeviceProperties extends CustomBaseEntity {

  /** 属性名称 */
  private String name;

  /** 数据类型 */
  private String type;

  /** 属性值来源 */
  private String source;

  /** 单位 */
  private String unit;

  /** 枚举键值 */
  private String elements;

  /** 描述 */
  private String description;

  /** 读写 */
  private String mode;
}
