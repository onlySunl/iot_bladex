package org.springblade.modules.iot.link.api.inner.hystrix;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.link.api.inner.ProductOpenInnerApi;
import org.springblade.modules.iot.product.vo.param.ProductParamVO;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;

/**
 * 产品开放接口降级处理
 *
 * @author mqttsnet
 * @date 2025-06-22
 */
public class ProductOpenInnerApiFallback implements ProductOpenInnerApi {
    @Override
    public R<ProductResultVO> getProductDetailByNorthbound(String productIdentification) {
        return R.timeout();
    }

    @Override
    public R<ProductParamVO> getProductThingModelByNorthbound(String productIdentification) {
        return R.timeout();
    }
}
