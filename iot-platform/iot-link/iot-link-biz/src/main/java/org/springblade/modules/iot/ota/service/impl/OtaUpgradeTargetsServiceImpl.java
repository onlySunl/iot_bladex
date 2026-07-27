package org.springblade.modules.iot.ota.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.mp.base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTargetsResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTargets;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTargetStatusEnum;
import org.springblade.modules.iot.ota.manager.OtaUpgradeTargetsManager;
import org.springblade.modules.iot.ota.service.OtaUpgradeTargetsService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTargetsPageQuery;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeTargetsSaveVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * OTA升级目标表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 * @create [2025-10-19 16:28:50] [mqttsnet] [代码生成器生成]
 */
@Slf4j
@AllArgsConstructor
@Service
public class OtaUpgradeTargetsServiceImpl extends BaseServiceImpl<OtaUpgradeTargetsMapper, OtaUpgradeTargets> implements OtaUpgradeTargetsService {

    @Override
    public void saveBatchForOtaUpgradeTargets(List<OtaUpgradeTargetsSaveVO> otaUpgradeTargetsSaveVOList) {
        if (CollUtil.isEmpty(otaUpgradeTargetsSaveVOList)) {
            return;
        }
        // 批量保存
        superManager.saveBatch(BeanUtil.toBeanList(otaUpgradeTargetsSaveVOList, OtaUpgradeTargets.class));
    }

    @Override
    public void deleteByTaskId(Long taskId) {
        if (Objects.isNull(taskId)) {
            log.warn("任务ID为空，无法删除OTA升级目标");
            return;
        }
        superManager.remove(Wrappers.<OtaUpgradeTargets>lbQ().eq(OtaUpgradeTargets::getTaskId, taskId));
    }

    /**
     * 根据任务ID获取目标设备标识列表
     *
     * @param taskId 任务ID
     * @return {@link Optional<List<String>>} 设备标识列表Optional
     */
    @Override
    public Optional<List<String>> getTargetDevicesByTaskIdOptional(Long taskId) {
        try {
            if (Objects.isNull(taskId)) {
                log.warn("任务ID为空，无法获取目标设备");
                return Optional.empty();
            }
            // 获取升级目标列表
            OtaUpgradeTargetsPageQuery query = new OtaUpgradeTargetsPageQuery();
            query.setTaskId(taskId);
            List<OtaUpgradeTargetsResultDTO> targets = getOtaUpgradeTargetsResultDTOList(query);

            if (CollUtil.isEmpty(targets)) {
                log.warn("未找到任务的目标设备 - 任务ID: {}", taskId);
                return Optional.empty();
            }
            // 提取目标值（设备标识、分组ID或区域编码）
            List<String> targetValues = targets.stream()
                    .map(OtaUpgradeTargetsResultDTO::getTargetValue)
                    .collect(Collectors.toList());

            log.info("获取到任务目标值列表 - 任务ID: {}, 目标数量: {}", taskId, targetValues.size());
            return Optional.of(targetValues);
        } catch (Exception e) {
            log.error("获取任务目标设备列表异常 - 任务ID: {}", taskId, e);
            return Optional.empty();
        }
    }

    /**
     * 根据查询条件获取升级目标信息列表
     *
     * @param query 查询条件
     * @return {@link List<OtaUpgradeTargetsResultDTO>} 升级目标信息列表
     */
    @Override
    public List<OtaUpgradeTargetsResultDTO> getOtaUpgradeTargetsResultDTOList(OtaUpgradeTargetsPageQuery query) {
        return BeanUtil.toBeanList(superManager.getOtaUpgradeTargetsList(query), OtaUpgradeTargetsResultDTO.class);
    }

    /**
     * 根据任务ID和目标值更新目标状态
     *
     * @param taskId                     任务ID
     * @param targetValue                目标值（设备标识/分组ID/区域编码）
     * @param otaUpgradeTargetStatusEnum 目标状态
     * @return 更新是否成功
     */
    @Override
    public boolean updateTargetStatusByTaskAndValue(Long taskId, String targetValue, OtaUpgradeTargetStatusEnum otaUpgradeTargetStatusEnum) {
        try {
            if (Objects.isNull(taskId) || Objects.isNull(targetValue) || Objects.isNull(otaUpgradeTargetStatusEnum)) {
                return false;
            }

            // 检查目标状态是否有效
            if (!OtaUpgradeTargetStatusEnum.isValid(otaUpgradeTargetStatusEnum.getValue())) {
                log.warn("目标状态无效 - 任务ID: {}, 目标值: {}, 目标状态: {}", taskId, targetValue, otaUpgradeTargetStatusEnum);
                return false;
            }

            // 更新目标状态
            boolean updated = superManager.update(Wrappers.<OtaUpgradeTargets>lbU()
                    .set(OtaUpgradeTargets::getTargetStatus, otaUpgradeTargetStatusEnum.getValue())
                    .eq(OtaUpgradeTargets::getTaskId, taskId)
                    .eq(OtaUpgradeTargets::getTargetValue, targetValue));

            if (updated) {
                log.info("更新目标状态成功 - 任务ID: {}, 目标值: {}, 目标状态: {}", taskId, targetValue, otaUpgradeTargetStatusEnum.getDesc());
            } else {
                log.warn("更新目标状态失败，未找到匹配记录 - 任务ID: {}, 目标值: {}", taskId, targetValue);
            }

            return updated;
        } catch (Exception e) {
            log.error("更新目标状态异常 - 任务ID: {}, 目标值: {}, 目标状态: {}", taskId, targetValue, otaUpgradeTargetStatusEnum, e);
            return false;
        }
    }
}