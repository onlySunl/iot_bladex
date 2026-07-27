package org.springblade.modules.iot.device.vo.query.group;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 表单查询条件VO
 * 设备分组关系表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-23 14:06:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(description = "设备分组关系")
public class DeviceGroupRelPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    /**
    * 分组ID;#device_group
    */
    @Schema(description = "分组ID")
    private Long groupId;
    /**
    * 设备标识
    */
    @Schema(description = "设备标识")
    private String deviceIdentification;
    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;
    /**
    * 最后修改时间
    */
    @Schema(description = "最后修改时间")
    private LocalDateTime updatedTime;
    /**
    * 最后修改人
    */
    @Schema(description = "最后修改人")
    private Long updatedBy;
    /**
    * 创建人组织
    */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
