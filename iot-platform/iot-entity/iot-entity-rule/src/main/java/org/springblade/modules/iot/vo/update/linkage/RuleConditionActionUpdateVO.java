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

import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 规则条件动作表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:24:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleConditionActionUpdateVO", description = "规则条件动作修改参数VO")
public class RuleConditionActionUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = Entity.Update.class)
    private Long id;

    /**
     * 规则条件ID
     */
    @Schema(description = "规则条件ID")
    @NotNull(message = "请填写规则条件ID")
    private Long ruleConditionId;
    /**
     * 执行动作
     */
    @Schema(description = "执行动作")
    @NotNull(message = "请填写执行动作")
    private Integer actionType;
    /**
     * 动作内容
     */
    @Schema(description = "动作内容")
    @NotEmpty(message = "请填写动作内容")
    @Size(max = 2147483647, message = "动作内容长度不能超过{max}")
    private String actionContent;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Size(max = 255, message = "描述长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
