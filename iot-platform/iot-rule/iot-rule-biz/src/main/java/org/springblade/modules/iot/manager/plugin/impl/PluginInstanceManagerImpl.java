package org.springblade.modules.iot.manager.plugin.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.core.database.mybatis.BladeServiceImpl;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.entity.plugin.PluginInstance;
import org.springblade.modules.iot.manager.plugin.PluginInstanceManager;
import org.springblade.modules.iot.mapper.plugin.PluginInstanceMapper;
import org.springblade.modules.iot.vo.query.plugin.PluginInstancePageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 插件实例信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:02:17
 * @create [2024-08-27 16:02:17] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PluginInstanceManagerImpl extends BladeServiceImpl<PluginInstanceMapper, PluginInstance> implements PluginInstanceManager {

    private final PluginInstanceMapper pluginInstanceMapper;

    /**
     * 查询插件实例列表
     *
     * @param query 查询条件
     * @return 插件实例列表
     */
    public List<PluginInstance> getPluginInstanceList(PluginInstancePageQuery query) {
        QueryWrapper<PluginInstance> queryWrapper = new QueryWrapper<>();

        // 拼接查询条件
        queryWrapper.lambda().eq(query.getId() != null, PluginInstance::getId, query.getId());
        queryWrapper.lambda().eq(query.getInstanceIdentification() != null, PluginInstance::getInstanceIdentification, query.getInstanceIdentification());
        queryWrapper.lambda().eq(query.getInstanceName() != null, PluginInstance::getInstanceName, query.getInstanceName());
        queryWrapper.lambda().eq(query.getApplicationName() != null, PluginInstance::getApplicationName, query.getApplicationName());
        queryWrapper.lambda().eq(query.getMachineIp() != null, PluginInstance::getMachineIp, query.getMachineIp());
        queryWrapper.lambda().eq(query.getWeight() != null, PluginInstance::getWeight, query.getWeight());
        queryWrapper.lambda().eq(query.getHealthy() != null, PluginInstance::getHealthy, query.getHealthy());
        queryWrapper.lambda().eq(query.getEnabled() != null, PluginInstance::getEnabled, query.getEnabled());
        queryWrapper.lambda().eq(query.getEphemeral() != null, PluginInstance::getEphemeral, query.getEphemeral());
        queryWrapper.lambda().eq(query.getClusterName() != null, PluginInstance::getClusterName, query.getClusterName());
        queryWrapper.lambda().eq(query.getHeartBeatInterval() != null, PluginInstance::getHeartBeatInterval, query.getHeartBeatInterval());
        queryWrapper.lambda().eq(query.getHeartBeatTimeOut() != null, PluginInstance::getHeartBeatTimeOut, query.getHeartBeatTimeOut());
        queryWrapper.lambda().eq(query.getIpDeleteTimeOut() != null, PluginInstance::getIpDeleteTimeOut, query.getIpDeleteTimeOut());
        queryWrapper.lambda().eq(query.getMachinePort() != null, PluginInstance::getMachinePort, query.getMachinePort());

        return pluginInstanceMapper.selectList(queryWrapper);
    }

    @Override
    public PluginInstance findOneByInstanceIdentification(String instanceIdentification) {
        QueryWrap<PluginInstance> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(instanceIdentification), PluginInstance::getInstanceIdentification, instanceIdentification);
        return pluginInstanceMapper.selectOne(queryWrap);
    }
}


