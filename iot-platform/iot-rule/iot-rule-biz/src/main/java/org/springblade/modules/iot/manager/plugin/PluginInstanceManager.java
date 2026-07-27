package org.springblade.modules.iot.manager.plugin;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.plugin.PluginInstance;
import org.springblade.modules.iot.vo.query.plugin.PluginInstancePageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 插件实例信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:02:17
 * @create [2024-08-27 16:02:17] [mqttsnet]
 */
public interface PluginInstanceManager extends BaseService<PluginInstance> {

    /**
     * 查询插件实例列表
     *
     * @param query 查询条件
     * @return {@link List<PluginInstance>} 插件实例列表
     */
    List<PluginInstance> getPluginInstanceList(PluginInstancePageQuery query);

    /**
     * @param instanceIdentification 实例标识
     * @return {@link PluginInstance} 插件实例
     */
    PluginInstance findOneByInstanceIdentification(String instanceIdentification);
}


