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
 * IoT产品物模型功能定义实体类
 * 迁移自 NexIoT - IoTDeviceFunction / ProductFunction
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("iot_product_function")
@Schema(description = "IoT产品功能定义实体类")
public class ProductFunction extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 产品Key
	 */
	@Schema(description = "产品Key")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 64)
	private String productKey;

	/**
	 * 标签: property-属性, event-事件, function-功能
	 */
	@Schema(description = "功能标签")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 32)
	private String tag;

	/**
	 * 功能名称
	 */
	@Schema(description = "功能名称")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String name;

	/**
	 * 别名
	 */
	@Schema(description = "别名")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 128)
	private String alias;

	/**
	 * 访问模式: r-读, rw-读写
	 */
	@Schema(description = "访问模式")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 16)
	private String accessMode;

	/**
	 * 描述
	 */
	@Schema(description = "描述")
	@ColumnType(value = ColumnTypeEnum.VARCHAR, length = 512)
	private String description;

	/**
	 * 输入参数（JSON）
	 */
	@Schema(description = "输入参数")
	private String input;

	/**
	 * 输出参数（JSON）
	 */
	@Schema(description = "输出参数")
	private String output;

	/**
	 * 创建者ID
	 */
	@Schema(description = "创建者ID")
	private Long creatorId;
}
