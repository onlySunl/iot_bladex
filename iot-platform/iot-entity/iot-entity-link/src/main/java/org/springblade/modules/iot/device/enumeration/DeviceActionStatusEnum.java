package org.springblade.modules.iot.device.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 设备动作状态 枚举
 * </p>
 *
 * @author shihuan sun
 * @date 2023-08-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceActionStatusEnum", description = "设备动作状态 枚举")
public enum DeviceActionStatusEnum {
    /**
     * 成功
     */
    SUCCESSFUL(0, "成功"),

    /**
     * 失败
     */
    FAIL(1, "失败"),
    ;

    private Integer value;
    private String desc;

    /**
     * 根据key获取对应的枚举
     *
     * @param value 设备连接的状态值
     * @return 返回对应的枚举，如果没找到则返回 Optional.empty()
     */
    public static Optional<DeviceActionStatusEnum> fromValue(Integer value) {
        return Stream.of(DeviceActionStatusEnum.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
