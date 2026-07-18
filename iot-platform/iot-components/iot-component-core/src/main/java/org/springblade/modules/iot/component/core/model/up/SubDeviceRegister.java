

package org.springblade.modules.iot.component.core.model.up;



import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 子设备注册动作
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class SubDeviceRegister extends ReportAction {

    /**
     * 型号
     */
    private String model;

    /**
     * 版本号
     */
    private String version;

    /**
     * 子设备注册
     */
    private List<DeviceRegister> subs;

    @Override
    public ActionType getType() {
        return ActionType.SUB_REGISTER;
    }
}
