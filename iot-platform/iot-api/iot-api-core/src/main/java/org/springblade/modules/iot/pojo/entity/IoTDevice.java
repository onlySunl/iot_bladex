

package org.springblade.modules.iot.pojo.entity;

import org.springblade.modules.iot.common.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_device")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDevice extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 对外设备唯一标识符 */
  @TableField(value = "iot_id")
  @AutoColumn(comment = "对外设备唯一标识符", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;

  /** 设备自身序号 */
  @Excel(name = "设备序列号")
  @TableField(value = "device_id")
  @AutoColumn(comment = "设备自身序号", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 实例名称 */
  // @Excel(name = "实例名称")
  @TableField(value = "instance")
  @AutoColumn(comment = "@Excel(name = \"实例名称\")", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String instance;

  /** 归属应用 */
  @TableField(value = "application")
  @AutoColumn(comment = "归属应用", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String application;

  /** 激活时间 */
  @TableField(value = "registry_time")
  @AutoColumn(comment = "激活时间", defaultValueType = DefaultValueEnum.NULL)
  private Integer registryTime;

  /** 最后上线时间 */
  @TableField(value = "online_time")
  @AutoColumn(comment = "最后上线时间", defaultValueType = DefaultValueEnum.NULL)
  private Long onlineTime;

  /** 第三方设备ID唯一标识符 */
  @TableField(value = "ext_device_id")
  @AutoColumn(comment = "第三方设备ID唯一标识符", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String extDeviceId;

  /** 设备名称 */
  @TableField(value = "product_name")
  @AutoColumn(comment = "设备名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productName;

  /** 网关产品ProductKey */
  @Excel(name = "网关产品ProductKey")
  @TableField(value = "gw_product_key")
  @AutoColumn(comment = "网关产品ProductKey", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String gwProductKey;

  /** 设备密钥 */
  @Excel(name = "设备密钥")
  @TableField(value = "device_secret")
  @AutoColumn(comment = "设备密钥", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceSecret;

  /** 产品key */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品key", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /** 设备实例名称 */
  @Excel(name = "设备名称")
  @TableField(value = "device_name")
  @AutoColumn(comment = "设备实例名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceName;

  @TableField(value = "creator_id")
  @AutoColumn(comment = "creatorId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;

  /** 扩展字段1 */
  @TableField(value = "ext1")
  @AutoColumn(comment = "扩展字段1", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String ext1;

  /** 扩展字段2 */
  @TableField(value = "ext2")
  @AutoColumn(comment = "扩展字段2", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String ext2;

  /** 扩展字段3 */
  @TableField(value = "ext3")
  @AutoColumn(comment = "扩展字段3", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String ext3;

  /** 扩展字段4 */
  @TableField(value = "ext4")
  @AutoColumn(comment = "扩展字段4", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String ext4;

  /** CSQ信号强度 */
  @TableField(value = "signal_strength")
  @AutoColumn(comment = "CSQ信号强度", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String signalStrength;

  /** 设备标签 */
  @TableField(value = "device_tag")
  @AutoColumn(comment = "设备标签", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deviceTag;

  /** 设备地址 */
  @TableField(value = "device_address")
  @AutoColumn(comment = "设备地址", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deviceAddress;

  /** 0-离线，1-在线 */
  @Excel(name = "在线状态")
  private Boolean state;

  /** 说明 */
  @Excel(name = "备注")
  private String detail;

  @TableField(value = "create_time")
  @AutoColumn(comment = "说明", defaultValueType = DefaultValueEnum.NULL)
  private Long createTime;

  /** 派生元数据,有的设备的属性，功能，事件可能会动态的添加 */
  @TableField(value = "derive_metadata")
  @ColumnType("text")
  @AutoColumn(comment = "派生元数据,有的设备的属性，功能，事件可能会动态的添加", defaultValueType = DefaultValueEnum.NULL)
  private String deriveMetadata;

  /** 其他配置 */
  @TableField(value = "configuration")
  @ColumnType("text")
  @AutoColumn(comment = "其他配置", defaultValueType = DefaultValueEnum.NULL)
  private String configuration;

  /** 区域ID */
  private String areasId;

  /** 坐标 */
  // @Excel(name = "设备坐标")
  private String coordinate;

  @Transient private String deviceNode;

  @Transient private String thirdPlatform;

  /** 纬度 */
  @Excel(name = "纬度")
  private transient String latitude;

  /** 经度 */
  @Excel(name = "经度")
  private transient String longitude;

  /** 请求参数 */
  @Builder.Default private Map<String, Object> params = new HashMap<>();

  /** 接收额外参数 */
  @Excel(name = "其他配置")
  @Builder.Default
  private Map<String, Object> otherParams = new HashMap<>();
}
