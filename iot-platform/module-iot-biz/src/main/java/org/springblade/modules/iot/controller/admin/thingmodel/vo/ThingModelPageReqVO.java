

package org.springblade.modules.iot.controller.admin.thingmodel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springblade.modules.iot.common.entity.PageParam;

@Schema(description = "管理后台 - 产品物模型分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ThingModelPageReqVO extends PageParam {

    @Schema(description = "产品key")
    private String productKey;

}
