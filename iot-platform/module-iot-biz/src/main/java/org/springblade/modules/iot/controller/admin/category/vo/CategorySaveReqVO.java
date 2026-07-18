

package org.springblade.modules.iot.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - IOT产品分类新增/修改 Request VO")
@Data
public class CategorySaveReqVO {

    @Schema(description = "分类id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14594")
    private Long id;

    @Schema(description = "父分类id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9527")
    @NotNull(message = "父分类id不能为空")
    private Long parentId;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "分类名称不能为空")
    private String name;

    @Schema(description = "分类排序")
    private Integer sort;

    @Schema(description = "开启状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "开启状态不能为空")
    private Integer status;

    @Schema(description = "图片地址", example = "http://www.enjoy-iot.cn")
    private String imgUrl;

}
