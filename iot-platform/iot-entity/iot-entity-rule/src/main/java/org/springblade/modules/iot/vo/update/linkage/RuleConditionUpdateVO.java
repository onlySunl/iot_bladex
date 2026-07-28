package org.springblade.modules.iot.vo.update.linkage;

import org.springblade.basic.base.entity.CustomBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
import java.util.List;

/**
 * <p>
 * 表单修改方法VO
 * 规则条件表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:36:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleConditionUpdateVO", description = "规则条件修改参数VO")
public class RuleConditionUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 规则条件动作参数
     */
    @Schema(description = "规则条件动作参数")
    List<RuleConditionActionUpdateVO> conditionActionUpdateVOS;
    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = CustomBaseEntity.Update.class)
    private Long id;
    /**
     * 规则
     */
    @Schema(description = "规则")
    @NotNull(message = "请填写规则")
    private Long ruleId;
    /**
     * 条件类型
     */
    @Schema(description = "条件类型")
    @NotNull(message = "请填写条件类型")
    private Integer conditionType;
    /**
     * 条件内容
     */
    @Schema(description = "条件内容")
    @Size(max = 2147483647, message = "条件内容长度不能超过{max}")
    private String conditionScheme;
    /**
     * 启用状态
     */
    @Schema(description = "启用状态")
    @NotNull(message = "请填写启用状态")
    private Integer status;
    /**
     * 防抖状态
     */
    @Schema(description = "防抖状态")
    @NotNull(message = "请填写防抖状态")
    private Integer antiShake;
    /**
     * 防抖策略
     */
    @Schema(description = "防抖策略")
    @Size(max = 255, message = "防抖策略长度不能超过{max}")
    private String antiShakeScheme;
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
