package org.springblade.modules.iot.ota.service.statemachine.strategy.scope.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;
import org.springblade.modules.iot.device.enumeration.DeviceConnectStatusEnum;
import org.springblade.modules.iot.device.enumeration.DeviceStatusEnum;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeScopeEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeTargetsService;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.DeviceVersionFilterStrategy;
import org.springblade.modules.iot.ota.service.statemachine.strategy.scope.UpgradeScopeStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 定向升级策略
 * <p>
 * 处理定向升级范围的设备筛选
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TargetedUpgradeScopeStrategyImpl implements UpgradeScopeStrategy {

    private final DeviceService deviceService;
    private final OtaUpgradeTargetsService otaUpgradeTargetsService;
    private final DeviceVersionFilterStrategy deviceVersionFilterStrategy;


    /**
     * 是否支持该升级范围
     *
     * @param upgradeScope 升级范围
     * @return {@link Boolean} 是否支持该升级范围
     */
    @Override
    public boolean supports(Integer upgradeScope) {
        return OtaUpgradeScopeEnum.TARGETED.getValue().equals(upgradeScope);
    }

    /**
     * 支持的升级范围类型
     *
     * @return {@link OtaUpgradeScopeEnum} 升级范围枚举
     */
    @Override
    public OtaUpgradeScopeEnum getSupportedScope() {
        return OtaUpgradeScopeEnum.TARGETED;
    }


    /**
     * 获取定向升级范围内的设备
     *
     * @param upgradeTask 升级任务
     * @return {@link Optional<List<DeviceResultVO>>} 设备列表Optional
     */
    @Override
    public Optional<List<DeviceResultVO>> getScopeDevices(OtaUpgradeTasksResultDTO upgradeTask) {
        try {
            Long taskId = upgradeTask.getId();
            if (Objects.isNull(taskId)) {
                log.warn("任务ID为空，无法获取定向升级设备");
                return Optional.empty();
            }
            // 从目标表中获取设备标识列表
            Optional<List<String>> deviceIdentificationsOpt = otaUpgradeTargetsService.getTargetDevicesByTaskIdOptional(taskId);
            if (deviceIdentificationsOpt.isEmpty() || deviceIdentificationsOpt.get().isEmpty()) {
                log.warn("定向升级任务的目标设备列表为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }

            String productIdentification = Optional.ofNullable(upgradeTask.getOtaUpgradesResult())
                    .map(OtaUpgradesResultDTO::getProductIdentification)
                    .orElse("");

            if (StrUtil.isBlank(productIdentification)) {
                log.warn("升级任务的产品标识为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }

            List<String> deviceIdentifications = deviceIdentificationsOpt.get();
            log.info("定向升级任务获取到设备标识列表 - 任务ID: {}, 产品标识: {}, 设备数量: {}", taskId, productIdentification, deviceIdentifications.size());

            DevicePageQuery devicePageQuery = new DevicePageQuery();
            devicePageQuery.setProductIdentification(productIdentification);
            devicePageQuery.setDeviceIdentificationList(deviceIdentifications);
            devicePageQuery.setDeviceStatus(DeviceStatusEnum.ACTIVATED.getValue());
            devicePageQuery.setConnectStatus(DeviceConnectStatusEnum.ONLINE.getValue());
            // 添加版本过滤条件
            devicePageQuery = deviceVersionFilterStrategy.buildVersionFilterQuery(devicePageQuery, upgradeTask);
            return Optional.of(deviceService.getDeviceResultVOList(devicePageQuery));
        } catch (Exception e) {
            log.error("获取定向升级设备列表异常 - 任务ID: {}", upgradeTask.getId(), e);
            return Optional.empty();
        }
    }
}