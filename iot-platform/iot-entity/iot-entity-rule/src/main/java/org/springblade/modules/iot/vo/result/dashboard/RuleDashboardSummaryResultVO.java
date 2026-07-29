package org.springblade.modules.iot.vo.result.dashboard;


import cn.hutool.core.map.MapUtil;
import org.springblade.basic.interfaces.echo.EchoVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmRecordResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springblade.basic.base.entity.Entity;

/**
 * -----------------------------------------------------------------------------
 * File Name: RuleDashboardSummaryResultVO.java
 * -----------------------------------------------------------------------------
 * Description:
 * 仪表板概要统计VO
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
 * @date 2024-10-22 16:58
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "RuleDashboardSummaryResultVO", description = "仪表板概要统计VO")
public class RuleDashboardSummaryResultVO extends Entity implements Serializable, EchoVO {
    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "规则总数量(场景联动规则)")
    private Long totalRulesCount;

    @Schema(description = "规则实例总数量(链式编程规则实例)")
    private Long totalRuleInstancesCount;

    @Schema(description = "插件总数量")
    private Long totalPluginCount;

    @Schema(description = "规则脚本总数量")
    private Long totalRuleGroovyScriptCount;

    @Schema(description = "规则告警配置总数量(告警规则)")
    private Long totalRuleAlarmsCount;

    @Schema(description = "告警记录总数量(告警记录)")
    private Long totalAlarmRecordsCount;

    @Schema(description = "告警记录数据(TOP30)")
    private List<RuleAlarmRecordResultVO> ruleAlarmRecordResultVOList;

    @Override
    public Map<String, Object> getEchoMap() {
        return echoMap;
    }}
