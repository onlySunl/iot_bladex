package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.Index;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;

/**
 * 产品分类 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_product_sort")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "ProductSort对象")
public class ProductSort extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 分类名称
	 */
	@TableField(value = "name")
	@AutoColumn(comment = "分类名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String name;

	/**
	 * 父分类ID
	 */
	@TableField(value = "parent_id")
	@AutoColumn(comment = "父分类ID", defaultValueType = DefaultValueEnum.NULL)
	@Index
	private Long parentId;

	/**
	 * 排序
	 */
	@TableField(value = "sort")
	@AutoColumn(comment = "排序", defaultValueType = DefaultValueEnum.NULL)
	private Integer sort;

	/**
	 * 备注
	 */
	@TableField(value = "remark")
	@AutoColumn(comment = "备注", length = 512, defaultValueType = DefaultValueEnum.NULL)
	private String remark;

}
