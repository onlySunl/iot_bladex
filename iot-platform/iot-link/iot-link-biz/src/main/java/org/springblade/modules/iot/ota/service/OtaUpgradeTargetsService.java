package org.springblade.modules.iot.ota.service;

import java.util.List;
import java.util.Optional;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTargetsResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTargets;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTargetStatusEnum;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTargetsPageQuery;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeTargetsSaveVO;

/**
 * <p>
 * 业务接口
 * OTA升级目标表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 * @create [2025-10-19 16:28:50] [mqttsnet] [代码生成器生成]
 */
public interface OtaUpgradeTargetsService extends BaseService<OtaUpgradeTargets> {
    /**
     * 批量保存OTA升级目标
     *
     * @param otaUpgradeTargetsSaveVOList OTA升级目标保存VO列表
     */
    void saveBatchForOtaUpgradeTargets(List<OtaUpgradeTargetsSaveVO> otaUpgradeTargetsSaveVOList);

    /**
     * 根据任务ID删除OTA升级目标
     *
     * @param taskId 任务ID
     */
    void deleteByTaskId(Long taskId);

    /**
     * 根据任务ID获取目标设备标识列表
     *
     * @param taskId 任务ID
     * @return {@link Optional<List<String>>} 设备标识列表Optional
     */
    Optional<List<String>> getTargetDevicesByTaskIdOptional(Long taskId);

    /**
     * 根据查询条件获取升级目标列表
     *
     * @param query 查询条件
     * @return {@link List<OtaUpgradeTargetsResultDTO>} 升级目标列表
     */
    List<OtaUpgradeTargetsResultDTO> getOtaUpgradeTargetsResultDTOList(OtaUpgradeTargetsPageQuery query);

    /**
     * 根据任务ID和目标值更新目标状态
     *
     * @param taskId       任务ID
     * @param targetValue  目标值（设备标识/分组ID/区域编码）
     * @param otaUpgradeTargetStatusEnum 目标状态
     * @return 更新是否成功
     */
    boolean updateTargetStatusByTaskAndValue(Long taskId, String targetValue, OtaUpgradeTargetStatusEnum otaUpgradeTargetStatusEnum);
}