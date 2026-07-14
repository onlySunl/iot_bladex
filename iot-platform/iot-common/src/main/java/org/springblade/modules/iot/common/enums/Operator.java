package org.springblade.modules.iot.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum Operator {

    eq("=="),
    not("!="),
    gt(">"),
    lt("<"),
    gte(">="),
    lte("<="),
    like("like");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    /**
     * 数据库存储：枚举name() eq/gt，mybatis‑plus依靠通用TypeHandler
     * 前端返回展示符号，例如 == > =
     */
    @JsonValue
    public String getSymbol() {
        return symbol;
    }

    /**
     * 将传入字符串值转为对应类型，后续可以根据业务改写
     * @param value 前端传的字符串
     * @return 转换后对象
     */
    public Object convert(String value) {
        return value;
    }

    /**
     * 根据符号获取枚举
     */
    public static Operator getBySymbol(String symbol) {
        return Arrays.stream(Operator.values())
                .filter(item -> item.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("不支持的运算符：" + symbol));
    }

    /**
     * 根据枚举名称获取枚举（数据库查询使用）
     */
    public static Operator getByName(String name) {
        return Operator.valueOf(name);
    }
}