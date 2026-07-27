package org.springblade.modules.iot.ota.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaUpgradeRecordStatusEnum", description = "OTA升级记录状态，字典标识：LINK_OTA_UPGRADE_RECORDS_STATUS")
public enum OtaUpgradeRecordStatusEnum {
    WAITING(0, "待升级"),
    UPGRADING(1, "升级中"),
    SUCCESS(2, "升级成功"),
    FAILURE(3, "升级失败");

    private Integer value;
    private String desc;

    /**
     * Get the enum value from an integer.
     *
     * @param value Integer value representing the status.
     * @return An Optional of OtaUpgradeRecordStatusEnum.
     */
    public static Optional<OtaUpgradeRecordStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaUpgradeRecordStatusEnum.values())
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
