package org.springblade.modules.nvr.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.nvr.pojo.entity.Product;
import org.springblade.modules.nvr.pojo.vo.ProductVO;

import java.util.Objects;

/**
 * IoT产品包装类
 */
public class ProductWrapper extends BaseEntityWrapper<Product, ProductVO> {

	public static ProductWrapper build() {
		return new ProductWrapper();
	}

	@Override
	public ProductVO entityVO(Product product) {
		ProductVO productVO = Objects.requireNonNull(BeanUtil.copyProperties(product, ProductVO.class));
		return productVO;
	}
}
