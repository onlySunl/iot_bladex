package org.springblade.modules.iot.vo.update.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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
 * -----------------------------------------------------------------------------
 * File Name: RuleInstanceFlowUpdateVO
 * -----------------------------------------------------------------------------
 * Description:
 * FlowUpdateVO
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/4/4       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/4/4 23:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleInstanceFlowUpdateVO", description = "规则实例流程数据更新VO")
public class RuleInstanceFlowUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "流程ID")
    @NotEmpty(message = "流程ID不能为空")
    @Size(max = 200, message = "流程ID长度不能超过{max}")
    private String flowId;

    @Schema(description = "流程数据")
    private String flowData;
}
