package org.springblade.modules.iot.manager.plugin;

import org.springblade.core.database.mybatis.BladeService;
import org.springblade.modules.iot.entity.plugin.PluginInfo;
import org.springblade.modules.iot.vo.query.plugin.PluginInfoPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 插件信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-25 19:05:11
 * @create [2024-08-25 19:05:11] [mqttsnet]
 */
public interface PluginInfoManager extends BladeService<PluginInfo> {

    /**
     * 根据插件标识获取插件信息
     *
     * @param pluginIdentification 插件标识
     * @return {@link PluginInfo} 插件信息
     */
    PluginInfo findByPluginIdentification(String pluginIdentification);

    /**
     * 查询插件信息列表
     *
     * @param query 查询条件
     * @return 插件信息列表
     */
    List<PluginInfo> getPluginInfoList(PluginInfoPageQuery query);

}


