package org.springblade.modules.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.pojo.entity.ProductSort;
import java.util.List;

public interface IProductSortService extends IService<ProductSort> {
	List<ProductSort> treeList();
}
