

package org.springblade.modules.iot.controller.admin.showmodel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 产品显示模型新增/修改 Request VO")
@Data
public class ShowModelSaveReqVO {

    @Schema(description = "显示id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4925")
    private Long id;

    @Schema(description = "显示内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "显示内容不能为空")
    private String cnf;

    @Schema(description = "类型 0:详情页, 1配置页", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "类型 0:详情页, 1配置页不能为空")
    private Integer typ;

    @Schema(description = "配置名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "配置名称不能为空")
    private String name;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "模型code", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "模型code不能为空")
    private String modelCode;

    @Schema(description = "状态（0正常 1禁用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态（0正常 1禁用）不能为空")
    private Integer status;

    @NotEmpty(message = "产品key不能为空")
    private String productKey;
}
