

package org.springblade.modules.iot.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

//@Schema(description = "绑定")
@Data
public class DeviceBindReqVO {

    @Schema(description = "设备id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3442")
    @NotEmpty(message = "设备id不许为空")
    private List<Long> idList;

    @Schema(description = "父级设备id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3442")
    @NotNull(message = "父级设备id不许为空")
    private Long parentId;


}
