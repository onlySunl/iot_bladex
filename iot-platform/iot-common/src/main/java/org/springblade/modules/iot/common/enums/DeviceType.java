package org.springblade.modules.iot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 设备类型枚举
 */
@Getter
@AllArgsConstructor
public enum DeviceType {
    DIRECT(1, "直连设备"),
    GATEWAY(2, "网关设备"),
    SUB(3, "子设备");

    private final int code;
    private final String desc;

    public static DeviceType of(int code) {
        for (DeviceType t : values()) {
            if (t.code == code) return t;
        }
        return null;
    }
}
