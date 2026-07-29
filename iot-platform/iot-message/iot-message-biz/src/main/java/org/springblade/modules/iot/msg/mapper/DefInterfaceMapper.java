package org.springblade.modules.iot.msg.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.msg.entity.DefInterface;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 接口
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [mqttsnet] 
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefInterfaceMapper extends SuperMapper<DefInterface> {

}


