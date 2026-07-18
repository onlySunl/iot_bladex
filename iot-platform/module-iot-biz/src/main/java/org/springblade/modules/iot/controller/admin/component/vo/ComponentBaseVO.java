

package org.springblade.modules.iot.controller.admin.component.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 组件配置 Base VO")
@Data
@SuperBuilder
@NoArgsConstructor
public class ComponentBaseVO {

    @Schema(description = "组件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "测试组件")
    @NotNull(message = "组件名称不能为空")
    private String name;

    @Schema(description = "组件类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "mqtt")
    @NotNull(message = "组件类型不能为空")
    private String type;

    @Schema(description = "组件配置", requiredMode = Schema.RequiredMode.REQUIRED)
    private String config;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "备注", example = "我是备注")
    private String remark;

}