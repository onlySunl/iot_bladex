

package org.springblade.modules.iot.controller.admin.device.vo;

import org.springblade.modules.iot.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 设备信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceUnbindPageReqVO extends PageParam {
    @Schema(description = "设备名称")
    private String name;

    @Schema(description = "设备唯一标识")
    private String dn;

    @Schema(description = "产品名称")
    private String productName;

}
