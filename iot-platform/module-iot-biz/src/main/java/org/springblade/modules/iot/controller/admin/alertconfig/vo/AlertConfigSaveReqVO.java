

package org.springblade.modules.iot.controller.admin.alertconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 报警配置新增/修改 Request VO")
@Data
public class AlertConfigSaveReqVO {

    @Schema(description = "告警配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7010")
    private Long id;

    @Schema(description = "告警名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "告警名称不能为空")
    private String name;

    @Schema(description = "关联消息转发模板ID", example = "9137")
    @NotNull(message = "关联消息转发模板ID不能为空")
    private Long messageTemplateId;

    @Schema(description = "规则引擎id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14376")
    @NotNull(message = "规则引擎id不能为空")
    private Long ruleInfoId;

    @Schema(description = "告警等级")
    private String level;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "状态(0启动 1禁用)", example = "1")
    private Integer status;

}
