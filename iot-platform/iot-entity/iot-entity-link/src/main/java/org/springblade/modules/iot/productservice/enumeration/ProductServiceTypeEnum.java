
package org.springblade.modules.iot.productservice.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 产品模型服务类型
 * </p>
 *
 * @author mqttsnet
 * @date 2025-12-14
 */
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "ProductServiceTypeEnum", description = "产品模型服务类型")
public enum ProductServiceTypeEnum {

    /**
     * 产品服务
     */
    PRODUCT_SERVICE("0", "产品服务"),

    /**
     * 模版服务
     */
    TEMPLATE_SERVICE("1", "模版服务"),

    ;

    private String value;
    private String desc;

    public static Optional<ProductServiceTypeEnum> fromValue(String value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(ProductServiceTypeEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
