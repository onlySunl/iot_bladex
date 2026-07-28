package org.springblade.modules.iot.ota.service.statemachine.strategy.executor.impl;

import java.util.Objects;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsResultDTO;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.UpgradeRecordDeduplicationStrategy;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 升级记录去重策略实现类
 * 检查设备是否已有升级记录，避免重复升级
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UpgradeRecordDeduplicationStrategyImpl implements UpgradeRecordDeduplicationStrategy {

    private final OtaUpgradeRecordsService otaUpgradeRecordsService;

    @Override
    public boolean hasUpgradeRecord(Long taskId, String deviceIdentification) {
        try {
            // 查询设备在该任务下是否已有升级记录
            Optional<OtaUpgradeRecordsResultVO> recordOpt = otaUpgradeRecordsService.getByTaskIdAndDeviceIdentification(taskId, deviceIdentification);
            OtaUpgradeRecordsResultVO record = recordOpt.orElse(null);
            boolean hasRecord = Objects.nonNull(record);
            if (hasRecord) {
                log.info("设备已有升级记录，跳过重复升级 - 任务ID: {}, 设备标识: {}", taskId, deviceIdentification);
            }
            return hasRecord;
        } catch (Exception e) {
            log.error("检查升级记录失败 - 任务ID: {}, 设备标识: {}", taskId, deviceIdentification, e);
            // 查询失败时不过滤，避免误判
            return false;
        }
    }

    @Override
    public Optional<OtaUpgradeRecordsResultDTO> getUpgradeRecordByTaskIdAndDeviceIdentification(Long taskId, String deviceIdentification) {
        if (Objects.isNull(taskId) || StrUtil.isEmpty(deviceIdentification)) {
            return Optional.empty();
        }
        try {
            Optional<OtaUpgradeRecordsResultVO> recordOptional = otaUpgradeRecordsService.getByTaskIdAndDeviceIdentification(taskId, deviceIdentification);
            return recordOptional.map(record -> BeanPlusUtil.toBeanIgnoreError(record, OtaUpgradeRecordsResultDTO.class));
        } catch (Exception e) {
            log.error("查询升级记录DTO异常 - 任务ID: {}, 设备标识: {}", taskId, deviceIdentification, e);
            return Optional.empty();
        }
    }
}