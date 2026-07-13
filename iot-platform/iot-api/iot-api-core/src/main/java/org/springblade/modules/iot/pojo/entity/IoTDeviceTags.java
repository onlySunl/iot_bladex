/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_device_tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceTags extends CustomBaseEntity {


  /** 设备deviceId */
  @TableField(value = "device_id")
  @AutoColumn(comment = "设备deviceId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 产品ID或者设备唯一标识 */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品ID或者设备唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  @TableField(value = "iot_id")
  @AutoColumn(comment = "产品ID或者设备唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;

  @TableField(value = "create_time")
  @AutoColumn(comment = "createTime", defaultValueType = DefaultValueEnum.NULL)
  private Long createTime;

  @TableField(value = "`name`")
  @AutoColumn(comment = "name", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String name;

  private String description;

  @TableField(value = "`type`")
  @AutoColumn(comment = "type", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String type;

  @TableField(value = "`value`")
  @AutoColumn(comment = "value", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String value;

  @TableField(value = "`key`")
  @AutoColumn(comment = "key", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String key;

  @TableField(value = "`instance`")
  @AutoColumn(comment = "instance", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String instance;

  private static final long serialVersionUID = 1L;
}
