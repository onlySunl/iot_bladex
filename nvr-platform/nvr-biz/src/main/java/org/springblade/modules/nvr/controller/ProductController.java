package org.springblade.modules.nvr.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.core.annotation.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tenant.mp.TenantEntity;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.nvr.pojo.entity.Product;
import org.springblade.modules.nvr.pojo.vo.ProductVO;
import org.springblade.modules.nvr.service.IProductService;
import org.springblade.modules.nvr.wrapper.ProductWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * IoT产品管理 控制器
 * 迁移自 NexIoT - IoTProductController
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product")
@Tag(name = "IoT产品管理", description = "IoT产品管理接口")
public class ProductController extends BladeController {

	private final IProductService productServiceImpl;

	/**
	 * 分页查询产品列表
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询产品", description = "分页查询产品列表")
	@ApiOperationSupport(order = 1)
	public R<IPage<ProductVO>> page(Product product, QueryWrapper<Product> queryWrapper) {
		QueryWrapper<Product> qw = Func.toQueryWrapper(queryWrapper);
		if (StrUtil.isNotBlank(product.getName())) {
			qw.lambda().like(Product::getName, product.getName());
		}
		if (StrUtil.isNotBlank(product.getProductKey())) {
			qw.lambda().eq(Product::getProductKey, product.getProductKey());
		}
		if (StrUtil.isNotBlank(product.getDeviceNode())) {
			qw.lambda().eq(Product::getDeviceNode, product.getDeviceNode());
		}
		if (product.getState() != null) {
			qw.lambda().eq(Product::getState, product.getState());
		}
		qw.lambda().orderByDesc(TenantEntity::getCreateTime);
		IPage<Product> pages = productServiceImpl.page(Condition.getPage(queryWrapper), qw);
		return R.data(ProductWrapper.build().pageVO(pages));
	}

	/**
	 * 查询产品详情
	 */
	@GetMapping("/detail")
	@Operation(summary = "查询产品详情", description = "根据ID查询产品详情")
	@ApiOperationSupport(order = 2)
	public R<ProductVO> detail(@Parameter(description = "产品ID", required = true) @RequestParam Long id) {
		Product product = productServiceImpl.getById(id);
		return R.data(ProductWrapper.build().toVO(product));
	}

	/**
	 * 根据ProductKey查询产品
	 */
	@GetMapping("/get-by-key")
	@Operation(summary = "根据ProductKey查询", description = "根据ProductKey查询产品详情")
	@ApiOperationSupport(order = 3)
	public R<ProductVO> getByKey(@Parameter(description = "产品Key", required = true) @RequestParam String productKey) {
		QueryWrapper<Product> qw = new QueryWrapper<>();
		qw.lambda().eq(Product::getProductKey, productKey);
		Product product = productServiceImpl.getOne(qw);
		return R.data(ProductWrapper.build().toVO(product));
	}

	/**
	 * 新增产品
	 */
	@PostMapping("/save")
	@Operation(summary = "新增产品", description = "新增产品")
	@ApiOperationSupport(order = 4)
	public R<Boolean> save(@RequestBody Product product) {
		product.setCreateTime(new Date());
		product.setState(0);
		return R.data(productServiceImpl.save(product));
	}

	/**
	 * 修改产品
	 */
	@PostMapping("/update")
	@Operation(summary = "修改产品", description = "修改产品")
	@ApiOperationSupport(order = 5)
	public R<Boolean> update(@RequestBody Product product) {
		product.setUpdateTime(new Date());
		return R.data(productServiceImpl.updateById(product));
	}

	/**
	 * 删除产品
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除产品", description = "根据ID删除产品")
	@ApiOperationSupport(order = 6)
	public R<Boolean> remove(@Parameter(description = "产品ID", required = true) @RequestParam Long id) {
		return R.data(productServiceImpl.removeById(id));
	}

	/**
	 * 发布产品
	 */
	@PostMapping("/release-product")
	@Operation(summary = "发布产品", description = "发布产品")
	@ApiOperationSupport(order = 7)
	public R<Boolean> releaseProduct(@Parameter(description = "产品ID", required = true) @RequestParam Long id) {
		Product product = new Product();
		product.setId(id);
		product.setState(1);
		product.setUpdateTime(new Date());
		return R.data(productServiceImpl.updateById(product));
	}
}
