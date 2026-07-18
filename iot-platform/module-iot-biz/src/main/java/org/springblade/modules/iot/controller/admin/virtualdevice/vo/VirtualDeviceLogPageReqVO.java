

package org.springblade.modules.iot.controller.admin.virtualdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.modules.iot.common.entity.PageParam;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VirtualDeviceLogPageReqVO extends PageParam {


    @Schema(description = "设备id", example = "1")
    private Long virtualDeviceId;


}
