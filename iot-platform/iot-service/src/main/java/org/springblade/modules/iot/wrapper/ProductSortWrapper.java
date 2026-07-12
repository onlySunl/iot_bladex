package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.ProductSort;
import org.springblade.modules.iot.pojo.vo.ProductSortVO;

public class ProductSortWrapper extends BaseEntityWrapper<ProductSort, ProductSortVO> {

	public static ProductSortWrapper build() {
		return new ProductSortWrapper();
	}

	@Override
	public ProductSortVO entityVO(ProductSort entity) {
		return Func.copyProperties(entity, ProductSortVO.class);
	}
}
