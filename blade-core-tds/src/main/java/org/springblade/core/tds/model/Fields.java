package org.springblade.core.tds.model;

import org.springblade.core.tds.enumeration.TdDataTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * TD字段信息
 */
@Data
public class Fields implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段值
     */
    private Object fieldValue;

    /**
     * 字段数据类型
     */
    private TdDataTypeEnum dataType;

    /**
     * 字段字节大小
     */
    private Integer size;

    public Fields() {
    }

    public Fields(String fieldName) {
        this.fieldName = fieldName;
    }

    public Fields(String fieldName, TdDataTypeEnum dataType) {
        this.fieldName = fieldName;
        this.dataType = dataType;
    }

    public Fields(String fieldName, TdDataTypeEnum dataType, Integer size) {
        this.fieldName = fieldName;
        this.dataType = dataType;
        this.size = size;
    }

    public Fields(String fieldName, Object fieldValue, TdDataTypeEnum dataType, Integer size) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.dataType = dataType;
        this.size = size;
    }
}
