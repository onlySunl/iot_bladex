package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.ProductSort;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "ProductSortVO")
public class ProductSortVO extends ProductSort {
	private static final long serialVersionUID = 1L;
	@Schema(description = "子分类数量")
	private Integer childCount;
}
