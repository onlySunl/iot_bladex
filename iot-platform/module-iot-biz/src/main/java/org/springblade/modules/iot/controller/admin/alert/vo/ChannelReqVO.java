

package org.springblade.modules.iot.controller.admin.alert.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static org.springblade.modules.iot.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 通道Request VO")
@Data
@ToString(callSuper = true)
public class ChannelReqVO {

    @Schema(description = "通道名称")
    private String code;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "机构id", example = "11671")
    private Long deptId;

}
