package org.springblade.modules.iot.vo.update.linkage;

import org.springblade.common.entity.CustomBaseEntity;
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

/**
 * <p>
 * 表单修改方法VO
 * 规则实例表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-05 23:04:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleInstanceUpdateVO", description = "规则实例表")
public class RuleInstanceUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @NotNull(message = "请填写id", groups = CustomBaseEntity.Update.class)
    private Long id;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 64, message = "应用ID长度不能超过{max}")
    private String appId;
    /**
     * 规则实例名称
     */
    @Schema(description = "规则实例名称")
    @Size(max = 255, message = "规则实例名称长度不能超过{max}")
    private String ruleName;
    /**
     * 流程ID， 规则实例类型为“规则编排”时，该项为对应的NedRed流程
     */
    @Schema(description = "流程ID， 规则实例类型为“规则编排”时，该项为对应的NedRed流程")
    @Size(max = 200, message = "流程ID， 规则实例类型为“规则编排”时，该项为对应的NedRed流程长度不能超过{max}")
    private String flowId;
    /**
     * 流程数据
     */
    @Schema(description = "流程数据")
    @Size(max = 2147483647, message = "流程数据长度不能超过{max}")
    private String flowData;
    /**
     * 规则实例类型(字典标识：RULE_INSTANCE_TYPE）
     */
    @Schema(description = "规则实例类型(字典标识：RULE_INSTANCE_TYPE）")
    @NotNull(message = "请填写规则实例类型(字典标识：RULE_INSTANCE_TYPE）")
    private Integer type;

    /**
     * 实例地址
     */
    @Schema(description = "实例地址")
    @NotEmpty(message = "请填写实例地址")
    @Size(max = 100, message = "实例地址长度不能超过{max}")
    private String instanceAddress;
    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "请填写状态")
    private Integer status;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
