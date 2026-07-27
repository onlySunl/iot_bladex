package org.springblade.modules.iot.entity.bridge;
import org.springblade.common.entity.CustomBaseEntity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

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
 * 实体类：桥接执行 trace 主表（链路回放用）
 * 对应表 rule_bridge_execution_trace
 * </p>
 *
 * <p>日志类表，无加密字段。trace_id 全局唯一，与设备 publish 日志串联。
 * 写入由 {@code BridgeTraceEventListener} 异步事件驱动（{@code @Async("ruleBridgeLogExecutor")}），
 * 主链路 0 DB I/O 阻塞。</p>
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
@AutoTable(value = "iot_rule_bridge_execution_trace", comment = "BridgeExecutionTrace table")
public class BridgeExecutionTrace extends CustomBaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 全链路追踪ID（贯穿 mqs → RocketMQ → rule）。
     */
    @AutoColumn(value = "trace_id", comment = "全链路追踪ID（贯穿 mqs → RocketMQ → rule）。")
    private String traceId;

    /**
     * 关联桥接规则 ID（出站必填；入站为订阅源拉取时为空）。
     * 数据库通过生成列 bridge_rule_id_key 归一空值，生成列不映射到实体。
     */
    @AutoColumn(value = "bridge_rule_id", comment = "关联桥接规则 ID（出站必填；入站为订阅源拉取时为空）。 数据库通过生成列 bridge_rule_id_key 归一空值，生成列不映射到实体。")
    private Long bridgeRuleId;

    /**
     * 桥接方向：10-出站 / 20-入站。
     */
    @AutoColumn(value = "direction", comment = "桥接方向：10-出站 / 20-入站。")
    private String direction;

    /**
     * 触发来源：DEVICE_DATA / SUBSCRIPTION / TEST_SINK / REPLAY。
     */
    @AutoColumn(value = "trigger_source", comment = "触发来源：DEVICE_DATA / SUBSCRIPTION / TEST_SINK / REPLAY。")
    private String triggerSource;

    /**
     * 产品标识（出站时来自设备事件）。
     */
    @AutoColumn(value = "product_identification", comment = "产品标识（出站时来自设备事件）。")
    private String productIdentification;

    /**
     * 设备标识（出站时来自设备事件）。
     */
    @AutoColumn(value = "device_identification", comment = "设备标识（出站时来自设备事件）。")
    private String deviceIdentification;

    /**
     * 事件类型（PUBLISH/CONNECT/CLOSE/...）。
     */
    @AutoColumn(value = "action_type", comment = "事件类型（PUBLISH/CONNECT/CLOSE/...）。")
    private String actionType;

    /**
     * 设备事件 topic。
     */
    @AutoColumn(value = "topic", comment = "设备事件 topic。")
    private String topic;

    /**
     * 关联数据源 ID（出站=目标 sink；入站=来源 source）。
     */
    @AutoColumn(value = "data_source_id", comment = "关联数据源 ID（出站=目标 sink；入站=来源 source）。")
    private Long dataSourceId;

    /**
     * 关联订阅源 ID（仅入站）。
     */
    @AutoColumn(value = "subscription_source_id", comment = "关联订阅源 ID（仅入站）。")
    private Long subscriptionSourceId;

    /**
     * 整体状态:00-成功 / 01-失败 / 02-部分成功 / 03-死信。
     */
    @AutoColumn(value = "trace_status", comment = "整体状态:00-成功 / 01-失败 / 02-部分成功 / 03-死信。")
    private String traceStatus;

    /**
     * 执行的步骤总数。
     */
    @AutoColumn(value = "step_count", comment = "执行的步骤总数。")
    private Integer stepCount;

    /**
     * 总耗时毫秒（开始到结束）。
     */
    @AutoColumn(value = "total_latency_ms", comment = "总耗时毫秒（开始到结束）。")
    private Integer totalLatencyMs;

    /**
     * 执行开始时间（毫秒精度）。
     */
    @AutoColumn(value = "start_time", comment = "执行开始时间（毫秒精度）。")
    private LocalDateTime startTime;

    /**
     * 执行结束时间（毫秒精度）。
     */
    @AutoColumn(value = "end_time", comment = "执行结束时间（毫秒精度）。")
    private LocalDateTime endTime;

    /**
     * 源消息摘要（envelope 前 1KB；便于排查 + 死信回放）。
     */
    @AutoColumn(value = "source_payload_summary", comment = "源消息摘要（envelope 前 1KB；便于排查 + 死信回放）。")
    private String sourcePayloadSummary;

    /**
     * 结果摘要（成功的 sink / 失败原因等一句话）。
     */
    @AutoColumn(value = "result_summary", comment = "结果摘要（成功的 sink / 失败原因等一句话）。")
    private String resultSummary;

    /**
     * 失败时的错误信息。
     */
    @AutoColumn(value = "error_msg", comment = "失败时的错误信息。")
    private String errorMsg;

    /**
     * 扩展参数。
     */
    @AutoColumn(value = "extend_params", comment = "扩展参数。")
    private String extendParams;

    /**
     * 创建人组织。
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织。")
    private Long createdOrgId;
}
