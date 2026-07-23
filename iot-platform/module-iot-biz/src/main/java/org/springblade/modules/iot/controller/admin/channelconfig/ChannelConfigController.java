package org.springblade.modules.iot.controller.admin.channelconfig;

import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.alert.ChannelConfigService;
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
import org.springblade.modules.iot.controller.admin.channelconfig.vo.*;

import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 通道配置")
@RestController
@RequestMapping("/iot/channel-config")
@Validated
public class ChannelConfigController {

    @Resource
    private ChannelConfigService channelConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建通道配置")
    public R<Long> createChannelConfig(@Valid @RequestBody ChannelConfigSaveReqVO createReqVO) {
        return R.data(channelConfigService.createChannelConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通道配置")
    public R<Boolean> updateChannelConfig(@Valid @RequestBody ChannelConfigSaveReqVO updateReqVO) {
        channelConfigService.updateChannelConfig(updateReqVO);
        return R.data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通道配置")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteChannelConfig(@RequestParam("id") Long id) {
        channelConfigService.deleteChannelConfig(id);
        return R.data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通道配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<ChannelConfigRespVO> getChannelConfig(@RequestParam("id") Long id) {
        ChannelConfig channelConfig = channelConfigService.getChannelConfig(id);
        return R.data(BeanUtils.toBean(channelConfig, ChannelConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通道配置分页")
    public R<PageResult<ChannelConfigRespVO>> getChannelConfigPage(@Valid ChannelConfigPageReqVO pageReqVO) {
        PageResult<ChannelConfig> pageResult = channelConfigService.getChannelConfigPage(pageReqVO);
        return R.data(BeanUtils.toBean(pageResult, ChannelConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通道配置 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChannelConfigExcel(@Valid ChannelConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChannelConfig> list = channelConfigService.getChannelConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通道配置.xls", "数据", ChannelConfigRespVO.class,
                        BeanUtils.toBean(list, ChannelConfigRespVO.class));
    }

}
