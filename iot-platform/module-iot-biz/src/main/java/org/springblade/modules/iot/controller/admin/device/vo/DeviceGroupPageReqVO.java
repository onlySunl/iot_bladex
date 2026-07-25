

package org.springblade.modules.iot.controller.admin.device.vo;

import org.springblade.modules.iot.common.entity.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 设备分组分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceGroupPageReqVO extends PageParam {

    @Schema(description = "分组名称", example = "赵六")
    private String name;

    @Schema(description = "用户ID", example = "25833")
    private Long uid;

    @Schema(description = "机构id", example = "30228")
    private Long deptId;

    @Schema(description = "分组类型(0系统, 1用户, 字典)")
    private Integer typ;

    @Schema(description = "分组ID")
    private Long groupId;

    @Schema(description = "设备标识")
    private String dn;

}
