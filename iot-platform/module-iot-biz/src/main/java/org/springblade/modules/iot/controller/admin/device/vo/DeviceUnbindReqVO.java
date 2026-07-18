

package org.springblade.modules.iot.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "解绑")
@Data
public class DeviceUnbindReqVO {

    @Schema(description = "设备id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3442")
    @NotEmpty(message = "设备id不许为空")
    private List<Long> idList;

}
