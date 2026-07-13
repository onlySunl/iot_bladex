/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 视频平台设备扩展表实体
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
 * 视频平台设备扩展表
 * 
 * 存储各视频平台设备的特有字段信息
 * 
 * @author gitee.com/NexIoT
 * @version 2.0
 * @since 2025/11/08
 */
@TableName("video_platform_device_ext")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoPlatformDeviceExt extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */

  /** 关联主表设备ID */
  @TableField(value = "device_id")
  @AutoColumn(comment = "关联主表设备ID", defaultValueType = DefaultValueEnum.NULL)
  private Long deviceId;

  /** 平台实例唯一标识 */
  @TableField(value = "instance_key")
  @AutoColumn(comment = "平台实例唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String instanceKey;

  /** 平台侧设备ID */
  @TableField(value = "platform_device_id")
  @AutoColumn(comment = "平台侧设备ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String platformDeviceId;

  // ==================== WVP GB28181 特有字段 ====================

  /** WVP字符集: GB2312/UTF-8 */
  @TableField(value = "charset")
  @AutoColumn(comment = "WVP字符集: GB2312", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String charset;

  /** WVP传输协议: UDP/TCP */
  @TableField(value = "transport")
  @AutoColumn(comment = "WVP传输协议: UDP", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String transport;

  /** WVP流模式: TCP-ACTIVE/TCP-PASSIVE/UDP */
  @TableField(value = "stream_mode")
  @AutoColumn(comment = "WVP流模式: TCP-ACTIVE", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String streamMode;

  /** WVP主机地址 */
  @TableField(value = "host_address")
  @AutoColumn(comment = "WVP主机地址", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String hostAddress;

  /** WVP注册有效期(秒) */
  @TableField(value = "expires")
  @AutoColumn(comment = "WVP注册有效期(秒)", defaultValueType = DefaultValueEnum.NULL)
  private Integer expires;

  /** WVP最后心跳时间 */
  @TableField(value = "keepalive_time")
  @AutoColumn(comment = "WVP最后心跳时间", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date keepaliveTime;

  /** WVP注册时间 */
  @TableField(value = "register_time")
  @AutoColumn(comment = "WVP注册时间", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date registerTime;

  /** WVP流媒体服务器ID */
  @TableField(value = "media_server_id")
  @AutoColumn(comment = "WVP流媒体服务器ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String mediaServerId;

  // ==================== 海康ISC 设备级特有字段 ====================

  /** 海康编码设备索引码 */
  @TableField(value = "encode_dev_index_code")
  @AutoColumn(comment = "海康编码设备索引码", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String encodeDevIndexCode;

  /** 海康设备能力集编码 */
  @TableField(value = "device_capability_set")
  @AutoColumn(comment = "海康设备能力集编码", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deviceCapabilitySet;

  // ==================== 大华ICC特有字段 ====================

  /** 大华设备序列号 */
  @TableField(value = "device_sn")
  @AutoColumn(comment = "大华设备序列号", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deviceSn;

  /** 大华设备类别 */
  @TableField(value = "device_category")
  @AutoColumn(comment = "大华设备类别", defaultValueType = DefaultValueEnum.NULL)
  private Integer deviceCategory;

  /** 大华设备类型 */
  @TableField(value = "device_type")
  @AutoColumn(comment = "大华设备类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String deviceType;

  /** 大华所属者编码 */
  @TableField(value = "owner_code")
  @AutoColumn(comment = "大华所属者编码", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String ownerCode;

  /** 大华在线状态: 0/1 */
  @TableField(value = "is_online")
  @AutoColumn(comment = "大华在线状态: 0", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String isOnline;

  /** 大华休眠状态: 0-非休眠/1-休眠 */
  @TableField(value = "sleep_stat")
  @AutoColumn(comment = "大华休眠状态: 0-非休眠", defaultValueType = DefaultValueEnum.NULL)
  private Integer sleepStat;

  /** 大华第三方代理端口 */
  @TableField(value = "third_proxy_port")
  @AutoColumn(comment = "大华第三方代理端口", defaultValueType = DefaultValueEnum.NULL)
  private Integer thirdProxyPort;

  /** 大华第三方代理服务器编码 */
  @TableField(value = "third_proxy_server_code")
  @AutoColumn(comment = "大华第三方代理服务器编码", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String thirdProxyServerCode;

  /** 大华license限制 */
  @TableField(value = "license_limit")
  @AutoColumn(comment = "大华license限制", defaultValueType = DefaultValueEnum.NULL)
  private Integer licenseLimit;

  /** 大华离线原因 */
  @TableField(value = "offline_reason")
  @AutoColumn(comment = "大华离线原因", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String offlineReason;

  /** 大华子系统标识 */
  @TableField(value = "sub_system")
  @AutoColumn(comment = "大华子系统标识", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String subSystem;

  /** 大华单元信息（JSON）包含unitType/channels等 */
  @TableField(value = "units_info")
  @AutoColumn(comment = "大华单元信息（JSON）包含unitType", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String unitsInfo;

  // ==================== 扩展字段（预留） ====================

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
