package org.springblade.modules.iot.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 协议类型枚举
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum ProtocolTypeEnum {

    /**
     * MQTT 协议
     */
    MQTT("mqtt", "MQTT协议"),

    /**
     * HTTP 协议
     */
    HTTP("http", "HTTP协议"),

    /**
     * TCP 协议
     */
    TCP("tcp", "TCP协议"),

    /**
     * UDP 协议
     */
    UDP("udp", "UDP协议"),

    /**
     * CoAP 协议
     */
    COAP("coap", "CoAP协议"),

    /**
     * Modbus 协议
     */
    MODBUS("modbus", "Modbus协议"),

    /**
     * GB28181 协议
     */
    GB28181("gb28181", "GB28181协议"),

    /**
     * ONVIF 协议
     */
    ONVIF("onvif", "ONVIF协议");

    private final String code;
    private final String name;

    public static ProtocolTypeEnum fromCode(String code) {
        if (code == null) {
            return null;
        }
        for (ProtocolTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

}
