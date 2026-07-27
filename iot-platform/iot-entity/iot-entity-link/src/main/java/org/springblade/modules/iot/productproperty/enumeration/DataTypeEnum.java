package org.springblade.modules.iot.productproperty.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * DataTypeEnum
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DataTypeEnum", description = "DataTypeEnum")
public enum DataTypeEnum {

    /**
     * String类型
     */
    STRING("string", "string"),

    /**
     * int类型
     */
    INT("int", "int"),

    /**
     * decimal类型
     */
    DECIMAL("decimal", "decimal"),

    /**
     * DateTime类型
     */
    DATETIME("DateTime", "DateTime"),

    /**
     * jsonObject类型
     */
    JSONOBIECT("jsonObject", "jsonObject"),

    /**
     * boolean类型
     */
    BOOLEAN("bool", "bool");

    /**
     * 可选值
     */
    public static final List<String> TYPE_COLLECTION = List.of(STRING.value, INT.value, DECIMAL.value, DATETIME.value, JSONOBIECT.value, BOOLEAN.value);
    private String value;
    private String desc;

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
