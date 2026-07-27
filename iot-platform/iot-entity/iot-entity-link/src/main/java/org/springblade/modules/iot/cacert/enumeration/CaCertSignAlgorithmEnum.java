package org.springblade.modules.iot.cacert.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 签名算法枚举
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/7/1
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "CaCertAlgorithmEnum", description = "证书签名算法枚举")
public enum CaCertSignAlgorithmEnum {
    /**
     * SHA256withRSA
     */
    SHA256withRSA(0, "SHA256withRSA"),
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
    public static Optional<CaCertSignAlgorithmEnum> fromValue(Integer value) {
        return Stream.of(CaCertSignAlgorithmEnum.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }

    public static Optional<CaCertSignAlgorithmEnum> fromDesc(String algorithm) {
        return Stream.of(values())
                .filter(e -> e.getDesc().equalsIgnoreCase(algorithm))
                .findFirst();
    }

}
