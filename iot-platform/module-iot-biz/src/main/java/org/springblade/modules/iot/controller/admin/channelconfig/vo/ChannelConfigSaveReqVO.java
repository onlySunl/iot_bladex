

package org.springblade.modules.iot.controller.admin.channelconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 通道配置新增/修改 Request VO")
@Data
public class ChannelConfigSaveReqVO {

    @Schema(description = "通道配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28160")
    private Long id;

    @Schema(description = "配置名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "配置名称不能为空")
    private String title;

    @Schema(description = "通道编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "通道编码不能为空")
    private String code;

    @Schema(description = "通道配置参数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "通道配置参数不能为空")
    private String param;

}
