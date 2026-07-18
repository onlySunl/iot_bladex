

package org.springblade.modules.iot.component.core.model.up;


import org.springblade.modules.iot.component.core.model.ActionType;
import org.springblade.modules.iot.common.enums.DeviceState;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 设备在线状态变更
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class DeviceStateChange extends ReportAction implements Serializable {

    private DeviceState state;

    @Override
    public ActionType getType() {
        return ActionType.STATE_CHANGE;
    }
}
