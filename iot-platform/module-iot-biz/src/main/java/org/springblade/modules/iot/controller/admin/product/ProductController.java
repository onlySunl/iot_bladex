package org.springblade.modules.iot.controller.admin.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.product.dto.ProductConfig;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.product.vo.*;
import org.springblade.modules.iot.controller.convert.ProductBizConvert;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.product.IProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 管理后台 - 物联网产品 Controller
 *
 * <p>遵循 BladeX 4.9.0 规范：</p>
 * <ul>
 *   <li>继承 BladeController，使用 R 统一返回</li>
 *   <li>使用 Swagger3 OpenAPI 注解</li>
 *   <li>参数校验使用 @Valid/@Validated</li>
 * </ul>
 *
 * @author EnjoyIot
 */
@Slf4j
@Tag(name = "管理后台 - 物联网产品")
@RestController
@RequestMapping("/eiot/product")
@Validated
public class ProductController extends BladeController {

    @Resource
    private IProductService productService;

    // ======================== 增删改 ========================

    @PostMapping("/create")
    @Operation(summary = "创建物联网产品")
    public R<Long> createProduct(@Valid @RequestBody ProductSaveReqVO createReqVO) {
        return data(productService.createProduct(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物联网产品")
    public R<Boolean> updateProduct(@Valid @RequestBody ProductUpdateReqVO updateReqVO) {
        productService.updateProduct(updateReqVO);
        return data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物联网产品")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return data(true);
    }

    // ======================== 查询 ========================

    @GetMapping("/get")
    @Operation(summary = "获得物联网产品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<ProductRespVO> getProduct(@RequestParam("id") Long id) {
        Product product = productService.getProduct(id);
        return data(BeanUtils.toBean(product, ProductRespVO.class));
    }

    @GetMapping("/getByPk")
    @Operation(summary = "根据 productKey 获得物联网产品")
    @Parameter(name = "pk", description = "productKey", required = true, example = "a1b2c3d4")
    public R<ProductRespVO> getProductByPk(@RequestParam("pk") String pk) {
        Product product = productService.getByPk(pk);
        return data(BeanUtils.toBean(product, ProductRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物联网产品分页")
    public R<PageResult<ProductRespVO>> getProductPage(@Valid ProductPageReqVO pageReqVO) {
        PageResult<Product> pageResult = productService.getProductPage(pageReqVO);
        return data(BeanUtils.toBean(pageResult, ProductRespVO.class));
    }

    @PostMapping("/list")
    @Operation(summary = "获得物联网产品列表（不分页）")
    public R<List<ProductRespVO>> getProductList(@RequestBody ProductPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Product> list = productService.getProductPage(pageReqVO).getList();
        return data(BeanUtils.toBean(list, ProductRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物联网产品 Excel")
    public void exportProductExcel(@Valid ProductPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Product> list = productService.getProductPage(pageReqVO).getList();
        ExcelUtils.write(response, "物联网产品.xls", "数据", ProductRespVO.class,
                BeanUtils.toBean(list, ProductRespVO.class));
    }

    // ======================== 产品配置 ========================

    @GetMapping("/getConfig")
    @Operation(summary = "获取产品配置")
    @Parameter(name = "pk", description = "productKey", required = true, example = "a1b2c3d4")
    public R<ProductConfigVo> getConfig(@RequestParam("pk") String pk) {
        ProductConfig config = productService.getConfigByPk(pk);
        return data(ProductBizConvert.INSTANCE.convertVO(config));
    }

    @PostMapping("/saveConfig")
    @Operation(summary = "保存产品配置")
    public R<Boolean> saveConfig(@RequestBody ProductConfigBo request) {
        ProductConfig config = ProductBizConvert.INSTANCE.convert(request);
        return data(productService.saveConfig(config));
    }
}
