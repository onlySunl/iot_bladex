package org.springblade.modules.iot.system.facade.impl;

import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import org.springblade.modules.iot.model.enumeration.system.DefTenantStatusEnum;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springblade.modules.iot.system.facade.DefTenantFacade;
import org.springblade.modules.iot.system.service.tenant.DefTenantService;
import lombok.RequiredArgsConstructor;
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
    private final DefTenantService defTenantService;

    @Override
    public R<List<DefTenant>> findAllTenant() {
        return R.success(defTenantService.list(Wraps.<DefTenant>lbQ().eq(DefTenant::getStatus, DefTenantStatusEnum.NORMAL)));
    }
}
