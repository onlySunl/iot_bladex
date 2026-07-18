

package org.springblade.modules.iot.controller.admin.alertconfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigRespVO;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigSaveReqVO;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.alert.AlertConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springblade.modules.iot.common.entity.CommonResult.success;
import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 报警配置")
@RestController
@RequestMapping("/eiot/alert-config")
@Validated
public class AlertConfigController {

    @Resource
    private AlertConfigService alertConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建报警配置")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:create')")
    public CommonResult<Long> createAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO createReqVO) {
        return success(alertConfigService.createAlertConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报警配置")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:update')")
    public CommonResult<Boolean> updateAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO updateReqVO) {
        alertConfigService.updateAlertConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报警配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:alert-config:delete')")
    public CommonResult<Boolean> deleteAlertConfig(@RequestParam("id") Long id) {
        alertConfigService.deleteAlertConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报警配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:query')")
    public CommonResult<AlertConfigRespVO> getAlertConfig(@RequestParam("id") Long id) {
        AlertConfig alertConfig = alertConfigService.getAlertConfig(id);
        return success(BeanUtils.toBean(alertConfig, AlertConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报警配置分页")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:query')")
    public CommonResult<PageResult<AlertConfigRespVO>> getAlertConfigPage(@Valid AlertConfigPageReqVO pageReqVO) {
        PageResult<AlertConfig> pageResult = alertConfigService.getAlertConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlertConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报警配置 Excel")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlertConfigExcel(@Valid AlertConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlertConfig> list = alertConfigService.getAlertConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "报警配置.xls", "数据", AlertConfigRespVO.class,
                        BeanUtils.toBean(list, AlertConfigRespVO.class));
    }

}
