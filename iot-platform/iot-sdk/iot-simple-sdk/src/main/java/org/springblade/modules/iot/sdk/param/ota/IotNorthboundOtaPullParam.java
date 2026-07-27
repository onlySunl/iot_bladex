package org.springblade.modules.iot.sdk.param.ota;

import org.springblade.modules.iot.sdk.request.ota.IotNorthboundOtaPullRequest;
import org.springblade.modules.iot.sdk.response.ota.IotNorthboundOtaPullResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-OTA拉取软固件信息参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundOtaPullParam extends BaseParam<IotNorthboundOtaPullRequest, IotNorthboundOtaPullResponse> {

    @Override
    protected String method() {
        return "iot.northbound.ota.pull";
    }
}
