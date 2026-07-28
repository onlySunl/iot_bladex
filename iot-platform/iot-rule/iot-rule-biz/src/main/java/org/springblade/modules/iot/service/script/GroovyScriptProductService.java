package org.springblade.modules.iot.service.script;


import org.springblade.modules.iot.dto.script.ProductInfoDTO;

/**
 * 商品service
 *
 * @author mqttsnet
 */
public interface GroovyScriptProductService {

    /**
     * 通过ID获取商品
     *
     * @param id id
     * @return {@link ProductInfoDTO}
     */
    ProductInfoDTO getProductById(Integer id);

    /**
     * 更新商品
     *
     * @param productInfo 商品
     */
    void updateProduct(ProductInfoDTO productInfo);
}
