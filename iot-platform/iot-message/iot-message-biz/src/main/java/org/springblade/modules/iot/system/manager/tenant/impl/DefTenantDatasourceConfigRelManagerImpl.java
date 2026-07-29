package org.springblade.modules.iot.system.manager.tenant.impl;

import com.mqttsnet.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.system.entity.tenant.DefTenantDatasourceConfigRel;
import org.springblade.modules.iot.system.manager.tenant.DefTenantDatasourceConfigRelManager;
import org.springblade.modules.iot.system.mapper.tenant.DefTenantDatasourceConfigRelMapper;
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
public class DefTenantDatasourceConfigRelManagerImpl extends SuperManagerImpl<DefTenantDatasourceConfigRelMapper, DefTenantDatasourceConfigRel> implements DefTenantDatasourceConfigRelManager {

}
