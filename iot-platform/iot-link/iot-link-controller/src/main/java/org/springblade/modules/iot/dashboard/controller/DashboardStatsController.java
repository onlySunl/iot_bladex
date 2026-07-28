package org.springblade.modules.iot.dashboard.controller;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.dashboard.service.DashboardStatsService;
import org.springblade.modules.iot.dashboard.vo.query.DashboardDetailsQuery;
import org.springblade.modules.iot.dashboard.vo.result.DashboardDetailsResultVO;
import org.springblade.modules.iot.dashboard.vo.result.DashboardSummaryResultVO;
import org.springblade.modules.iot.dashboard.vo.result.DashboardTopologySummaryResultVO;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * -----------------------------------------------------------------------------
 * File Name: DashboardStatsController.java
 * -----------------------------------------------------------------------------
 * Description:
 * 数据统计
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-11-25 14:50
 */

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/dashboardStats")
@Tag(name = "仪表盘数据统计")
public class DashboardStatsController {
    private final DashboardStatsService dashboardStatsService;


    @Operation(summary = "获取仪表盘资产统计概要统计信息", description = "返回仪表板资产统计的总览统计信息")
    @GetMapping("/assetSummary")
    public R<DashboardSummaryResultVO> getDashboardAssetSummary() {
        DataScopeHelper.startDataScope("device");
        DataScopeHelper.startDataScope("product");
        DashboardSummaryResultVO summary = dashboardStatsService.getDashboardAssetSummary();
        return R.success(summary);
    }

    @Operation(summary = "获取设备拓扑数据统计信息", description = "返回最近3天的设备拓扑数据指标")
    @GetMapping("/topologySummary")
    public R<DashboardTopologySummaryResultVO> getTopologySummary() {
        DashboardTopologySummaryResultVO summary = dashboardStatsService.getTopologySummary();
        return R.success(summary);
    }

    @Operation(summary = "获取仪表盘资产统计数据详细信息", description = "返回仪表板资产统计的数据详细信息")
    @PostMapping("/assetStatsDetails")
    public R<DashboardDetailsResultVO> getAssetStatsDetails(@RequestBody DashboardDetailsQuery detailsQuery) {
        DashboardDetailsResultVO detailsData = dashboardStatsService.getAssetStatsDetails(detailsQuery);
        return R.success(detailsData);
    }

}
