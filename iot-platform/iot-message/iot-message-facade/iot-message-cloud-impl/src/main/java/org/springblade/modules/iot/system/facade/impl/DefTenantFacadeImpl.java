package org.springblade.modules.iot.system.facade.impl;

import com.mqttsnet.basic.base.R;
import org.springblade.modules.iot.system.api.DefTenantApi;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springblade.modules.iot.system.facade.DefTenantFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author tangyh
 * @since 2024/12/19 22:21
 */
@Service
@RequiredArgsConstructor
public class DefTenantFacadeImpl implements DefTenantFacade {
    @Autowired
    @Lazy  // 一定要延迟加载，否则thinglinks-gateway-server无法启动
    private DefTenantApi defTenantApi;

    @Override
    public R<List<DefTenant>> findAllTenant() {
        return defTenantApi.findAllTenant();
    }
}
