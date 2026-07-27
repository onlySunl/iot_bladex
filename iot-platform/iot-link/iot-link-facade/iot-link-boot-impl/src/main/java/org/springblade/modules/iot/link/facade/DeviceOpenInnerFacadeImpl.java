package org.springblade.modules.iot.link.facade;

import java.util.List;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import org.springblade.core.tool.api.R;
import org.springblade.core.secure.constant.SecureConstant;
import org.springblade.core.secure.utils.AuthUtil;
import cn.hutool.core.lang.Assert;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.service.DeviceActionService;
import org.springblade.modules.iot.device.service.DeviceCommandService;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.service.DeviceShadowService;
import org.springblade.modules.iot.device.vo.query.DeviceShadowPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceActionSaveVO;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.device.vo.save.DeviceSaveVO;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangyh
 * @since 2024/12/24 17:02
 */
@Slf4j
@Service
public class DeviceOpenInnerFacadeImpl implements DeviceOpenInnerFacade {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceActionService deviceActionService;

    @Autowired
    private DeviceCommandService deviceCommandService;

    @Autowired
    private DeviceShadowService deviceShadowService;

    @Override
    public R<Boolean> updateDeviceConnectionStatus(String clientIdentifier, Integer connectionStatus) {
        log.info("updateDeviceConnectionStatus clientIdentifier:{}, connectionStatus:{}", clientIdentifier, connectionStatus);
        ArgumentAssert.notBlank(clientIdentifier, "clientIdentifier Cannot be null");
        //根据设备标识解析出租户ID 例如：clientId: 1000000000000000001@tenantId
        String tenantId = Optional.ofNullable(clientIdentifier)
                .filter(StrUtil::isNotBlank)
                .map(id -> StrUtil.subAfter(id, ContextConstants.SPECIAL_CHARACTER, true))
                .orElse(ContextConstants.BUILT_IN_TENANT_ID_STR);

        ContextUtil.setTenantIdStr(tenantId);


        DeviceResultVO deviceResultVO = deviceService.findOneByClientId(clientIdentifier);
        ArgumentAssert.notNull(deviceResultVO, "设备不存在");

        return R.success(deviceService.updateDeviceConnectionStatusById(deviceResultVO.getId(), connectionStatus));
    }

    @Override
    public R<Boolean> updateDeviceConnectionStatusByEvent(String clientIdentifier, Integer connectionStatus, Long eventHlc) {
        log.info("updateDeviceConnectionStatusByEvent clientIdentifier:{} status:{} hlc:{}",
                clientIdentifier, connectionStatus, eventHlc);
        ArgumentAssert.notBlank(clientIdentifier, "clientIdentifier cannot be blank");
        ArgumentAssert.notNull(connectionStatus, "connectionStatus cannot be null");
        ArgumentAssert.isTrue(eventHlc != null && eventHlc > 0, "eventHlc must be > 0");

        // 与老方法同模式:从 clientIdentifier 解析租户(clientId 格式 deviceIdentifier@tenantId)
        String tenantId = Optional.ofNullable(clientIdentifier)
                .filter(StrUtil::isNotBlank)
                .map(id -> StrUtil.subAfter(id, ContextConstants.SPECIAL_CHARACTER, true))
                .orElse(ContextConstants.BUILT_IN_TENANT_ID_STR);
        ContextUtil.setTenantIdStr(tenantId);

        return R.success(deviceService.updateDeviceConnectionStatusByEvent(clientIdentifier, connectionStatus, eventHlc));
    }

    @Override
    public R<TopoAddDeviceResultVO> saveSubDeviceByMqtt(TopoAddSubDeviceParam topoAddSubDeviceParam) {
        return R.success(deviceService.saveSubDeviceByMqtt(topoAddSubDeviceParam));
    }

    @Override
    public R<TopoAddDeviceResultVO> saveSubDeviceByNorthbound(TopoAddSubDeviceParam topoAddSubDeviceParam) {
        return R.success(deviceService.saveSubDeviceByNorthbound(topoAddSubDeviceParam));
    }

    @Override
    public R<TopoDeviceOperationResultVO> updateSubDeviceConnectStatusByMqtt(TopoUpdateSubDeviceStatusParam topoUpdateSubDeviceStatusParam) {
        TopoDeviceOperationResultVO topoDeviceOperationResultVO = deviceService.updateSubDeviceConnectStatusByMqtt(topoUpdateSubDeviceStatusParam);
        return R.success(topoDeviceOperationResultVO);
    }

    @Override
    public R<TopoDeviceOperationResultVO> updateSubDeviceConnectStatusByNorthbound(TopoUpdateSubDeviceStatusParam topoUpdateSubDeviceStatusParam) {
        log.info("updateSubDeviceConnectStatusByNorthbound param:{}", JSON.toJSONString(topoUpdateSubDeviceStatusParam));
        try {
            TopoDeviceOperationResultVO topoDeviceOperationResultVO = deviceService.updateSubDeviceConnectStatusByNorthbound(topoUpdateSubDeviceStatusParam);
            return R.success(topoDeviceOperationResultVO);
        } catch (Exception e) {
            log.error("修改子设备连接状态失败", e);
            return R.fail("修改子设备连接状态失败: " + e.getMessage());
        }
    }

    @Override
    public R<TopoDeviceOperationResultVO> deleteSubDeviceByMqtt(TopoDeleteSubDeviceParam topoDeleteSubDeviceParam) {
        TopoDeviceOperationResultVO topoDeviceOperationResultVO = deviceService.deleteSubDeviceByMqtt(topoDeleteSubDeviceParam);
        return R.success(topoDeviceOperationResultVO);
    }

    @Override
    public R<TopoDeviceOperationResultVO> deleteSubDeviceByNorthbound(TopoDeleteSubDeviceParam topoDeleteSubDeviceParam) {
        log.info("deleteSubDeviceByNorthbound param:{}", JSON.toJSONString(topoDeleteSubDeviceParam));
        try {
            TopoDeviceOperationResultVO topoDeviceOperationResultVO = deviceService.deleteSubDeviceByNorthbound(topoDeleteSubDeviceParam);
            return R.success(topoDeviceOperationResultVO);
        } catch (Exception e) {
            log.error("删除子设备失败", e);
            return R.fail("删除子设备失败: " + e.getMessage());
        }
    }

    @Override
    public R<TopoDeviceOperationResultVO> deviceDataReportByMqtt(TopoDeviceDataReportParam topoDeviceDataReportParam) {
        TopoDeviceOperationResultVO topoDeviceOperationResultVO = deviceService.deviceDataReportByMqtt(topoDeviceDataReportParam);
        return R.success(topoDeviceOperationResultVO);
    }

    @Override
    public R<TopoDeviceOperationResultVO> deviceDataReportByNorthbound(TopoDeviceDataReportParam topoDeviceDataReportParam) {
        log.info("deviceDataReportByNorthbound param:{}", JSON.toJSONString(topoDeviceDataReportParam));
        try {
            TopoDeviceOperationResultVO topoDeviceOperationResultVO = deviceService.deviceDataReportByNorthbound(topoDeviceDataReportParam);
            return R.success(topoDeviceOperationResultVO);
        } catch (Exception e) {
            log.error("设备数据上报失败", e);
            return R.fail("设备数据上报失败: " + e.getMessage());
        }
    }

    @Override
    public R<DeviceAction> saveDeviceAction(DeviceActionSaveVO deviceActionSaveVO) {
        return R.success(deviceActionService.saveDeviceAction(deviceActionSaveVO));
    }


    @Override
    public R<DeviceCommand> saveDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO) {
        DeviceCommand savedDeviceCommand = deviceCommandService.saveDeviceCommand(deviceCommandSaveVO);
        return R.success(savedDeviceCommand);
    }

    @Override
    public R<TopoQueryDeviceResultVO> queryDeviceByMqtt(TopoQueryDeviceParam topoQueryDeviceParam) {
        return R.success(deviceService.queryDeviceByMqtt(topoQueryDeviceParam));
    }

    @Override
    public R<TopoQueryDeviceResultVO> queryDeviceByNorthbound(TopoQueryDeviceParam topoQueryDeviceParam) {
        log.info("queryDeviceByNorthbound param:{}", JSON.toJSONString(topoQueryDeviceParam));
        try {
            return R.success(deviceService.queryDeviceByNorthbound(topoQueryDeviceParam));
        } catch (Exception e) {
            log.error("查询设备信息失败", e);
            return R.fail("查询设备信息失败: " + e.getMessage());
        }
    }

    @Override
    public R<Boolean> reportDeviceHeartbeat(String clientIdentifier, Long heartbeatTime, Long eventHlc) {
        try {
            log.info("reportDeviceHeartbeat clientIdentifier:{} heartbeatTime:{} eventHlc:{}", clientIdentifier, heartbeatTime, eventHlc);
            // Call the service method to handle the report event
            boolean report = deviceService.reportDeviceHeartbeat(clientIdentifier, heartbeatTime, eventHlc);
            return R.success(report);
        } catch (Exception e) {
            // Log the exception and return a failure response wrapper
            // Assuming R.fail() is a method to create a failure response
            return R.fail("Error processing report device heartbeat event: " + e.getMessage());
        }
    }

    @Override
    public R<List<DeviceCommandResultVO>> issueCommands(DeviceCommandWrapperParam commandWrapper) {
        log.info("issueCommands commandWrapper:{}", JSON.toJSONString(commandWrapper));
        try {
            List<DeviceCommandResultVO> results = deviceCommandService.processDeviceCommands(commandWrapper);
            return R.success(results);
        } catch (Exception e) {
            return R.fail("Error processing issue commands event: " + e.getMessage());
        }
    }

    @Override
    public R<DeviceResultVO> saveDeviceByNorthbound(DeviceSaveVO deviceSaveVO) {
        log.info("saveDeviceByNorthbound deviceSaveVO:{}", JSON.toJSONString(deviceSaveVO));
        try {
            deviceService.saveDevice(deviceSaveVO);
            return R.success(deviceService.findByDeviceIdentification(deviceSaveVO.getDeviceIdentification()));
        } catch (Exception e) {
            return R.fail("Error processing save device by northbound event: " + e.getMessage());
        }

    }

    @Override
    public R<DeviceDetailsResultVO> getDeviceDetailByNorthbound(String deviceIdentification) {
        log.info("getDeviceDetailByNorthbound deviceIdentification:{}", deviceIdentification);
        try {
            return R.success(deviceService.findOneByDeviceIdentification(deviceIdentification));
        } catch (Exception e) {
            return R.fail("Error processing get device detail by northbound event: " + e.getMessage());
        }
    }

    @Override
    public R<ProductResultVO> queryDeviceShadowByNorthbound(
            String deviceIdentification,
            Long startTime,
            Long endTime,
            String serviceCode) {
        log.info("queryDeviceShadowByNorthbound deviceIdentification:{}, startTime:{}, endTime:{}, serviceCode:{}",
                deviceIdentification, startTime, endTime, serviceCode);

        try {
            DeviceShadowPageQuery deviceShadowPageQuery = new DeviceShadowPageQuery();
            deviceShadowPageQuery.setDeviceIdentification(deviceIdentification);
            deviceShadowPageQuery.setStartTime(startTime);
            deviceShadowPageQuery.setEndTime(endTime);
            deviceShadowPageQuery.setServiceCode(serviceCode);

            ProductResultVO productResultVO = deviceShadowService.queryDeviceShadow(deviceShadowPageQuery);
            return R.success(productResultVO);
        } catch (Exception e) {
            log.error("查询设备影子失败", e);
            return R.fail("查询设备影子失败: " + e.getMessage());
        }
    }

    @Override
    public R<Boolean> updateDeviceStatusByNorthbound(String deviceIdentification, Integer status) {
        log.info("updateDeviceStatusByNorthbound deviceIdentification:{}, status:{}", deviceIdentification, status);
        try {
            DeviceDetailsResultVO deviceDetails = deviceService.findOneByDeviceIdentification(deviceIdentification);
            ArgumentAssert.notNull(deviceDetails, "设备不存在");
            return R.success(deviceService.updateDeviceStatus(deviceDetails.getId(), status));
        } catch (Exception e) {
            log.error("修改设备状态失败", e);
            return R.fail("修改设备状态失败: " + e.getMessage());
        }
    }
}
