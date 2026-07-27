package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * OTA升级包签名方式
 * 字典标识：LINK_OTA_PACKAGES_SIGN_METHOD
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/05
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaPackageSignMethodEnum", description = "OTA升级包签名方式，字典标识：LINK_OTA_PACKAGES_SIGN_METHOD")
public enum OtaPackageSignMethodEnum {
    MD5(0, "MD5"),
    SHA256(1, "SHA256");

    private Integer value;
    private String desc;

    public static Optional<OtaPackageSignMethodEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaPackageSignMethodEnum.values())
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
