package org.springblade.modules.iot.system.mapper.system;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.system.DefParameter;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 参数配置
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefParameterMapper extends SuperMapper<DefParameter> {

}
