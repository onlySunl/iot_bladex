

package org.springblade.modules.iot.controller.admin.virtualdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "管理后台 - 规则引擎设置状态 VO")
@Data
public class EiotVirtualDeviceSetStateReqVO {

    /**
     * 虚拟设备id
     */
    @Schema(description = "虚拟设备id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12640")
    @NotNull(message = "虚拟设备id不能为空")
    private Long id;

    /**
     * 运行状态
     */
    @Schema(description = "状态(0启用 1禁用)", example = "1")
    @NotNull(message = "虚拟设备运行状态不能为空")
    private String state;


}
