package org.springblade.modules.iot.system.strategy.impl;

import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.model.enumeration.system.TenantConnectTypeEnum;
import org.springblade.modules.iot.system.entity.tenant.DefDatasourceConfig;
import org.springblade.modules.iot.system.entity.tenant.DefTenantDatasourceConfigRel;
import org.springblade.modules.iot.system.manager.tenant.DefDatasourceConfigManager;
import org.springblade.modules.iot.system.manager.tenant.DefTenantDatasourceConfigRelManager;
import org.springblade.modules.iot.system.strategy.InitSystemStrategy;
import org.springblade.modules.iot.system.vo.save.tenant.DefTenantInitVO;
import org.springblade.modules.iot.tenant.service.DataSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mqttsnet.basic.context.ContextConstants.TENANT_BASE_POOL_NAME_HEADER;
import static com.mqttsnet.basic.context.ContextConstants.TENANT_EXTEND_POOL_NAME_HEADER;

/**
 * 初始化系统
 * <p>
 *
 * @author mqttsnet
 * @date 2019/10/25
 */
@Service("DATASOURCE")
@Slf4j
@RequiredArgsConstructor
public class DatasourceInitSystemStrategy implements InitSystemStrategy {
    private final DefDatasourceConfigManager defDatasourceConfigManager;
    private final DefTenantDatasourceConfigRelManager defTenantDatasourceConfigRelManager;
    private final DataSourceService dataSourceService;

    /**
     * 求优化！
     *
     * @param tenantInitVO 链接信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initData(DefTenantInitVO tenantInitVO) {
        if (TenantConnectTypeEnum.CUSTOM.eq(tenantInitVO.getConnectType())) {
            ArgumentAssert.notNull(tenantInitVO.getBaseDatasourceId(), "请配置基础库数据库链接信息");
            ArgumentAssert.notNull(tenantInitVO.getExtendDatasourceId(), "请配置时序库数据库链接信息");
            List<Long> ids = Arrays.asList(tenantInitVO.getBaseDatasourceId(), tenantInitVO.getExtendDatasourceId());
            List<DefDatasourceConfig> dcList = defDatasourceConfigManager.listByIds(ids);
            ArgumentAssert.notEmpty(dcList, "请配置正确的基础库、时序数据库链接信息");

            defTenantDatasourceConfigRelManager.remove(Wraps.<DefTenantDatasourceConfigRel>lbQ().eq(DefTenantDatasourceConfigRel::getTenantId, tenantInitVO.getId()));
            List<DefTenantDatasourceConfigRel> list = new ArrayList<>();
            list.add(DefTenantDatasourceConfigRel.builder().dbPrefix(TENANT_BASE_POOL_NAME_HEADER).tenantId(tenantInitVO.getId()).datasourceConfigId(tenantInitVO.getBaseDatasourceId()).build());
            list.add(DefTenantDatasourceConfigRel.builder().dbPrefix(TENANT_EXTEND_POOL_NAME_HEADER).tenantId(tenantInitVO.getId()).datasourceConfigId(tenantInitVO.getExtendDatasourceId()).build());
            defTenantDatasourceConfigRelManager.saveBatch(list);

            dataSourceService.addCustomDsAndData(tenantInitVO.getId());
        } else if (TenantConnectTypeEnum.SYSTEM.eq(tenantInitVO.getConnectType())) {
            // 本地模式： 在自己的数据库中 实时新建 SCHEMA， 远程模式：需要自己先创建好数据库，并指定地址
            dataSourceService.createDatabase(tenantInitVO.getId());
            // 2. 动态添加所有数据源链接 & 创建表 & 初始数据
            dataSourceService.addSystemDsAndData(tenantInitVO.getId());
        }
        return true;
    }


    @Override
    public boolean reset(String tenant) {

        return true;
    }

    @Override
    public boolean delete(List<Long> ids) {
        ids.forEach(dataSourceService::removeDbAndDs);
        return true;
    }
}
