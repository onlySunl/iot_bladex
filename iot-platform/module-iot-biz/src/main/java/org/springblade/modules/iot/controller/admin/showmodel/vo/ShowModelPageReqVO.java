

package org.springblade.modules.iot.controller.admin.showmodel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品显示模型分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShowModelPageReqVO extends PageParam {

    @Schema(description = "类型 0:详情页, 1配置页")
    private Integer typ;

    @Schema(description = "配置名称", example = "张三")
    private String name;

    @Schema(description = "模型code")
    private String modelCode;

    @Schema(description = "状态（0正常 1禁用）", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime[] createTime;

}
