package org.springblade.modules.iot.cache.product;

import org.springblade.modules.iot.cache.CacheSuperAbstract;
import org.springblade.modules.iot.cache.vo.product.ProductModelCacheVO;
import org.springframework.stereotype.Service;

/**
 * 产品模型缓存服务
 */
@Service
public class ProductModelCacheService extends CacheSuperAbstract {
    
    public ProductModelCacheVO getProductModel(String productId) {
        // TODO: 从缓存获取产品模型
        return null;
    }
    
    public void putProductModel(ProductModelCacheVO model) {
        // TODO: 缓存产品模型
    }
    
    public void evictProductModel(String productId) {
        // TODO: 清除产品模型缓存
    }
}
