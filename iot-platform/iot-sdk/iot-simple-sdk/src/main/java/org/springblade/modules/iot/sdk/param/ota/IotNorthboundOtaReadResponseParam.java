package org.springblade.modules.iot.sdk.param.ota;

import org.springblade.modules.iot.sdk.request.ota.IotNorthboundOtaReadResponseRequest;
import org.springblade.modules.iot.sdk.response.ota.IotNorthboundOtaReadResponseResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-OTA读取设备软固件版本信息响应参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundOtaReadResponseParam extends BaseParam<IotNorthboundOtaReadResponseRequest, IotNorthboundOtaReadResponseResponse> {

    @Override
    protected String method() {
        return "iot.northbound.ota.readResponse";
    }
}
