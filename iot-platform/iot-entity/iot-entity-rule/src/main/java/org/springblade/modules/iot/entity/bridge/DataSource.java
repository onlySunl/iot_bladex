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

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类：数据桥接-数据源（出/入站共用）
 * 对应表 rule_data_source
 * </p>
 *
 * <h3>加密字段</h3>
 * <ul>
 *   <li>{@link #connectionJson} 走 {@link EncryptTypeHandler} 整体加密落盘
 *       （JDBC URL 可能 ?password=、Redis URI 可能 redis://:pwd@host、HTTP URL 可能含 token）</li>
 *   <li>{@link #credentialJson} 同上（含 password / saslPassword / accessKey / secretKey 等核心机密）</li>
 * </ul>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_rule_data_source", comment = "DataSource table")
public class DataSource extends CustomBaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID。
     */
    @AutoColumn(value = "app_id", comment = "应用ID。")
    private String appId;

    /**
     * 数据源名称（用户起的友好标识，列表页显示）。
     */
    @AutoColumn(value = "data_source_name", comment = "数据源名称（用户起的友好标识，列表页显示）。")
    private String dataSourceName;

    /**
     * 业务唯一编码（snowflake，外部系统引用）。
     */
    @AutoColumn(value = "data_source_code", comment = "业务唯一编码（snowflake，外部系统引用）。")
    private String dataSourceCode;

    /**
     * 方向：10-出站sink / 20-入站source / 30-双向。
     */
    @AutoColumn(value = "direction", comment = "方向：10-出站sink / 20-入站source / 30-双向。")
    private String direction;

    /**
     * 协议类型：KAFKA/REDIS/ROCKETMQ/RABBITMQ/MYSQL/HTTP/WEBHOOK/MQTT。
     */
    @AutoColumn(value = "source_type", comment = "协议类型：KAFKA/REDIS/ROCKETMQ/RABBITMQ/MYSQL/HTTP/WEBHOOK/MQTT。")
    private String sourceType;

    /**
     * 连接参数 JSON（host/port/topic/database/mode 等）。
     * <b>整体 EncryptTypeHandler 加密落盘</b>，防御 JDBC URL / Redis URI 等可能内嵌的密码 / token。
     */
    @AutoColumn(value = "connection_json", comment = "连接参数 JSON（host/port/topic/database/mode 等）。")
    private String connectionJson;

    /**
     * 凭证 JSON（password / saslPassword / accessKey / secretKey / bearerToken / HMAC secretKey 等）。
     * <b>整体 EncryptTypeHandler 加密落盘</b>，与 device.password 同规约。
     */
    @AutoColumn(value = "credential_json", comment = "凭证 JSON（password / saslPassword / accessKey / secretKey / bearerToken / HMAC secretKey 等）。")
    private String credentialJson;

    /**
     * 序列化策略：JSON/AVRO/STRING/BINARY（与 Serializer.name() 1:1 对齐）。
     */
    @AutoColumn(value = "serialization", comment = "序列化策略：JSON/AVRO/STRING/BINARY（与 Serializer.name() 1:1 对齐）。")
    private String serialization;

    /**
     * 默认可靠性级别：0-fire-forget / 1-at-least-once / 2-exactly-once（规则可覆盖）。
     */
    @AutoColumn(value = "default_qos", comment = "默认可靠性级别：0-fire-forget / 1-at-least-once / 2-exactly-once（规则可覆盖）。")
    private Integer defaultQos;

    /**
     * 默认 QPS 限流（0=不限）。
     */
    @AutoColumn(value = "default_rate_limit_qps", comment = "默认 QPS 限流（0=不限）。")
    private Integer defaultRateLimitQps;

    /**
     * 默认最大重试次数。
     */
    @AutoColumn(value = "default_retry_max_times", comment = "默认最大重试次数。")
    private Integer defaultRetryMaxTimes;

    /**
     * 默认初始退避时长 ms（指数倍增 1s/2s/4s/...）。
     */
    @AutoColumn(value = "default_retry_backoff_ms", comment = "默认初始退避时长 ms（指数倍增 1s/2s/4s/...）。")
    private Integer defaultRetryBackoffMs;

    /**
     * 默认单次发送超时 ms。
     */
    @AutoColumn(value = "default_timeout_ms", comment = "默认单次发送超时 ms。")
    private Integer defaultTimeoutMs;

    /**
     * 默认死信投递的数据源 FK。
     */
    @AutoColumn(value = "default_dead_letter_data_source_id", comment = "默认死信投递的数据源 FK。")
    private Long defaultDeadLetterDataSourceId;

    /**
     * 是否启用：0-禁用 / 1-启用（必须测试连接成功后手动启用）。
     */
    @AutoColumn(value = "enable", comment = "是否启用：0-禁用 / 1-启用（必须测试连接成功后手动启用）。")
    private Boolean enable;

    /**
     * 健康状态：HEALTHY/DEGRADED/DOWN/UNKNOWN。
     */
    @AutoColumn(value = "health_status", comment = "健康状态：HEALTHY/DEGRADED/DOWN/UNKNOWN。")
    private String healthStatus;

    /**
     * 上次健康检查时间。
     */
    @AutoColumn(value = "last_health_check_time", comment = "上次健康检查时间。")
    private LocalDateTime lastHealthCheckTime;

    /**
     * 扩展参数（协议特异调参 JSON）。
     */
    @AutoColumn(value = "extend_params", comment = "扩展参数（协议特异调参 JSON）。")
    private String extendParams;

    /**
     * 备注。
     */

    /**
     * 创建人组织。
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织。")
    private Long createdOrgId;
}
