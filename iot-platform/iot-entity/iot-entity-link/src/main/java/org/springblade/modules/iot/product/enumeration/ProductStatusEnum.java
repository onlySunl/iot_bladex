package org.springblade.modules.iot.product.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 产品模型状态
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "ProductStatusEnum", description = "产品模型状态类型")
public enum ProductStatusEnum {

    /**
     * 已激活
     */
    ACTIVATED(0, "启用"),

    /**
     * 已锁定
     */
    LOCKED(1, "停用"),

    ;

    private Integer value;
    private String desc;

    public static Optional<ProductStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(ProductStatusEnum.values())
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
