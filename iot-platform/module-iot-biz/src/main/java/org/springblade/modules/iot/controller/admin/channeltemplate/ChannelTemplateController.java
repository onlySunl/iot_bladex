package org.springblade.modules.iot.controller.admin.channeltemplate;

import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.alert.ChannelTemplateService;
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

import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.*;

import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 通道模板")
@RestController
@RequestMapping("/iot/channel-template")
@Validated
public class ChannelTemplateController {

    @Resource
    private ChannelTemplateService channelTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建通道模板")
    public R<Long> createChannelTemplate(@Valid @RequestBody ChannelTemplateSaveReqVO createReqVO) {
        return R.data(channelTemplateService.createChannelTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通道模板")
    public R<Boolean> updateChannelTemplate(@Valid @RequestBody ChannelTemplateSaveReqVO updateReqVO) {
        channelTemplateService.updateChannelTemplate(updateReqVO);
        return R.data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通道模板")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteChannelTemplate(@RequestParam("id") Long id) {
        channelTemplateService.deleteById(id);
        return R.data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通道模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<ChannelTemplateRespVO> getChannelTemplate(@RequestParam("id") Long id) {
        ChannelTemplate channelTemplate = channelTemplateService.getChannelTemplate(id);
        return R.data(BeanUtils.toBean(channelTemplate, ChannelTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通道模板分页")
    public R<PageResult<ChannelTemplateRespVO>> getChannelTemplatePage(@Valid ChannelTemplatePageReqVO pageReqVO) {
        PageResult<ChannelTemplate> pageResult = channelTemplateService.getChannelTemplatePage(pageReqVO);
        return R.data(BeanUtils.toBean(pageResult, ChannelTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通道模板 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChannelTemplateExcel(@Valid ChannelTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChannelTemplate> list = channelTemplateService.getChannelTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通道模板.xls", "数据", ChannelTemplateRespVO.class,
                        BeanUtils.toBean(list, ChannelTemplateRespVO.class));
    }

}
