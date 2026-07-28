package org.springblade.modules.iot.ota.service.statemachine.strategy.executor;

import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;

/**
 * APP确认策略接口
 * 处理APP确认升级逻辑
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
public interface AppConfirmationStrategy {

    /**
     * 检查是否需要APP确认
     *
     * @param task 升级任务
     * @param deviceIdentification 设备标识
     * @return true-需要确认，false-无需确认
     */
    boolean needConfirmation(OtaUpgradeTasksResultDTO task, String deviceIdentification);

    /**
     * 检查是否可以继续升级（APP已确认或无需确认）
     *
     * @param task 升级任务
     * @param deviceIdentification 设备标识
     * @return true-可以继续升级，false-需要等待确认
     */
    boolean canProceedUpgrade(OtaUpgradeTasksResultDTO task, String deviceIdentification);
}