package org.springblade.modules.iot.controller.admin.alert;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.controller.admin.alert.vo.ChannelReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.*;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplate;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplatePageReqVO;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplateSaveReqVO;
import org.springblade.modules.iot.service.alert.ChannelConfigService;
import org.springblade.modules.iot.service.alert.ChannelService;
import org.springblade.modules.iot.service.alert.ChannelTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.util.List;

/**
 * author: 石恒
 * date: 2023-05-11 15:17
 * description:
 **/
@Tag(name = "消息通知")
@Slf4j
@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Resource
    private ChannelService channelService;

    @Resource
    private ChannelConfigService channelConfigService;

    @Resource
    private ChannelTemplateService channelTemplateService;

    @Operation(summary = "获取通道类型列表")
    @PostMapping("/channel/getList")
    public R<List<Channel>> getChannelList(@RequestBody @Validated ChannelReqVO reqVO) {
        return R.data(channelService.getChannelList(reqVO));
    }

    @Operation(summary = "获取通道配置分页列表")
    @PostMapping("/channel/config/getList")
    public R<PageResult<ChannelConfig>> getChannelConfigList(@RequestBody @Validated ChannelConfigPageReqVO request) {
        return R.data(channelConfigService.getChannelConfigPage(request));
    }


    @Operation(summary = "新增通道配置")
    @PostMapping("/channel/config/add")
    public R<Long> addChannelConfig(@RequestBody @Validated ChannelConfigSaveReqVO request) {
        return R.data(channelConfigService.createChannelConfig(request));
    }

    @Operation(summary = "根据ID获取通道配置")
    @PostMapping("/channel/config/getById")
    public R<ChannelConfig> getChannelConfigById(@RequestBody @Validated IdReqVo request) {
        return R.data(channelConfigService.getChannelConfig(request.getId()));
    }

    @Operation(summary = "修改通道配置")
    @PostMapping("/channel/config/updateById")
    public R<Boolean> updateChannelConfigById(@RequestBody @Validated ChannelConfigSaveReqVO request) {
        channelConfigService.updateChannelConfig(request);
        return R.data(true);
    }

    @Operation(summary = "删除通道配置")
    @PostMapping("/channel/config/delById")
    public R<Boolean> delChannelConfigById(@RequestBody @Validated IdReqVo reqVo) {
        channelConfigService.deleteChannelConfig(reqVo.getId());
        return R.data(true);
    }

    @Operation(summary = "获取通道模板列表")
    @PostMapping("/channel/template/getList")
    public R<PageResult<ChannelTemplate>> getChannelTemplateList(@RequestBody @Validated ChannelTemplatePageReqVO request) {
        return R.data(channelTemplateService.getChannelTemplatePage(request));
    }

    @Operation(summary = "新增通道模板")
    @PostMapping("/channel/template/add")
    public R<Long> addChannelTemplate(@RequestBody @Validated ChannelTemplateSaveReqVO request) {
        return R.data(channelTemplateService.createChannelTemplate(request));
    }

    @Operation(summary = "根据ID获取通道模板")
    @PostMapping("/channel/template/getById")
    public R<ChannelTemplate> getChannelTemplateById(@RequestBody @Validated IdReqVo request) {
        return R.data(channelTemplateService.getChannelTemplate(request.getId()));
    }

    @Operation(summary = "修改通道模板")
    @PostMapping("/channel/template/updateById")
    public R<Boolean> updateChannelTemplateById(@RequestBody @Validated ChannelTemplateSaveReqVO request) {
        channelTemplateService.updateChannelTemplate(request);
        return R.data(true);
    }

    @Operation(summary = "删除通道模板")
    @PostMapping("/channel/template/delById")
    public R<Boolean> delChannelTemplateById(@RequestBody @Validated IdReqVo request) {
        channelTemplateService.deleteById(request.getId());
        return R.data(true);
    }

//    @Operation(summary = "消息列表")
//
//    @PostMapping("/message/getList")
//    public R<PageResult<NotifyMessage>> messageList(@RequestBody @Validated NotifyMessagePageReq request) {
//        return R.success(notifyService.getNotifyMessageList(request));
//    }

}
