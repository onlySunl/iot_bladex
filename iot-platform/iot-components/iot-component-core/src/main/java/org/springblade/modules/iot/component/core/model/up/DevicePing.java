

package org.springblade.modules.iot.component.core.model.up;



import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 设备在线状态变更
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class DevicePing extends ReportAction {

    @Override
    public ActionType getType() {
        return ActionType.PING;
    }
}
