package org.springblade.modules.iot.product.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 产品类型
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "ProductTypeEnum", description = "产品类型")
public enum ProductTypeEnum {

    /**
     * 普通产品，需直连设备
     */
    COMMON(1, "common"),

    /**
     * 网关产品，可挂载子设备
     */
    GATEWAY(2, "gateway"),

    /**
     * 未知产品
     */
    UNKNOWN(0, "unknown"),

    ;

    /**
     * 可选值
     */
    public static final List<Integer> TYPE_COLLECTION = List.of(COMMON.value, GATEWAY.value, UNKNOWN.value);
    private Integer value;
    private String desc;

    public static ProductTypeEnum valueOf(Integer value) {
        return Arrays.stream(values())
                .filter(type -> type.getValue().equals(Optional.ofNullable(value).orElse(-1)))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
