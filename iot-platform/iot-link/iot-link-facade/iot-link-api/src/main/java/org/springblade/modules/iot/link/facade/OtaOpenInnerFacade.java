package org.springblade.modules.iot.link.facade;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.ota.vo.param.DeviceOtaUpgradeAppConfirmationParam;
import org.springblade.modules.iot.ota.vo.result.DeviceOtaUpgradeAppConfirmationResultVO;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaCommandResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaListUpgradeableVersionsResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReadResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportResponseParam;

/**
 * Description:
 * OTA相关开放接口
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/12/26
 */
public interface OtaOpenInnerFacade {

    /**
     * Handles app confirmation for device OTA upgrade.
     *
     * @param param The device OTA upgrade app confirmation parameters.
     * @return {@link R<DeviceOtaUpgradeAppConfirmationResultVO>} A response wrapper containing the OTA upgrade app confirmation result.
     */
    R<DeviceOtaUpgradeAppConfirmationResultVO> otaUpgradeAppConfirmation(DeviceOtaUpgradeAppConfirmationParam param);

    /**
     * MQTT协议保存OTA升级记录
     *
     * @param topoOtaCommandResponseParam OTA命令响应参数
     * @return {@link R<TopoOtaCommandResponseParam>} 保存的OTA升级记录
     */
    R<TopoOtaCommandResponseParam> saveOtaUpgradeRecordByMqtt(TopoOtaCommandResponseParam topoOtaCommandResponseParam);

    /**
     * 北向API-保存OTA升级记录
     *
     * @param topoOtaCommandResponseParam OTA命令响应参数
     * @return {@link R<TopoOtaCommandResponseParam>} 保存的OTA升级记录
     */
    R<TopoOtaCommandResponseParam> saveOtaUpgradeRecordByNorthbound(TopoOtaCommandResponseParam topoOtaCommandResponseParam);

    /**
     * MQTT协议拉取OTA信息
     *
     * @param topoOtaPullParam OTA拉取参数
     * @return {@link R<TopoOtaPullResponseParam>} OTA拉取响应结果
     */
    R<TopoOtaPullResponseParam> otaPullByMqtt(TopoOtaPullParam topoOtaPullParam);

    /**
     * 北向API-OTA拉取软固件信息
     *
     * @param topoOtaPullParam OTA拉取参数
     * @return {@link R<TopoOtaPullResponseParam>} OTA拉取响应结果
     */
    R<TopoOtaPullResponseParam> otaPullByNorthbound(TopoOtaPullParam topoOtaPullParam);

    /**
     * MQTT协议上报OTA信息
     *
     * @param topoOtaReportParam OTA上报参数
     * @return {@link R<TopoOtaReportResponseParam>} OTA上报响应结果
     */
    R<TopoOtaReportResponseParam> otaReportByMqtt(TopoOtaReportParam topoOtaReportParam);

    /**
     * 北向API-OTA上报软固件版本
     *
     * @param topoOtaReportParam OTA上报参数
     * @return {@link R<TopoOtaReportResponseParam>} OTA上报响应结果
     */
    R<TopoOtaReportResponseParam> otaReportByNorthbound(TopoOtaReportParam topoOtaReportParam);

    /**
     * MQTT协议OTA读取响应
     *
     * @param topoOtaReadResponseParam OTA读取响应参数
     * @return {@link R<?>} OTA读取响应结果
     */
    R<?> otaReadResponseByMqtt(TopoOtaReadResponseParam topoOtaReadResponseParam);

    /**
     * 北向API-OTA读取设备软固件版本信息响应
     *
     * @param topoOtaReadResponseParam OTA读取响应参数
     * @return {@link R<?>} OTA读取响应结果
     */
    R<?> otaReadResponseByNorthbound(TopoOtaReadResponseParam topoOtaReadResponseParam);

    /**
     * 北向API-OTA获取可升级版本列表
     *
     * @param deviceIdentification 设备标识
     * @param packageType          包类型
     * @return {@link R<TopoOtaListUpgradeableVersionsResponseParam>} 可升级版本列表响应结果
     */
    R<TopoOtaListUpgradeableVersionsResponseParam> getAvailableUpgradeVersionsByNorthbound(String deviceIdentification, Integer packageType);
}
