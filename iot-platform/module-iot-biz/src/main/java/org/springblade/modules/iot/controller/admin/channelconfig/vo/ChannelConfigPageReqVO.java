

package org.springblade.modules.iot.controller.admin.channelconfig.vo;

import org.springblade.core.tool.utils.DateUtil;
import org.springblade.modules.iot.common.entity.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Schema(description = "管理后台 - 通道配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChannelConfigPageReqVO extends PageParam {

    @Schema(description = "配置名称")
    private String title;

    @Schema(description = "通道编码")
    private String code;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime[] createTime;

    @Schema(description = "机构id", example = "18017")
    private Long deptId;

}
