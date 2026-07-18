

package org.springblade.modules.iot.api.product;

import org.springblade.modules.iot.api.product.dto.Product;

public interface ProductApi {

    Product getProduct(String pk);

    Product getProductByPkFromCache(String pk);
    
}
