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
@TableName("iot_device_events")
@Data
@Builder
public class IoTDeviceEvents extends CustomBaseEntity {

  /** 事件标识 */

  /** 事件名称 */
  private String name;

  /** 事件级别 */
  private String level;

  /** 描述 */
  private String description;

  /** 事件总数 */
  private String qty;

  /** 最新事件上报时间 */
  private String time;

  // 是否设置存储策略
  private boolean storagePolicy;
}
