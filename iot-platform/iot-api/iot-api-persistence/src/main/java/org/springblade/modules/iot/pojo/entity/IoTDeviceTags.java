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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceTags extends CustomBaseEntity {

  /** 设备deviceId */
  @TableField("device_id")
  private String deviceId;

  /** 产品ID或者设备唯一标识 */
  @TableField("product_key")
  private String productKey;

  @TableField("iot_id")
  private String iotId;

  @TableField("`name`")
  private String name;

  private String description;

  @TableField("`type`")
  private String type;

  @TableField("`value`")
  private String value;

  @TableField("`key`")
  private String key;

  @TableField("`instance`")
  private String instance;
}
