package org.springblade.modules.iot.vo.query.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * 表单查询条件VO
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
@Schema(title = "RuleConditionActionPageQuery", description = "规则条件动作表")
public class RuleConditionActionPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    /**
     * 规则条件ID
     */
    @Schema(description = "规则条件ID")
    private Long ruleConditionId;
    /**
     * 执行动作
     */
    @Schema(description = "执行动作")
    private Integer actionType;
    /**
     * 动作内容
     */
    @Schema(description = "动作内容")
    private String actionContent;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
