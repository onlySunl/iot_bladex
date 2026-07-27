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

/**
 * <p>
 * 实体类：数据桥接-订阅源
 * 对应表 rule_subscription_source
 * </p>
 *
 * <p>本表无加密字段。凭证统一来自关联的 {@link DataSource}（direction=20 入站 / 30 双向）。</p>
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
@AutoTable(value = "iot_rule_subscription_source", comment = "SubscriptionSource table")
public class SubscriptionSource extends CustomBaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID。
     */
    @AutoColumn(value = "app_id", comment = "应用ID。")
    private String appId;

    /**
     * 订阅源名称（用户可读）。
     */
    @AutoColumn(value = "source_name", comment = "订阅源名称（用户可读）。")
    private String sourceName;

    /**
     * 业务唯一编码（snowflake；HTTP 入站 endpoint URL 用此值）。
     */
    @AutoColumn(value = "source_code", comment = "业务唯一编码（snowflake；HTTP 入站 endpoint URL 用此值）。")
    private String sourceCode;

    /**
     * 复用数据源 FK→rule_data_source.id（direction 须为 20-入站 或 30-双向）。
     */
    @AutoColumn(value = "data_source_id", comment = "复用数据源 FK→rule_data_source.id（direction 须为 20-入站 或 30-双向）。")
    private Long dataSourceId;

    /**
     * 入站后处理方式：MQTT_FORWARD / RAW_INSERT / RULE_TRIGGER。
     */
    @AutoColumn(value = "target_handler", comment = "入站后处理方式：MQTT_FORWARD / RAW_INSERT / RULE_TRIGGER。")
    private String targetHandler;

    /**
     * 字段映射 JSON。
     */
    @AutoColumn(value = "mapping_json", comment = "字段映射 JSON。")
    private String mappingJson;

    /**
     * target_handler=MQTT_FORWARD 时的目标产品标识。
     */
    @AutoColumn(value = "target_product_identification", comment = "target_handler=MQTT_FORWARD 时的目标产品标识。")
    private String targetProductIdentification;

    /**
     * 目标 topic 模板（含 ${} 占位符）。
     */
    @AutoColumn(value = "target_topic_template", comment = "目标 topic 模板（含 ${} 占位符）。")
    private String targetTopicTemplate;

    /**
     * 是否启用：0-禁用 / 1-启用。
     */
    @AutoColumn(value = "enable", comment = "是否启用：0-禁用 / 1-启用。")
    private Boolean enable;

    /**
     * 上次消费位点（Kafka offset / MQTT messageId / HTTP 时间戳；重启后接续消费）。
     */
    @AutoColumn(value = "last_consume_offset", comment = "上次消费位点（Kafka offset / MQTT messageId / HTTP 时间戳；重启后接续消费）。")
    private String lastConsumeOffset;

    /**
     * 扩展参数。
     */
    @AutoColumn(value = "extend_params", comment = "扩展参数。")
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
