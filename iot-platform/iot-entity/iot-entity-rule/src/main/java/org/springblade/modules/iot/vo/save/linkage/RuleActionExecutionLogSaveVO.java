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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单保存方法VO
 * 规则动作执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:54:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleActionExecutionLogSaveVO", description = "规则动作执行日志表")
public class RuleActionExecutionLogSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 规则执行日志ID（外键）
     */
    @Schema(description = "规则执行日志ID（外键）")
    @NotNull(message = "请填写规则执行日志ID（外键）")
    private Long ruleExecutionId;
    /**
     * 动作类型：0-命令下发，1-触发告警，2-数据转发
     */
    @Schema(description = "动作类型：0-命令下发，1-触发告警，2-数据转发")
    @NotNull(message = "请填写动作类型：0-命令下发，1-触发告警，2-数据转发")
    private Integer actionType;
    /**
     * 动作内容
     */
    @Schema(description = "动作内容")
    @NotEmpty(message = "请填写动作内容")
    @Size(max = 65535, message = "动作内容长度不能超过{max}")
    private String actionContent;
    /**
     * 动作是否执行成功
     */
    @Schema(description = "动作是否执行成功")
    @NotNull(message = "请填写动作是否执行成功")
    private Boolean result;
    /**
     * 动作开始执行时间
     */
    @Schema(description = "动作开始执行时间")
    @NotNull(message = "请填写动作开始执行时间")
    private LocalDateTime startTime;
    /**
     * 动作结束执行时间
     */
    @Schema(description = "动作结束执行时间")
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
