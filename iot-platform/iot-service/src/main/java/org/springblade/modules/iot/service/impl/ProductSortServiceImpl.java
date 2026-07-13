package org.springblade.modules.iot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springblade.core.mp.service.impl.BladeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.ProductSortMapper;
import org.springblade.modules.iot.pojo.entity.ProductSort;
import org.springblade.modules.iot.service.IProductSortService;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ProductSortServiceImpl extends BladeServiceImpl<ProductSortMapper, ProductSort> implements IProductSortService {

	@Override
	public List<ProductSort> treeList() {
		LambdaQueryWrapper<ProductSort> qw = new LambdaQueryWrapper<>();
		qw.eq(ProductSort::getIsDeleted, 0);
		qw.orderByAsc(ProductSort::getSortOrder);
		return list(qw);
	}
}
