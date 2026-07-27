package org.springblade.modules.iot.productservice.vo.param;

import org.springblade.modules.iot.productcommand.vo.param.ProductCommandParamVO;
import org.springblade.modules.iot.productproperty.vo.param.ProductPropertyParamVO;
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
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 表单保存方法VO
 * 产品模型服务表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "ProductServiceParamVO", description = "产品模型服务参数VO")
public class ProductServiceParamVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @Schema(description = "产品ID")
    private Long productId;
    /**
     * 服务编码:支持英文大小写、数字、下划线和中划线
     */
    @Schema(description = "服务编码:支持英文大小写、数字、下划线和中划线")
    @NotEmpty(message = "请填写服务编码:支持英文大小写、数字、下划线和中划线")
    @Size(max = 255, message = "服务编码:支持英文大小写、数字、下划线和中划线长度不能超过{max}")
    private String serviceCode;
    /**
     * 服务名称
     */
    @Schema(description = "服务名称")
    @Size(max = 255, message = "服务名称长度不能超过{max}")
    private String serviceName;
    /**
     * 服务类型
     */
    @Schema(description = "服务类型")
    @Size(max = 255, message = "服务类型长度不能超过{max}")
    private String serviceType;
    /**
     * 状态(字典值：0启用  1停用)
     */
    @Schema(description = "状态(字典值：0启用  1停用)")
    @NotNull(message = "请填写状态(字典值：0启用  1停用)")
    private Integer serviceStatus;
    /**
     * 服务的描述信息:文本描述，不影响实际功能，可配置为空字符串。
     */
    @Schema(description = "服务的描述信息:文本描述，不影响实际功能，可配置为空字符串。")
    @Size(max = 255, message = "服务的描述信息:文本描述，不影响实际功能，可配置为空字符串。长度不能超过{max}")
    private String description;
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

    @Schema(description = "产品服务命令")
    private List<ProductCommandParamVO> commands;

    @Schema(description = "产品服务属性")
    private List<ProductPropertyParamVO> properties;

}
