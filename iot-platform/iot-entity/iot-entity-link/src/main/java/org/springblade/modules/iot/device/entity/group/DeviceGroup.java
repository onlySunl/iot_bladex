package org.springblade.modules.iot.device.entity.group;
import com.mqttsnet.basic.base.entity.TreeEntity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 实体类
 * 设备分组表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-19 18:05:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_device_group", comment = "DeviceGroup table")
public class DeviceGroup extends TreeEntity<DeviceGroup, Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 分组名称
     */
    @AutoColumn(value = "group_name", comment = "分组名称")
    private String groupName;
    /**
     * 分组类型
     */
    @AutoColumn(value = "type", comment = "分组类型")
    private Integer type;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @AutoColumn(value = "state", comment = "状态;[0-禁用 1-启用]")
    private Boolean state;
    /**
     * 分组描述
     */
    @AutoColumn(value = "description", comment = "分组描述")
    private String description;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;
}
