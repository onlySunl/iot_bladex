package org.springblade.modules.iot.device.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

/**
 * <p>
 * 实体类
 * 设备动作数据
 * </p>
 *
 * @author mqttsnet
 * @date 2023-06-10 16:38:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_device_action", comment = "DeviceAction table")
public class DeviceAction extends Entity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备标识
     */
    @AutoColumn(value = "device_identification", comment = "设备标识")
    private String deviceIdentification;
    /**
     * 动作类型
     */
    @AutoColumn(value = "action_type", comment = "动作类型")
    private String actionType;
    /**
     * 内容信息
     */
    @AutoColumn(value = "message", comment = "内容信息")
    private String message;
    /**
     * 状态
     */
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
