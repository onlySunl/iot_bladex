package org.springblade.modules.iot.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.application.DefTenantApplicationRecord;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 租户应用授权记录
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-15
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefTenantApplicationRecordMapper extends SuperMapper<DefTenantApplicationRecord> {

}
