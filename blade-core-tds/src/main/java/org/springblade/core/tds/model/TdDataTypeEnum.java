package org.springblade.core.tds.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TDengine 数据类型枚举
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum TdDataTypeEnum {

    TIMESTAMP("TIMESTAMP", "时间戳"),
    INT("INT", "整型"),
    BIGINT("BIGINT", "长整型"),
    FLOAT("FLOAT", "浮点型"),
    DOUBLE("DOUBLE", "双精度浮点型"),
    BOOL("BOOL", "布尔型"),
    BINARY("BINARY", "二进制"),
    NCHAR("NCHAR", "字符串");

    private final String type;
    private final String description;
}
