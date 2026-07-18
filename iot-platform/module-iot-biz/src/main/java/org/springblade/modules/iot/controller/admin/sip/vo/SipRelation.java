

package org.springblade.modules.iot.controller.admin.sip.vo;

import org.springblade.modules.iot.api.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 监控设备关联对象 iot_sip_relation
 *

 */
@Schema(description = "监控设备关联 iot_sip_relation")
@Data
@EqualsAndHashCode(callSuper = true)
public class SipRelation extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 业务id
     */
    private Long id;

    /**
     * 监控设备编号
     */

    @Schema(description = "监控设备编号")
    private String channelId;

    @Schema(description = "通道名称")

    private String channelName;

    @Schema(description = "产品型号")

    private String model;

    /**
     * 关联的设备id
     */

    @Schema(description = "关联的设备id")
    private Long reDeviceId;

    /**
     * 关联的场景id
     */

    @Schema(description = "关联的场景id")
    private Long reSceneModelId;

    @Schema(description = "监控设备id")
    private Long deviceId;

    @Schema(description = "sip设备编号")
    private String deviceSipId;

    @Schema(description = "通道状态")
    private Integer status;

}
