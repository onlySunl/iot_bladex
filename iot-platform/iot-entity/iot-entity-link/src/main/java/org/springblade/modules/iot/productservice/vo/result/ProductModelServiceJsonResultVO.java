package org.springblade.modules.iot.productservice.vo.result;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import org.springblade.modules.iot.productcommand.vo.result.ProductModelCommandJsonResultVO;
import org.springblade.modules.iot.productproperty.vo.result.ProductModelPropertyJsonResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Schema(title = "ProductModelServiceJsonResultVO", description = "产品模型服务JSON参数VO")
public class ProductModelServiceJsonResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    @Schema(description = "产品服务命令")
    private List<ProductModelCommandJsonResultVO> commands;

    @Schema(description = "产品服务属性")
    private List<ProductModelPropertyJsonResultVO> properties;

}
