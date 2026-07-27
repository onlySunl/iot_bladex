package org.springblade.modules.iot.ota.manager.impl;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.common.base.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTargets;
import org.springblade.modules.iot.ota.manager.OtaUpgradeTargetsManager;
import org.springblade.modules.iot.ota.mapper.OtaUpgradeTargetsMapper;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTargetsPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 通用业务实现类
 * OTA升级目标表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 * @create [2025-10-19 16:28:50] [mqttsnet] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OtaUpgradeTargetsManagerImpl extends SuperManagerImpl<OtaUpgradeTargetsMapper, OtaUpgradeTargets> implements OtaUpgradeTargetsManager {
    private final OtaUpgradeTargetsMapper otaUpgradeTargetsMapper;

    @Override
    public List<OtaUpgradeTargets> getOtaUpgradeTargetsList(OtaUpgradeTargetsPageQuery query) {
        QueryWrapper<OtaUpgradeTargets> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(query.getTaskId() != null, OtaUpgradeTargets::getTaskId, query.getTaskId());
        queryWrapper.lambda().in(CollUtil.isNotEmpty(query.getTaskIds()), OtaUpgradeTargets::getTaskId, query.getTaskIds());
        queryWrapper.lambda().like(query.getTargetStatus() != null, OtaUpgradeTargets::getTargetStatus, query.getTargetStatus());
        return otaUpgradeTargetsMapper.selectList(queryWrapper);
    }
}


