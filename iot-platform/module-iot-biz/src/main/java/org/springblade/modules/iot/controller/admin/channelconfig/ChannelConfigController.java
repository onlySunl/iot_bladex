

package org.springblade.modules.iot.controller.admin.channelconfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigPageReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigRespVO;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.alert.ChannelConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springblade.modules.iot.common.entity.CommonResult.success;
import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 通道配置")
@RestController
@RequestMapping("/eiot/channel-config")
@Validated
public class ChannelConfigController {

    @Resource
    private ChannelConfigService channelConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建通道配置")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:create')")
    public CommonResult<Long> createChannelConfig(@Valid @RequestBody ChannelConfig createReqVO) {
        return success(channelConfigService.createChannelConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通道配置")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:update')")
    public CommonResult<Boolean> updateChannelConfig(@Valid @RequestBody ChannelConfig updateReqVO) {
        channelConfigService.updateChannelConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通道配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:channel-config:delete')")
    public CommonResult<Boolean> deleteChannelConfig(@RequestParam("id") Long id) {
        channelConfigService.deleteChannelConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通道配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:query')")
    public CommonResult<ChannelConfigRespVO> getChannelConfig(@RequestParam("id") Long id) {
        ChannelConfig channelConfig = channelConfigService.getChannelConfig(id);
        return success(BeanUtils.toBean(channelConfig, ChannelConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通道配置分页")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:query')")
    public CommonResult<PageResult<ChannelConfigRespVO>> getChannelConfigPage(@Valid ChannelConfigPageReqVO pageReqVO) {
        PageResult<ChannelConfig> pageResult = channelConfigService.getChannelConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChannelConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通道配置 Excel")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:export')")
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
