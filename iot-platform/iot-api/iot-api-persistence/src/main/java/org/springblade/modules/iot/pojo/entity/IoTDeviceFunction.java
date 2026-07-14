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

import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_function")
@Data
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
