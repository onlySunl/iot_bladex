

package org.springblade.modules.iot.controller.admin.channeltemplate.vo;

import org.springblade.core.tool.utils.DateUtil;
import org.springblade.modules.iot.common.entity.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Schema(description = "管理后台 - 通道模板分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChannelTemplatePageReqVO extends PageParam {

    @Schema(description = "通道模板名称")
    private String title;

    @Schema(description = "通道配置id", example = "11962")
    private Long channelConfigId;

    @Schema(description = "通道模板内容")
    private String content;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime[] createTime;

    @Schema(description = "机构id", example = "25751")
    private Long deptId;

}
