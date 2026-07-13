package org.springblade.modules.iot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.ProductSort;
import org.springblade.modules.iot.pojo.vo.ProductSortVO;
import org.springblade.modules.iot.service.IProductSortService;
import org.springblade.modules.iot.wrapper.ProductSortWrapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product-sort")
@Tag(name = "产品分类", description = "产品分类管理接口")
public class ProductSortController extends BladeController {

	private final IProductSortService productSortService;

	@GetMapping("/list")
	@Operation(summary = "产品分类列表")
	public R<List<ProductSortVO>> list() {
		List<ProductSort> list = productSortService.treeList();
		return R.data(ProductSortWrapper.build().listVO(list));
	}

	@GetMapping("/detail")
	@ApiOperationSupport(order = 2)
	@Operation(summary = "产品分类详情")
	public R<ProductSortVO> detail(@Parameter(name = "id", description = "主键", required = true) @RequestParam Long id) {
		ProductSort entity = productSortService.getById(id);
		return R.data(ProductSortWrapper.build().entityVO(entity));
	}

	@PostMapping("/save")
	@ApiOperationSupport(order = 3)
	@Operation(summary = "新增或修改产品分类")
	public R<Boolean> save(@RequestBody ProductSort entity) {
		return R.data(productSortService.saveOrUpdate(entity));
	}

	@PostMapping("/remove")
	@ApiOperationSupport(order = 4)
	@Operation(summary = "删除产品分类")
	public R<Boolean> remove(@RequestBody List<Long> ids) {
		return R.data(productSortService.removeByIds(ids));
	}
}
