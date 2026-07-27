package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Ota upgrade scope
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/9/28
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaUpgradeScopeEnum", description = "OTA升级范围，字典标识：LINK_OTA_UPGRADE_SCOPE")
public enum OtaUpgradeScopeEnum {
    ALL_DEVICES(0, "全部设备"),
    TARGETED(1, "定向升级"),
    GROUP(2, "分组升级"),
    REGIONAL(3, "区域升级");

    private Integer value;
    private String desc;

    /**
     * Get the enum value from an integer.
     *
     * @param value Integer value representing the scope.
     * @return An Optional of OtaUpgradeScopeEnum.
     */
    public static Optional<OtaUpgradeScopeEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaUpgradeScopeEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
