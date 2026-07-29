package org.springblade.core.protocol;

/**
 * 协议类型枚举
 *
 * @author Chill
 */
public enum ProtocolType {

    /**
     * MQTT 协议
     */
    MQTT("mqtt", "MQTT 协议"),

    /**
     * HTTP 协议
     */
    HTTP("http", "HTTP 协议"),

    /**
     * TCP 协议
     */
    TCP("tcp", "TCP 协议"),

    /**
     * UDP 协议
     */
    UDP("udp", "UDP 协议"),

    /**
     * CoAP 协议
     */
    COAP("coap", "CoAP 协议"),

    /**
     * Modbus 协议
     */
    MODBUS("modbus", "Modbus 协议"),

    /**
     * GB28181 协议
     */
    GB28181("gb28181", "GB28181 国标协议"),

    /**
     * ONVIF 协议
     */
    ONVIF("onvif", "ONVIF 协议");

    private final String code;
    private final String desc;

    ProtocolType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ProtocolType of(String code) {
        for (ProtocolType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
