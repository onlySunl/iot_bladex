package org.springblade.modules.iot.sdk.param.ota;

import org.springblade.modules.iot.sdk.request.ota.IotNorthboundOtaConfirmTaskRequest;
import org.springblade.modules.iot.sdk.response.ota.IotNorthboundOtaConfirmTaskResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 物联网北向API-OTA确认任务
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/1/6
 */
public class IotNorthboundOtaConfirmTaskParam extends BaseParam<IotNorthboundOtaConfirmTaskRequest, IotNorthboundOtaConfirmTaskResponse> {
    @Override
    protected String method() {
        return "iot.northbound.ota.confirmTask";
    }
}
