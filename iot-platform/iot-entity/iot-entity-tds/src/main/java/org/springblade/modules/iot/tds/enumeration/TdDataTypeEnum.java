package org.springblade.modules.iot.tds.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TDengine Data Type Enum
 * Compatibility class for IoT migration
 */
@Getter
@AllArgsConstructor
public enum TdDataTypeEnum {
    TIMESTAMP("TIMESTAMP"),
    INT("INT"),
    BIGINT("BIGINT"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    BOOL("BOOL"),
    BINARY("BINARY"),
    NCHAR("NCHAR");
    
    private final String type;
}
