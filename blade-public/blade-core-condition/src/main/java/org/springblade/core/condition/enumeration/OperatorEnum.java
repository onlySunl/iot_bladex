package org.springblade.core.condition.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springblade.basic.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 条件操作符
 *
 * @author shisen
 **/
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OperatorEnum", description = "OperatorEnum")
@Getter
public enum OperatorEnum {

    EQ("=", "等于", DataTypeEnum.STRING, DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT, DataTypeEnum.VARBINARY),
    NEQ("<>", "不等于", DataTypeEnum.STRING, DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT, DataTypeEnum.VARBINARY),
    GT(">", "大于", DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT),
    LT("<", "小于", DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT),
    GE(">=", "大于等于", DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT),
    LE("<=", "小于等于", DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT),
    RLIKE("Rlike", "右匹配", DataTypeEnum.STRING),
    LLIKE("Llike", "左匹配", DataTypeEnum.STRING),
    LIKE("like", "全匹配", DataTypeEnum.STRING),
    NOTLIKE("notLike", "不匹配", DataTypeEnum.STRING),
    IN("in", "包含", DataTypeEnum.STRING, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY),
    NOTIN("notIn", "不包含", DataTypeEnum.STRING, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY),
    NULL("isNull", "为空", DataTypeEnum.STRING, DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY),
    NOTNULL("notNull", "不为空", DataTypeEnum.STRING, DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY),
    ZERO("=0", "为零", DataTypeEnum.INT, DataTypeEnum.DECIMAL),
    NOTZERO("<>0", "不为零", DataTypeEnum.INT, DataTypeEnum.DECIMAL),
    BETWEEN("between", "介于", DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT),
    NOTBETWEEN("notBetween", "不介于", DataTypeEnum.INT, DataTypeEnum.DECIMAL, DataTypeEnum.DATETIME, DataTypeEnum.BIGINT),
    INCLUDEALL("includeAll", "包含全部", DataTypeEnum.STRING, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY),
    INCLUDEANY("includeAny", "包含其中一个", DataTypeEnum.STRING, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY),
    NOTINCLUDEALL("notIncludeAll", "不包含全部", DataTypeEnum.STRING, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY),
    NOTINCLUDEANY("notIncludeAny", "不包含其中一个", DataTypeEnum.STRING, DataTypeEnum.JSONOBIECT, DataTypeEnum.BIGINT, DataTypeEnum.BOOL, DataTypeEnum.VARBINARY);

    private String value;
    private String desc;
    private List<DataTypeEnum> applicableDataTypes;

    OperatorEnum(String value, String desc, DataTypeEnum... applicableDataTypes) {
        this.value = value;
        this.desc = desc;
        this.applicableDataTypes = Arrays.asList(applicableDataTypes);
    }


    /**
     * 获取全部的操作数据
     *
     * @return
     */
    public static List<OperatorEnum> getAllOperators() {
        OperatorEnum[] values = OperatorEnum.values();
        List<OperatorEnum> list = Arrays.asList(values);
        return list;
    }

    /**
     * 通过值获取枚举
     *
     * @param value
     * @return
     */
    public static OperatorEnum getByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        OperatorEnum[] values = OperatorEnum.values();
        Optional<OperatorEnum> opt = Arrays.stream(values).filter(operatorEnum -> operatorEnum.getValue().equals(value)).findFirst();
        return opt.orElse(null);
    }

    /**
     * 通过名称获取枚举
     *
     * @param name
     * @return
     */
    public static OperatorEnum getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        OperatorEnum[] values = OperatorEnum.values();
        Optional<OperatorEnum> first = Arrays.stream(values).filter(e -> e.name().equals(name)).findFirst();
        return first.orElse(null);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "DataTypeEnum", description = "OperatorEnum DataTypeEnum")
    @Getter
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
         * bigint类型
         */
        BIGINT("bigint", "bigint"),

        /**
         * decimal类型
         */
        DECIMAL("decimal", "decimal"),

        /**
         * DateTime类型
         */
        DATETIME("DateTime", "DateTime"),

        /**
         * BOOL类型
         */
        BOOL("bool", "bool"),


        /**
         * jsonObject类型
         */
        JSONOBIECT("jsonObject", "jsonObject"),

        /**
         * 可变长的二进制数据
         */
        VARBINARY("varBinary", "varBinary"),

        ;

        private String value;
        private String desc;

        public void setValue(String value) {
            this.value = value;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
