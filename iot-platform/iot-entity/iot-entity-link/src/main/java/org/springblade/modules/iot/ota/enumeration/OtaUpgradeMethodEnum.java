package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Ota upgrade method
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/9/28
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaUpgradeMethodEnum", description = "OTA升级方式，字典标识：LINK_OTA_UPGRADE_METHOD")
public enum OtaUpgradeMethodEnum {
    STATIC(0, "静态升级"),
    DYNAMIC(1, "动态升级");

    private Integer value;
    private String desc;

    /**
     * Get the enum value from an integer.
     *
     * @param value Integer value representing the method.
     * @return An Optional of OtaUpgradeMethodEnum.
     */
    public static Optional<OtaUpgradeMethodEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaUpgradeMethodEnum.values())
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