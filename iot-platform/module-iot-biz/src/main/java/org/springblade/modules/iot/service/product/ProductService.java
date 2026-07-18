

package org.springblade.modules.iot.service.product;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.product.dto.ProductConfig;
import org.springblade.modules.iot.controller.admin.product.vo.ProductPageReqVO;
import org.springblade.modules.iot.controller.admin.product.vo.ProductSaveReqVO;
import org.springblade.modules.iot.controller.admin.product.vo.ProductUpdateReqVO;

import jakarta.validation.Valid;

/**
 * 物联网产品 Service 接口
 *
 * @author EnjoyIot
 */
public interface ProductService {

    /**
     * 创建物联网产品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProduct(@Valid ProductSaveReqVO createReqVO);

    /**
     * 更新物联网产品
     *
     * @param updateReqVO 更新信息
     */
    void updateProduct(@Valid ProductUpdateReqVO updateReqVO);

    /**
     * 删除物联网产品
     *
     * @param id 编号
     */
    Boolean deleteProduct(Long id);

    /**
     * 获得物联网产品
     *
     * @param id 编号
     * @return 物联网产品
     */
    Product getProduct(Long id);

    Product getByPk(String pk);

    /**
     * 获得物联网产品分页
     *
     * @param pageReqVO 分页查询
     * @return 物联网产品分页
     */
    PageResult<Product> getProductPage(ProductPageReqVO pageReqVO);

    Product getProductByPkFromCache(String pk);

    ProductConfig getConfigByPk(String pk);

    boolean saveConfig(ProductConfig request);
}
