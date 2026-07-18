

package org.springblade.modules.iot.controller.admin.component.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 组件配置更新 Request VO")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ComponentUpdateReqVO extends ComponentBaseVO {

    @Schema(description = "组件编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "组件编号不能为空")
    private Long id;

}