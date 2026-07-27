package org.springblade.modules.iot.sdk.param.ota;

import org.springblade.modules.iot.sdk.request.ota.IotNorthboundOtaRejectTaskRequest;
import org.springblade.modules.iot.sdk.response.ota.IotNorthboundOtaRejectTaskResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 物联网北向API-OTA拒绝任务
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/1/6
 */
public class IotNorthboundOtaRejectTaskParam extends BaseParam<IotNorthboundOtaRejectTaskRequest, IotNorthboundOtaRejectTaskResponse> {
    @Override
    protected String method() {
        return "iot.northbound.ota.rejectTask";
    }
}
