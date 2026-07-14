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

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceLog extends CustomBaseEntity {

  /** 唯一编码 */
  @TableField("iot_id")
  private String iotId;

  /** 设备自身序号 */
  @TableField("device_id")
  private String deviceId;

  /** 产品ID */
  @TableField("product_key")
  private String productKey;

  /** 设备名称 */
  @TableField("device_name")
  private String deviceName;

  /** 消息类型 */
  @TableField("message_type")
  private String messageType;

  /** 指令ID */
  @TableField("command_id")
  private String commandId;

  /** 指令ID */
  @TableField("command_status")
  private Integer commandStatus;

  /** 事件名称 */
  private String event;

  /** 创建时间 */

  /** 内容 */
  private String content;

  private String point;
}
