package org.springblade.modules.iot.entity.protocol;

import java.util.Map;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * -----------------------------------------------------------------------------
 * File Name: ProtocolEvent
 * -----------------------------------------------------------------------------
 * Description:
 * 协议事件类
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/3/11       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/3/11 15:03
 */
@Getter
public class ProtocolEvent extends ApplicationEvent {
    private final Map<String, String> contextUtilLocalMap;
    private final String message;

    public ProtocolEvent(Object source, Map<String, String> contextUtilLocalMap, String message) {
        super(source);
        this.contextUtilLocalMap = contextUtilLocalMap;
        this.message = message;
    }

}
