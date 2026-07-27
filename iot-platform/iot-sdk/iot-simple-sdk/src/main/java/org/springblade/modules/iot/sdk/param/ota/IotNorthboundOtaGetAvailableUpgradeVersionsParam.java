package org.springblade.modules.iot.sdk.param.ota;

import org.springblade.modules.iot.sdk.request.ota.IotNorthboundOtaListUpgradeableVersionsRequest;
import org.springblade.modules.iot.sdk.response.ota.IotNorthboundOtaGetAvailableUpgradeVersionsResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * Description:
 * 北向API-OTA获取可用升级版本参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/09
 */
public class IotNorthboundOtaGetAvailableUpgradeVersionsParam extends BaseParam<IotNorthboundOtaListUpgradeableVersionsRequest, IotNorthboundOtaGetAvailableUpgradeVersionsResponse> {

    @Override
    protected String method() {
        return "iot.northbound.ota.getAvailableUpgradeVersions";
    }
}
