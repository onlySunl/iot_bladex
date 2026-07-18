

package org.springblade.modules.iot.controller.admin.product.vo;

import org.springblade.core.tool.utils.DateUtil;
import org.springblade.modules.iot.common.entity.PageParam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Schema(description = "产品分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPageReqVO extends PageParam {

    @Schema(description = "产品名称", example = "王五")
    private String name;

    @Schema(description = "产品分类id", example = "19389")
    private Long categoryId;

    @Schema(description = "productKey")
    private String productKey;

    @Schema(description = "mcu code")
    private String mcuCode;

    @Schema(description = "状态（0启用 1禁用）", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime[] createTime;

    @Schema(description = "设备类型(0 网关设备, 1 网关子设备, 2 直连设备, 3 非联网设备 )", example = "2")
    private Integer nodeType;

    @Schema(description = "协议code")
    private String protocolCode;

    @Schema(description = "是否透传")
    private Boolean transparent;

}
