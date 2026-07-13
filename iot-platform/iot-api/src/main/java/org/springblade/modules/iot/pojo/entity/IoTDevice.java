/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import cn.universal.common.annotation.Excel;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDevice extends CustomBaseEntity {
  

  /** 对外设备唯一标识符 */
  @TableField("iot_id")
  private String iotId;

  /** 设备自身序号 */
  @Excel(name = "设备序列号")
  @TableField("device_id")
  private String deviceId;

  // @Excel(name = "实例名称")
  @TableField("instance")
  private String instance;

  /** 归属应用 */
  @TableField("application")
  private String application;

  /** 激活时间 */
  @TableField("registry_time")
  private Integer registryTime;

  /** 最后上线时间 */
  @TableField("online_time")
  private Long onlineTime;

  /** 第三方设备ID唯一标识符 */
  @TableField("ext_device_id")
  private String extDeviceId;

  /** 设备名称 */
  @TableField("product_name")
  private String productName;

  /** 网关产品ProductKey */
  @Excel(name = "网关产品ProductKey")
  @TableField("gw_product_key")
  private String gwProductKey;

  /** 设备密钥 */
  @Excel(name = "设备密钥")
  @TableField("device_secret")
  private String deviceSecret;

  /** 产品key */
  @TableField("product_key")
  private String productKey;

  /** 设备实例名称 */
  @Excel(name = "设备名称")
  @TableField("device_name")
  private String deviceName;

  @TableField("creator_id")
  private String creatorId;

  /** 扩展字段1 */
  @TableField("ext1")
  private String ext1;

  /** 扩展字段2 */
  @TableField("ext2")
  private String ext2;

  /** 扩展字段3 */
  @TableField("ext3")
  private String ext3;

  /** 扩展字段4 */
  @TableField("ext4")
  private String ext4;

  /** CSQ信号强度 */
  @TableField("signal_strength")
  private String signalStrength;

  /** 设备标签 */
  @TableField("device_tag")
  private String deviceTag;

  /** 设备地址 */
  @TableField("device_address")
  private String deviceAddress;

  /** 0-离线，1-在线 */
  @Excel(name = "在线状态")
  private Boolean state;

  /** 说明 */
  @Excel(name = "备注")
  private String detail;

  /** 派生元数据,有的设备的属性，功能，事件可能会动态的添加 */
  @TableField("derive_metadata")
  private String deriveMetadata;

  /** 其他配置 */
  @TableField("configuration")
  private String configuration;

  /** 区域ID */
  private String areasId;

  // @Excel(name = "设备坐标")
  private String coordinate;

  /** 纬度 */
  @Excel(name = "纬度")
  private transient String latitude;

  /** 经度 */
  @Excel(name = "经度")
  private transient String longitude;

  /** 接收额外参数 */
  @Excel(name = "其他配置")
  @Builder.Default
  private Map<String, Object> otherParams = new HashMap<>();
}
