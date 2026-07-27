package org.springblade.modules.iot.dto.linkage;

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
import java.util.List;

/**
 * <p>
 * 规则信息策略DTO
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
public class RulePolicyDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    @NotEmpty(message = "请填写规则名称")
    @Size(max = 100, message = "规则名称长度不能超过{max}")
    private String ruleName;
    /**
     * 规则标识
     */
    @NotEmpty(message = "请填写规则标识")
    @Size(max = 100, message = "规则标识长度不能超过{max}")
    private String ruleIdentification;
    /**
     * 应用ID
     */
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 64, message = "应用ID长度不能超过{max}")
    private String appId;
    /**
     * 生效类型
     */
    @NotNull(message = "请填写生效类型")
    private Integer effectiveType;
    /**
     * 指定内容
     */
    @Size(max = 500, message = "指定内容长度不能超过{max}")
    private String appointContent;
    /**
     * 启用状态
     */
    @NotNull(message = "请填写启用状态")
    private Integer status;
    /**
     * 描述
     */
    @Size(max = 255, message = "描述长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    private Long createdOrgId;

    /**
     * 规则条件
     */
    private List<RuleConditionPolicyDTO> ruleConditionPolicyDTOS;

}
