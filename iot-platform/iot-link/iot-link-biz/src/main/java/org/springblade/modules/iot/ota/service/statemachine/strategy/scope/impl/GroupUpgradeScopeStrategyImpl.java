package org.springblade.modules.iot.ota.service.statemachine.strategy.scope.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import org.springblade.modules.iot.device.enumeration.DeviceConnectStatusEnum;
import org.springblade.modules.iot.device.enumeration.DeviceStatusEnum;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.service.group.DeviceGroupRelService;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeScopeEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeTargetsService;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.DeviceVersionFilterStrategy;
import org.springblade.modules.iot.ota.service.statemachine.strategy.scope.UpgradeScopeStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 分组升级策略
 * <p>
 * 处理分组升级范围的设备筛选
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
@AllArgsConstructor
public class GroupUpgradeScopeStrategyImpl implements UpgradeScopeStrategy {

    private final DeviceService deviceService;
    private final OtaUpgradeTargetsService otaUpgradeTargetsService;
    private final DeviceGroupRelService deviceGroupRelService;
    private final DeviceVersionFilterStrategy deviceVersionFilterStrategy;

    /**
     * 是否支持该升级范围
     *
     * @param upgradeScope 升级范围
     * @return 是否支持
     */
    @Override
    public boolean supports(Integer upgradeScope) {
        return OtaUpgradeScopeEnum.GROUP.getValue().equals(upgradeScope);
    }

    /**
     * 支持的升级范围类型
     *
     * @return 升级范围枚举
     */
    @Override
    public OtaUpgradeScopeEnum getSupportedScope() {
        return OtaUpgradeScopeEnum.GROUP;
    }

    /**
     * 获取分组升级范围内的设备
     *
     * @param upgradeTask 升级任务
     * @return {@link Optional<List<DeviceResultVO>>} 设备列表Optional
     */
    @Override
    public Optional<List<DeviceResultVO>> getScopeDevices(OtaUpgradeTasksResultDTO upgradeTask) {
        try {
            Long taskId = upgradeTask.getId();
            if (Objects.isNull(taskId)) {
                log.warn("任务ID为空，无法获取分组升级设备");
                return Optional.empty();
            }

            // 从目标表中获取分组ID列表
            Optional<List<String>> groupIdsOpt = otaUpgradeTargetsService.getTargetDevicesByTaskIdOptional(taskId);

            if (groupIdsOpt.isEmpty() || groupIdsOpt.get().isEmpty()) {
                log.warn("分组升级任务的分组ID列表为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }

            String productIdentification = Optional.ofNullable(upgradeTask.getOtaUpgradesResult())
                    .map(OtaUpgradesResultDTO::getProductIdentification)
                    .orElse("");

            if (StrUtil.isBlank(productIdentification)) {
                log.warn("升级任务的产品标识为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }

            // 将字符串分组ID转换为Long类型
            List<Long> groupIdList = groupIdsOpt.get().stream().map(this::parseGroupId)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            log.info("分组升级任务获取到分组ID列表 - 任务ID: {}, 产品标识: {}, 分组数量: {}", taskId, productIdentification, groupIdList.size());

            if (groupIdList.isEmpty()) {
                log.warn("分组升级任务的有效分组ID列表为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }
            return Optional.of(getActiveDeviceIdentificationsByGroupIds(groupIdList, productIdentification, upgradeTask));
        } catch (Exception e) {
            log.error("获取分组升级设备列表异常 - 任务ID: {}", upgradeTask.getId(), e);
            return Optional.empty();
        }
    }

    /**
     * 解析分组ID
     *
     * @param groupIdStr 分组ID字符串
     * @return 分组ID Optional
     */
    private Optional<Long> parseGroupId(String groupIdStr) {
        try {
            return Optional.of(Long.parseLong(groupIdStr));
        } catch (NumberFormatException e) {
            log.warn("分组ID格式错误: {}", groupIdStr);
            return Optional.empty();
        }
    }

    /**
     * 根据分组ID列表获取已激活设备标识列表
     *
     * @param groupIdList           分组ID列表
     * @param productIdentification 产品标识
     * @param upgradeTask           升级任务
     * @return {@link List<DeviceResultVO>} 已激活设备标识列表
     */
    private List<DeviceResultVO> getActiveDeviceIdentificationsByGroupIds(List<Long> groupIdList, String productIdentification, OtaUpgradeTasksResultDTO upgradeTask) {
        List<String> deviceIdentifications = deviceGroupRelService.getDeviceIdentificationsByGroupIds(groupIdList);
        DevicePageQuery devicePageQuery = new DevicePageQuery();
        devicePageQuery.setProductIdentification(productIdentification);
        devicePageQuery.setDeviceIdentificationList(deviceIdentifications);
        devicePageQuery.setDeviceStatus(DeviceStatusEnum.ACTIVATED.getValue());
        devicePageQuery.setConnectStatus(DeviceConnectStatusEnum.ONLINE.getValue());
        // 添加版本过滤条件
        devicePageQuery = deviceVersionFilterStrategy.buildVersionFilterQuery(devicePageQuery, upgradeTask);
        return deviceService.getDeviceResultVOList(devicePageQuery);
    }
}