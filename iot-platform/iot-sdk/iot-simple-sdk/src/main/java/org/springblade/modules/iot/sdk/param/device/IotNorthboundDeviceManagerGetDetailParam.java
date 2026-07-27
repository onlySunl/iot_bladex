package org.springblade.modules.iot.sdk.param.device;

import org.springblade.modules.iot.sdk.request.device.IotNorthboundDeviceGetDetailRequest;
import org.springblade.modules.iot.sdk.response.device.IotNorthboundDeviceGetDetailResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-查询设备详情参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/01/22
 */
public class IotNorthboundDeviceManagerGetDetailParam extends BaseParam<IotNorthboundDeviceGetDetailRequest, IotNorthboundDeviceGetDetailResponse> {


    @Override
    protected String method() {
        return "iot.northbound.device.getDetail";
    }
}
