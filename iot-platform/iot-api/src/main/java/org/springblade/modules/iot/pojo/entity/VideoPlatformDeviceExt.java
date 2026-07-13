/*
 *
 *
 *
 */
package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("video_platform_device_ext")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoPlatformDeviceExt extends CustomBaseEntity {

  

  /** 关联主表设备ID */
  @TableField("device_id")
  private Long deviceId;

  /** 平台实例唯一标识 */
  @TableField("instance_key")
  private String instanceKey;

  /** 平台侧设备ID */
  @TableField("platform_device_id")
  private String platformDeviceId;

  /** WVP字符集: GB2312/UTF-8 */
  @TableField("charset")
  private String charset;

  /** WVP传输协议: UDP/TCP */
  @TableField("transport")
  private String transport;

  /** WVP流模式: TCP-ACTIVE/TCP-PASSIVE/UDP */
  @TableField("stream_mode")
  private String streamMode;

  /** WVP主机地址 */
  @TableField("host_address")
  private String hostAddress;

  /** WVP注册有效期(秒) */
  @TableField("expires")
  private Integer expires;

  /** WVP最后心跳时间 */
  @TableField("keepalive_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date keepaliveTime;

  /** WVP注册时间 */
  @TableField("register_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date registerTime;

  /** WVP流媒体服务器ID */
  @TableField("media_server_id")
  private String mediaServerId;

  /** 海康编码设备索引码 */
  @TableField("encode_dev_index_code")
  private String encodeDevIndexCode;

  /** 海康设备能力集编码 */
  @TableField("device_capability_set")
  private String deviceCapabilitySet;

  /** 大华设备序列号 */
  @TableField("device_sn")
  private String deviceSn;

  /** 大华设备类别 */
  @TableField("device_category")
  private Integer deviceCategory;

  /** 大华设备类型 */
  @TableField("device_type")
  private String deviceType;

  /** 大华所属者编码 */
  @TableField("owner_code")
  private String ownerCode;

  /** 大华在线状态: 0/1 */
  @TableField("is_online")
  private String isOnline;

  /** 大华休眠状态: 0-非休眠/1-休眠 */
  @TableField("sleep_stat")
  private Integer sleepStat;

  /** 大华第三方代理端口 */
  @TableField("third_proxy_port")
  private Integer thirdProxyPort;

  /** 大华第三方代理服务器编码 */
  @TableField("third_proxy_server_code")
  private String thirdProxyServerCode;

  /** 大华license限制 */
  @TableField("license_limit")
  private Integer licenseLimit;

  /** 大华离线原因 */
  @TableField("offline_reason")
  private String offlineReason;

  /** 大华子系统标识 */
  @TableField("sub_system")
  private String subSystem;

  /** 大华单元信息（JSON）包含unitType/channels等 */
  @TableField("units_info")
  private String unitsInfo;

  /** 扩展字段1 */
  @TableField("ext_field1")
  private String extField1;

  /** 扩展字段2 */
  @TableField("ext_field2")
  private String extField2;

  /** 扩展字段3（JSON） */
  @TableField("ext_field3")
  private String extField3;

  /** 创建者ID */
  @TableField("create_id")
  private String createId;

  /** 更新者ID */
  @TableField("update_id")
  private String updateId;
}
