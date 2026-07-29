package org.springblade.modules.iot.entity.protocol;

import java.util.Map;

/**
 * -----------------------------------------------------------------------------
 * File Name: WebSocketEvent
 * -----------------------------------------------------------------------------
 * Description:
 * WebSocket事件
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
 * @date 2024/3/11 15:06
 */
public class WebSocketEvent extends ProtocolEvent {
    public WebSocketEvent(Object source, Map<String, String> contextUtilLocalMap, String message) {
        super(source, contextUtilLocalMap, message);
    }
}
