package org.springblade.modules.iot.vo.param.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
 * 规则条件动作参数VO
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
@Schema(title = "RuleConditionActionParamVO", description = "规则条件动作参数VO")
public class RuleConditionActionParamVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

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
    @NotBlank(message = "请填写动作内容")
    private String actionContent;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String remark;
}
