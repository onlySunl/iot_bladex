package org.springblade.modules.iot.productpublishrecord.vo.result;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

import org.springblade.model.vo.AuditableResultVO;
import org.springblade.modules.iot.productpublishrecord.vo.ddl.PublishDdlItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 产品发布记录返回 VO。
 *
 * @author mqttsnet
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "ProductPublishRecordResultVO", description = "产品发布记录")
public class ProductPublishRecordResultVO extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "产品标识")
    private String productIdentification;

    @Schema(description = "源版本号")
    private String sourceVersion;

    @Schema(description = "目标版本号")
    private String targetVersion;

    @Schema(description = "操作意图(字典 PRODUCT_PUBLISH_RECORD_INTENT)")
    private Integer intent;


    @Schema(description = "DDL 执行明细列表(typed,与后端 ddl_summary JSON 列对齐)")
    private List<PublishDdlItemVO> ddlItems;

    /**
     * 发布策略(字典 PRODUCT_PUBLISH_STRATEGY,0=全量 / 1=灰度 / 2=影子)。本表无此字段,
     * Controller 在 handlerResult 阶段按 (productIdentification, targetVersion) 反查 product_version 富化;
     * 仅发布(intent=0)记录有意义,回滚/历史清理为 null。
     */
    @Schema(description = "发布策略(字典 PRODUCT_PUBLISH_STRATEGY)")
    private Integer publishStrategy;

    /** 灰度配置 JSON(仅 publishStrategy=灰度 时非空),结构同 product_version.canary_config_json。 */
    @Schema(description = "灰度配置 JSON(仅灰度策略非空)")
    private String canaryConfigJson;

    @Schema(description = "策略执行结果快照(发布那一刻冻结;全量/灰度/影子按策略填不同字段)")
    private StrategyResultDTO canaryResult;

    @Schema(description = "失败原因")
    private String failedReason;

    @Schema(description = "重试次数(达上限不再重跑)")
    private Integer retryCount;

    @Schema(description = "最大重试次数(用户可配,上限10)")
    private Integer maxRetryCount;

    @Schema(description = "开始时间")
    private LocalDateTime startedTime;

    @Schema(description = "结束时间")
    private LocalDateTime finishedTime;

}
