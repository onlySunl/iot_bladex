package org.springblade.modules.iot.vo.save.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单保存方法VO
 * 规则条件执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:53:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleConditionExecutionLogSaveVO", description = "规则条件执行日志表")
public class RuleConditionExecutionLogSaveVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 规则执行日志ID
     */
    @Schema(description = "规则执行日志ID")
    @NotNull(message = "请填写规则执行日志ID")
    private Long ruleExecutionId;
    /**
     * 条件唯一标识
     */
    @Schema(description = "条件唯一标识")
    @NotEmpty(message = "请填写条件唯一标识")
    @Size(max = 100, message = "条件唯一标识长度不能超过{max}")
    private String conditionUuid;
    /**
     * 条件类型：0-设备属性触发，1-定时触发，2-设备动作触发等
     */
    @Schema(description = "条件类型：0-设备属性触发，1-定时触发，2-设备动作触发等")
    @NotNull(message = "请填写条件类型：0-设备属性触发，1-定时触发，2-设备动作触发等")
    private Integer conditionType;
    /**
     * 条件是否成立
     */
    @Schema(description = "条件是否成立")
    @NotNull(message = "请填写条件是否成立")
    private Boolean evaluationResult;
    /**
     * 条件评估开始时间
     */
    @Schema(description = "条件评估开始时间")
    @NotNull(message = "请填写条件评估开始时间")
    private LocalDateTime startTime;
    /**
     * 条件评估结束时间
     */
    @Schema(description = "条件评估结束时间")
    private LocalDateTime endTime;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Size(max = 255, message = "描述长度不能超过{max}")
    private String remark;
    /**
     * 扩展参数（文本格式）
     */
    @Schema(description = "扩展参数（文本格式）")
    @Size(max = 65535, message = "扩展参数（文本格式）长度不能超过{max}")
    private String extendParams;

    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
