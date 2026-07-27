package org.springblade.modules.iot.sdk.param.device;

import org.springblade.modules.iot.sdk.request.device.IotNorthboundDeviceUpdateSubDeviceConnectStatusRequest;
import org.springblade.modules.iot.sdk.response.device.IotNorthboundDeviceUpdateSubDeviceConnectStatusResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-修改子设备连接状态参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundDeviceUpdateSubDeviceConnectStatusParam extends BaseParam<IotNorthboundDeviceUpdateSubDeviceConnectStatusRequest, IotNorthboundDeviceUpdateSubDeviceConnectStatusResponse> {

    @Override
    protected String method() {
        return "iot.northbound.device.updateSubDeviceConnectStatus";
    }
}
