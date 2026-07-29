package org.springblade.modules.iot.system.mapper.system;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.system.DefArea;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 地区表
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefAreaMapper extends SuperMapper<DefArea> {

}
