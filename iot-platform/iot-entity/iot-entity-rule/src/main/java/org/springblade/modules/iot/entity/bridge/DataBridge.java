package org.springblade.modules.iot.entity.bridge;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;

import java.io.Serial;

/**
 * <p>
 * 实体类：数据桥接-规则
 * 对应表 rule_data_bridge
 * </p>
 *
 * <h3>v3 三段 JSON 设计</h3>
 * <ul>
 *   <li>{@link #matchConfigJson}：匹配条件 ── 不加密（matcher 热路径要按内容查询，不含凭证）</li>
 *   <li>{@link #actionConfigJson}：动作配置 ── <b>EncryptTypeHandler 加密</b>（HTTP sink headers
 *       可能内联 Bearer token；MySQL columnMapping 可能含敏感 SQL）</li>
 * </ul>
 *
 * <h3>流控 / 重试两层 fallback</h3>
 * 规则字段 NOT NULL → 用规则值；NULL → fallback 到 {@link DataSource#getDefaultQos()} 等数据源默认值。
 * 实现见 {@code BridgeRetryPolicyResolver}。
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
@AutoTable(value = "iot_rule_data_bridge", comment = "DataBridge table")
public class DataBridge extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID。
     */
    @AutoColumn(value = "app_id", comment = "应用ID。")
    private String appId;

    /**
     * 规则名称（列表页展示）。
     */
    @AutoColumn(value = "rule_name", comment = "规则名称（列表页展示）。")
    private String ruleName;

    /**
     * 规则业务唯一编码（snowflake）。
     */
    @AutoColumn(value = "rule_code", comment = "规则业务唯一编码（snowflake）。")
    private String ruleCode;

    /**
     * 桥接方向：10-出站(平台→第三方) / 20-入站(第三方→平台)。
     */
    @AutoColumn(value = "direction", comment = "桥接方向：10-出站(平台→第三方) / 20-入站(第三方→平台)。")
    private String direction;

    /**
     * 关联数据源 FK→rule_data_source.id。
     */
    @AutoColumn(value = "data_source_id", comment = "关联数据源 FK→rule_data_source.id。")
    private Long dataSourceId;

    /**
     * 匹配条件 JSON。
     * 出站含 productIdentifications/actionTypes/topicPatterns/deviceFilter/payloadFilter/timeWindow；
     * 入站含 subscriptionSourceIds/messageFilter。
     * <p>不加密：matcher 热路径要按内容查询。
     */
    @AutoColumn(value = "match_config_json", comment = "匹配条件 JSON。 出站含 productIdentifications/actionTypes/topicPatterns/deviceFilter/payloadFilter/timeWindow； 入站含 subscriptionSourceIds/messageFilter。")
    private String matchConfigJson;

    /**
     * 动作配置 JSON。出站含 payloadTemplate/transformScript/sourceType 特异参数；
     * 入站含 targetHandler/fieldMapping。
     * <p><b>EncryptTypeHandler 加密落盘</b>：防御 HTTP sink 内联 Bearer token / MySQL columnMapping 敏感 SQL。
     */
    @AutoColumn(value = "action_config_json", comment = "动作配置 JSON。出站含 payloadTemplate/transformScript/sourceType 特异参数； 入站含 targetHandler/fieldMapping。")
    private String actionConfigJson;

    /**
     * 规则级可靠性级别覆盖（NULL=用数据源默认）。
     */
    @AutoColumn(value = "qos", comment = "规则级可靠性级别覆盖（NULL=用数据源默认）。")
    private Integer qos;

    /**
     * 规则级 QPS 限流覆盖。
     */
    @AutoColumn(value = "rate_limit_qps", comment = "规则级 QPS 限流覆盖。")
    private Integer rateLimitQps;

    /**
     * 规则级最大重试次数覆盖。
     */
    @AutoColumn(value = "retry_max_times", comment = "规则级最大重试次数覆盖。")
    private Integer retryMaxTimes;

    /**
     * 规则级初始退避时长覆盖（毫秒）。
     */
    @AutoColumn(value = "retry_backoff_ms", comment = "规则级初始退避时长覆盖（毫秒）。")
    private Integer retryBackoffMs;

    /**
     * 规则级单次发送超时覆盖（毫秒）。
     */
    @AutoColumn(value = "timeout_ms", comment = "规则级单次发送超时覆盖（毫秒）。")
    private Integer timeoutMs;

    /**
     * 规则级死信数据源覆盖。
     */
    @AutoColumn(value = "dead_letter_data_source_id", comment = "规则级死信数据源覆盖。")
    private Long deadLetterDataSourceId;

    /**
     * 是否启用：0-禁用 / 1-启用（必须测试发送成功后手动启用）。
     */
    @AutoColumn(value = "enable", comment = "是否启用：0-禁用 / 1-启用（必须测试发送成功后手动启用）。")
    private Boolean enable;

    /**
     * 优先级（数字越小越先匹配）。
     */
    @AutoColumn(value = "priority", comment = "优先级（数字越小越先匹配）。")
    private Integer priority;

    /**
     * 扩展参数（兜底，未来加加密/流量分级/A-B 灰度等 0 改表）。
     */
    @AutoColumn(value = "extend_params", comment = "扩展参数（兜底，未来加加密/流量分级/A-B 灰度等 0 改表）。")
    private String extendParams;

    /**
     * 创建人组织。
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织。")
    private Long createdOrgId;
}
