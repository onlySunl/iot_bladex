/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 视频平台设备主表实体
 * @Author: gitee.com/NexIoT
 *
 */
package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * 视频平台设备主表
 * 
 * 存储各视频平台设备的公共字段信息
 * 
 * @author gitee.com/NexIoT
 * @version 2.0
 * @since 2025/11/08
 */
@TableName("video_platform_device")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoPlatformDevice extends CustomBaseEntity {

  /** 主键ID */

  /** 平台实例唯一标识 */
  @TableField("instance_key")
  private String instanceKey;

  /** 设备ID（平台侧唯一标识） */
  @TableField("device_id")
  private String deviceId;

  /** 设备名称 */
  @TableField("device_name")
  private String deviceName;

  /** 设备状态: online/offline/1/0等 */
  @TableField("device_status")
  private String deviceStatus;

  /** 设备型号 */
  @TableField("device_model")
  private String deviceModel;

  /** 设备IP地址 */
  @TableField("device_ip")
  private String deviceIp;

  /** 设备端口 */
  @TableField("device_port")
  private Integer devicePort;

  /** 设备厂商 */
  @TableField("manufacturer")
  private String manufacturer;

  /** 所属组织ID */
  @TableField("org_id")
  private String orgId;

  /** 所属组织名称 */
  @TableField("org_name")
  private String orgName;

  /** 经度（longitude） */
  @TableField("gps_x")
  private String gpsX;

  /** 纬度（latitude） */
  @TableField("gps_y")
  private String gpsY;

  /** Z轴高度（altitude） */
  @TableField("gps_z")
  private String gpsZ;

  /** 设备配置（JSON）包含channelList、能力集等公共扩展信息 */
  @TableField("configuration")
  private String configuration;

  /** 备注说明 */
  @TableField("remark")
  private String remark;

  /** 是否启用：0-禁用，1-启用 */
  @TableField("enabled")
  private Integer enabled;

  /** 创建者ID */
  @TableField("create_id")
  private String createId;

  /** 更新者ID */
  @TableField("update_id")
  private String updateId;

  /** 创建时间 */
  /** 更新时间 */
}
