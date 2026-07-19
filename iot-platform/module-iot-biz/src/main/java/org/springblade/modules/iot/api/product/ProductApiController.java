package org.springblade.modules.iot.api.product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.product.ProductApi;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 产品对外API控制器，与RemoteIotProductService接口一一对应
 */
@RestController
@RequestMapping("/productApi")
@Tag(name = "产品对外API", description = "产品基础信息、缓存查询接口")
public class ProductApiController extends BladeController {

    @Resource
    private ProductApi productApi;

    @GetMapping("/getProduct")
    @Operation(summary = "根据产品Key查询数据库产品信息")
    public Product getProduct(@Parameter(description = "产品唯一标识productKey") @RequestParam String pk) {
        return productApi.getProduct(pk);
    }

    @GetMapping("/getProductByPkFromCache")
    @Operation(summary = "根据产品Key从缓存获取产品信息")
    public Product getProductByPkFromCache(@Parameter(description = "产品唯一标识productKey") @RequestParam String pk) {
        return productApi.getProductByPkFromCache(pk);
    }
}