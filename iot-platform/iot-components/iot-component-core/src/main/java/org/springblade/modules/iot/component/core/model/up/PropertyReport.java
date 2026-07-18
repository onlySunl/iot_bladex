

package org.springblade.modules.iot.component.core.model.up;



import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 属性上报
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class PropertyReport extends ReportAction {

    /**
     * 属性参数
     */
    private Map<String, Object> params;

    @Override
    public ActionType getType() {
        return ActionType.PROPERTY_REPORT;
    }
}
