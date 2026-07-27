package org.springblade.modules.iot.device.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 设备认证模式（用于连接鉴权）
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceAuthModeEnum", description = "设备认证模式类型")
public enum DeviceAuthModeEnum {

    /**
     * 用户名密码模式
     */
    ACCOUNT_MODE(0, "用户名密码模式"),

    /**
     * SSL/TLS证书模式
     */
    SSL_MODE(1, "SSL/TLS证书模式"),
    ;

    private Integer value;
    private String desc;

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据key获取对应的枚举
     *
     * @param value 状态值
     * @return 返回对应的枚举，如果没找到则返回 Optional.empty()
     */
    public static Optional<DeviceAuthModeEnum> fromValue(Integer value) {
        return Stream.of(DeviceAuthModeEnum.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }
}
