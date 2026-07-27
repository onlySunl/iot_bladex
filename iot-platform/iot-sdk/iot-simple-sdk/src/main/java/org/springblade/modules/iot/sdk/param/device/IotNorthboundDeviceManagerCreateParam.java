package org.springblade.modules.iot.sdk.param.device;

import org.springblade.modules.iot.sdk.request.device.IotNorthboundDeviceManagerCreateRequest;
import org.springblade.modules.iot.sdk.response.device.IotNorthboundDeviceManagerCreateResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-创建设备参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/23
 */
public class IotNorthboundDeviceManagerCreateParam extends BaseParam<IotNorthboundDeviceManagerCreateRequest, IotNorthboundDeviceManagerCreateResponse> {
    @Override
    protected String method() {
        return "iot.northbound.device.create";
    }

}
