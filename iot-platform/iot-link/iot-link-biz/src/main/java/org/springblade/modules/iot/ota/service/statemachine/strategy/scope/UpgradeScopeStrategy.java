package org.springblade.modules.iot.ota.service.statemachine.strategy.scope;

import java.util.List;
import java.util.Optional;

import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeScopeEnum;

/**
 * OTA升级范围策略接口
 * <p>
 * 定义不同升级范围的设备获取策略
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
public interface UpgradeScopeStrategy {

    /**
     * 获取升级范围内的设备列表
     *
     * @param upgradeTask 升级任务
     * @return 设备列表Optional
     */
    Optional<List<DeviceResultVO>> getScopeDevices(OtaUpgradeTasksResultDTO upgradeTask);

    /**
     * 是否支持该升级范围
     *
     * @param upgradeScope 升级范围
     * @return 是否支持
     */
    boolean supports(Integer upgradeScope);

    /**
     * 支持的升级范围类型
     *
     * @return 升级范围枚举
     */
    OtaUpgradeScopeEnum getSupportedScope();
}
