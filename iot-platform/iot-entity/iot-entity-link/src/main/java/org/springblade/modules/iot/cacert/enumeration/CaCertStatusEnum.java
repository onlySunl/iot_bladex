package org.springblade.modules.iot.cacert.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 证书状态
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-26
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "CaCertStatusEnum", description = "证书状态枚举")
public enum CaCertStatusEnum {

    /**
     * 待完善
     */
    PENDING(0, "待完善"),

    /**
     * 已颁发
     */
    ISSUED(1, "已颁发"),

    /**
     * 已撤销
     */
    REVOKED(2, "已撤销");

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
    public static Optional<CaCertStatusEnum> fromValue(Integer value) {
        return Stream.of(CaCertStatusEnum.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }
}
