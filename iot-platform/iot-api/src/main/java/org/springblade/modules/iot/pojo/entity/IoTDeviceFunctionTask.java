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

import org.springblade.modules.iot.persistence.common.inteceptor.SQenGenId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

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

  private static final long serialVersionUID = 1L;

  @Id
  @KeySql(genId = SQenGenId.class)
@TableField(value = "id")
@AutoColumn(comment = "id", defaultValueType = DefaultValueEnum.NULL)
  private Long id;

@TableField(value = "task_name")
@AutoColumn(comment = "taskName", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String taskName;
@TableField(value = "product_key")
@AutoColumn(comment = "productKey", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@TableField(value = "begin_time")
@AutoColumn(comment = "beginTime", defaultValueType = DefaultValueEnum.NULL)
  private Date beginTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@TableField(value = "end_time")
@AutoColumn(comment = "endTime", defaultValueType = DefaultValueEnum.NULL)
  private Date endTime;

@TableField(value = "creator")
@AutoColumn(comment = "creator", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String creator;
@TableField(value = "creator_id")
@AutoColumn(comment = "creatorId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;
@TableField(value = "command")
@AutoColumn(comment = "command", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String command;
@TableField(value = "command_data")
@AutoColumn(comment = "commandData", defaultValueType = DefaultValueEnum.NULL)
  private String commandData;

  /** 状态 0.待执行；1.已执行；2.正在执行 */
@TableField(value = "status")
@AutoColumn(comment = "状态 0.待执行；1.已执行；2.正在执行", defaultValueType = DefaultValueEnum.NULL)
  private Integer status;
}
