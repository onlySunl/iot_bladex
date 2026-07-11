package org.springblade.modules.nvr.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.ColumnTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.nvr.common.entity.CustomBaseEntity;

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
	@Schema(description = "规则名称")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String ruleName;

	/**
	 * 数据级别: PRODUCT-产品, DEVICE-设备, GROUP-分组
	 */
	@Schema(description = "数据级别")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 32)
	private String dataLevel;

	/**
	 * 描述
	 */
	@Schema(description = "描述")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 512)
	private String description;

	/**
	 * 状态: start-启用, stop-停用
	 */
	@Schema(description = "状态")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 16)
	private String status;

	/**
	 * 产品KEY
	 */
	@Schema(description = "产品KEY")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String productKey;

	/**
	 * 规则配置（JSON）
	 */
	@Schema(description = "规则配置")
	private String config;

	/**
	 * 创建者ID
	 */
	@Schema(description = "创建者ID")
	private Long creatorId;
}
