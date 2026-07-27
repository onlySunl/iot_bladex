package org.springblade.modules.iot.sdk.param.product;

import org.springblade.modules.iot.sdk.request.product.IotNorthboundProductGetThingModelRequest;
import org.springblade.modules.iot.sdk.response.product.IotNorthboundProductGetThingModelResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-查询产品物模型参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundProductManagerGetThingModelParam extends BaseParam<IotNorthboundProductGetThingModelRequest, IotNorthboundProductGetThingModelResponse> {

    @Override
    protected String method() {
        return "iot.northbound.product.getThingModel";
    }
}
