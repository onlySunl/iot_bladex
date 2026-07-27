package org.springblade.modules.iot.ota.manager.impl;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.boot.request.PageParam;
import org.springblade.common.database.mybatis.conditions.Wraps;
import org.springblade.common.database.mybatis.conditions.query.LbQueryWrap;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.core.tool.utils.StringUtils;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTasks;
import org.springblade.modules.iot.ota.manager.OtaUpgradeTasksManager;
import org.springblade.modules.iot.ota.mapper.OtaUpgradeTasksMapper;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTasksPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * OTA升级任务表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:40:04
 * @create [2024-01-12 22:40:04] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OtaUpgradeTasksManagerImpl extends SuperManagerImpl<OtaUpgradeTasksMapper, OtaUpgradeTasks> implements OtaUpgradeTasksManager {

    private final OtaUpgradeTasksMapper otaUpgradeTasksMapper;

    /**
     * Queries the paginated details of OTA upgrade tasks.
     *
     * @param params Pagination parameters
     * @return {@link IPage <OtaUpgradeTasks>} Paginated details of OTA upgrade tasks
     */
    @Override
    public IPage<OtaUpgradeTasks> getOtaUpgradeTasksPage(PageParams<OtaUpgradeTasksPageQuery> params) {
        IPage<OtaUpgradeTasks> page = params.buildPage(OtaUpgradeTasks.class);
        OtaUpgradeTasksPageQuery paramsModel = params.getModel();

        // Construct query conditions
        LbQueryWrap<OtaUpgradeTasks> wrap = Wraps.lbQ();
        wrap.eq(paramsModel.getId() != null, OtaUpgradeTasks::getId, paramsModel.getId())
                .eq(paramsModel.getUpgradeId() != null, OtaUpgradeTasks::getUpgradeId, paramsModel.getUpgradeId())
                .like(StringUtils.hasText(paramsModel.getTaskName()), OtaUpgradeTasks::getTaskName, paramsModel.getTaskName())
                .eq(paramsModel.getTaskStatus() != null, OtaUpgradeTasks::getTaskStatus, paramsModel.getTaskStatus())
                .ge(paramsModel.getScheduledStartTime() != null, OtaUpgradeTasks::getScheduledStartTime, paramsModel.getScheduledStartTime())
                .like(StringUtils.hasText(paramsModel.getDescription()), OtaUpgradeTasks::getDescription, paramsModel.getDescription())
                .like(StringUtils.hasText(paramsModel.getRemark()), OtaUpgradeTasks::getRemark, paramsModel.getRemark())
                .eq(paramsModel.getCreatedOrgId() != null, OtaUpgradeTasks::getCreatedOrgId, paramsModel.getCreatedOrgId());

        return otaUpgradeTasksMapper.selectPage(page, wrap);
    }


    @Override
    public List<OtaUpgradeTasks> getOtaUpgradeTasksList(OtaUpgradeTasksPageQuery query) {
        QueryWrap<OtaUpgradeTasks> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(query.getId() != null, OtaUpgradeTasks::getId, query.getId());
        queryWrap.lambda().eq(query.getUpgradeId() != null, OtaUpgradeTasks::getUpgradeId, query.getUpgradeId());
        queryWrap.lambda().in(CollUtil.isNotEmpty(query.getUpgradeIdList()), OtaUpgradeTasks::getUpgradeId, query.getUpgradeIdList());
        queryWrap.lambda().like(StringUtils.hasText(query.getTaskName()), OtaUpgradeTasks::getTaskName, query.getTaskName());
        queryWrap.lambda().eq(query.getTaskStatus() != null, OtaUpgradeTasks::getTaskStatus, query.getTaskStatus());
        queryWrap.lambda().in(CollUtil.isNotEmpty(query.getTaskStatusList()), OtaUpgradeTasks::getTaskStatus, query.getTaskStatusList());
        queryWrap.lambda().le(query.getScheduledStartTime() != null, OtaUpgradeTasks::getScheduledStartTime, query.getScheduledStartTime());
        queryWrap.lambda().ge(query.getScheduledEndTime() != null, OtaUpgradeTasks::getScheduledEndTime, query.getScheduledEndTime());
        queryWrap.lambda().between(query.getScheduledStartTimeStart() != null && query.getScheduledStartTimeEnd() != null, OtaUpgradeTasks::getScheduledStartTime, query.getScheduledStartTimeStart(), query.getScheduledStartTimeEnd());
        queryWrap.lambda().like(StringUtils.hasText(query.getDescription()), OtaUpgradeTasks::getDescription, query.getDescription());
        queryWrap.lambda().like(StringUtils.hasText(query.getRemark()), OtaUpgradeTasks::getRemark, query.getRemark());
        queryWrap.lambda().orderByDesc(OtaUpgradeTasks::getCreatedTime);
        return otaUpgradeTasksMapper.selectList(queryWrap);
    }
}