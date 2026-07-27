package org.springblade.modules.iot.sdk.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.sdk.request.PayTradeWapPayRequest;
import org.springblade.modules.iot.sdk.response.PayTradeWapPayResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * pay.trade.wap.pay(手机网站支付接口)
 *
 * @author 六如
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PayTradeWapPayParam extends BaseParam<PayTradeWapPayRequest, PayTradeWapPayResponse> {
    @Override
    protected String method() {
        return "openapi.wap.pay";
    }
}
