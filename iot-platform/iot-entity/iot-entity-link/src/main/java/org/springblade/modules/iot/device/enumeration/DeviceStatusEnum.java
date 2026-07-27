package org.springblade.modules.iot.device.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 设备状态
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceStatusEnum", description = "设备状态类型")
public enum DeviceStatusEnum {

    /**
     * 未激活
     */
    UNACTIVATED(0, "未激活"),

    /**
     * 已激活
     */
    ACTIVATED(1, "已激活"),

    /**
     * 已锁定
     */
    LOCKED(2, "已锁定"),
    ;

    /**
     * 设备连接的状态
     */
    public static final List<Integer> ALL_STATE_COLLECTION = List.of(UNACTIVATED.value, ACTIVATED.value, LOCKED.value);
    /**
     * 禁止设备连接的状态
     */
    public static final List<Integer> DISCONNECTION_STATE_COLLECTION = List.of(UNACTIVATED.value, LOCKED.value);
    private Integer value;
    private String desc;

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
