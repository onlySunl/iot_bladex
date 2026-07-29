package org.springblade.modules.iot.plugin.controller;

import org.springblade.basic.base.controller.SuperController;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.basic.database.mybatis.conditions.query.QueryWrap;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.entity.plugin.PluginInstanceHeartbeat;
import org.springblade.modules.iot.service.plugin.PluginInstanceHeartbeatService;
import org.springblade.modules.iot.vo.query.plugin.PluginInstanceHeartbeatPageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInstanceHeartbeatResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInstanceHeartbeatSaveVO;
import org.springblade.modules.iot.vo.update.plugin.PluginInstanceHeartbeatUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 插件实例心跳表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:31:15
 * @create [2024-08-27 16:31:15] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/pluginInstanceHeartbeat")
@Tag(name = "插件实例心跳")
public class PluginInstanceHeartbeatController extends SuperController<PluginInstanceHeartbeatService, Long, PluginInstanceHeartbeat, PluginInstanceHeartbeatSaveVO,
        PluginInstanceHeartbeatUpdateVO, PluginInstanceHeartbeatPageQuery, PluginInstanceHeartbeatResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<PluginInstanceHeartbeat> handlerWrapper(PluginInstanceHeartbeat model, PageParams<PluginInstanceHeartbeatPageQuery> params) {
        QueryWrap<PluginInstanceHeartbeat> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("plugin_instance_heartbeat");
        return queryWrap;
    }

}


