package org.springblade.modules.iot.linkage.controller;

import com.mqttsnet.basic.base.controller.SuperController;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.entity.linkage.RuleExecutionLog;
import org.springblade.modules.iot.service.linkage.RuleExecutionLogService;
import org.springblade.modules.iot.vo.query.linkage.RuleExecutionLogPageQuery;
import org.springblade.modules.iot.vo.result.linkage.RuleExecutionLogDetailsResultVO;
import org.springblade.modules.iot.vo.result.linkage.RuleExecutionLogResultVO;
import org.springblade.modules.iot.vo.result.linkage.RuleExecutionLogStatsResultVO;
import org.springblade.modules.iot.vo.save.linkage.RuleExecutionLogSaveVO;
import org.springblade.modules.iot.vo.update.linkage.RuleExecutionLogUpdateVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * 规则执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:41:26
 * @create [2024-12-02 18:41:26] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/ruleExecutionLog")
@Tag(name = "规则执行日志")
    public class RuleExecutionLogController extends BladeController {
    private final EchoService echoService;
    private final RuleExecutionLogService superService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<RuleExecutionLog> handlerWrapper(RuleExecutionLog model,
                                                      PageParams<RuleExecutionLogPageQuery> params) {
        QueryWrap<RuleExecutionLog> queryWrap = super.handlerWrapper(model, params);
        RuleExecutionLogPageQuery query = params == null ? null : params.getModel();
        if (query != null) {
            queryWrap.lambda()
                    .ge(query.getStartTimeBegin() != null, RuleExecutionLog::getStartTime, query.getStartTimeBegin())
                    .le(query.getStartTimeEnd() != null, RuleExecutionLog::getStartTime, query.getStartTimeEnd())
                    .orderByDesc(RuleExecutionLog::getStartTime);
        }
        DataScopeHelper.startDataScope("rule_execution_log");
        return queryWrap;
    }


    /**
     * 获取规则执行日志详情
     *
     * @param id 规则执行日志ID
     * @return 规则执行日志详情
     */
    @Operation(summary = "获取规则执行日志详情", description = "Fetches the detailed information of a rule execution log by its ID")
    @Parameters({@Parameter(name = "id", description = "规则执行日志ID", required = true)})
    @GetMapping("/getRuleExecutionLogDetails/{id}")
    public R<RuleExecutionLogDetailsResultVO> getRuleExecutionLogDetails(@PathVariable("id") Long id) {
        log.info("getRuleExecutionLogDetails id: {}", id);
        RuleExecutionLogDetailsResultVO result = superService.getRuleExecutionLogDetails(id);
        echoService.action(result);
        return R.data(result);
    }

    /**
     * 获取规则执行日志统计。
     *
     * @param params 查询条件
     * @return 统计信息
     */
    @Operation(summary = "获取规则执行日志统计", description = "Fetches summary metrics of rule execution logs by query conditions")
    @PostMapping("/stats")
    public R<RuleExecutionLogStatsResultVO> stats(@RequestBody PageParams<RuleExecutionLogPageQuery> params) {
        RuleExecutionLogPageQuery query = params == null ? null : params.getModel();
        return R.data(superService.getRuleExecutionLogStats(query));
    }

    /**
     * 清理规则执行日志。
     *
     * @param params 查询条件
     * @return 清理的主日志条数
     */
    @Operation(summary = "清理规则执行日志", description = "Clears rule execution logs by query conditions")
    @PostMapping("/clear")
    public R<Long> clear(@RequestBody PageParams<RuleExecutionLogPageQuery> params) {
        RuleExecutionLogPageQuery query = params == null ? null : params.getModel();
        return R.data(superService.clearRuleExecutionLogs(query));
    }

}
