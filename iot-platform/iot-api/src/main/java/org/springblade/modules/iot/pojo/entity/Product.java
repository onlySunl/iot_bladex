package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.ColumnTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

import java.io.Serial;

/**
 * IoT产品实体类
 * 迁移自 NexIoT - IoTProduct
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("iot_product")
@Schema(description = "IoT产品实体类")
public class Product extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 产品Key（唯一标识）
	 */
	@Schema(description = "产品Key")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String productKey;

	/**
	 * 产品密钥（一型一密）
	 */
	@Schema(description = "产品密钥")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String productSecret;

	/**
	 * 产品名称
	 */
	@Schema(description = "产品名称")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String name;

	/**
	 * 设备类型: gateway-网关, device-设备
	 */
	@Schema(description = "设备类型")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 32)
	private String deviceNode;

	/**
	 * 所属网关产品的ProductKey
	 */
	@Schema(description = "网关产品ProductKey")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String gwProductKey;

	/**
	 * 分类ID
	 */
	@Schema(description = "分类ID")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String classifiedId;

	/**
	 * 分类名称
	 */
	@Schema(description = "分类名称")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String classifiedName;

	/**
	 * 网络组件ID
	 */
	@Schema(description = "网络组件ID")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String networkUnionId;

	/**
	 * 传输协议: MQTT,COAP,UDP
	 */
	@Schema(description = "传输协议")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 32)
	private String transportProtocol;

	/**
	 * 消息协议
	 */
	@Schema(description = "消息协议")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String messageProtocol;

	/**
	 * 接入方式/第三方平台
	 */
	@Schema(description = "接入方式")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String thirdPlatform;

	/**
	 * 第三方平台配置信息
	 */
	@Schema(description = "第三方平台配置")
	private String thirdConfiguration;

	/**
	 * 协议配置
	 */
	@Schema(description = "协议配置")
	private String configuration;

	/**
	 * 物模型（JSON）
	 */
	@Schema(description = "物模型")
	private String metadata;

	/**
	 * 数据存储策略
	 */
	@Schema(description = "数据存储策略")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 32)
	private String storePolicy;

	/**
	 * 数据存储策略配置
	 */
	@Schema(description = "数据存储策略配置")
	private String storePolicyConfiguration;

	/**
	 * 图片地址
	 */
	@Schema(description = "图片地址")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 512)
	private String photoUrl;

	/**
	 * 产品标签
	 */
	@Schema(description = "产品标签")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 256)
	private String tags;

	/**
	 * 说明
	 */
	@Schema(description = "说明")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 512)
	private String describeInfo;

	/**
	 * 产品状态: 0-开发中, 1-已发布
	 */
	@Schema(description = "产品状态")
	private Integer state;

	/**
	 * 创建者ID
	 */
	@Schema(description = "创建者ID")
	private Long creatorId;
}
