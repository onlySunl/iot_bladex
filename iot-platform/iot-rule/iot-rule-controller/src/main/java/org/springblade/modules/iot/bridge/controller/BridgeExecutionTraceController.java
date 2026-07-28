package org.springblade.modules.iot.bridge.controller;

import com.mqttsnet.basic.annotation.log.WebLog;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.service.bridge.BridgeExecutionTraceService;
import org.springblade.modules.iot.vo.query.bridge.BridgeExecutionTracePageQuery;
import org.springblade.modules.iot.vo.result.bridge.BridgeExecutionTraceResultVO;
import org.springblade.modules.iot.vo.result.bridge.BridgeExecutionTraceStatsResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 桥接执行 trace 控制器(链路回放)。
 * 仅查询 + 死信重放;trace 写入由 BridgeTraceEventListener 异步事件驱动。
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/bridgeExecutionTrace")
@Tag(name = "桥接执行 trace(链路回放)")
public class BridgeExecutionTraceController extends BladeController {

    private final EchoService echoService;


    public EchoService getEchoService() {
        return echoService;
    }

    private final BridgeExecutionTraceService superService;


    @Operation(summary = "trace 统计", description = "按查询条件聚合桥接执行日志分布、趋势和触发量 Top 规则")
    @PostMapping("/stats")
    public R<BridgeExecutionTraceStatsResultVO> stats(@RequestBody PageParams<BridgeExecutionTracePageQuery> params) {
        try {
            BridgeExecutionTracePageQuery query = params == null ? null : params.getModel();
            return R.data(superService.getTraceStats(query));
        } catch (Exception e) {
            log.error("查询 trace 统计失败: {}", e.getMessage(), e);
            return R.fail("查询 trace 统计失败");
        }
    }

    @Operation(summary = "trace 详情(含步骤)", description = "返回 trace 元信息 + 按 step_no 升序的步骤列表;同 traceId 命中多规则时建议传 ruleId 精确定位")
    @GetMapping("/detail/{traceId}")
    @Parameters({
            @Parameter(name = "traceId", description = "全链路追踪 ID", required = true),
            @Parameter(name = "ruleId", description = "桥接规则 ID(可选,多规则场景精确定位)")
    })
    public R<BridgeExecutionTraceResultVO> getDetail(
            @PathVariable("traceId") String traceId,
            @RequestParam(value = "ruleId", required = false) Long ruleId) {
        try {
            BridgeExecutionTraceResultVO result = superService.getTraceDetail(traceId, ruleId);
            echoService.action(result);
            return R.data(result);
        }  catch (Exception e) {
            log.error("查询 trace 详情失败 traceId={} ruleId={}: {}", traceId, ruleId, e.getMessage(), e);
            return R.fail("查询 trace 详情失败");
        }
    }

    @Operation(summary = "死信重放", description = "从 trace 取 envelope + 规则,重走 SinkDispatcher 完整链路")
    @PostMapping("/replay/{traceId}")
    @WebLog(value = "死信重放", request = false)
    @Parameters({@Parameter(name = "traceId", description = "原 trace ID", required = true)})
    public R<String> replay(@PathVariable("traceId") String traceId) {
        try {
            return R.success(superService.replay(traceId));
        }  catch (Exception e) {
            log.error("死信重放失败 traceId={}: {}", traceId, e.getMessage(), e);
            return R.fail("死信重放失败");
        }
    }
}
