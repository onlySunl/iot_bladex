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

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceLog;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "设备日志视图对象")
public class IoTDeviceLogVO extends IoTDeviceLog {

  /*-----------------日志信息---------------------*/

  /** 日志ID，非自增 */

  /** 创建时间 */
}
