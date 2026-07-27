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
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeScopeEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeTargetsService;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.DeviceVersionFilterStrategy;
import org.springblade.modules.iot.ota.service.statemachine.strategy.scope.UpgradeScopeStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 全部设备升级策略
 * <p>
 * 处理全部设备升级范围的设备筛选
 * 筛选出产品下所有已激活的设备
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
@AllArgsConstructor
public class AllDevicesScopeStrategyImpl implements UpgradeScopeStrategy {

    private final DeviceService deviceService;
    private final OtaUpgradeTargetsService otaUpgradeTargetsService;
    private final DeviceVersionFilterStrategy deviceVersionFilterStrategy;

    /**
     * 是否支持该升级范围
     *
     * @param upgradeScope 升级范围
     * @return 是否支持
     */
    @Override
    public boolean supports(Integer upgradeScope) {
        return OtaUpgradeScopeEnum.ALL_DEVICES.getValue().equals(upgradeScope);
    }

    /**
     * 支持的升级范围类型
     *
     * @return 升级范围枚举
     */
    @Override
    public OtaUpgradeScopeEnum getSupportedScope() {
        return OtaUpgradeScopeEnum.ALL_DEVICES;
    }

    @Override
    public Optional<List<DeviceResultVO>> getScopeDevices(OtaUpgradeTasksResultDTO upgradeTask) {
        try {
            Long taskId = upgradeTask.getId();
            if (Objects.isNull(taskId)) {
                log.warn("任务ID为空，无法获取全部设备升级设备");
                return Optional.empty();
            }

            // 从目标表中获取产品标识列表
            Optional<List<String>> productIdentificationsOpt = otaUpgradeTargetsService.getTargetDevicesByTaskIdOptional(taskId);

            if (productIdentificationsOpt.isEmpty() || productIdentificationsOpt.get().isEmpty()) {
                log.warn("全部设备升级任务的产品标识列表为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }

            // 获取第一个产品标识（全部设备升级通常只针对一个产品）
            String productIdentification = productIdentificationsOpt.get().get(0);

            if (StrUtil.isBlank(productIdentification)) {
                log.warn("升级任务的产品标识为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }

            log.info("全部设备升级任务获取到产品标识 - 任务ID: {}, 产品标识: {}", taskId, productIdentification);

            // 查询该产品下所有已激活的设备
            DevicePageQuery devicePageQuery = new DevicePageQuery();
            devicePageQuery.setProductIdentification(productIdentification);
            devicePageQuery.setDeviceStatus(DeviceStatusEnum.ACTIVATED.getValue());
            devicePageQuery.setConnectStatus(DeviceConnectStatusEnum.ONLINE.getValue());
            // 添加版本过滤条件
            devicePageQuery = deviceVersionFilterStrategy.buildVersionFilterQuery(devicePageQuery, upgradeTask);
            List<DeviceResultVO> devices = deviceService.getDeviceResultVOList(devicePageQuery);
            log.info("全部设备策略 - 任务ID: {}, 产品标识: {}, 已激活设备数量: {}", taskId, productIdentification, devices.size());
            return Optional.of(devices);
        } catch (Exception e) {
            log.error("获取全部设备升级设备列表异常 - 任务ID: {}", upgradeTask.getId(), e);
            return Optional.empty();
        }
    }
}