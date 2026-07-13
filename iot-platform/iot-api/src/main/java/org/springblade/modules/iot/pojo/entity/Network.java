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
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_network")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Network extends CustomBaseEntity {


  /** TCP_CLIENT,MQTT_CLIENT,HTTP_CLIENT,WEB_SOCKET_CLIENT */
  private String type;

  /** 唯一标识 */
  @TableField(value = "product_key")
  @AutoColumn(comment = "唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  @TableField(value = "union_id")
  @AutoColumn(comment = "唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String unionId;

  private String name;

  /** 详细描述 */
  private String description;

  @TableField(value = "create_date")
  @AutoColumn(comment = "详细描述", defaultValueType = DefaultValueEnum.NULL)
  private Date createDate;

  /** enable,disable */
  private Boolean state;

  /** 配置内容 */
  private String configuration;

  /** 创建用户 */
  @TableField(value = "create_user")
  @AutoColumn(comment = "创建用户", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String createUser;

  private static final long serialVersionUID = 1L;
}
