package org.springblade.modules.iot.ota.service.statemachine.strategy.scope;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springblade.core.mvc.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeScopeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 升级范围策略工厂
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
public class UpgradeScopeStrategyFactory {

    private final Map<OtaUpgradeScopeEnum, UpgradeScopeStrategy> strategyMap;

    /**
     * 构造函数
     *
     * @param strategies 策略列表
     */
    public UpgradeScopeStrategyFactory(List<UpgradeScopeStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .filter(s -> s != null && s.getSupportedScope() != null)
                .collect(Collectors.toMap(
                        UpgradeScopeStrategy::getSupportedScope,
                        Function.identity(),
                        (existing, replacement) -> {
                            log.warn("发现重复的策略注册: {}, 使用现有策略", existing.getSupportedScope());
                            return existing;
                        }
                ));
    }

    /**
     * 根据升级范围获取策略
     *
     * @param upgradeScope 升级范围
     * @return 策略Optional
     */
    public Optional<UpgradeScopeStrategy> getStrategy(Integer upgradeScope) {
        if (Objects.isNull(upgradeScope)) {
            log.warn("升级范围参数为空");
            return Optional.empty();
        }

        // 通过枚举值查找对应的枚举
        Optional<OtaUpgradeScopeEnum> scopeEnumOpt = OtaUpgradeScopeEnum.fromValue(upgradeScope);
        if (scopeEnumOpt.isEmpty()) {
            log.warn("不支持的升级范围值: {}", upgradeScope);
            return Optional.empty();
        }

        OtaUpgradeScopeEnum scopeEnum = scopeEnumOpt.get();
        UpgradeScopeStrategy strategy = strategyMap.get(scopeEnum);

        if (Objects.isNull(strategy)) {
            log.warn("未找到对应的升级范围策略: {}", scopeEnum);
            return Optional.empty();
        }

        return Optional.of(strategy);
    }

    /**
     * 根据升级范围获取策略，如果不存在则抛出异常
     *
     * @param upgradeScope 升级范围
     * @return 策略
     * @throws BizException 如果策略不存在
     */
    public UpgradeScopeStrategy getStrategyRequired(Integer upgradeScope) {
        return getStrategy(upgradeScope)
                .orElseThrow(() -> {
                    log.error("不支持的升级范围策略: {}", upgradeScope);
                    return new BizException("不支持的升级范围: " + upgradeScope);
                });
    }

    /**
     * 根据升级范围枚举获取策略
     *
     * @param upgradeScope 升级范围枚举
     * @return 策略Optional
     */
    public Optional<UpgradeScopeStrategy> getStrategy(OtaUpgradeScopeEnum upgradeScope) {
        if (Objects.isNull(upgradeScope)) {
            log.warn("升级范围枚举参数为空");
            return Optional.empty();
        }

        UpgradeScopeStrategy strategy = strategyMap.get(upgradeScope);
        if (Objects.isNull(strategy)) {
            log.warn("未找到对应的升级范围策略: {}", upgradeScope);
            return Optional.empty();
        }

        return Optional.of(strategy);
    }

    /**
     * 根据升级范围枚举获取策略，如果不存在则抛出异常
     *
     * @param upgradeScope 升级范围枚举
     * @return 策略
     * @throws BizException 如果策略不存在
     */
    public UpgradeScopeStrategy getStrategyRequired(OtaUpgradeScopeEnum upgradeScope) {
        return getStrategy(upgradeScope)
                .orElseThrow(() -> {
                    log.error("不支持的升级范围策略: {}", upgradeScope);
                    return new BizException("不支持的升级范围: " + upgradeScope);
                });
    }

    /**
     * 获取所有已注册的策略
     *
     * @return 策略映射
     */
    public Map<OtaUpgradeScopeEnum, UpgradeScopeStrategy> getAllStrategies() {
        return Map.copyOf(strategyMap);
    }

    /**
     * 检查是否支持该升级范围
     *
     * @param upgradeScope 升级范围
     * @return 是否支持
     */
    public boolean supports(Integer upgradeScope) {
        return getStrategy(upgradeScope).isPresent();
    }

    /**
     * 检查是否支持该升级范围枚举
     *
     * @param upgradeScope 升级范围枚举
     * @return 是否支持
     */
    public boolean supports(OtaUpgradeScopeEnum upgradeScope) {
        return getStrategy(upgradeScope).isPresent();
    }
}
