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
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
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

  /** 主键ID */

  /** 关联设备主表ID */
  @TableField("device_id")
  private Long deviceId;

  /** 平台实例唯一标识 */
  @TableField("instance_key")
  private String instanceKey;

  /** 平台侧设备ID */
  @TableField("platform_device_id")
  private String platformDeviceId;

  /** 通道ID（平台侧唯一标识） */
  @TableField("channel_id")
  private String channelId;

  // ==================== 通道公共字段 ====================

  /** 通道名称 */
  @TableField("channel_name")
  private String channelName;

  /** 通道状态: online/offline */
  @TableField("channel_status")
  private String channelStatus;

  /** 通道类型: analog/digital/virtual */
  @TableField("channel_type")
  private String channelType;

  /** 父通道ID（级联场景） */
  @TableField("parent_id")
  private String parentId;

  /** 通道厂商 */
  @TableField("manufacturer")
  private String manufacturer;

  /** 通道型号 */
  @TableField("model")
  private String model;

  /** 通道所有者 */
  @TableField("owner")
  private String owner;

  /** 行政区划 */
  @TableField("civil_code")
  private String civilCode;

  /** 安装地址 */
  @TableField("address")
  private String address;

  /** 是否有子设备: 0-否 1-是 */
  @TableField("parental")
  private Integer parental;

  /** 信令安全模式 */
  @TableField("safety_way")
  private Integer safetyWay;

  /** 注册方式 */
  @TableField("register_way")
  private Integer registerWay;

  /** 保密属性 */
  @TableField("secrecy")
  private Integer secrecy;

  /** 通道IP地址 */
  @TableField("ip_address")
  private String ipAddress;

  /** 通道端口 */
  @TableField("port")
  private Integer port;

  /** 经度 */
  @TableField("longitude")
  private String longitude;

  /** 纬度 */
  @TableField("latitude")
  private String latitude;

  /** 云台类型: 0-不支持 1-球机 2-半球 3-固定枪机 4-遥控枪机 */
  @TableField("ptz_type")
  private Integer ptzType;

  /** 位置类型 */
  @TableField("position_type")
  private Integer positionType;

  // ==================== WVP GB28181 通道级特有字段 ====================

  /** WVP流ID */
  @TableField("stream_id")
  private String streamId;

  /** WVP国标流ID */
  @TableField("gb_stream_id")
  private String gbStreamId;

  /** WVP是否有音频 */
  @TableField("has_audio")
  private Integer hasAudio;

  // ==================== 海康ISC 通道级特有字段 ====================

  /** 海康摄像机唯一标识码(用于抓图/预览) */
  @TableField("camera_index_code")
  private String cameraIndexCode;

  /** 海康通道号 */
  @TableField("channel_no")
  private String channelNo;

  /** 海康摄像机类型: 0-枪机/1-球机/2-半球 */
  @TableField("camera_type")
  private Integer cameraType;

  /** 海康是否支持云台: 0-否/1-是 */
  @TableField("ptz")
  private Integer ptz;

  /** 海康通道能力集编码 */
  @TableField("capability_set")
  private String capabilitySet;

  /** 海康安装位置 */
  @TableField("install_location")
  private String installLocation;

  // ==================== 大华ICC 通道级特有字段 ====================

  /** 大华通道编码 */
  @TableField("channel_code")
  private String channelCode;

  /** 大华通道序号 */
  @TableField("channel_seq")
  private Integer channelSeq;

  /** 大华编码格式 */
  @TableField("encode_format")
  private String encodeFormat;

  /** 大华分辨率 */
  @TableField("resolution")
  private String resolution;

  // ==================== 通道能力与配置 ====================

  /** 通道能力集（JSON）: 录像/抓图/对讲/报警等 */
  @TableField("capabilities")
  private String capabilities;

  /** 流配置（JSON）: 主码流/子码流参数 */
  @TableField("stream_config")
  private String streamConfig;

  // ==================== 扩展字段 ====================

  /** 扩展字段1 */
  @TableField("ext_field1")
  private String extField1;

  /** 扩展字段2 */
  @TableField("ext_field2")
  private String extField2;

  /** 扩展字段3（JSON） */
  @TableField("ext_field3")
  private String extField3;

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
