package org.springblade.modules.iot.msg.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.msg.entity.DefInterfaceProperty;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 接口属性
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefInterfacePropertyMapper extends SuperMapper<DefInterfaceProperty> {

}


