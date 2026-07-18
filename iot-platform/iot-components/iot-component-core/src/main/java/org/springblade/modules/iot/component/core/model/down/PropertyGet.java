

package org.springblade.modules.iot.component.core.model.down;


import org.springblade.modules.iot.component.core.model.AbstractAction;
import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 属性获取
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PropertyGet extends AbstractAction {

    /**
     * 属性列表
     */
    private List<String> keys;

    @Override
    public ActionType getType() {
        return ActionType.PROPERTY_GET;
    }
}
