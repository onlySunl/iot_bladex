package org.springblade.modules.iot.sdk.param.ota;

import org.springblade.modules.iot.sdk.request.ota.IotNorthboundOtaReportRequest;
import org.springblade.modules.iot.sdk.response.ota.IotNorthboundOtaReportResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-OTA上报软固件版本参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
public class IotNorthboundOtaReportParam extends BaseParam<IotNorthboundOtaReportRequest, IotNorthboundOtaReportResponse> {

    @Override
    protected String method() {
        return "iot.northbound.ota.report";
    }
}
