

package org.springblade.modules.iot.controller.admin.virtualdevice.vo;

import org.springblade.modules.iot.common.entity.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VirtualDevicePageReqVO extends PageParam {


    @Schema(description = "状态(running:运行中 stopped:已暂停)", example = "running")
    private String state;

    @Schema(description = "产品key", example = "mpDXsY7yihnfBmw5")
    private String productKey;

}
