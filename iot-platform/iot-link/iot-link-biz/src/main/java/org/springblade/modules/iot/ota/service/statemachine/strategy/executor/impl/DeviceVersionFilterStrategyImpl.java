package org.springblade.modules.iot.ota.service.statemachine.strategy.executor.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import cn.hutool.core.util.StrUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaPackageTypeEnum;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.DeviceVersionFilterStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 设备版本过滤策略实现类
 * 根据任务关联的升级包类型和源版本号过滤设备
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
public class DeviceVersionFilterStrategyImpl implements DeviceVersionFilterStrategy {

    @Override
    public boolean isVersionMatch(OtaUpgradeTasksResultDTO task, DeviceResultVO device) {
        try {
            // 获取任务关联的源版本号（多个逗号分隔）
            String sourceVersions = task.getSourceVersions();
            if (Objects.isNull(sourceVersions) || sourceVersions.trim().isEmpty()) {
                log.info("任务源版本号为空，跳过版本过滤 - 任务ID: {}", task.getId());
                // 如果源版本号为空，不过滤设备
                return true;
            }

            // 获取设备当前版本
            String deviceCurrentVersion = getDeviceCurrentVersion(task, device);
            if (StrUtil.isEmpty(deviceCurrentVersion)) {
                log.info("设备版本号为空，不符合升级条件 - 设备标识: {}", device.getDeviceIdentification());
                return false;
            }

            // 解析源版本号列表（逗号分隔）
            List<String> sourceVersionList = List.of(sourceVersions.split(StrPool.COMMA));
            // 检查设备版本是否在源版本号列表中
            boolean isMatch = sourceVersionList.stream()
                    .anyMatch(sourceVersion -> sourceVersion.trim().equals(deviceCurrentVersion.trim()));
            if (!isMatch) {
                log.info("设备版本不匹配 - 设备标识: {}, 设备版本: {}, 源版本列表: {}", device.getDeviceIdentification(), deviceCurrentVersion, sourceVersions);
            }
            return isMatch;
        } catch (Exception e) {
            log.warn("设备版本过滤失败 - 任务ID: {}, 设备标识: {}", task.getId(), device.getDeviceIdentification(), e);
            return false;
        }
    }

    @Override
    public String getDeviceCurrentVersion(OtaUpgradeTasksResultDTO task, DeviceResultVO device) {
        return Optional.ofNullable(task.getOtaUpgradesResult())
                .flatMap(otaUpgradesResult -> OtaPackageTypeEnum.fromValue(otaUpgradesResult.getPackageType()))
                .map(packageType -> switch (packageType) {
                    case SOFTWARE -> Optional.ofNullable(device.getSwVersion()).orElse(StrPool.EMPTY);
                    case FIRMWARE -> Optional.ofNullable(device.getFwVersion()).orElse(StrPool.EMPTY);
                })
                .orElse(StrPool.EMPTY);
    }

    /**
     * 根据升级任务构建包含版本过滤的设备查询条件
     *
     * @param devicePageQuery 基础设备查询条件
     * @param upgradeTask     升级任务
     * @return 包含版本过滤条件的设备查询条件
     */
    @Override
    public DevicePageQuery buildVersionFilterQuery(DevicePageQuery devicePageQuery, OtaUpgradeTasksResultDTO upgradeTask) {
        if (Objects.isNull(devicePageQuery) || Objects.isNull(upgradeTask) || StrUtil.isBlank(upgradeTask.getSourceVersions())) {
            return devicePageQuery;
        }
        // 解析源版本号
        List<String> sourceVersionList = parseSourceVersions(upgradeTask.getSourceVersions());
        if (sourceVersionList.isEmpty()) {
            return devicePageQuery;
        }
        // 根据升级包类型设置对应的版本过滤条件
        Optional<OtaPackageTypeEnum> packageTypeOpt = Optional.ofNullable(upgradeTask.getOtaUpgradesResult())
                .flatMap(otaUpgradesResult -> OtaPackageTypeEnum.fromValue(otaUpgradesResult.getPackageType()));
        if (packageTypeOpt.isPresent()) {
            OtaPackageTypeEnum packageType = packageTypeOpt.get();
            if (packageType.equals(OtaPackageTypeEnum.SOFTWARE)) {
                devicePageQuery.setSwVersionList(sourceVersionList);
            } else if (packageType.equals(OtaPackageTypeEnum.FIRMWARE)) {
                devicePageQuery.setFwVersionList(sourceVersionList);
            }
        }
        return devicePageQuery;
    }

    /**
     * 解析源版本号字符串
     *
     * @param sourceVersions 逗号分隔的源版本号字符串
     * @return 版本号列表
     */
    @Override
    public List<String> parseSourceVersions(String sourceVersions) {
        if (StrUtil.isBlank(sourceVersions)) {
            return Collections.emptyList();
        }
        return Stream.of(sourceVersions.split(StrPool.COMMA))
                .map(String::trim)
                .filter(StrUtil::isNotBlank)
                .toList();
    }
}