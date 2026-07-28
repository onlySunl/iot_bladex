package org.springblade.modules.iot.ota.service.statemachine.strategy.executor;

import java.util.Optional;

import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsResultDTO;

/**
 * 升级记录去重策略接口
 * 检查设备是否已有升级记录，避免重复升级
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
public interface UpgradeRecordDeduplicationStrategy {

    /**
     * 检查设备是否已有升级记录
     *
     * @param taskId 任务ID
     * @param deviceIdentification 设备标识
     * @return {@code true} 已有记录，{@code false} 无记录
     */
    boolean hasUpgradeRecord(Long taskId, String deviceIdentification);

    /**
     * 根据任务ID和设备标识查询升级记录DTO
     *
     * @param taskId 任务ID
     * @param deviceIdentification 设备标识
     * @return {@link Optional}包装的升级记录DTO，如果不存在则返回空Optional
     */
    Optional<OtaUpgradeRecordsResultDTO> getUpgradeRecordByTaskIdAndDeviceIdentification(Long taskId, String deviceIdentification);
}