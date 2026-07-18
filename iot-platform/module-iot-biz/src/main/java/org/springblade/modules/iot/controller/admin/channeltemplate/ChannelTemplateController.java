package org.springblade.modules.iot.controller.admin.channeltemplate;

import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.service.alert.ChannelTemplateService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import org.springblade.modules.iot.controller.admin.channeltemplate.vo.*;

import static org.springblade.modules.iot.common.entity.CommonResult.success;

@Tag(name = "管理后台 - 通道模板")
@RestController
@RequestMapping("/eiot/channel-template")
@Validated
public class ChannelTemplateController {

    @Resource
    private ChannelTemplateService channelTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建通道模板")
    @PreAuthorize("@ss.hasPermission('iot:channel-template:create')")
    public CommonResult<Long> createChannelTemplate(@Valid @RequestBody ChannelTemplateSaveReqVO createReqVO) {
        return success(channelTemplateService.createChannelTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通道模板")
    @PreAuthorize("@ss.hasPermission('iot:channel-template:update')")
    public CommonResult<Boolean> updateChannelTemplate(@Valid @RequestBody ChannelTemplateSaveReqVO updateReqVO) {
        channelTemplateService.updateChannelTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通道模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:channel-template:delete')")
    public CommonResult<Boolean> deleteChannelTemplate(@RequestParam("id") Long id) {
        channelTemplateService.deleteById(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通道模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:channel-template:query')")
    public CommonResult<ChannelTemplateRespVO> getChannelTemplate(@RequestParam("id") Long id) {
        ChannelTemplate channelTemplate = channelTemplateService.getChannelTemplate(id);
        return success(BeanUtils.toBean(channelTemplate, ChannelTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通道模板分页")
    public CommonResult<PageResult<ChannelTemplateRespVO>> getChannelTemplatePage(@Valid ChannelTemplatePageReqVO pageReqVO) {
        PageResult<ChannelTemplate> pageResult = channelTemplateService.getChannelTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChannelTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通道模板 Excel")
    @PreAuthorize("@ss.hasPermission('iot:channel-template:export')")
    public void exportChannelTemplateExcel(@Valid ChannelTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChannelTemplate> list = channelTemplateService.getChannelTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通道模板.xls", "数据", ChannelTemplateRespVO.class,
                BeanUtils.toBean(list, ChannelTemplateRespVO.class));
    }

}
