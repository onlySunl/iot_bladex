package org.springblade.modules.iot.device.entity.group;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import org.springblade.basic.base.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 实体类
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
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_device_group_rel", comment = "DeviceGroupRel table")
public class DeviceGroupRel extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分组ID;#device_group
     */
    @AutoColumn(value = "group_id", comment = "分组ID;#device_group")
    private Long groupId;
    /**
     * 设备标识
     */
    @AutoColumn(value = "device_identification", comment = "设备标识")
    private String deviceIdentification;
    /**
     * 备注
     */
    /**
     * 最后修改时间
     */
    @AutoColumn(value = "updated_time", comment = "最后修改时间")
    private LocalDateTime updatedTime;
    /**
     * 最后修改人
     */
    @AutoColumn(value = "updated_by", comment = "最后修改人")
    private Long updatedBy;
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;
}
