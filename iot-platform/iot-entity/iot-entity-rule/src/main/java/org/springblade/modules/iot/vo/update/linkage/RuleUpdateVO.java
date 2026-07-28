package org.springblade.modules.iot.vo.update.linkage;

import org.springblade.basic.base.entity.CustomBaseEntity;
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
 * 规则信息
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
@Schema(title = "RuleUpdateVO", description = "规则信息")
public class RuleUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = CustomBaseEntity.Update.class)
    private Long id;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    @NotEmpty(message = "请填写规则名称")
    @Size(max = 100, message = "规则名称长度不能超过{max}")
    private String ruleName;
    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 64, message = "应用ID长度不能超过{max}")
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
    @Size(max = 500, message = "指定内容长度不能超过{max}")
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
    @Size(max = 255, message = "描述长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
