package org.springblade.modules.iot.system.mapper.tenant;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.tenant.DefDatasourceConfig;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 数据源
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-13
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefDatasourceConfigMapper extends SuperMapper<DefDatasourceConfig> {

}
