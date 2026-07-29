package org.springblade.modules.iot.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.application.DefUserApplication;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 用户的默认应用
 * </p>
 *
 * @author mqttsnet
 * @date 2022-03-06
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefUserApplicationMapper extends SuperMapper<DefUserApplication> {

}
