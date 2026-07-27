package org.springblade.modules.iot.device.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 设备类型
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceNodeTypeEnum", description = "设备类型")
public enum DeviceNodeTypeEnum {

    /**
     * 普通设备
     */
    ORDINARY(0, "普通设备"),

    /**
     * 网关设备
     */
    GATEWAY(1, "网关设备"),

    /**
     * 子设备
     */
    SUBDEVICE(2, "子设备"),
    ;

    private Integer value;
    private String desc;

    public static Optional<DeviceNodeTypeEnum> fromValue(Integer value) {
        return Stream.of(DeviceNodeTypeEnum.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst();
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
