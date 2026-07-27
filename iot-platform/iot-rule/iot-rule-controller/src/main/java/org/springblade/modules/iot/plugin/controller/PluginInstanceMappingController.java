package org.springblade.modules.iot.plugin.controller;

import org.springblade.core.mp.base.BaseController;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.entity.plugin.PluginInstanceMapping;
import org.springblade.modules.iot.service.plugin.PluginInstanceMappingService;
import org.springblade.modules.iot.vo.query.plugin.PluginInstanceMappingPageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInstanceMappingResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInstanceMappingSaveVO;
import org.springblade.modules.iot.vo.update.plugin.PluginInstanceMappingUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 插件与实例及端口管理
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:30:09
 * @create [2024-08-27 16:30:09] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/pluginInstanceMapping")
@Tag(name = "插件实例端口管理")
public class PluginInstanceMappingController extends BaseController<PluginInstanceMappingService, Long, PluginInstanceMapping, PluginInstanceMappingSaveVO,
        PluginInstanceMappingUpdateVO, PluginInstanceMappingPageQuery, PluginInstanceMappingResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


