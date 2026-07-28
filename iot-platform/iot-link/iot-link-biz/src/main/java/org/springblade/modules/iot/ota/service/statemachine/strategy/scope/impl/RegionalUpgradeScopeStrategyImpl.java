package org.springblade.modules.iot.ota.service.statemachine.strategy.scope.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.basic.utils.StrPool;
import org.springblade.modules.iot.device.enumeration.DeviceConnectStatusEnum;
import org.springblade.modules.iot.device.enumeration.DeviceStatusEnum;
import org.springblade.modules.iot.device.service.DeviceLocationService;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;
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
 * 区域升级策略实现类
 * <p>
 * 处理区域升级范围的设备筛选逻辑，从逗号分隔的区域编码中提取市级编码并查询对应设备
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RegionalUpgradeScopeStrategyImpl implements UpgradeScopeStrategy {

    private final DeviceService deviceService;
    private final OtaUpgradeTargetsService otaUpgradeTargetsService;
    private final DeviceLocationService deviceLocationService;
    private final DeviceVersionFilterStrategy deviceVersionFilterStrategy;

    /**
     * 检查是否支持指定的升级范围类型
     *
     * @param upgradeScope 升级范围类型值
     * @return 如果支持区域升级范围则返回true，否则返回false
     */
    @Override
    public boolean supports(Integer upgradeScope) {
        return OtaUpgradeScopeEnum.REGIONAL.getValue().equals(upgradeScope);
    }

    /**
     * 获取支持的升级范围枚举类型
     *
     * @return 区域升级范围枚举
     */
    @Override
    public OtaUpgradeScopeEnum getSupportedScope() {
        return OtaUpgradeScopeEnum.REGIONAL;
    }

    /**
     * 获取区域升级范围内的设备列表
     *
     * @param upgradeTask 升级任务数据传输对象
     * @return 包含设备列表的Optional对象，如果获取失败则返回空的Optional
     */
    @Override
    public Optional<List<DeviceResultVO>> getScopeDevices(OtaUpgradeTasksResultDTO upgradeTask) {
        try {
            Long taskId = upgradeTask.getId();
            if (Objects.isNull(taskId)) {
                log.warn("任务ID为空，无法获取区域升级设备");
                return Optional.empty();
            }

            Optional<List<String>> regionCodesOpt = otaUpgradeTargetsService.getTargetDevicesByTaskIdOptional(taskId);

            if (regionCodesOpt.isEmpty() || regionCodesOpt.get().isEmpty()) {
                log.warn("区域升级任务的区域编码列表为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }

            String productIdentification = Optional.ofNullable(upgradeTask.getOtaUpgradesResult())
                    .map(OtaUpgradesResultDTO::getProductIdentification)
                    .orElse("");

            if (StrUtil.isBlank(productIdentification)) {
                log.warn("升级任务的产品标识为空 - 任务ID: {}", taskId);
                return Optional.empty();
            }
            List<String> regionCodes = regionCodesOpt.get();

            // 解析逗号分隔的区域编码，提取市级编码
            List<String> cityCodes = parseCityCodesFromRegionCodes(regionCodes);
            if (cityCodes.isEmpty()) {
                log.warn("无法从区域编码中解析出有效的市级编码 - 任务ID: {}", taskId);
                return Optional.empty();
            }
            log.info("解析出市级编码 - 任务ID: {}, 产品标识: {}, 市级编码数量: {}", taskId, productIdentification, cityCodes.size());
            List<DeviceResultVO> devices = getDeviceIdentificationsByCityCodes(productIdentification, cityCodes, upgradeTask);
            return Optional.of(devices);
        } catch (Exception e) {
            log.error("获取区域升级设备列表异常 - 任务ID: {}", upgradeTask.getId(), e);
            return Optional.empty();
        }
    }

    /**
     * 从逗号分隔的区域编码列表中解析出市级编码
     *
     * @param regionCodes 逗号分隔的区域编码列表
     * @return 去重后的市级编码列表
     */
    private List<String> parseCityCodesFromRegionCodes(List<String> regionCodes) {
        return Optional.ofNullable(regionCodes)
                .orElse(Collections.emptyList())
                .stream()
                .filter(StrUtil::isNotBlank)
                .map(String::trim)
                .flatMap(this::extractCityCodesStream)
                .filter(StrUtil::isNotBlank)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 从单个逗号分隔的字符串中提取市级编码
     *
     * @param regionCodeStr 逗号分隔的区域编码字符串
     * @return 市级编码流
     */
    private Stream<String> extractCityCodesStream(String regionCodeStr) {
        return Optional.ofNullable(regionCodeStr)
                .filter(StrUtil::isNotBlank).stream().flatMap(str -> Arrays.stream(str.split(StrPool.COMMA))
                        .skip(1)
                        .map(String::trim)
                        .filter(StrUtil::isNotBlank));
    }

    /**
     * 根据市级编码列表获取设备标识列表
     *
     * @param productIdentification 产品标识
     * @param cityCodes             市级编码列表
     * @param upgradeTask           升级任务
     * @return 设备结果视图对象列表
     */
    private List<DeviceResultVO> getDeviceIdentificationsByCityCodes(String productIdentification, List<String> cityCodes, OtaUpgradeTasksResultDTO upgradeTask) {
        if (CollUtil.isEmpty(cityCodes)) {
            log.warn("市级编码列表为空，无法查询设备");
            return Collections.emptyList();
        }
        try {
            DeviceLocationPageQuery deviceLocationPageQuery = new DeviceLocationPageQuery();
            deviceLocationPageQuery.setCityCodeList(cityCodes);
            List<DeviceLocationResultVO> deviceLocationResultVOList = deviceLocationService.getDeviceLocationResultVOList(deviceLocationPageQuery);

            if (deviceLocationResultVOList.isEmpty()) {
                log.info("在指定的市级编码下未找到设备位置信息 - 市级编码: {}", cityCodes);
                return Collections.emptyList();
            }

            List<String> deviceIdentifications = deviceLocationResultVOList.stream()
                    .map(DeviceLocationResultVO::getDeviceIdentification)
                    .filter(StrUtil::isNotBlank)
                    .distinct()
                    .collect(Collectors.toList());

            log.info("从设备位置信息中提取到设备标识数量: {}", deviceIdentifications.size());
            if (deviceIdentifications.isEmpty()) {
                return Collections.emptyList();
            }
            DevicePageQuery devicePageQuery = new DevicePageQuery();
            devicePageQuery.setProductIdentification(productIdentification);
            devicePageQuery.setDeviceIdentificationList(deviceIdentifications);
            devicePageQuery.setDeviceStatus(DeviceStatusEnum.ACTIVATED.getValue());
            devicePageQuery.setConnectStatus(DeviceConnectStatusEnum.ONLINE.getValue());
            // 添加版本过滤条件
            devicePageQuery = deviceVersionFilterStrategy.buildVersionFilterQuery(devicePageQuery, upgradeTask);
            return deviceService.getDeviceResultVOList(devicePageQuery);
        } catch (Exception e) {
            log.error("根据市级编码获取设备列表异常 - 市级编码: {}", cityCodes, e);
            return Collections.emptyList();
        }
    }
}