package org.springblade.modules.iot.ota.service;

import java.util.List;

import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.vo.param.SendDeviceOtaUpgradeCommandRequestParam;
import org.springblade.modules.iot.ota.vo.result.DeviceOtaUpgradeAppConfirmationResultVO;

/**
 * Description:
 * OTA任务执行服务接口
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
public interface OtaUpgradeTaskExecutionService {

    /**
     * 执行OTA升级任务
     * 定时任务方法，扫描需要执行的OTA升级任务
     *
     * @param tenantId 租户ID
     */
    void otaUpgradeTasksExecute(Long tenantId);

    /**
     * 执行OTA升级任务
     *
     * @param upgradeTask 升级任务
     */
    void executeUpgradeTask(OtaUpgradeTasksResultDTO upgradeTask);

    /**
     * 发送设备OTA 升级命令
     *
     * @param sendDeviceOtaUpgradeCommandRequestParam 升级命令参数
     */
    void sendDeviceOtaUpgradeCommand(SendDeviceOtaUpgradeCommandRequestParam sendDeviceOtaUpgradeCommandRequestParam);

    /**
     * 执行OTA升级任务（直接指定设备标识）
     *
     * @param upgradeTask           升级任务
     * @param deviceIdentifications 设备标识列表
     */
    void executeUpgradeTaskWithDevices(OtaUpgradeTasksResultDTO upgradeTask, List<String> deviceIdentifications);

    /**
     * 处理OTA升级任务APP确认操作
     * 通过状态机处理APP确认升级逻辑
     *
     * @param taskId                   任务ID
     * @param deviceIdentificationList 设备标识集合
     * @param confirmStatusEnum        确认状态枚举
     * @return {@link DeviceOtaUpgradeAppConfirmationResultVO} 确认结果
     */
    DeviceOtaUpgradeAppConfirmationResultVO otaUpgradeAppConfirmation(Long taskId, List<String> deviceIdentificationList, OtaTaskRecordAppConfirmStatusEnum confirmStatusEnum);
}