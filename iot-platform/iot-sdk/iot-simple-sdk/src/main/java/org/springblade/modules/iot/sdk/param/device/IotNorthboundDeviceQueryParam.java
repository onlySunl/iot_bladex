package org.springblade.modules.iot.sdk.param.device;

import org.springblade.modules.iot.sdk.request.device.IotNorthboundDeviceQueryRequest;
import org.springblade.modules.iot.sdk.response.device.IotNorthboundDeviceQueryResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-查询设备信息参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundDeviceQueryParam extends BaseParam<IotNorthboundDeviceQueryRequest, IotNorthboundDeviceQueryResponse> {

    @Override
    protected String method() {
        return "iot.northbound.device.query";
    }
}
