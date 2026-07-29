package org.springblade.modules.iot.productpublishrecord.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import org.springblade.modules.iot.productpublishrecord.vo.ddl.PublishDdlItemVO;
import org.springblade.modules.iot.productpublishrecord.vo.result.StrategyResultDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 产品发布记录实体,记录发布 / 回滚 / 历史清理操作的执行轨迹。
 *
 * @author mqttsnet
 * @see com.mqttsnet.thinglinks.productpublishrecord.enumeration.ProductPublishRecordIntentEnum
 * @see com.mqttsnet.thinglinks.productpublishrecord.enumeration.ProductPublishRecordStatusEnum
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_product_publish_record", comment = "ProductPublishRecord table")
public class ProductPublishRecord extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 产品标识。 */
    @AutoColumn(value = "product_identification", comment = "产品标识。")
    private String productIdentification;

    /** 源版本号(发布时为上一版,历史清理时同 target,首次发布为 null)。 */
    @AutoColumn(value = "source_version", comment = "源版本号(发布时为上一版,历史清理时同 target,首次发布为 null)。")
    private String sourceVersion;

    /** 目标版本号(发布:新版本;回滚:回滚目标;历史清理:被清理版本)。 */
    @AutoColumn(value = "target_version", comment = "目标版本号(发布:新版本;回滚:回滚目标;历史清理:被清理版本)。")
    private String targetVersion;

    /** 操作意图(0-发布,1-回滚,2-历史清理)。 */
    @AutoColumn(value = "intent", comment = "操作意图(0-发布,1-回滚,2-历史清理)。")
    private Integer intent;

    /** 执行状态(0-执行中,1-成功,2-失败)。 */

    /**
     * DDL 执行明细列表 ── 每条对应一个 service 的 CREATE_STABLE / DROP_STABLE 执行结果。
     * DB 列仍是 ddl_summary TEXT(JSON 字符串),由 {@link JacksonTypeHandler} 自动序列化为 List。
     * 表必须 {@code autoResultMap = true}(已开启)typeHandler 才生效。
     */
    @AutoColumn(value = "ddl_summary", comment = "DDL 执行明细列表 ── 每条对应一个 service 的 CREATE_STABLE / DROP_STABLE 执行结果。 DB 列仍是 ddl_summary TEXT(JSON 字符串),由 {@link JacksonTypeHandler} 自动序列化为 List。 表必须 {@code autoResultMap = true}(已开启)typeHandler 才生效。")
    private List<PublishDdlItemVO> ddlItems;

    /** 策略执行结果快照(发布那一刻冻结)── DB 列 canary_result_json(JSON),由 {@link JacksonTypeHandler} 自动序列化为 typed。 */
    @AutoColumn(value = "canary_result_json", comment = "策略执行结果快照(发布那一刻冻结)── DB 列 canary_result_json(JSON),由 {@link JacksonTypeHandler} 自动序列化为 typed。")
    private StrategyResultDTO canaryResult;

    /** 失败原因(成功时为 null)。 */
    @AutoColumn(value = "failed_reason", comment = "失败原因(成功时为 null)。")
    private String failedReason;

    /** 重试次数 ── 达 {@link #maxRetryCount} 不再实际重跑,保持 FAILED 待扫描窗口老化。 */
    @AutoColumn(value = "retry_count", comment = "重试次数 ── 达 {@link #maxRetryCount} 不再实际重跑,保持 FAILED 待扫描窗口老化。")
    private Integer retryCount;

    /** 最大重试次数(发布时用户可配,默认 3、上限 10);达此值不再重跑。 */
    @AutoColumn(value = "max_retry_count", comment = "最大重试次数(发布时用户可配,默认 3、上限 10);达此值不再重跑。")
    private Integer maxRetryCount;

    /** 开始时间。 */
    @AutoColumn(value = "started_time", comment = "开始时间。")
    private LocalDateTime startedTime;

    /** 结束时间。 */
    @AutoColumn(value = "finished_time", comment = "结束时间。")
    private LocalDateTime finishedTime;

    /** 备注。 */

    /** 创建人组织。 */
    @AutoColumn(value = "created_org_id", comment = "创建人组织。")
    private Long createdOrgId;

    /** 逻辑删除标识(0-未删除、1-已删除)。 */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识(0-未删除、1-已删除)。")
    private Integer deleted;
}
