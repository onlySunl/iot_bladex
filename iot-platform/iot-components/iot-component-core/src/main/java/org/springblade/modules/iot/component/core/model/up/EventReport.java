

package org.springblade.modules.iot.component.core.model.up;


import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 事件上报
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class EventReport extends ReportAction {

    /**
     * 事件名
     */
    private String name;

    /**
     * 事件参数
     */
    private Map<String, Object> params;

    @Override
    public ActionType getType() {
        return ActionType.EVENT_REPORT;
    }
}
