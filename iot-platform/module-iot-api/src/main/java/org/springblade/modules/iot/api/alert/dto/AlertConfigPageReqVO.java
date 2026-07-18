

package org.springblade.modules.iot.api.alert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.modules.iot.common.entity.PageParam;

@Schema(description = "管理后台 - 报警配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlertConfigPageReqVO extends PageParam {

    @Schema(description = "告警名称", example = "王五")
    private String name;

    @Schema(description = "规则引擎id", example = "14376")
    private Long ruleInfoId;

    @Schema(description = "告警等级")
    private String level;

    @Schema(description = "状态(0启动 1禁用)", example = "1")
    private Integer status;

}
