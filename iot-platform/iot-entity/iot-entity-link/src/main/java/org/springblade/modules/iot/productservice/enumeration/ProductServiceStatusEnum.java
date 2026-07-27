package org.springblade.modules.iot.productservice.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 产品模型服务状态
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "ProductServiceStatusEnum", description = "产品模型服务状态类型")
public enum ProductServiceStatusEnum {

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

    public static Optional<ProductServiceStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(ProductServiceStatusEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
