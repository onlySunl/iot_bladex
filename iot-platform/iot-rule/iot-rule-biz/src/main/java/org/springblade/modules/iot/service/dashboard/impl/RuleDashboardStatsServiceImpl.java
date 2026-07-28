package org.springblade.modules.iot.service.dashboard.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.entity.alarm.RuleAlarmRecord;
import org.springblade.modules.iot.entity.bridge.BridgeExecutionTrace;
import org.springblade.modules.iot.entity.bridge.DataBridge;
import org.springblade.modules.iot.entity.bridge.DataSource;
import org.springblade.modules.iot.entity.bridge.SubscriptionSource;
import org.springblade.modules.iot.enumeration.alarm.AlarmRecordHandledStatusEnum;
import org.springblade.modules.iot.enumeration.bridge.BridgeDirectionEnum;
import org.springblade.modules.iot.service.alarm.RuleAlarmRecordService;
import org.springblade.modules.iot.service.alarm.RuleAlarmService;
import org.springblade.modules.iot.service.bridge.BridgeExecutionTraceService;
import org.springblade.modules.iot.service.bridge.DataBridgeService;
import org.springblade.modules.iot.service.bridge.DataSourceService;
import org.springblade.modules.iot.service.bridge.SubscriptionSourceService;
import org.springblade.modules.iot.service.dashboard.RuleDashboardStatsService;
import org.springblade.modules.iot.service.linkage.RuleInstanceService;
import org.springblade.modules.iot.service.linkage.RuleService;
import org.springblade.modules.iot.service.plugin.PluginInfoService;
import org.springblade.modules.iot.service.script.RuleGroovyScriptService;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmRecordResultVO;
import org.springblade.modules.iot.vo.result.dashboard.RuleBridgeSummaryResultVO;
import org.springblade.modules.iot.vo.result.dashboard.RuleDashboardSummaryResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * -----------------------------------------------------------------------------
 * File Name: RuleDashboardStatsServiceImpl.java
 * -----------------------------------------------------------------------------
 * Description:
 * 仪表盘数据业务层接口实现类
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
 * @date 2024-10-22 17:02
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class RuleDashboardStatsServiceImpl implements RuleDashboardStatsService {

    private final RuleService ruleService;

    private final RuleAlarmService ruleAlarmService;

    private final RuleAlarmRecordService ruleAlarmRecordService;

    private final RuleInstanceService ruleInstanceService;

    private final PluginInfoService pluginInfoService;

    private final RuleGroovyScriptService ruleGroovyScriptService;

    private final DataSourceService dataSourceService;

    private final DataBridgeService dataBridgeService;

    private final SubscriptionSourceService subscriptionSourceService;

    private final BridgeExecutionTraceService bridgeExecutionTraceService;


    /**
     * 获取仪表盘概要统计信息
     *
     * @return {@link RuleDashboardSummaryResultVO} 仪表盘概要统计信息
     */
    @Override
    public RuleDashboardSummaryResultVO getDashboardAssetSummary() {
        RuleDashboardSummaryResultVO dashboardSummary = new RuleDashboardSummaryResultVO();

        // 统计规则总数量
        long totalRulesCount = getTotalRulesCount();
        dashboardSummary.setTotalRulesCount(totalRulesCount);

        // 统计规则实例数量
        long totalRuleInstancesCount = getTotalRuleInstancesCount();
        dashboardSummary.setTotalRuleInstancesCount(totalRuleInstancesCount);

        // 统计规则插件数量
        long totalPluginCount = getTotalPluginCount();
        dashboardSummary.setTotalPluginCount(totalPluginCount);

        // 统计规则脚本数量
        long totalRuleGroovyScriptCount = getTotalRuleGroovyScriptCount();
        dashboardSummary.setTotalRuleGroovyScriptCount(totalRuleGroovyScriptCount);

        // 统计规则告警配置数量
        long totalRuleAlarmsCount = getTotalRuleAlarmsCount();
        dashboardSummary.setTotalRuleAlarmsCount(totalRuleAlarmsCount);

        // 统计告警记录数量
        long totalAlarmRecordsCount = getTotalAlarmRecordsCount();
        dashboardSummary.setTotalAlarmRecordsCount(totalAlarmRecordsCount);

        // 获取最近 30 条告警记录
        List<RuleAlarmRecordResultVO> recentAlarmRecords = getRecentAlarmRecords();
        dashboardSummary.setRuleAlarmRecordResultVOList(recentAlarmRecords);

        return dashboardSummary;
    }


    /**
     * 北向集成 / 数据桥接统计。全部走 selectCount,逐项 try-catch 兜底 0,单项失败不影响整体看板。
     *
     * @return 数据桥接统计概要
     */
    @Override
    public RuleBridgeSummaryResultVO getBridgeSummary() {
        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        return RuleBridgeSummaryResultVO.builder()
                // 数据源
                .dataSourceTotal(safeCount(() -> dataSourceService.selectCount(new QueryWrapper<>())))
                .dataSourceEnabled(safeCount(() -> dataSourceService.selectCount(
                        new LambdaQueryWrapper<DataSource>().eq(DataSource::getEnable, true))))
                .dataSourceHealthy(safeCount(() -> dataSourceService.selectCount(
                        new LambdaQueryWrapper<DataSource>().eq(DataSource::getHealthStatus, "HEALTHY"))))
                .dataSourceAbnormal(safeCount(() -> dataSourceService.selectCount(
                        new LambdaQueryWrapper<DataSource>().in(DataSource::getHealthStatus, "DEGRADED", "DOWN"))))
                // 桥接规则
                .bridgeRuleTotal(safeCount(() -> dataBridgeService.selectCount(new QueryWrapper<>())))
                .bridgeRuleEnabled(safeCount(() -> dataBridgeService.selectCount(
                        new LambdaQueryWrapper<DataBridge>().eq(DataBridge::getEnable, true))))
                .bridgeRuleOutbound(safeCount(() -> dataBridgeService.selectCount(
                        new LambdaQueryWrapper<DataBridge>().eq(DataBridge::getDirection, BridgeDirectionEnum.OUTBOUND.getValue()))))
                .bridgeRuleInbound(safeCount(() -> dataBridgeService.selectCount(
                        new LambdaQueryWrapper<DataBridge>().eq(DataBridge::getDirection, BridgeDirectionEnum.INBOUND.getValue()))))
                // 订阅源
                .subscriptionTotal(safeCount(() -> subscriptionSourceService.selectCount(new QueryWrapper<>())))
                .subscriptionEnabled(safeCount(() -> subscriptionSourceService.selectCount(
                        new LambdaQueryWrapper<SubscriptionSource>().eq(SubscriptionSource::getEnable, true))))
                // 今日执行(按 status 00/01/02/03)
                .todayExecTotal(safeCount(() -> bridgeExecutionTraceService.selectCount(
                        new LambdaQueryWrapper<BridgeExecutionTrace>().ge(BridgeExecutionTrace::getStartTime, todayStart))))
                .todaySuccess(safeCount(() -> bridgeExecutionTraceService.selectCount(
                        new LambdaQueryWrapper<BridgeExecutionTrace>().ge(BridgeExecutionTrace::getStartTime, todayStart)
                                .eq(BridgeExecutionTrace::getStatus, "00"))))
                .todayFailed(safeCount(() -> bridgeExecutionTraceService.selectCount(
                        new LambdaQueryWrapper<BridgeExecutionTrace>().ge(BridgeExecutionTrace::getStartTime, todayStart)
                                .eq(BridgeExecutionTrace::getStatus, "01"))))
                .todayPartial(safeCount(() -> bridgeExecutionTraceService.selectCount(
                        new LambdaQueryWrapper<BridgeExecutionTrace>().ge(BridgeExecutionTrace::getStartTime, todayStart)
                                .eq(BridgeExecutionTrace::getStatus, "02"))))
                .todayDeadLetter(safeCount(() -> bridgeExecutionTraceService.selectCount(
                        new LambdaQueryWrapper<BridgeExecutionTrace>().ge(BridgeExecutionTrace::getStartTime, todayStart)
                                .eq(BridgeExecutionTrace::getStatus, "03"))))
                .build();
    }

    /**
     * 统一 count 兜底:任一统计项失败仅 log + 返 0,不影响整体看板。
     *
     * @param supplier 统计取数逻辑
     * @return 统计值;异常时返 0
     */
    private long safeCount(java.util.function.LongSupplier supplier) {
        try {
            return supplier.getAsLong();
        } catch (Exception e) {
            log.error("[bridgeSummary] count failed: {}", e.getMessage());
            return 0L;
        }
    }

    private long getTotalRulesCount() {
        try {
            return ruleService.selectCount(new QueryWrapper<>());
        } catch (Exception e) {
            log.error("Error fetching total rules count: {}", e.getMessage());
            return 0;
        }
    }

    private long getTotalRuleInstancesCount() {
        try {
            return ruleInstanceService.selectCount(new QueryWrapper<>());
        } catch (Exception e) {
            log.error("Error fetching total rule instances count: {}", e.getMessage());
            return 0;
        }
    }

    private long getTotalPluginCount() {
        try {
            return pluginInfoService.selectCount(new QueryWrapper<>());
        } catch (Exception e) {
            log.error("Error fetching total plugin count: {}", e.getMessage());
            return 0;
        }
    }

    private long getTotalRuleGroovyScriptCount() {
        try {
            return ruleGroovyScriptService.selectCount(new QueryWrapper<>());
        } catch (Exception e) {
            log.error("Error fetching total groovy script count: {}", e.getMessage());
            return 0;
        }
    }

    private long getTotalRuleAlarmsCount() {
        try {
            return ruleAlarmService.selectCount(new QueryWrapper<>());
        } catch (Exception e) {
            log.error("Error fetching total rule alarms count: {}", e.getMessage());
            return 0;
        }
    }

    private long getTotalAlarmRecordsCount() {
        try {
            return ruleAlarmRecordService.selectCount(new QueryWrapper<>());
        } catch (Exception e) {
            log.error("Error fetching total alarm records count: {}", e.getMessage());
            return 0;
        }
    }

    // 获取最近 30 条告警记录
    private List<RuleAlarmRecordResultVO> getRecentAlarmRecords() {
        try {
            // 查询条件
            LambdaQueryWrapper<RuleAlarmRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(RuleAlarmRecord::getHandledStatus, Arrays.asList(AlarmRecordHandledStatusEnum.PENDING.getValue(), AlarmRecordHandledStatusEnum.IN_PROGRESS.getValue()));
            lambdaQueryWrapper.orderByDesc(RuleAlarmRecord::getCreatedTime);
            lambdaQueryWrapper.last("LIMIT 30");

            // 获取告警记录列表
            List<RuleAlarmRecord> alarmRecords = ruleAlarmRecordService.list(lambdaQueryWrapper);

            // 过滤掉 null 值，使用 BeanPlusUtil.toBeanIgnoreError 进行转换
            return alarmRecords.stream().filter(Objects::nonNull)
                    .map(record -> {
                        try {
                            return BeanPlusUtil.toBeanIgnoreError(record, RuleAlarmRecordResultVO.class);
                        } catch (Exception e) {
                            log.error("Error converting RuleAlarmRecord to RuleAlarmRecordResultVO: {}", e.getMessage());
                            return null; // 如果转换失败，返回 null
                        }
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching recent alarm records: {}", e.getMessage());
            return Collections.emptyList();
        }
    }


}
