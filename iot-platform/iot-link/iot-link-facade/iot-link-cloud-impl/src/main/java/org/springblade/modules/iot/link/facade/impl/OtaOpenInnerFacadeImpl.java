package org.springblade.modules.iot.link.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.link.api.inner.OtaOpenInnerApi;
import org.springblade.modules.iot.link.facade.OtaOpenInnerFacade;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaListUpgradeableVersionsResponseParam;
import org.springblade.modules.iot.ota.vo.param.DeviceOtaUpgradeAppConfirmationParam;
import org.springblade.modules.iot.ota.vo.result.DeviceOtaUpgradeAppConfirmationResultVO;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaCommandResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReadResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportResponseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Description:
 * OTA相关开放接口-服务层实现类
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/12/26
 */
@Service
public class OtaOpenInnerFacadeImpl implements OtaOpenInnerFacade {
    @Lazy
    @Autowired
    private OtaOpenInnerApi otaOpenInnerApi;

    @Override
    public R<DeviceOtaUpgradeAppConfirmationResultVO> otaUpgradeAppConfirmation(DeviceOtaUpgradeAppConfirmationParam param) {
        return otaOpenInnerApi.otaUpgradeAppConfirmation(param);
    }

    @Override
    public R<TopoOtaCommandResponseParam> saveOtaUpgradeRecordByMqtt(TopoOtaCommandResponseParam topoOtaCommandResponseParam) {
        return otaOpenInnerApi.saveOtaUpgradeRecordByMqtt(topoOtaCommandResponseParam);
    }

    @Override
    public R<TopoOtaCommandResponseParam> saveOtaUpgradeRecordByNorthbound(TopoOtaCommandResponseParam topoOtaCommandResponseParam) {
        return otaOpenInnerApi.saveOtaUpgradeRecordByNorthbound(topoOtaCommandResponseParam);
    }

    @Override
    public R<TopoOtaPullResponseParam> otaPullByMqtt(TopoOtaPullParam topoOtaPullParam) {
        return otaOpenInnerApi.otaPullByMqtt(topoOtaPullParam);
    }

    @Override
    public R<TopoOtaPullResponseParam> otaPullByNorthbound(TopoOtaPullParam topoOtaPullParam) {
        return otaOpenInnerApi.otaPullByNorthbound(topoOtaPullParam);
    }

    @Override
    public R<TopoOtaReportResponseParam> otaReportByMqtt(TopoOtaReportParam topoOtaReportParam) {
        return otaOpenInnerApi.otaReportByMqtt(topoOtaReportParam);
    }

    @Override
    public R<TopoOtaReportResponseParam> otaReportByNorthbound(TopoOtaReportParam topoOtaReportParam) {
        return otaOpenInnerApi.otaReportByNorthbound(topoOtaReportParam);
    }

    @Override
    public R<?> otaReadResponseByMqtt(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        return otaOpenInnerApi.otaReadResponseByMqtt(topoOtaReadResponseParam);
    }

    @Override
    public R<?> otaReadResponseByNorthbound(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        return otaOpenInnerApi.otaReadResponseByNorthbound(topoOtaReadResponseParam);
    }

    @Override
    public R<TopoOtaListUpgradeableVersionsResponseParam> getAvailableUpgradeVersionsByNorthbound(String deviceIdentification, Integer packageType) {
        return otaOpenInnerApi.getAvailableUpgradeVersionsByNorthbound(deviceIdentification, packageType);
    }
}
