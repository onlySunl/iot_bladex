
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.controller.admin.alertconfig;

import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.alert.AlertConfigService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
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

@Tag(name = "管理后台 - 报警配置")
@RestController
@RequestMapping("/iot/alert-config")
@Validated
public class AlertConfigController {

    @Resource
    private AlertConfigService alertConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建报警配置")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:create')")
    public R<Long> createAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO createReqVO) {
        return R.data(alertConfigService.createAlertConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报警配置")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:update')")
    public R<Boolean> updateAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO updateReqVO) {
        alertConfigService.updateAlertConfig(updateReqVO);
        return R.data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报警配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:alert-config:delete')")
    public R<Boolean> deleteAlertConfig(@RequestParam("id") Long id) {
        alertConfigService.deleteAlertConfig(id);
        return R.data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报警配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:query')")
    public R<AlertConfigRespVO> getAlertConfig(@RequestParam("id") Long id) {
        AlertConfig alertConfig = alertConfigService.getAlertConfig(id);
        return R.data(BeanUtils.toBean(alertConfig, AlertConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报警配置分页")
    @PreAuthorize("@ss.hasPermission('iot:alert-config:query')")
    public R<PageResult<AlertConfigRespVO>> getAlertConfigPage(@Valid AlertConfigPageReqVO pageReqVO) {
        PageResult<AlertConfig> pageResult = alertConfigService.getAlertConfigPage(pageReqVO);
        return R.data(BeanUtils.toBean(pageResult, AlertConfigRespVO.class));
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
