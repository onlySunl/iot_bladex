

package org.springblade.modules.iot.controller.admin.sip.vo;

import org.springblade.modules.iot.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 监控设备关联分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SipRelationPageReqVO extends PageParam {

    @Schema(description = "监控设备编号", example = "25976")
    private String channelId;

    @Schema(description = "关联的设备id", example = "393")
    private Long reDeviceId;

    @Schema(description = "关联的场景id", example = "27742")
    private Long reSceneModelId;

}
