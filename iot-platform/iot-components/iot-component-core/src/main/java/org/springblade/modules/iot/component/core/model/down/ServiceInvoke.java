

package org.springblade.modules.iot.component.core.model.down;


import org.springblade.modules.iot.component.core.model.AbstractAction;
import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 服务调用
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ServiceInvoke extends AbstractAction {

    /**
     * 服务名
     */
    private String name;

    /**
     * 服务参数
     */
    private Map<String, ?> params;

    @Override
    public ActionType getType() {
        return ActionType.SERVICE_INVOKE;
    }
}
