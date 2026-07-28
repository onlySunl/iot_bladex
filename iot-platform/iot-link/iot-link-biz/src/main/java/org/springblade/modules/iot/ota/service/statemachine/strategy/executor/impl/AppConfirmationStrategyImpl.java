package org.springblade.modules.iot.ota.service.statemachine.strategy.executor.impl;

import java.util.Objects;
import java.util.Optional;

import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.AppConfirmationStrategy;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * APP确认策略实现类
 * 处理APP确认升级逻辑
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppConfirmationStrategyImpl implements AppConfirmationStrategy {

    private final OtaUpgradeRecordsService otaUpgradeRecordsService;

    @Override
    public boolean needConfirmation(OtaUpgradeTasksResultDTO task, String deviceIdentification) {
        try {
            // 检查任务是否需要APP确认
            Boolean appConfirmationRequired = task.getAppConfirmationRequired();
            if (Objects.isNull(appConfirmationRequired)) {
                log.info("任务APP确认状态为空，默认无需确认 - 任务ID: {}", task.getId());
                return false;
            }
            if (appConfirmationRequired) {
                log.info("任务需要APP确认 - 任务ID: {}, 设备标识: {}", task.getId(), deviceIdentification);
            }
            return appConfirmationRequired;
        } catch (Exception e) {
            log.error("检查APP确认需求失败 - 任务ID: {}, 设备标识: {}",
                    task.getId(), deviceIdentification, e);
            return false;
        }
    }

    @Override
    public boolean canProceedUpgrade(OtaUpgradeTasksResultDTO task, String deviceIdentification) {
        try {
            // 检查是否需要APP确认
            if (!needConfirmation(task, deviceIdentification)) {
                return true;
            }
            // 检查升级记录的APP确认状态
            OtaUpgradeRecordsResultVO record = otaUpgradeRecordsService.getByTaskIdAndDeviceIdentification(task.getId(), deviceIdentification).orElse(null);
            if (Objects.isNull(record)) {
                log.info("设备升级记录不存在，需要等待APP确认 - 任务ID: {}, 设备标识: {}", task.getId(), deviceIdentification);
                return false;
            }
            // 检查APP确认状态
            Optional<OtaTaskRecordAppConfirmStatusEnum> appConfirmStatusEnumOptional = OtaTaskRecordAppConfirmStatusEnum.fromValue(record.getAppConfirmationStatus());
            if (appConfirmStatusEnumOptional.isEmpty()) {
                log.info("APP确认状态为空，需要等待确认 - 任务ID: {}, 设备标识: {}", task.getId(), deviceIdentification);
                return false;
            }
            OtaTaskRecordAppConfirmStatusEnum appConfirmStatus = appConfirmStatusEnumOptional.get();
            if (appConfirmStatus.equals(OtaTaskRecordAppConfirmStatusEnum.CONFIRMED)) {
                log.info("APP已确认，可以继续升级 - 任务ID: {}, 设备标识: {}", task.getId(), deviceIdentification);
                return true;
            } else if (appConfirmStatus.equals(OtaTaskRecordAppConfirmStatusEnum.REJECTED)) {
                log.info("APP已拒绝，不能升级 - 任务ID: {}, 设备标识: {}", task.getId(), deviceIdentification);
                return false;
            } else {
                log.info("APP确认状态: {}，需要等待确认 - 任务ID: {}, 设备标识: {}", appConfirmStatus, task.getId(), deviceIdentification);
                return false;
            }
        } catch (Exception e) {
            log.error("检查APP确认状态失败 - 任务ID: {}, 设备标识: {}", task.getId(), deviceIdentification, e);
            return false;
        }
    }
}