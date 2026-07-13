package org.springblade.modules.iot.service;

import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.pojo.entity.ProductSort;
import java.util.List;

public interface IProductSortService extends BladeService<ProductSort> {
	List<ProductSort> treeList();
}
