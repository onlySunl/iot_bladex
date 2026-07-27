package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * OTA升级目标状态枚举
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/24
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaTargetStatusEnum", description = "OTA升级目标状态")
public enum OtaUpgradeTargetStatusEnum {
    PENDING(0, "待推送"),

    PUSHING(1, "推送中"),

    SUCCESS(2, "推送成功"),

    FAILED(3, "推送失败"),

    ;

    private Integer value;
    private String desc;

    /**
     * 根据数值获取枚举
     */
    public static Optional<OtaUpgradeTargetStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaUpgradeTargetStatusEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    /**
     * 根据数值获取描述
     */
    public static String getDescByValue(Integer value) {
        return fromValue(value)
                .map(OtaUpgradeTargetStatusEnum::getDesc)
                .orElse(null);
    }

    /**
     * 检查数值是否有效
     */
    public static boolean isValid(Integer value) {
        return fromValue(value).isPresent();
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
