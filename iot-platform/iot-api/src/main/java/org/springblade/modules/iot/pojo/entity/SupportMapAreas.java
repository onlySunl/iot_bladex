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
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("support_map_areas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupportMapAreas extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;
  @Id private String id;

  /** 父id */
@TableField(value = "pid")
@AutoColumn(comment = "父id", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String pid;

  /** 地址 */
@TableField(value = "name")
@AutoColumn(comment = "地址", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String name;

  /** 详细地址 */
@TableField(value = "full_name")
@AutoColumn(comment = "详细地址", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String fullName;

  /** 深度 */
@TableField(value = "deep")
@AutoColumn(comment = "深度", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deep;

  /** 核心点坐标 */
@TableField(value = "location")
@AutoColumn(comment = "核心点坐标", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String location;

  /** 范围坐标 */
@TableField(value = "polygon")
@AutoColumn(comment = "范围坐标", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String polygon;
}
