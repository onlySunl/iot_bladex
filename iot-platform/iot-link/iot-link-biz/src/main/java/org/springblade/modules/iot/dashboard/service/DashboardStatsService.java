package org.springblade.modules.iot.dashboard.service;

import org.springblade.modules.iot.dashboard.vo.query.DashboardDetailsQuery;
import org.springblade.modules.iot.dashboard.vo.result.DashboardDetailsResultVO;
import org.springblade.modules.iot.dashboard.vo.result.DashboardSummaryResultVO;
import org.springblade.modules.iot.dashboard.vo.result.DashboardTopologySummaryResultVO;

/**
 * -----------------------------------------------------------------------------
 * File Name: DashboardStatsService.java
 * -----------------------------------------------------------------------------
 * Description:
 * 仪表盘数据业务层接口
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
 * @date 2023-11-25 17:01
 */
public interface DashboardStatsService {

    /**
     * 获取仪表盘资产统计概要统计信息
     *
     * @return {@link DashboardSummaryResultVO} 仪表盘资产概要统计信息
     */
    DashboardSummaryResultVO getDashboardAssetSummary();

    /**
     * 获取设备拓扑数据统计信息
     * @return {@link DashboardTopologySummaryResultVO} 设备拓扑数据统计信息
     */
    DashboardTopologySummaryResultVO getTopologySummary();

    /**
     * 获取仪表盘资产统计数据详细信息
     *
     * @param detailsQuery 查询条件
     * @return {@link DashboardDetailsResultVO} 仪表盘资产统计数据详细信息
     */
    DashboardDetailsResultVO getAssetStatsDetails(DashboardDetailsQuery detailsQuery);


}
