

package org.springblade.modules.iot.controller.admin.virtualdevice.vo;

import org.springblade.modules.iot.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VirtualDeviceLogPageReqVO extends PageParam {


    @Schema(description = "设备id", example = "1")
    private Long virtualDeviceId;


}
