
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
package org.springblade.modules.iot.controller.admin.channelconfig;

import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.alert.ChannelConfigService;
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
    @PreAuthorize("@ss.hasPermission('iot:channel-config:create')")
    public R<Long> createChannelConfig(@Valid @RequestBody ChannelConfigSaveReqVO createReqVO) {
        return R.data(channelConfigService.createChannelConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通道配置")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:update')")
    public R<Boolean> updateChannelConfig(@Valid @RequestBody ChannelConfigSaveReqVO updateReqVO) {
        channelConfigService.updateChannelConfig(updateReqVO);
        return R.data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通道配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:channel-config:delete')")
    public R<Boolean> deleteChannelConfig(@RequestParam("id") Long id) {
        channelConfigService.deleteChannelConfig(id);
        return R.data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通道配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:query')")
    public R<ChannelConfigRespVO> getChannelConfig(@RequestParam("id") Long id) {
        ChannelConfig channelConfig = channelConfigService.getChannelConfig(id);
        return R.data(BeanUtils.toBean(channelConfig, ChannelConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通道配置分页")
    @PreAuthorize("@ss.hasPermission('iot:channel-config:query')")
    public R<PageResult<ChannelConfigRespVO>> getChannelConfigPage(@Valid ChannelConfigPageReqVO pageReqVO) {
        PageResult<ChannelConfig> pageResult = channelConfigService.getChannelConfigPage(pageReqVO);
        return R.data(BeanUtils.toBean(pageResult, ChannelConfigRespVO.class));
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
