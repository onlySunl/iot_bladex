

package org.springblade.modules.iot.controller.admin.channeltemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 通道模板新增/修改 Request VO")
@Data
public class ChannelTemplateSaveReqVO {

    @Schema(description = "通道模板id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21211")
    private Long id;

    @Schema(description = "通道模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "通道模板名称不能为空")
    private String title;

    @Schema(description = "通道配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11962")
    @NotNull(message = "通道配置id不能为空")
    private Long channelConfigId;

    @Schema(description = "通道模板内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "通道模板内容不能为空")
    private String content;

    @Schema(description = "模板状态", example = "0")
    private Integer status;

    @Schema(description = "模板代码", example = "SMS_123456789")
    private String templateCode;

}
