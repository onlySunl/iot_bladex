

package org.springblade.modules.iot.controller.admin.channelconfig.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通道配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChannelConfigRespVO {

    @Schema(description = "通道配置id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28160")
    @ExcelProperty("通道配置id")
    private Long id;

    @Schema(description = "配置名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("配置名称")
    private String title;

    @Schema(description = "通道编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("通道编码")
    private String code;

    @Schema(description = "通道配置参数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("通道配置参数")
    private String param;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "机构id", example = "18017")
    @ExcelProperty("机构id")
    private Long deptId;

}
