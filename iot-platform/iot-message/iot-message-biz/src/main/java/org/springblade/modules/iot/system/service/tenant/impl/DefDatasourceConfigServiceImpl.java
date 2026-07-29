package org.springblade.modules.iot.system.service.tenant.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.system.entity.tenant.DefDatasourceConfig;
import org.springblade.modules.iot.system.manager.tenant.DefDatasourceConfigManager;
import org.springblade.modules.iot.system.service.tenant.DefDatasourceConfigService;
import org.springblade.modules.iot.tenant.service.DataSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-13
 */
@Slf4j
@Service
@DS(DsConstant.DEFAULTS)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefDatasourceConfigServiceImpl extends SuperServiceImpl<DefDatasourceConfigManager, Long, DefDatasourceConfig>
        implements DefDatasourceConfigService {
    private final DataSourceService dataSourceService;

    @Override
    public Boolean testConnection(Long id) {
        DefDatasourceConfig config = superManager.getById(id);
        DataSourceProperty dataSourceProperty = BeanUtil.toBean(config, DataSourceProperty.class);
        return dataSourceService.testConnection(dataSourceProperty);
    }
}
