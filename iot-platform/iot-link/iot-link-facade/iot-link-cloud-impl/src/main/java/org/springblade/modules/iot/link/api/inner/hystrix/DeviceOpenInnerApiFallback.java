package org.springblade.modules.iot.link.api.inner.hystrix;

import java.util.List;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceActionSaveVO;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.device.vo.save.DeviceSaveVO;
import org.springblade.modules.iot.link.api.inner.DeviceOpenInnerApi;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.modules.iot.protocol.vo.param.DeviceCommandWrapperParam;
import org.springblade.modules.iot.protocol.vo.param.TopoAddSubDeviceParam;
import org.springblade.modules.iot.protocol.vo.param.TopoDeleteSubDeviceParam;
import org.springblade.modules.iot.protocol.vo.param.TopoDeviceDataReportParam;
import org.springblade.modules.iot.protocol.vo.param.TopoQueryDeviceParam;
import org.springblade.modules.iot.protocol.vo.param.TopoUpdateSubDeviceStatusParam;
import org.springblade.modules.iot.protocol.vo.result.DeviceCommandResultVO;
import org.springblade.modules.iot.protocol.vo.result.TopoAddDeviceResultVO;
import org.springblade.modules.iot.protocol.vo.result.TopoDeviceOperationResultVO;
import org.springblade.modules.iot.protocol.vo.result.TopoQueryDeviceResultVO;
import org.springframework.stereotype.Component;

/**
 * @program: thinglinks-cloud
 * @description: 设备开放API熔断
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-06 12:37
 **/
@Component
public class DeviceOpenInnerApiFallback implements DeviceOpenInnerApi {

    @Override
    public R<Boolean> updateDeviceConnectionStatus(String clientIdentifier, Integer connectionStatus) {
        return R.timeout();
    }

    @Override
    public R<Boolean> updateDeviceConnectionStatusByEvent(String clientIdentifier, Integer connectionStatus, Long eventHlc) {
        return R.timeout();
    }

    @Override
    public R<TopoAddDeviceResultVO> saveSubDeviceByMqtt(TopoAddSubDeviceParam topoAddSubDeviceParam) {
        return R.timeout();
    }

    @Override
    public R<TopoAddDeviceResultVO> saveSubDeviceByNorthbound(TopoAddSubDeviceParam topoAddSubDeviceParam) {
        return R.timeout();
    }

    @Override
    public R<TopoDeviceOperationResultVO> updateSubDeviceConnectStatusByMqtt(TopoUpdateSubDeviceStatusParam topoUpdateSubDeviceStatusParam) {
        return R.timeout();
    }

    @Override
    public R<TopoDeviceOperationResultVO> updateSubDeviceConnectStatusByNorthbound(TopoUpdateSubDeviceStatusParam topoUpdateSubDeviceStatusParam) {
        return R.timeout();
    }

    @Override
    public R<TopoDeviceOperationResultVO> deleteSubDeviceByMqtt(TopoDeleteSubDeviceParam topoDeleteSubDeviceParam) {
        return R.timeout();
    }

    @Override
    public R<TopoDeviceOperationResultVO> deleteSubDeviceByNorthbound(TopoDeleteSubDeviceParam topoDeleteSubDeviceParam) {
        return R.timeout();
    }

    @Override
    public R<TopoDeviceOperationResultVO> deviceDataReportByMqtt(TopoDeviceDataReportParam topoDeviceDataReportParam) {
        return R.timeout();
    }


    @Override
    public R<TopoDeviceOperationResultVO> deviceDataReportByNorthbound(TopoDeviceDataReportParam topoDeviceDataReportParam) {
        return R.timeout();
    }

    @Override
    public R<DeviceAction> saveDeviceAction(DeviceActionSaveVO deviceActionSaveVO) {
        return R.timeout();
    }

    @Override
    public R<DeviceCommand> saveDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO) {
        return R.timeout();
    }


    @Override
    public R<TopoQueryDeviceResultVO> queryDeviceByMqtt(TopoQueryDeviceParam topoQueryDeviceParam) {
        return R.timeout();
    }

    @Override
    public R<TopoQueryDeviceResultVO> queryDeviceByNorthbound(TopoQueryDeviceParam topoQueryDeviceParam) {
        return R.timeout();
    }

    @Override
    public R<Boolean> reportDeviceHeartbeat(String clientIdentifier, Long heartbeatTime, Long eventHlc) {
        return R.timeout();
    }

    @Override
    public R<List<DeviceCommandResultVO>> issueCommands(DeviceCommandWrapperParam commandWrapper) {
        return R.timeout();
    }

    @Override
    public R<DeviceResultVO> saveDeviceByNorthbound(DeviceSaveVO deviceSaveVO) {
        return R.timeout();
    }

    @Override
    public R<DeviceDetailsResultVO> getDeviceDetailByNorthbound(String deviceIdentification) {
        return R.timeout();
    }


    @Override
    public R<ProductResultVO> queryDeviceShadowByNorthbound(String deviceIdentification, Long startTime, Long endTime, String serviceCode) {
        return R.timeout();
    }

    @Override
    public R<Boolean> updateDeviceStatusByNorthbound(String deviceIdentification, Integer status) {
        return R.timeout();
    }
}
