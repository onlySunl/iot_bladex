package org.springblade.modules.iot.msg.manager;

import org.springblade.core.mvc.manager.SuperManager;
import org.springblade.modules.iot.msg.entity.DefInterfaceProperty;

import java.util.Map;

/**
 * <p>
 * 通用业务接口
 * 接口属性
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
public interface DefInterfacePropertyManager extends SuperManager<DefInterfaceProperty> {
    /**
     * 根据接口ID查询接口属性参数
     *
     * @param id
     * @return
     */
    Map<String, Object> listByInterfaceId(Long id);
}


