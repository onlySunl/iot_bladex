

package org.springblade.modules.iot.controller.admin.showmodel.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品显示模型 Response VO")
@Data
public class ShowModelRespVO {

    @Schema(description = "显示id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4925")
    @ExcelProperty("显示id")
    private Long id;

    @Schema(description = "类型 0:详情页, 1配置页", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("类型 0:详情页, 1配置页")
    private Integer typ;

    @Schema(description = "配置名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("配置名称")
    private String name;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "模型code", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模型code")
    private String modelCode;

    @Schema(description = "状态（0正常 1禁用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态（0正常 1禁用）")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    private String productKey;


}
