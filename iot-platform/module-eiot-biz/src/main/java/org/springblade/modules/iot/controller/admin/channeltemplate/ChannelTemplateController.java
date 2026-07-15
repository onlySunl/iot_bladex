
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
package org.springblade.modules.iot.controller.admin.channeltemplate;

import org.springblade.modules.iot.service.alert.ChannelTemplateService;
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

import org.springblade.modules.iot.framework.common.pojo.PageParam;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.common.pojo.CommonResult;
import org.springblade.modules.iot.framework.common.util.object.BeanUtils;
import static org.springblade.modules.iot.framework.common.pojo.CommonResult.success;

import org.springblade.modules.iot.framework.excel.core.util.ExcelUtils;

import org.springblade.modules.iot.framework.apilog.core.annotation.ApiAccessLog;
import static org.springblade.modules.iot.framework.apilog.core.enums.OperateTypeEnum.*;

import org.springblade.modules.iot.controller.admin.channeltemplate.vo.*;

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
    @PreAuthorize("@ss.hasPermission('iot:channel-template:query')")
    public CommonResult<PageResult<ChannelTemplateRespVO>> getChannelTemplatePage(@Valid ChannelTemplatePageReqVO pageReqVO) {
        PageResult<ChannelTemplate> pageResult = channelTemplateService.getChannelTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChannelTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通道模板 Excel")
    @PreAuthorize("@ss.hasPermission('iot:channel-template:export')")
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
