

package org.springblade.modules.iot.controller.admin.channeltemplate.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通道模板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChannelTemplateRespVO {

    @Schema(description = "通道模板id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21211")
    @ExcelProperty("通道模板id")
    private Long id;

    @Schema(description = "通道模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("通道模板名称")
    private String title;

    @Schema(description = "通道配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11962")
    @ExcelProperty("通道配置id")
    private Long channelConfigId;

    @Schema(description = "通道模板内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("通道模板内容")
    private String content;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "模板状态", example = "1")
    @ExcelProperty("模板状态")
    private Integer status;

    @Schema(description = "模板代码", example = "SMS_123456789")
    @ExcelProperty("模板代码")
    private String templateCode;
}
