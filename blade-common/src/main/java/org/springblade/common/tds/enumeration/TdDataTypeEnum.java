package org.springblade.common.tds.enumeration;

/**
 * TDengine 数据类型枚举
 */
public enum TdDataTypeEnum {
    TIMESTAMP("TIMESTAMP"),
    INT("INT"),
    BIGINT("BIGINT"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    BINARY("BINARY"),
    NCHAR("NCHAR"),
    BOOL("BOOL");
    
    private final String type;
    
    TdDataTypeEnum(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}
