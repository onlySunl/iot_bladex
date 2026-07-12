package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.ColumnComment;
import com.tangzc.autotable.annotation.Index;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

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

	private static final long serialVersionUID = 1L;

	@Schema(description = "分类名称")
	private String name;

	@Schema(description = "父分类ID")
	@Index
	private Long parentId;

	@Schema(description = "排序")
	private Integer sort;

	@Schema(description = "备注")
	private String remark;

}
