package org.springblade.modules.iot.sdk.param.device;

import org.springblade.modules.iot.sdk.request.device.IotNorthboundDeviceDataReportRequest;
import org.springblade.modules.iot.sdk.response.device.IotNorthboundDeviceDataReportResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-设备数据上报参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundDeviceDataReportParam extends BaseParam<IotNorthboundDeviceDataReportRequest, IotNorthboundDeviceDataReportResponse> {

    @Override
    protected String method() {
        return "iot.northbound.device.dataReport";
    }
}
