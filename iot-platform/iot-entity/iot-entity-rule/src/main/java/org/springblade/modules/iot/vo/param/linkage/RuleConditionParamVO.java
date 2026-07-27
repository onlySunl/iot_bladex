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
 * 规则条件参数VO
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
@Schema(title = "RuleConditionParamVO", description = "规则条件参数VO")
public class RuleConditionParamVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

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
    @NotBlank(message = "请填写条件内容")
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
    private String antiShakeScheme;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String remark;


}
