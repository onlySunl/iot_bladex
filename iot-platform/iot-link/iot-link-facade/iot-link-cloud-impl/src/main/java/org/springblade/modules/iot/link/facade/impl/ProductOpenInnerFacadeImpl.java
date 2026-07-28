package org.springblade.modules.iot.link.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.link.api.inner.ProductOpenInnerApi;
import org.springblade.modules.iot.link.facade.ProductOpenInnerFacade;
import org.springblade.modules.iot.product.vo.param.ProductParamVO;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 产品-开放接口API实现类
 *
 * @author mqttsnet
 * @date 2025-06-22
 */
@Service
public class ProductOpenInnerFacadeImpl implements ProductOpenInnerFacade {
    @Lazy
    @Autowired
    private ProductOpenInnerApi productOpenInnerApi;

    @Override
    public R<ProductResultVO> getProductDetailByNorthbound(String productIdentification) {
        return productOpenInnerApi.getProductDetailByNorthbound(productIdentification);
    }

    @Override
    public R<ProductParamVO> getProductThingModelByNorthbound(String productIdentification) {
        return productOpenInnerApi.getProductThingModelByNorthbound(productIdentification);
    }
}
