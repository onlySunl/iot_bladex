
package org.springblade.modules.iot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 桥接类型枚举
 */
@AllArgsConstructor
@Getter
public enum BridgeType {
    /** MQTT桥接 */
    MQTT(1, "MQTT桥接"),
    /** TCP桥接 */
    TCP(2, "TCP桥接"),
    /** HTTP桥接 */
    HTTP(3, "HTTP桥接"),
    /** WebSocket桥接 */
    WEBSOCKET(4, "WebSocket桥接");

    private int code;
    private String desc;

    public static BridgeType transfer(Integer code) {
        for (BridgeType value : BridgeType.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return BridgeType.MQTT;
    }
}
