package org.springblade.modules.iot.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 设备状态枚举
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum DeviceStatusEnum {

    /**
     * 未激活
     */
    INACTIVE(0, "未激活"),

    /**
     * 在线
     */
    ONLINE(1, "在线"),

    /**
     * 离线
     */
    OFFLINE(2, "离线"),

    /**
     * 禁用
     */
    DISABLED(3, "禁用");

    private final Integer code;
    private final String name;

    public static DeviceStatusEnum fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (DeviceStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

}
