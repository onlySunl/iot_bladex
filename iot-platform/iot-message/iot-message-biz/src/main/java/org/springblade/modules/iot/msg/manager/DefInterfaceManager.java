package org.springblade.modules.iot.msg.manager;

import com.mqttsnet.basic.base.manager.SuperManager;
import org.springblade.modules.iot.msg.entity.DefInterface;

/**
 * <p>
 * 通用业务接口
 * 接口
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [mqttsnet] 
 */
public interface DefInterfaceManager extends SuperManager<DefInterface> {

    /**
     * 根据类型查询接口
     *
     * @param type
     * @return
     */
    DefInterface getByType(String type);
}


