package org.springblade.modules.iot.productservice.vo.query;

import org.springblade.modules.iot.productcommand.entity.ProductCommand;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 产品模型服务详情
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
@Schema(title = "ProductServicesDetails", description = "产品模型服务详情")
public class ProductServicesDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "服务id")
    private Long id;

    /**
     * 产品ID
     */
    @Schema(description = "产品ID")
    private Long productId;
    /**
     * 服务编码:支持英文大小写、数字、下划线和中划线
     */
    @Schema(description = "服务编码:支持英文大小写、数字、下划线和中划线")
    private String serviceCode;
    /**
     * 服务名称
     */
    @Schema(description = "服务名称")
    private String serviceName;
    /**
     * 服务类型
     */
    @Schema(description = "服务类型")
    private String serviceType;
    /**
     * 状态(字典值：0启用  1停用)
     */
    @Schema(description = "状态(字典值：0启用  1停用)")
    private Integer serviceStatus;
    /**
     * 服务的描述信息:文本描述，不影响实际功能，可配置为空字符串。
     */
    @Schema(description = "服务的描述信息:文本描述，不影响实际功能，可配置为空字符串。")
    private String description;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

    /**
     * 产品模型设备服务命令
     */
    @Schema(description = "产品模型设备服务命令")
    private List<ProductCommand> commands;

    /**
     * 产品模型服务属性
     */
    @Schema(description = "产品模型服务属性")
    private List<ProductProperty> properties;

}
