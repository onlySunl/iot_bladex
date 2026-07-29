package org.springblade.modules.iot.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.application.DefTenantResourceRel;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 租户的资源
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-13
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefTenantResourceRelMapper extends SuperMapper<DefTenantResourceRel> {

}
