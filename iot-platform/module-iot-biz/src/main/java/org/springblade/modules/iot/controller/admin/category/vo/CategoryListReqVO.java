

package org.springblade.modules.iot.controller.admin.category.vo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - IOT产品分类列表 Request VO")
@Data
public class CategoryListReqVO {

    @Schema(description = "父分类id", example = "9527")
    private Long parentId;

    @Schema(description = "分类名称", example = "王五")
    private String name;

    @Schema(description = "分类排序")
    private Integer sort;

    @Schema(description = "开启状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime[] createTime;

    @Schema(description = "是否系统通用（0-否，1-是）")
    private Integer isSys;

}
