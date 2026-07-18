package org.springblade.modules.iot.api.rule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.modules.iot.common.entity.PageParam;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RuleInfoPageReqVO extends PageParam {

    @Schema(description = "类型(1数据流转 2场景联动)")
    private String typ;

    @Schema(description = "状态(0启用 1禁用)", example = "1")
    private Integer state;

    @Schema(description = "机构id", example = "21724")
    private Long deptId;

}
