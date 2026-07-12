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
 * IoT规则模型实体类
 * 迁移自 NexIoT - RuleModel
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("iot_rule_model")
@Schema(description = "IoT规则模型实体类")
public class RuleModel extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 规则名称
	 */
	@TableField(value = "rule_name")
	@AutoColumn(comment = "规则名称", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String ruleName;

	/**
	 * 数据级别: PRODUCT-产品, DEVICE-设备, GROUP-分组
	 */
	@TableField(value = "data_level")
	@AutoColumn(comment = "数据级别", length = 32, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String dataLevel;

	/**
	 * 描述
	 */
	@TableField(value = "description")
	@AutoColumn(comment = "描述", length = 512, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String description;


	/**
	 * 产品KEY
	 */
	@TableField(value = "product_key")
	@AutoColumn(comment = "产品KEY", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String productKey;

	/**
	 * 规则配置（JSON）
	 */
	@TableField(value = "config")
	@AutoColumn(comment = "规则配置", defaultValueType = DefaultValueEnum.NULL)
	private String config;

}
