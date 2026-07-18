

package org.springblade.modules.iot.controller.admin.channelconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 通道配置 Request VO")
@Data
@ToString(callSuper = true)
public class ChannelConfigReqVO {

    @Schema(description = "配置名称")
    private String title;

    @Schema(description = "通道编码")
    private String code;


    @Schema(description = "机构id", example = "18017")
    private Long deptId;

}
