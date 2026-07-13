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
	@TableField(value = "product_key")
	@AutoColumn(comment = "产品KEY", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String productKey;

	/**
	 * 产品密钥（一型一密）
	 */
	@TableField(value = "product_secret")
	@AutoColumn(comment = "产品密钥", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String productSecret;

	/**
	 * 产品名称
	 */

	@TableField(value = "name")
	@AutoColumn(comment = "产品名称", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String name;

	/**
	 * 设备类型: gateway-网关, device-设备
	 */
	@TableField(value = "device_node")
	@AutoColumn(comment = "设备类型", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String deviceNode;

	/**
	 * 所属网关产品的ProductKey
	 */

	@TableField(value = "gw_product_key")
	@AutoColumn(comment = "网关产品ProductKey", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String gwProductKey;

	/**
	 * 分类ID
	 */
	@TableField(value = "classified_id")
	@AutoColumn(comment = "分类ID", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String classifiedId;

	/**
	 * 分类名称
	 */
	@TableField(value = "classified_name")
	@AutoColumn(comment = "分类名称", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String classifiedName;

	/**
	 * 网络组件ID
	 */
	@TableField(value = "network_union_id")
	@AutoColumn(comment = "网络组件ID", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String networkUnionId;

	/**
	 * 传输协议: MQTT,COAP,UDP
	 */
	@TableField(value = "transport_protocol")
	@AutoColumn(comment = "传输协议", length = 32, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String transportProtocol;

	/**
	 * 消息协议
	 */
	@TableField(value = "message_protocol")
	@AutoColumn(comment = "消息协议", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String messageProtocol;

	/**
	 * 接入方式/第三方平台
	 */
	@TableField(value = "third_platform")
	@AutoColumn(comment = "接入方式", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String thirdPlatform;

	/**
	 * 第三方平台配置信息
	 */
	@TableField(value = "third_configuration")
	@AutoColumn(comment = "第三方平台配置",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String thirdConfiguration;

	/**
	 * 协议配置
	 */
	@TableField(value = "configuration")
	@AutoColumn(comment = "协议配置",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String configuration;

	/**
	 * 物模型（JSON）
	 */
	@TableField(value = "metadata")
	@AutoColumn(comment = "物模型",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String metadata;

	/**
	 * 数据存储策略
	 */

	@TableField(value = "store_policy")
	@AutoColumn(comment = "数据存储策略", length = 32,  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String storePolicy;

	/**
	 * 数据存储策略配置
	 */
	@TableField(value = "store_policy_configuration")
	@AutoColumn(comment = "数据存储策略配置",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String storePolicyConfiguration;

	/**
	 * 图片地址
	 */

	@TableField(value = "photo_url")
	@AutoColumn(comment = "图片地址",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String photoUrl;

	/**
	 * 产品标签
	 */
	@TableField(value = "tags")
	@AutoColumn(comment = "产品标签",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String tags;

	/**
	 * 说明
	 */

	@TableField(value = "describe_info")
	@AutoColumn(comment = "说明",  length = 512,defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String describeInfo;

}
