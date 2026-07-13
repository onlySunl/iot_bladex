/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 视频平台通道表实体
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
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频平台通道表
 *
 * <p>存储视频平台设备的通道信息，一个设备可包含多个通道
 *
 * @author gitee.com/NexIoT
 * @version 2.0
 * @since 2025/11/08
 */
@TableName("video_platform_channel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoPlatformChannel extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */

  /** 关联设备主表ID */
  @TableField(value = "device_id")
  @AutoColumn(comment = "关联设备主表ID", defaultValueType = DefaultValueEnum.NULL)
  private Long deviceId;

  /** 平台实例唯一标识 */
  @TableField(value = "instance_key")
  @AutoColumn(comment = "平台实例唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String instanceKey;

  /** 平台侧设备ID */
  @TableField(value = "platform_device_id")
  @AutoColumn(comment = "平台侧设备ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String platformDeviceId;

  /** 通道ID（平台侧唯一标识） */
  @TableField(value = "channel_id")
  @AutoColumn(comment = "通道ID（平台侧唯一标识）", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String channelId;

  // ==================== 通道公共字段 ====================

  /** 通道名称 */
  @TableField(value = "channel_name")
  @AutoColumn(comment = "通道名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String channelName;

  /** 通道状态: online/offline */
  @TableField(value = "channel_status")
  @AutoColumn(comment = "通道状态: online", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String channelStatus;

  /** 通道类型: analog/digital/virtual */
  @TableField(value = "channel_type")
  @AutoColumn(comment = "通道类型: analog", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String channelType;

  /** 父通道ID（级联场景） */
  @TableField(value = "parent_id")
  @AutoColumn(comment = "父通道ID（级联场景）", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String parentId;

  /** 通道厂商 */
  @TableField(value = "manufacturer")
  @AutoColumn(comment = "通道厂商", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String manufacturer;

  /** 通道型号 */
  @TableField(value = "model")
  @AutoColumn(comment = "通道型号", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String model;

  /** 通道所有者 */
  @TableField(value = "owner")
  @AutoColumn(comment = "通道所有者", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String owner;

  /** 行政区划 */
  @TableField(value = "civil_code")
  @AutoColumn(comment = "行政区划", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String civilCode;

  /** 安装地址 */
  @TableField(value = "address")
  @AutoColumn(comment = "安装地址", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String address;

  /** 是否有子设备: 0-否 1-是 */
  @TableField(value = "parental")
  @AutoColumn(comment = "是否有子设备: 0-否 1-是", defaultValueType = DefaultValueEnum.NULL)
  private Integer parental;

  /** 信令安全模式 */
  @TableField(value = "safety_way")
  @AutoColumn(comment = "信令安全模式", defaultValueType = DefaultValueEnum.NULL)
  private Integer safetyWay;

  /** 注册方式 */
  @TableField(value = "register_way")
  @AutoColumn(comment = "注册方式", defaultValueType = DefaultValueEnum.NULL)
  private Integer registerWay;

  /** 保密属性 */
  @TableField(value = "secrecy")
  @AutoColumn(comment = "保密属性", defaultValueType = DefaultValueEnum.NULL)
  private Integer secrecy;

  /** 通道IP地址 */
  @TableField(value = "ip_address")
  @AutoColumn(comment = "通道IP地址", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String ipAddress;

  /** 通道端口 */
  @TableField(value = "port")
  @AutoColumn(comment = "通道端口", defaultValueType = DefaultValueEnum.NULL)
  private Integer port;

  /** 经度 */
  @TableField(value = "longitude")
  @AutoColumn(comment = "经度", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String longitude;

  /** 纬度 */
  @TableField(value = "latitude")
  @AutoColumn(comment = "纬度", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String latitude;

  /** 云台类型: 0-不支持 1-球机 2-半球 3-固定枪机 4-遥控枪机 */
  @TableField(value = "ptz_type")
  @AutoColumn(comment = "云台类型: 0-不支持 1-球机 2-半球 3-固定枪机 4-遥控枪机", defaultValueType = DefaultValueEnum.NULL)
  private Integer ptzType;

  /** 位置类型 */
  @TableField(value = "position_type")
  @AutoColumn(comment = "位置类型", defaultValueType = DefaultValueEnum.NULL)
  private Integer positionType;

  // ==================== WVP GB28181 通道级特有字段 ====================

  /** WVP流ID */
  @TableField(value = "stream_id")
  @AutoColumn(comment = "WVP流ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String streamId;

  /** WVP国标流ID */
  @TableField(value = "gb_stream_id")
  @AutoColumn(comment = "WVP国标流ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String gbStreamId;

  /** WVP是否有音频 */
  @TableField(value = "has_audio")
  @AutoColumn(comment = "WVP是否有音频", defaultValueType = DefaultValueEnum.NULL)
  private Integer hasAudio;

  // ==================== 海康ISC 通道级特有字段 ====================

  /** 海康摄像机唯一标识码(用于抓图/预览) */
  @TableField(value = "camera_index_code")
  @AutoColumn(comment = "海康摄像机唯一标识码(用于抓图", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String cameraIndexCode;

  /** 海康通道号 */
  @TableField(value = "channel_no")
  @AutoColumn(comment = "海康通道号", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String channelNo;

  /** 海康摄像机类型: 0-枪机/1-球机/2-半球 */
  @TableField(value = "camera_type")
  @AutoColumn(comment = "海康摄像机类型: 0-枪机", defaultValueType = DefaultValueEnum.NULL)
  private Integer cameraType;

  /** 海康是否支持云台: 0-否/1-是 */
  @TableField(value = "ptz")
  @AutoColumn(comment = "海康是否支持云台: 0-否", defaultValueType = DefaultValueEnum.NULL)
  private Integer ptz;

  /** 海康通道能力集编码 */
  @TableField(value = "capability_set")
  @AutoColumn(comment = "海康通道能力集编码", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String capabilitySet;

  /** 海康安装位置 */
  @TableField(value = "install_location")
  @AutoColumn(comment = "海康安装位置", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String installLocation;

  // ==================== 大华ICC 通道级特有字段 ====================

  /** 大华通道编码 */
  @TableField(value = "channel_code")
  @AutoColumn(comment = "大华通道编码", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String channelCode;

  /** 大华通道序号 */
  @TableField(value = "channel_seq")
  @AutoColumn(comment = "大华通道序号", defaultValueType = DefaultValueEnum.NULL)
  private Integer channelSeq;

  /** 大华编码格式 */
  @TableField(value = "encode_format")
  @AutoColumn(comment = "大华编码格式", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String encodeFormat;

  /** 大华分辨率 */
  @TableField(value = "resolution")
  @AutoColumn(comment = "大华分辨率", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String resolution;

  // ==================== 通道能力与配置 ====================

  /** 通道能力集（JSON）: 录像/抓图/对讲/报警等 */
  @TableField(value = "capabilities")
  @AutoColumn(comment = "通道能力集（JSON）: 录像", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String capabilities;

  /** 流配置（JSON）: 主码流/子码流参数 */
  @TableField(value = "stream_config")
  @ColumnType("text")
  @AutoColumn(comment = "流配置（JSON）: 主码流", defaultValueType = DefaultValueEnum.NULL)
  private String streamConfig;

  // ==================== 扩展字段 ====================

  /** 扩展字段1 */
  @TableField(value = "ext_field1")
  @AutoColumn(comment = "扩展字段1", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String extField1;

  /** 扩展字段2 */
  @TableField(value = "ext_field2")
  @AutoColumn(comment = "扩展字段2", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String extField2;

  /** 扩展字段3（JSON） */
  @TableField(value = "ext_field3")
  @AutoColumn(comment = "扩展字段3（JSON）", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String extField3;

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
