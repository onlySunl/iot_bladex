package org.springblade.modules.iot.service.plugin.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.entity.plugin.PluginInstanceMapping;
import org.springblade.modules.iot.manager.plugin.PluginInstanceMappingManager;
import org.springblade.modules.iot.service.plugin.PluginInstanceMappingService;
import org.springblade.modules.iot.vo.query.plugin.PluginInstanceMappingPageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInstanceMappingResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInstanceMappingSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 插件与实例及端口管理表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:30:09
 * @create [2024-08-27 16:30:09] [mqttsnet]
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class PluginInstanceMappingServiceImpl extends BaseServiceImpl<PluginInstanceMappingManager, Long, PluginInstanceMapping> implements PluginInstanceMappingService {


    @Override
    public List<PluginInstanceMappingResultVO> getPluginInstanceMappingResultVOList(PluginInstanceMappingPageQuery query) {
        return BeanPlusUtil.toBeanList(superManager.getPluginInstanceMappingList(query), PluginInstanceMappingResultVO.class);
    }

    @Override
    public void deletePluginInstanceMapping(String pluginIdentification, String instanceIdentification) {
        superManager.deletePluginInstanceMapping(pluginIdentification, instanceIdentification);
    }

    @Override
    public void savePluginInstanceMapping(PluginInstanceMappingSaveVO saveVO) {
        superManager.savePluginInstanceMapping(saveVO);
    }

    @Override
    public boolean isPluginInstalledOnInstance(String pluginIdentification, String instanceIdentification) {
        PluginInstanceMappingPageQuery query = new PluginInstanceMappingPageQuery()
                .setPluginIdentification(pluginIdentification)
                .setInstanceIdentification(instanceIdentification);
        List<PluginInstanceMapping> pluginInstanceMappingList = superManager.getPluginInstanceMappingList(query);
        return CollUtil.isNotEmpty(pluginInstanceMappingList);
    }

}


