/*
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
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

@TableName("video_platform_device_cache")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoPlatformDeviceCache extends CustomBaseEntity {
  private static final long serialVersionUID = 1L;


  @TableField(value = "instance_key")
  @AutoColumn(comment = "instanceKey", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String instanceKey;

  @TableField(value = "device_id")
  @AutoColumn(comment = "deviceId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  @TableField(value = "device_name")
  @AutoColumn(comment = "deviceName", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceName;

  @TableField(value = "status")
  @AutoColumn(comment = "status", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String status;

  @TableField(value = "model")
  @AutoColumn(comment = "model", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String model;

  /** 设备配置（JSON），包含channelList等 */
  @TableField(value = "configuration")
  @ColumnType("text")
  @AutoColumn(comment = "设备配置（JSON），包含channelList等", defaultValueType = DefaultValueEnum.NULL)
  private String configuration;

  /** 组织ID（可选） */
  @TableField(value = "org_id")
  @AutoColumn(comment = "组织ID（可选）", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String orgId;

  /** 创建者ID */
  @TableField(value = "create_id")
  @AutoColumn(comment = "创建者ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String createId;

  /** 更新者ID */
  @TableField(value = "update_id")
  @AutoColumn(comment = "更新者ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String updateId;

  @TableField(value = "create_time")
  @AutoColumn(comment = "更新者ID", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @TableField(value = "update_time")
  @AutoColumn(comment = "updateTime", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
}
