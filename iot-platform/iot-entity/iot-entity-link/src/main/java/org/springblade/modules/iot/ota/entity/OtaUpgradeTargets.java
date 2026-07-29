package org.springblade.modules.iot.ota.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 实体类
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
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "ota_upgrade_targets", comment = "OtaUpgradeTargets table")
public class OtaUpgradeTargets extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @AutoColumn(value = "task_id", comment = "任务ID")
    private Long taskId;
    /**
     * 目标值(设备标识/分组ID/省市区域编码)
     */
    @AutoColumn(value = "target_value", comment = "目标值(设备标识/分组ID/省市区域编码)")
    private String targetValue;
    /**
     * 目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)
     */
    @AutoColumn(value = "target_status", comment = "目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)")
    private Integer targetStatus;
    /**
     * 描述
     */
    /**
     * 逻辑删除标识(0-未删除、1-已删除)
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识(0-未删除、1-已删除)")
    private Integer deleted;
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

}
