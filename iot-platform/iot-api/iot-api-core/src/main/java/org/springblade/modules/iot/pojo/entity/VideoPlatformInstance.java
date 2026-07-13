/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 视频平台实例实体
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

@TableName("video_platform_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoPlatformInstance extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */

  /** 平台类型：wvp/ics/icc */
  @TableField(value = "platform_type")
  @AutoColumn(comment = "平台类型：wvp", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String platformType;

  /** 实例唯一标识 */
  @TableField(value = "instance_key")
  @AutoColumn(comment = "实例唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String instanceKey;

  /** 实例名称 */
  @TableField(value = "name")
  @AutoColumn(comment = "实例名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String name;

  /** 平台API地址或域 */
  @TableField(value = "endpoint")
  @AutoColumn(comment = "平台API地址或域", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String endpoint;

  /** 鉴权配置（JSON） */
  @TableField(value = "auth")
  @AutoColumn(comment = "鉴权配置（JSON）", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String auth;

  /** 平台版本 */
  @TableField(value = "version")
  @AutoColumn(comment = "平台版本", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String version;

  /** 其他选项配置（JSON） */
  @TableField(value = "options")
  @AutoColumn(comment = "其他选项配置（JSON）", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String options;

  /** WVP是否自动创建GB/级联产品 */
  @TableField(value = "auto_create_products")
  @AutoColumn(comment = "WVP是否自动创建GB", defaultValueType = DefaultValueEnum.NULL)
  private Integer autoCreateProducts;

  /** 是否启用 */
  @TableField(value = "enabled")
  @AutoColumn(comment = "是否启用", defaultValueType = DefaultValueEnum.NULL)
  private Integer enabled;

  /** 创建者ID */
  @TableField(value = "creator_id")
  @AutoColumn(comment = "创建者ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;

  /** 创建时间 */
  @TableField(value = "create_time")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  /** 更新时间 */
  @TableField(value = "update_time")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
}
