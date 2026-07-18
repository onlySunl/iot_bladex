

package org.springblade.modules.iot.controller.admin.product.vo;

import org.springblade.modules.iot.common.entity.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IotThingModelPageReqVO extends PageParam {

    @Schema(description = "产品key")
    private String productKey;

}
