package org.springblade.modules.nvr.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.ColumnTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.nvr.common.entity.CustomBaseEntity;

import java.io.Serial;

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
	@Schema(description = "设备唯一标识符")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String iotId;

	/**
	 * 设备序列号
	 */
	@Schema(description = "设备序列号")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String deviceId;

	/**
	 * 设备名称
	 */
	@Schema(description = "设备名称")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String deviceName;

	/**
	 * 设备密钥
	 */
	@Schema(description = "设备密钥")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String deviceSecret;

	/**
	 * 产品Key
	 */
	@Schema(description = "产品Key")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String productKey;

	/**
	 * 产品名称
	 */
	@Schema(description = "产品名称")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String productName;

	/**
	 * 网关产品ProductKey
	 */
	@Schema(description = "网关产品ProductKey")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String gwProductKey;

	/**
	 * 设备节点类型
	 */
	@Schema(description = "设备节点类型")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 32)
	private String deviceNode;

	/**
	 * 第三方平台
	 */
	@Schema(description = "第三方平台")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String thirdPlatform;

	/**
	 * 在线状态: 0-离线, 1-在线
	 */
	@Schema(description = "在线状态")
	private Integer state;

	/**
	 * 激活时间
	 */
	@Schema(description = "激活时间")
	private Long registryTime;

	/**
	 * 最后上线时间
	 */
	@Schema(description = "最后上线时间")
	private Long onlineTime;

	/**
	 * 第三方设备ID
	 */
	@Schema(description = "第三方设备ID")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String extDeviceId;

	/**
	 * 设备标签
	 */
	@Schema(description = "设备标签")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 256)
	private String deviceTag;

	/**
	 * 设备地址
	 */
	@Schema(description = "设备地址")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 256)
	private String deviceAddress;

	/**
	 * CSQ信号强度
	 */
	@Schema(description = "信号强度")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 32)
	private String signalStrength;

	/**
	 * 坐标
	 */
	@Schema(description = "坐标")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String coordinate;

	/**
	 * 区域ID
	 */
	@Schema(description = "区域ID")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String areasId;

	/**
	 * 派生元数据
	 */
	@Schema(description = "派生元数据")
	private String deriveMetadata;

	/**
	 * 其他配置
	 */
	@Schema(description = "其他配置")
	private String configuration;

	/**
	 * 备注
	 */
	@Schema(description = "备注")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 512)
	private String detail;

	/**
	 * 扩展字段1
	 */
	@Schema(description = "扩展字段1")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 256)
	private String ext1;

	/**
	 * 扩展字段2
	 */
	@Schema(description = "扩展字段2")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 256)
	private String ext2;

	/**
	 * 扩展字段3
	 */
	@Schema(description = "扩展字段3")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 256)
	private String ext3;

	/**
	 * 扩展字段4
	 */
	@Schema(description = "扩展字段4")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 256)
	private String ext4;

	/**
	 * 创建者ID
	 */
	@Schema(description = "创建者ID")
	private Long creatorId;

	// ---- 非持久化字段 ----

	/**
	 * 设备分组ID列表（逗号分隔）
	 */
	@Schema(description = "设备分组ID列表")
	@TableField(exist = false)
	private String devGroupId;
}
