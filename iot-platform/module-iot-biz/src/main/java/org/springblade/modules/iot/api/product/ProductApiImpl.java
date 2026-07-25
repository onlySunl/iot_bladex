

package org.springblade.modules.iot.api.product;

import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.common.utils.TenantUtils;
import org.springblade.modules.iot.service.product.IProductService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class ProductApiImpl implements ProductApi {

    @Resource
    private IProductService productService;

    @Override
    public Product getProduct(String pk) {
        return TenantUtils.executeIgnoreResult(() -> productService.getByPk(pk));
    }

    @Override
    public Product getProductByPkFromCache(String pk) {
        return TenantUtils.executeIgnoreResult(() -> productService.getProductByPkFromCache(pk));
    }

}
