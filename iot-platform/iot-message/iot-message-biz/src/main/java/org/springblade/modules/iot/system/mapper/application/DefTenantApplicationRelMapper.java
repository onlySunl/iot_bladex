package org.springblade.modules.iot.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.application.DefTenantApplicationRel;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 租户的应用
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-15
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefTenantApplicationRelMapper extends SuperMapper<DefTenantApplicationRel> {

}
