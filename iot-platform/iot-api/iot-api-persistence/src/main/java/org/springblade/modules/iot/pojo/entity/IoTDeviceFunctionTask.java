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

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@TableName("iot_device_function_task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceFunctionTask extends CustomBaseEntity {

  private String taskName;
  private String productKey;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date beginTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;

  private String creator;
  private String creatorId;
  private String command;
  private String commandData;

  /** 状态 0.待执行；1.已执行；2.正在执行 */
}
