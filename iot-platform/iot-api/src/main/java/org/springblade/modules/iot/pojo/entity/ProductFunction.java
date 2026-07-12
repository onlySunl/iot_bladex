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
	@TableField(value = "product_key")
	@AutoColumn(comment = "产品KEY", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String productKey;

	/**
	 * 标签: property-属性, event-事件, function-功能
	 */
	@TableField(value = "tag")
	@AutoColumn(comment = "功能标签", length = 32, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String tag;

	/**
	 * 功能名称
	 */
	@TableField(value = "name")
	@AutoColumn(comment = "功能名称", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String name;

	/**
	 * 别名
	 */
	@TableField(value = "alias")
	@AutoColumn(comment = "别名", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String alias;

	/**
	 * 访问模式: r-读, rw-读写
	 */
	@TableField(value = "access_mode")
	@AutoColumn(comment = "访问模式", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String accessMode;

	/**
	 * 描述
	 */
	@TableField(value = "description")
	@AutoColumn(comment = "描述", length = 512, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String description;

	/**
	 * 输入参数（JSON）
	 */
	@TableField(value = "input")
	@AutoColumn(comment = "输入参数",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String input;

	/**
	 * 输出参数（JSON）
	 */
	@TableField(value = "output")
	@AutoColumn(comment = "输出参数",  defaultValueType = DefaultValueEnum.EMPTY_STRING)
	private String output;

}
