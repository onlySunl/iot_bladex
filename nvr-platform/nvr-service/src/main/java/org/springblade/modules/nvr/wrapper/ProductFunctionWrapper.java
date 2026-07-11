package org.springblade.modules.nvr.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.nvr.pojo.entity.ProductFunction;
import org.springblade.modules.nvr.pojo.vo.ProductFunctionVO;

import java.util.Objects;

/**
 * IoT产品功能定义包装类
 */
public class ProductFunctionWrapper extends BaseEntityWrapper<ProductFunction, ProductFunctionVO> {

	public static ProductFunctionWrapper build() {
		return new ProductFunctionWrapper();
	}

	@Override
	public ProductFunctionVO entityVO(ProductFunction function) {
		ProductFunctionVO vo = Objects.requireNonNull(BeanUtil.copyProperties(function, ProductFunctionVO.class));
		return vo;
	}
}
