package org.springblade.modules.iot.vo.result.linkage;

import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 表单查询方法返回值VO
 * 规则详情结果
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
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "RuleDetailsResultVO", description = "规则详情结果VO")
public class RuleDetailsResultVO extends AuditableResultVO {

    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String ruleName;
    /**
     * 规则标识
     */
    @Schema(description = "规则标识")
    private String ruleIdentification;
    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 生效类型
     */
    @Schema(description = "生效类型")
    private Integer effectiveType;
    /**
     * 指定内容
     */
    @Schema(description = "指定内容")
    private String appointContent;
    /**
     * 启用状态
     */
    /**
     * 描述
     */


    @Schema(description = "规则条件详情结果VO集合")
    private List<RuleConditionDetailsResultVO> conditionDetailsResultVOS;
}
