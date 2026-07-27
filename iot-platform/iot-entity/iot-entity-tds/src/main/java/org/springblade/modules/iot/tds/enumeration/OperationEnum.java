package org.springblade.modules.iot.tds.enumeration;


import org.apache.commons.lang3.StringUtils;

/**
 * 产品服务、属性操作类型枚举
 */
public enum OperationEnum {
    ADD("ADD"), UPDATE("UPDATE"), DELETE("DELETE");

    private final String name;

    OperationEnum(String name) {
        this.name = name;
    }

    public static OperationEnum getOperationEnum(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        switch (name.toUpperCase()) {
            case "ADD":
                return ADD;
            case "UPDATE":
                return UPDATE;
            case "DELETE":
                return DELETE;
            default:
                return null;
        }
    }

}
