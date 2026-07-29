package org.springblade.modules.iot.system.facade;

import com.mqttsnet.basic.base.R;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;

import java.util.List;

/**
 *
 * @author tangyh
 * @since 2024/12/19 22:21
 */
public interface DefTenantFacade {
    R<List<DefTenant>> findAllTenant();
}
