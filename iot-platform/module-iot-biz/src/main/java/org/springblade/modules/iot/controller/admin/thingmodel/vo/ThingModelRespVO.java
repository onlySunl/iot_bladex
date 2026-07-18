

package org.springblade.modules.iot.controller.admin.thingmodel.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 产品物模型 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ThingModelRespVO {

    @Schema(description = "物模型id", requiredMode = Schema.RequiredMode.REQUIRED, example = "856")
    @ExcelProperty("物模型id")
    private Long id;

    @Schema(description = "产品key", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("产品key")
    private String productKey;

}
