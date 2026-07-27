package org.springblade.modules.iot.link.api.inner.hystrix;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.link.api.inner.OtaOpenInnerApi;
import org.springblade.modules.iot.ota.vo.param.DeviceOtaUpgradeAppConfirmationParam;
import org.springblade.modules.iot.ota.vo.result.DeviceOtaUpgradeAppConfirmationResultVO;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaCommandResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaListUpgradeableVersionsResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReadResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportResponseParam;
import org.springframework.stereotype.Component;

/**
 * Description:
 * OTA相关开放接口-API熔断
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/12/26
 */
@Component
public class OtaOpenInnerApiFallback implements OtaOpenInnerApi {

    @Override
    public R<DeviceOtaUpgradeAppConfirmationResultVO> otaUpgradeAppConfirmation(DeviceOtaUpgradeAppConfirmationParam param) {
        return R.timeout();
    }

    @Override
    public R<TopoOtaCommandResponseParam> saveOtaUpgradeRecordByMqtt(TopoOtaCommandResponseParam topoOtaCommandResponseParam) {
        return R.timeout();
    }

    @Override
    public R<TopoOtaCommandResponseParam> saveOtaUpgradeRecordByNorthbound(TopoOtaCommandResponseParam topoOtaCommandResponseParam) {
        return R.timeout();
    }

    @Override
    public R<TopoOtaPullResponseParam> otaPullByMqtt(TopoOtaPullParam topoOtaPullParam) {
        return R.timeout();
    }

    @Override
    public R<TopoOtaPullResponseParam> otaPullByNorthbound(TopoOtaPullParam topoOtaPullParam) {
        return R.timeout();
    }

    @Override
    public R<TopoOtaReportResponseParam> otaReportByMqtt(TopoOtaReportParam topoOtaReportParam) {
        return R.timeout();
    }

    @Override
    public R<TopoOtaReportResponseParam> otaReportByNorthbound(TopoOtaReportParam topoOtaReportParam) {
        return R.timeout();
    }

    @Override
    public R<?> otaReadResponseByMqtt(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        return R.timeout();
    }

    @Override
    public R<?> otaReadResponseByNorthbound(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        return R.timeout();
    }

    @Override
    public R<TopoOtaListUpgradeableVersionsResponseParam> getAvailableUpgradeVersionsByNorthbound(String deviceIdentification, Integer packageType) {
        return R.timeout();
    }
}
