package org.springblade.modules.iot.controller.admin.alertconfig;

import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.alert.IAlertConfigService;
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
import org.springblade.modules.iot.controller.admin.alertconfig.vo.*;

import static org.springblade.modules.iot.common.enums.OperateTypeEnum.EXPORT;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;

@Tag(name = "管理后台 - 报警配置")
@RestController
@RequestMapping("/iot/alert-config")
@Validated
public class AlertConfigController extends BladeController {

    @Resource
    private IAlertConfigService alertConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建报警配置")
    public R<Long> createAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO createReqVO) {
        return R.data(alertConfigService.createAlertConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报警配置")
    public R<Boolean> updateAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO updateReqVO) {
        alertConfigService.updateAlertConfig(updateReqVO);
        return R.data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报警配置")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteAlertConfig(@RequestParam("id") Long id) {
        alertConfigService.deleteAlertConfig(id);
        return R.data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报警配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<AlertConfigRespVO> getAlertConfig(@RequestParam("id") Long id) {
        AlertConfig alertConfig = alertConfigService.getAlertConfig(id);
        return R.data(BeanUtils.toBean(alertConfig, AlertConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报警配置分页")
    public R<PageResult<AlertConfigRespVO>> getAlertConfigPage(@Valid AlertConfigPageReqVO pageReqVO) {
        PageResult<AlertConfig> pageResult = alertConfigService.getAlertConfigPage(pageReqVO);
        return R.data(BeanUtils.toBean(pageResult, AlertConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报警配置 Excel")
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
