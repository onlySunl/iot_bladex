package org.springblade.modules.iot.device.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 设备连接状态
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceConnectStatusEnum", description = "设备连接状态类型")
public enum DeviceConnectStatusEnum {

    /**
     * 未连接
     */
    UNCONNECTED(0, "unconnected"),

    /**
     * 在线
     */
    ONLINE(1, "online"),

    /**
     * 离线
     */
    OFFLINE(2, "offline"),
    ;

    /**
     * 所以设备连接的状态
     */
    public static final List<Integer> ALL_COLLECTION_STATES = List.of(UNCONNECTED.value, ONLINE.value, OFFLINE.value);

    private Integer value;
    private String desc;

    /**
     * 根据key获取对应的枚举
     *
     * @param value 设备连接的状态值
     * @return 返回对应的枚举，如果没找到则返回 Optional.empty()
     */
    public static Optional<DeviceConnectStatusEnum> fromValue(Integer value) {
        return Stream.of(DeviceConnectStatusEnum.values())
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
