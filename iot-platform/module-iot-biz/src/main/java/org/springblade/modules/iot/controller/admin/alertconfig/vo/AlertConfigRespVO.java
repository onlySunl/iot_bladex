

package org.springblade.modules.iot.controller.admin.alertconfig.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 报警配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AlertConfigRespVO {

    @Schema(description = "告警配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7010")
    @ExcelProperty("告警配置id")
    private Long id;

    @Schema(description = "告警名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("告警名称")
    private String name;

    @Schema(description = "关联消息转发模板ID", example = "9137")
    @ExcelProperty("关联消息转发模板ID")
    private Long messageTemplateId;

    @Schema(description = "规则引擎id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14376")
    @ExcelProperty("规则引擎id")
    private Long ruleInfoId;

    @Schema(description = "告警等级")
    @ExcelProperty("告警等级")
    private String level;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "状态(0启动 1禁用)", example = "1")
    @ExcelProperty("状态(0启动 1禁用)")
    private Integer status;

}
