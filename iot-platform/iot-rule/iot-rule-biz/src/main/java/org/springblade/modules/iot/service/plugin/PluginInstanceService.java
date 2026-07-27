package org.springblade.modules.iot.service.plugin;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.plugin.PluginInstance;
import org.springblade.modules.iot.vo.query.plugin.PluginInstancePageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInstanceResultVO;
import org.springblade.modules.iot.vo.result.plugin.PluginNacosInstanceResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInstanceSaveVO;
import org.springblade.modules.iot.vo.update.plugin.PluginInstanceUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 插件实例信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:02:17
 * @create [2024-08-27 16:02:17] [mqttsnet]
 */
public interface PluginInstanceService extends BaseService<Long, PluginInstance> {


    /**
     * 保存插件实例
     *
     * @param saveVO 插件实例保存参数
     * @return {@link PluginInstanceSaveVO} 插件实例实体
     */
    PluginInstanceSaveVO savePluginInstance(PluginInstanceSaveVO saveVO);

    /**
     * 更新插件实例
     *
     * @param updateVO 插件实例更新参数
     * @return {@link PluginInstanceUpdateVO} 插件实例更新结果
     */
    PluginInstanceUpdateVO updatePluginInstance(PluginInstanceUpdateVO updateVO);


    /**
     * 获取可用的插件服务实例
     *
     * @return {@link List<PluginNacosInstanceResultVO>}
     */
    List<PluginNacosInstanceResultVO> getAvailableInstances();

    /**
     * 查询插件实例列表
     *
     * @param query 查询条件
     * @return {@link List<PluginInstanceResultVO>} 插件实例列表
     */
    List<PluginInstanceResultVO> getPluginInstanceResultVOList(PluginInstancePageQuery query);

    /**
     * 查询插件实例
     *
     * @param instanceIdentification 实例标识
     * @return {@link PluginInstanceResultVO} 插件实例
     */
    PluginInstanceResultVO getPluginInstanceResultVO(String instanceIdentification);

    /**
     * 查询插件实例
     *
     * @param id 实例ID
     * @return {@link PluginInstanceResultVO} 插件实例
     */
    PluginInstanceResultVO getPluginInstanceResultVOById(Long id);


    /**
     * 删除插件实例
     *
     * @param id
     * @return
     */
    Boolean deletePluginInstance(Long id);
}


