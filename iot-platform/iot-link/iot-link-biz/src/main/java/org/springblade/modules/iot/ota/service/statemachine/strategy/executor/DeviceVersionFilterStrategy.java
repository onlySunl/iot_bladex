package org.springblade.modules.iot.ota.service.statemachine.strategy.executor;

import java.util.List;

import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;

/**
 * 设备版本过滤策略接口
 * 根据任务关联的升级包类型和源版本号过滤设备
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
public interface DeviceVersionFilterStrategy {

    /**
     * 检查设备版本是否符合升级条件
     *
     * @param task   升级任务
     * @param device 设备信息
     * @return true-符合条件，false-不符合条件
     */
    boolean isVersionMatch(OtaUpgradeTasksResultDTO task, DeviceResultVO device);

    /**
     * 获取设备当前版本
     *
     * @param task   升级任务
     * @param device 设备信息
     * @return {@link String} 设备当前版本号，若为空则返回空字符串
     */
    String getDeviceCurrentVersion(OtaUpgradeTasksResultDTO task, DeviceResultVO device);

    /**
     * 根据升级任务构建包含版本过滤的设备查询条件
     *
     * @param devicePageQuery 基础设备查询条件
     * @param upgradeTask     升级任务
     * @return 包含版本过滤条件的设备查询条件
     */
    DevicePageQuery buildVersionFilterQuery(DevicePageQuery devicePageQuery, OtaUpgradeTasksResultDTO upgradeTask);

    /**
     * 解析源版本号字符串
     *
     * @param sourceVersions 逗号分隔的源版本号字符串
     * @return 版本号列表
     */
    List<String> parseSourceVersions(String sourceVersions);
}