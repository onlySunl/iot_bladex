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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoPlatformDevice extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */

  /** 平台实例唯一标识 */
  @TableField(value = "instance_key")
  @AutoColumn(comment = "平台实例唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String instanceKey;

  /** 设备ID（平台侧唯一标识） */
  @TableField(value = "device_id")
  @AutoColumn(comment = "设备ID（平台侧唯一标识）", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 设备名称 */
  @TableField(value = "device_name")
  @AutoColumn(comment = "设备名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceName;

  /** 设备状态: online/offline/1/0等 */
  @TableField(value = "device_status")
  @AutoColumn(comment = "设备状态: online", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String deviceStatus;

  /** 设备型号 */
  @TableField(value = "device_model")
  @AutoColumn(comment = "设备型号", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deviceModel;

  /** 设备IP地址 */
  @TableField(value = "device_ip")
  @AutoColumn(comment = "设备IP地址", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deviceIp;

  /** 设备端口 */
  @TableField(value = "device_port")
  @AutoColumn(comment = "设备端口", defaultValueType = DefaultValueEnum.NULL)
  private Integer devicePort;

  /** 设备厂商 */
  @TableField(value = "manufacturer")
  @AutoColumn(comment = "设备厂商", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String manufacturer;

  /** 所属组织ID */
  @TableField(value = "org_id")
  @AutoColumn(comment = "所属组织ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String orgId;

  /** 所属组织名称 */
  @TableField(value = "org_name")
  @AutoColumn(comment = "所属组织名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String orgName;

  /** 经度（longitude） */
  @TableField(value = "gps_x")
  @AutoColumn(comment = "经度（longitude）", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String gpsX;

  /** 纬度（latitude） */
  @TableField(value = "gps_y")
  @AutoColumn(comment = "纬度（latitude）", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String gpsY;

  /** Z轴高度（altitude） */
  @TableField(value = "gps_z")
  @AutoColumn(comment = "Z轴高度（altitude）", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String gpsZ;

  /** 设备配置（JSON）包含channelList、能力集等公共扩展信息 */
  @TableField(value = "configuration")
  @ColumnType("text")
  @AutoColumn(comment = "设备配置（JSON）包含channelList、能力集等公共扩展信息", defaultValueType = DefaultValueEnum.NULL)
  private String configuration;

  /** 备注说明 */
  @TableField(value = "remark")
  @ColumnType("text")
  @AutoColumn(comment = "备注说明", defaultValueType = DefaultValueEnum.NULL)
  private String remark;

  /** 是否启用：0-禁用，1-启用 */
  @TableField(value = "enabled")
  @AutoColumn(comment = "是否启用：0-禁用，1-启用", defaultValueType = DefaultValueEnum.NULL)
  private Integer enabled;

  /** 创建者ID */
  @TableField(value = "create_id")
  @AutoColumn(comment = "创建者ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String createId;

  /** 更新者ID */
  @TableField(value = "update_id")
  @AutoColumn(comment = "更新者ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String updateId;

  /** 创建时间 */
  @TableField(value = "create_time")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  /** 更新时间 */
  @TableField(value = "update_time")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
}
