package org.springblade.modules.iot.productservice.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 *  产品模型服务Mq参数VO
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
@Schema(title = "ProductServiceMqParamVO", description = "产品模型服务Mq参数VO")
public class ProductServiceMqParamVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "操作类型")
    private String operationType;

    @Schema(description = "支持以下两种产品类型1•COMMON：普通产品，需直连设备。2•GATEWAY：网关产品，可挂载子设备。 0其他未知产品")
    private String productType;

    @Schema(description = "产品标识")
    private String productIdentification;

    @Schema(description = "服务编码:支持英文大小写、数字、下划线和中划线")
    private String serviceCode;

}
