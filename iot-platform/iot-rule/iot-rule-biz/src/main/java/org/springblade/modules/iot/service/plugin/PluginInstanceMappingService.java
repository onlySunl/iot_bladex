package org.springblade.modules.iot.service.plugin;

import org.springblade.core.mp.service.BladeService;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.entity.plugin.PluginInstanceMapping;
import org.springblade.modules.iot.vo.query.plugin.PluginInstanceMappingPageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInstanceMappingResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInstanceMappingSaveVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 插件与实例及端口管理表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:30:09
 * @create [2024-08-27 16:30:09] [mqttsnet]
 */
public interface PluginInstanceMappingService extends BladeService<PluginInstanceMapping> {


    /**
     * 插件实例端口列表
     *
     * @param query 查询条件
     * @return {@link List<PluginInstanceMappingResultVO>} 插件列表结果
     */
    List<PluginInstanceMappingResultVO> getPluginInstanceMappingResultVOList(PluginInstanceMappingPageQuery query);

    /**
     * 删除插件实例及端口
     *
     * @param pluginIdentification   插件标识
     * @param instanceIdentification 实例标识
     */
    void deletePluginInstanceMapping(String pluginIdentification, String instanceIdentification);

    /**
     * 保存插件实例映射
     *
     * @param saveVO 保存VO
     */
    void savePluginInstanceMapping(PluginInstanceMappingSaveVO saveVO);

    /**
     * 判断插件是否安装在实例上
     *
     * @param pluginIdentification   插件标识
     * @param instanceIdentification 实例标识
     * @return {@link Boolean} 是否安装
     */
    boolean isPluginInstalledOnInstance(String pluginIdentification, String instanceIdentification);
}


