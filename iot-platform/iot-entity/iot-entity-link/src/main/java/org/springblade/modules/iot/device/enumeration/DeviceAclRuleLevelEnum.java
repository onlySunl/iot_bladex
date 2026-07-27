package org.springblade.modules.iot.device.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Description:
 * 设备ACl规则级别枚举
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceAclRuleLevelEnum", description = "设备ACl规则级别类型")
public enum DeviceAclRuleLevelEnum {
    /**
     * 产品级
     */
    PRODUCT_LEVEL(0, "产品级"),

    /**
     * 设备级
     */
    DEVICE_LEVEL(1, "设备级"),

    ;

    private Integer value;
    private String desc;

    public static Optional<DeviceAclRuleLevelEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(DeviceAclRuleLevelEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

}
