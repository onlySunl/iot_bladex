package org.springblade.modules.iot.manager.plugin.impl;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.plugin.PluginInstanceHeartbeat;
import org.springblade.modules.iot.manager.plugin.PluginInstanceHeartbeatManager;
import org.springblade.modules.iot.mapper.plugin.PluginInstanceHeartbeatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 插件实例心跳表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:31:15
 * @create [2024-08-27 16:31:15] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PluginInstanceHeartbeatManagerImpl extends BaseServiceImpl<PluginInstanceHeartbeatMapper, PluginInstanceHeartbeat> implements PluginInstanceHeartbeatManager {

}


