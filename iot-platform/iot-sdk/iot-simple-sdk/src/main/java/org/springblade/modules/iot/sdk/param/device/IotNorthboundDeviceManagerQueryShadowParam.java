package org.springblade.modules.iot.sdk.param.device;

import org.springblade.modules.iot.sdk.request.device.IotNorthboundDeviceQueryShadowRequest;
import org.springblade.modules.iot.sdk.response.device.IotNorthboundDeviceQueryShadowResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-查询设备影子参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/22
 */
public class IotNorthboundDeviceManagerQueryShadowParam extends BaseParam<IotNorthboundDeviceQueryShadowRequest, IotNorthboundDeviceQueryShadowResponse> {

    @Override
    protected String method() {
        return "iot.northbound.device.queryShadow";
    }
}
