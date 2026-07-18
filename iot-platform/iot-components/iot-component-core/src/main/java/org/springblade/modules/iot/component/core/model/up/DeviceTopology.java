

package org.springblade.modules.iot.component.core.model.up;



import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 设备拓扑更新
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class DeviceTopology extends ReportAction {

    /**
     * 父设备下的子设备列表
     */
//    private List<String> subDevices;

    private List<DeviceRegister> subs;
    @Override
    public ActionType getType() {
        return ActionType.TOPOLOGY;
    }
}
