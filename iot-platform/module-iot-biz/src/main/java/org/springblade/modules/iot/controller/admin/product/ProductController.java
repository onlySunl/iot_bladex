package org.springblade.modules.iot.controller.admin.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.product.dto.ProductConfig;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.product.vo.*;
import org.springblade.modules.iot.controller.convert.ProductBizConvert;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.product.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springblade.modules.iot.common.entity.CommonResult.success;
import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 物联网产品")
@RestController
@RequestMapping("/eiot/product")
@Validated
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/create")
    @Operation(summary = "创建物联网产品")
    public CommonResult<Long> createProduct(@Valid @RequestBody ProductSaveReqVO createReqVO) {
        return success(productService.createProduct(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物联网产品")
    public CommonResult<Boolean> updateProduct(@Valid @RequestBody ProductUpdateReqVO updateReqVO) {
        productService.updateProduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物联网产品")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物联网产品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<ProductRespVO> getProduct(@RequestParam("id") Long id) {
        Product product = productService.getProduct(id);
        return success(BeanUtils.toBean(product, ProductRespVO.class));
    }

    @GetMapping("/getByPk")
    @Operation(summary = "获得物联网产品")
    @Parameter(name = "pk", description = "编号", required = true, example = "1024")
    public CommonResult<ProductRespVO> getProduct(@RequestParam("pk") String pk) {
        Product product = productService.getByPk(pk);
        return success(BeanUtils.toBean(product, ProductRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物联网产品分页")
    public CommonResult<PageResult<ProductRespVO>> getProductPage(@Valid ProductPageReqVO pageReqVO) {
        PageResult<Product> pageResult = productService.getProductPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProductRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物联网产品 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProductExcel(@Valid ProductPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Product> list = productService.getProductPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "物联网产品.xls", "数据", ProductRespVO.class,
                        BeanUtils.toBean(list, ProductRespVO.class));
    }

    /**
     * 获取产品配置详细信息
     *
     */
    @GetMapping("/getConfig")
    @Parameter(name = "pk", description = "productKey", required = true, example = "1024")
    public ProductConfigVo getDetail(@RequestParam("pk") String pk) {
        return ProductBizConvert.INSTANCE.convertVO( productService.getConfigByPk(pk));
    }


    /**
     * 修改产品配置
     */
    @PostMapping("/saveConfig")
    public boolean saveConfig( ProductConfigBo request) {
        ProductConfig config = ProductBizConvert.INSTANCE.convert(request);
        return productService.saveConfig(config);
    }
}
