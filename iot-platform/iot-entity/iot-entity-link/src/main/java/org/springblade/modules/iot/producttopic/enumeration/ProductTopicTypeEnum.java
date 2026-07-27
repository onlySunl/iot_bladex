package org.springblade.modules.iot.producttopic.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 产品Topic类型枚举
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "ProductTopicTypeEnum", description = "产品Topic类型")
public enum ProductTopicTypeEnum {

    /**
     * 基础Topic
     */
    BASIC(0, "基础Topic"),

    /**
     * 自定义Topic
     */
    CUSTOM(1, "自定义Topic");

    /**
     * 可选值
     */
    public static final List<Integer> TYPE_COLLECTION = List.of(BASIC.value, CUSTOM.value);

    /**
     * 数值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

    /**
     * 根据数值获取枚举
     */
    public static ProductTopicTypeEnum valueOf(Integer value) {
        return Arrays.stream(values())
                .filter(type -> type.getValue().equals(Optional.ofNullable(value).orElse(-1)))
                .findFirst()
                .orElse(null);
    }

    /**
     * 检查是否为有效类型
     */
    public static boolean isValidType(Integer value) {
        return TYPE_COLLECTION.contains(Optional.ofNullable(value).orElse(-1));
    }

    /**
     * 获取所有有效类型的描述
     */
    public static List<String> getAllValidDesc() {
        return Arrays.stream(values())
                .map(ProductTopicTypeEnum::getDesc)
                .collect(Collectors.toList());
    }

    /**
     * 获取所有有效类型的数值
     */
    public static List<Integer> getAllValidValues() {
        return Arrays.stream(values())
                .map(ProductTopicTypeEnum::getValue)
                .collect(Collectors.toList());
    }

    /**
     * 判断是否为自定义Topic
     */
    public boolean isCustom() {
        return CUSTOM.equals(this);
    }

    /**
     * 判断是否为基础Topic
     */
    public boolean isBasic() {
        return BASIC.equals(this);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
