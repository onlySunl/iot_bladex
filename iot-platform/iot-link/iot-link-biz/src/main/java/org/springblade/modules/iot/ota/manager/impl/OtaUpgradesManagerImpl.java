package org.springblade.modules.iot.ota.manager.impl;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.database.mybatis.BladeServiceImpl;
import org.springblade.common.base.request.PageParams;
import org.springblade.common.database.mybatis.conditions.Wraps;
import org.springblade.common.database.mybatis.conditions.query.LbQueryWrap;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.core.tool.utils.StringUtils;
import org.springblade.modules.iot.ota.entity.OtaUpgrades;
import org.springblade.modules.iot.ota.manager.OtaUpgradesManager;
import org.springblade.modules.iot.ota.mapper.OtaUpgradesMapper;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradesPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * OTA升级包
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:36:27
 * @create [2024-01-12 22:36:27] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OtaUpgradesManagerImpl extends BladeServiceImpl<OtaUpgradesMapper, OtaUpgrades> implements OtaUpgradesManager {

    private final OtaUpgradesMapper otaUpgradesMapper;

    @Override
    public IPage<OtaUpgrades> getOtaUpgradesPage(PageParams<OtaUpgradesPageQuery> params) {
        IPage<OtaUpgrades> page = params.buildPage(OtaUpgrades.class);
        OtaUpgradesPageQuery paramsModel = params.getModel();

        // Construct query conditions
        LbQueryWrap<OtaUpgrades> wrap = Wraps.lbQ();
        wrap.eq(paramsModel.getId() != null, OtaUpgrades::getId, paramsModel.getId())
                .eq(StringUtils.hasText(paramsModel.getAppId()), OtaUpgrades::getAppId, paramsModel.getAppId())
                .like(StringUtils.hasText(paramsModel.getPackageName()), OtaUpgrades::getPackageName, paramsModel.getPackageName())
                .eq(paramsModel.getPackageType() != null, OtaUpgrades::getPackageType, paramsModel.getPackageType())
                .eq(StringUtils.hasText(paramsModel.getProductIdentification()), OtaUpgrades::getProductIdentification, paramsModel.getProductIdentification())
                .like(StringUtils.hasText(paramsModel.getVersion()), OtaUpgrades::getVersion, paramsModel.getVersion())
                .like(StringUtils.hasText(paramsModel.getFileLocation()), OtaUpgrades::getFileLocation, paramsModel.getFileLocation())
                .eq(paramsModel.getStatus() != null, OtaUpgrades::getStatus, paramsModel.getStatus())
                .like(StringUtils.hasText(paramsModel.getDescription()), OtaUpgrades::getDescription, paramsModel.getDescription())
                .like(StringUtils.hasText(paramsModel.getCustomInfo()), OtaUpgrades::getCustomInfo, paramsModel.getCustomInfo())
                .like(StringUtils.hasText(paramsModel.getRemark()), OtaUpgrades::getRemark, paramsModel.getRemark())
                .eq(paramsModel.getCreatedOrgId() != null, OtaUpgrades::getCreatedOrgId, paramsModel.getCreatedOrgId())
                .orderByDesc(OtaUpgrades::getCreatedTime);

        return otaUpgradesMapper.selectPage(page, wrap);
    }


    /**
     * Retrieves a list of OTA (Over-The-Air) upgrade records based on the specified query criteria.
     * This method supports filtering by a single ID or multiple IDs.
     *
     * @param query The {@link OtaUpgradesPageQuery} object containing the search criteria,
     *              which may include a single ID or multiple IDs for OTA upgrades.
     * @return A {@link List} of {@link OtaUpgrades} that match the given query criteria. Returns an empty list
     * if no records match the criteria.
     */
    @Override
    public List<OtaUpgrades> getOtaUpgradesList(OtaUpgradesPageQuery query) {
        QueryWrap<OtaUpgrades> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(query.getId() != null, OtaUpgrades::getId, query.getId());
        queryWrap.lambda().in(CollUtil.isNotEmpty(query.getIds()), OtaUpgrades::getId, query.getIds());
        queryWrap.lambda().eq(query.getStatus() != null, OtaUpgrades::getStatus, query.getStatus());
        queryWrap.lambda().orderByDesc(OtaUpgrades::getCreatedTime);
        return otaUpgradesMapper.selectList(queryWrap);
    }

}


