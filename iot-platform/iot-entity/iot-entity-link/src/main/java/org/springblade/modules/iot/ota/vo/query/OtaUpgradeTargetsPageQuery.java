package org.springblade.modules.iot.ota.vo.query;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 表单查询条件VO
 * OTA升级目标表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(description = "OTA升级目标表")
public class OtaUpgradeTargetsPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    /**
     * 任务ID
     */
    @Schema(description = "任务ID")
    private Long taskId;

    /**
     * 任务ID列表
     */
    @Schema(description = "任务ID列表")
    private List<Long> taskIds;

    /**
     * 目标值(设备标识/分组ID/省市区域编码)
     */
    @Schema(description = "目标值(设备标识/分组ID/省市区域编码)")
    private String targetValue;
    /**
     * 目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)
     */
    @Schema(description = "目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)")
    private Integer targetStatus;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String remark;
    /**
     * 逻辑删除标识(0-未删除、1-已删除)
     */
    @Schema(description = "逻辑删除标识(0-未删除、1-已删除)")
    private Integer deleted;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
