

package org.springblade.modules.iot.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
public class IotThingModelSaveReqVO {

    @Schema(description = "物模型id", requiredMode = Schema.RequiredMode.REQUIRED, example = "856")
    private Long id;

    @Schema(description = "产品key", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "产品key不能为空")
    private String productKey;

    @Schema(description = "物模型")
    private String model;

}
