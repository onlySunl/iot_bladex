package org.springblade.modules.iot.wrapper;

import org.springblade.core.tenant.TenantCache;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.modules.iot.pojo.entity.ProductSort;
import org.springblade.modules.iot.pojo.vo.ProductSortVO;
import org.springblade.system.wrapper.BaseEntityWrapper;
import java.util.Objects;

public class ProductSortWrapper extends BaseEntityWrapper<ProductSort, ProductSortVO> {

	public static ProductSortWrapper build() {
		return new ProductSortWrapper();
	}

	@Override
	public ProductSortVO entityVO(ProductSort entity) {
		ProductSortVO vo = new ProductSortVO();
		Func.copyProperties(entity, vo);
		return vo;
	}
}
