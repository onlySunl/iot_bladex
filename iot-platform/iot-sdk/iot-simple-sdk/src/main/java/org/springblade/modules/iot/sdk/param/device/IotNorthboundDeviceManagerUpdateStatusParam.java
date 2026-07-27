package org.springblade.modules.iot.sdk.param.device;

import org.springblade.modules.iot.sdk.request.device.IotNorthboundDeviceUpdateStatusRequest;
import org.springblade.modules.iot.sdk.response.device.IotNorthboundDeviceUpdateStatusResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-修改设备状态参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundDeviceManagerUpdateStatusParam extends BaseParam<IotNorthboundDeviceUpdateStatusRequest, IotNorthboundDeviceUpdateStatusResponse> {

    @Override
    protected String method() {
        return "iot.northbound.device.updateStatus";
    }
}
