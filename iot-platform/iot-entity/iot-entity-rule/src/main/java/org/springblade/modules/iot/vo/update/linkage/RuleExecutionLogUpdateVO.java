package org.springblade.modules.iot.vo.update.linkage;

import org.springblade.basic.base.entity.Entity;
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
 * 表单修改方法VO
 * 规则执行日志表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-12-02 18:41:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleExecutionLogUpdateVO", description = "规则执行日志表")
public class RuleExecutionLogUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = Entity.Update.class)
    private Long id;

    /**
     * 规则标识
     */
    @Schema(description = "规则标识")
    @NotEmpty(message = "请填写规则标识")
    @Size(max = 100, message = "规则标识长度不能超过{max}")
    private String ruleIdentification;
    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    @NotEmpty(message = "请填写规则名称")
    @Size(max = 255, message = "规则名称长度不能超过{max}")
    private String ruleName;
    /**
     * 规则执行状态：0-未执行，1-执行中，2-已完成
     */
    @Schema(description = "规则执行状态：0-未执行，1-执行中，2-已完成")
    @NotNull(message = "请填写规则执行状态：0-未执行，1-执行中，2-已完成")
    private Integer status;
    /**
     * 规则执行开始时间
     */
    @Schema(description = "规则执行开始时间")
    @NotNull(message = "请填写规则执行开始时间")
    private LocalDateTime startTime;
    /**
     * 规则执行结束时间
     */
    @Schema(description = "规则执行结束时间")
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
