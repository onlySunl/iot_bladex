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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_device_subscribe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceSubscribe extends CustomBaseEntity {


  /** 消息类别：属性（PROPERTIES），指令（REPLY），事件（EVENT），上下线（EVENT：online,offline），所有 */
  @TableField(value = "msg_type")
  @AutoColumn(comment = "消息类别：属性（PROPERTIES），指令（REPLY），事件（EVENT），上下线（EVENT：online,offline），所有", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String msgType;

  /** 订阅级别：设备级，产品级 */
  @TableField(value = "sub_type")
  @AutoColumn(comment = "订阅级别：设备级，产品级", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String subType;

  /** 设备deviceId */
  @TableField(value = "device_id")
  @AutoColumn(comment = "设备deviceId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 产品ID或者设备唯一标识 */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品ID或者设备唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /** 产品ID或者设备唯一标识 */
  @TableField(value = "iot_id")
  @AutoColumn(comment = "产品ID或者设备唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;

  /** 订阅地址 */
  private String url;

  /** 主题 */
  private String topic;

  /** 创建时间 */
  @TableField(value = "create_date")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  /** 创建人 */
  private String creater;

  /** 实例编号(应用标识) */
  @TableField(value = "`instance`")
  @AutoColumn(comment = "实例编号(应用标识)", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String instance;

  /** 是否启用 */
  private Boolean enabled;

  private static final long serialVersionUID = 1L;
}
