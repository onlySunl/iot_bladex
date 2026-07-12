package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * IoT设备实体类
 * 迁移自 NexIoT - IoTDevice
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("iot_device")
@Schema(description = "IoT设备实体类")
public class Device extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 设备唯一标识符
	 */
	@TableField(value = "iot_id")
	@AutoColumn(comment = "设备唯一标识符",length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String iotId;

	/**
	 * 设备序列号
	 */

	@TableField(value = "device_id")
	@AutoColumn(comment = "设备序列号设备序列号",length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String deviceId;

	/**
	 * 设备名称
	 */

	@TableField(value = "device_name")
	@AutoColumn(comment = "设备名称",length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String deviceName;

	/**
	 * 设备密钥
	 */

	@TableField(value = "device_secret")
	@AutoColumn(comment = "设备密钥",length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String deviceSecret;

	/**
	 * 产品Key
	 */
	@TableField(value = "product_key")
	@AutoColumn(comment = "产品Key",length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String productKey;

	/**
	 * 产品名称
	 */
	@TableField(value = "product_name")
	@AutoColumn(comment = "产品名称",length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String productName;

	/**
	 * 网关产品ProductKey
	 */

	@TableField(value = "gw_product_key")
	@AutoColumn(comment = "网关产品ProductKey",length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String gwProductKey;

	/**
	 * 设备节点类型
	 */
	@TableField(value = "device_node")
	@AutoColumn(comment = "设备节点类型",length = 32, defaultValueType = DefaultValueEnum.NULL)
	private String deviceNode;

	/**
	 * 第三方平台
	 */

	@TableField(value = "third_platform")
	@AutoColumn(comment = "第三方平台",length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String thirdPlatform;

	/**
	 * 在线状态: 0-离线, 1-在线
	 */

	@TableField(value = "state")
	@AutoColumn(comment = "在线状态",length = 1, defaultValueType = DefaultValueEnum.NULL)
	private Integer state;

	/**
	 * 激活时间
	 */
	@TableField(value = "registry_time")
	@AutoColumn(comment = "激活时间", defaultValueType = DefaultValueEnum.NULL)
	private Date registryTime;

	/**
	 * 最后上线时间
	 */
	@TableField(value = "online_time")
	@AutoColumn(comment = "最后上线时间", defaultValueType = DefaultValueEnum.NULL)
	private Long onlineTime;

	/**
	 * 第三方设备ID
	 */
	@TableField(value = "ext_device_id")
	@AutoColumn(comment = "第三方设备ID", defaultValueType = DefaultValueEnum.NULL)
	private String extDeviceId;

	/**
	 * 设备标签
	 */

	@TableField(value = "device_tag")
	@AutoColumn(comment = "设备标签", length = 256, defaultValueType = DefaultValueEnum.NULL)
	private String deviceTag;

	/**
	 * 设备地址
	 */

	@TableField(value = "device_address")
	@AutoColumn(comment = "设备地址", length = 256, defaultValueType = DefaultValueEnum.NULL)
	private String deviceAddress;

	/**
	 * CSQ信号强度
	 */

	@TableField(value = "signal_strength")
	@AutoColumn(comment = "信号强度", length = 32, defaultValueType = DefaultValueEnum.NULL)
	private String signalStrength;

	/**
	 * 坐标
	 */

	@TableField(value = "coordinate")
	@AutoColumn(comment = "坐标", length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String coordinate;

	/**
	 * 区域ID
	 */

	@TableField(value = "areas_id")
	@AutoColumn(comment = "区域ID", length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String areasId;

	/**
	 * 派生元数据
	 */
	@TableField(value = "derive_meta_data")
	@AutoColumn(comment = "派生元数据",  defaultValueType = DefaultValueEnum.NULL)
	private String deriveMetaData;

	/**
	 * 其他配置
	 */
	@TableField(value = "configuration")
	@AutoColumn(comment = "其他配置",  defaultValueType = DefaultValueEnum.NULL)
	private String configuration;

	/**
	 * 备注
	 */
	@TableField(value = "detail")
	@AutoColumn(comment = "备注", length = 512,  defaultValueType = DefaultValueEnum.NULL)
	private String detail;
	// ---- 非持久化字段 ----

	/**
	 * 设备分组ID列表（逗号分隔）
	 */
	@Schema(description = "设备分组ID列表")
	@TableField(exist = false)
	private String devGroupId;
}
