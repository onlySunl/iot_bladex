package org.springblade.modules.iot.vo.param.linkage;

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
 * 规则参数VO
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:20:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleParamVO", description = "规则参数VO")
public class RuleParamVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    @NotEmpty(message = "请填写规则名称")
    @Size(max = 255, message = "规则名称长度不能超过{max}")
    private String ruleName;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 100, message = "应用ID长度不能超过{max}")
    private String appId;

    /**
     * 生效类型
     */
    @Schema(description = "生效类型")
    @NotNull(message = "请填写生效类型")
    private Integer effectiveType;

    /**
     * 指定内容
     */
    @Schema(description = "指定内容")
    @Size(max = 255, message = "指定内容长度不能超过{max}")
    private String appointContent;

    /**
     * 启用状态
     */
    @Schema(description = "启用状态")
    @NotNull(message = "请填写启用状态")
    private Integer status;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String remark;

}
