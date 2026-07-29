package org.springblade.modules.iot.system.manager.tenant.impl;

import com.mqttsnet.basic.base.manager.impl.SuperManagerImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import org.springblade.modules.iot.system.entity.tenant.DefDatasourceConfig;
import org.springblade.modules.iot.system.manager.tenant.DefDatasourceConfigManager;
import org.springblade.modules.iot.system.mapper.tenant.DefDatasourceConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 应用管理
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
@RequiredArgsConstructor
@Service
public class DefDatasourceConfigManagerImpl extends SuperManagerImpl<DefDatasourceConfigMapper, DefDatasourceConfig> implements DefDatasourceConfigManager {
    @Override
    public DefDatasourceConfig getByName(String dsName) {
        return getOne(Wraps.<DefDatasourceConfig>lbQ().eq(DefDatasourceConfig::getName, dsName), false);
    }
}
