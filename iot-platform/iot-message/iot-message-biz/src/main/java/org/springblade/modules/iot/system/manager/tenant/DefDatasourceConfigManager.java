package org.springblade.modules.iot.system.manager.tenant;

import com.mqttsnet.basic.base.manager.SuperManager;
import org.springblade.modules.iot.system.entity.tenant.DefDatasourceConfig;

/**
 * <p>
 * 通用业务层
 * 数据源
 * </p>
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
public interface DefDatasourceConfigManager extends SuperManager<DefDatasourceConfig> {

    /**
     * 根据名称查询
     *
     * @param dsName
     * @return
     */
    DefDatasourceConfig getByName(String dsName);
}
