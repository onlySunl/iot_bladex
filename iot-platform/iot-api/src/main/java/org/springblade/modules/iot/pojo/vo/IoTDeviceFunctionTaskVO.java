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

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
public class IoTDeviceFunctionTaskVO extends IoTDeviceFunctionTask {

  private Integer successNum;
  private Integer totalNum;
  private Integer status;
}
