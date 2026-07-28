package org.springblade.modules.iot.entity.bridge;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import org.springblade.basic.base.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类：桥接执行步骤明细
 * 对应表 rule_bridge_execution_step
 * </p>
 *
 * <p>日志类表，无加密字段。一次执行（trace）按 step_no 升序展开 N 个步骤，
 * 前端"链路回放"详情抽屉按 step_type 渲染不同的子卡片（INGEST / RULE_MATCH / TRANSFORM / SINK_SEND 等）。</p>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_bridge_execution_step", comment = "BridgeExecutionStep table")
public class BridgeExecutionStep extends CustomBaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联 trace（FK→rule_bridge_execution_trace.trace_id）。
     */
    
    @AutoColumn(value = "trace_id", comment = "关联 trace（FK→rule_bridge_execution_trace.trace_id）。")
    private String traceId;

    /**
     * 关联桥接规则 ID（同 traceId 命中多条规则时区分 step 归属）。
     */
    
    @AutoColumn(value = "bridge_rule_id", comment = "关联桥接规则 ID（同 traceId 命中多条规则时区分 step 归属）。")
    private Long bridgeRuleId;

    /**
     * 步骤顺序号（从1起，前端按此排序）。
     */
    
    @AutoColumn(value = "step_no", comment = "步骤顺序号（从1起，前端按此排序）。")
    private Integer stepNo;

    /**
     * 类型枚举:INGEST / RULE_MATCH / RATE_LIMIT / TRANSFORM / SINK_SEND / DEAD_LETTER / INBOUND_FORWARD。
     */
    
    @AutoColumn(value = "step_type", comment = "类型枚举:INGEST / RULE_MATCH / RATE_LIMIT / TRANSFORM / SINK_SEND / DEAD_LETTER / INBOUND_FORWARD。")
    private String stepType;

    /**
     * 步骤可读名称（中文，前端卡片标题用）。
     */
    
    @AutoColumn(value = "step_name", comment = "步骤可读名称（中文，前端卡片标题用）。")
    private String stepName;

    /**
     * 状态:00-成功 / 01-失败 / 02-跳过。
     */
    
    // @AutoColumn(value = "status", comment = "状态:00-成功 / 01-失败 / 02-跳过。")
    // private Integer status; // 与 BaseEntity 冲突，已移除

    /**
     * 本步骤耗时（毫秒）。
     */
    
    @AutoColumn(value = "latency_ms", comment = "本步骤耗时（毫秒）。")
    private Integer latencyMs;

    /**
     * 输入摘要 JSON。
     */
    
    @AutoColumn(value = "input_summary", comment = "输入摘要 JSON。")
    private String inputSummary;

    /**
     * 输出摘要 JSON。
     */
    
    @AutoColumn(value = "output_summary", comment = "输出摘要 JSON。")
    private String outputSummary;

    /**
     * 失败错误（status=01 时填）。
     */
    
    @AutoColumn(value = "error_msg", comment = "失败错误（status=01 时填）。")
    private String errorMsg;

    /**
     * 步骤开始时间（毫秒精度）。
     */
    
    @AutoColumn(value = "started_at", comment = "步骤开始时间（毫秒精度）。")
    private LocalDateTime startedAt;

    /**
     * 扩展参数（步骤特异协议数据 JSON）：
     * SINK_SEND 含 sinkType/partition/messageId；
     * RULE_MATCH 含命中条件细节；
     * RATE_LIMIT 含阈值/当前 QPS；
     * TRANSFORM 含 scriptId/scriptVersion 等。
     */
    
    @AutoColumn(value = "extend_params", comment = "扩展参数（步骤特异协议数据 JSON）： SINK_SEND 含 sinkType/partition/messageId； RULE_MATCH 含命中条件细节； RATE_LIMIT 含阈值/当前 QPS； TRANSFORM 含 scriptId/scriptVersion 等。")
    private String extendParams;

    }
