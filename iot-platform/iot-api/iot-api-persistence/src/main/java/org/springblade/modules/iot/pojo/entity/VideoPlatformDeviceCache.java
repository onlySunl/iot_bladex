/*
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 */
package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("video_platform_device_cache")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoPlatformDeviceCache extends CustomBaseEntity {

  @TableField("instance_key")
  private String instanceKey;

  @TableField("device_id")
  private String deviceId;

  @TableField("device_name")
  private String deviceName;

  @TableField("model")
  private String model;

  /** 设备配置（JSON），包含channelList等 */
  @TableField("configuration")
  private String configuration;

  /** 组织ID（可选） */
  @TableField("org_id")
  private String orgId;

  /** 创建者ID */
  @TableField("create_id")
  private String createId;

  /** 更新者ID */
  @TableField("update_id")
  private String updateId;
}
