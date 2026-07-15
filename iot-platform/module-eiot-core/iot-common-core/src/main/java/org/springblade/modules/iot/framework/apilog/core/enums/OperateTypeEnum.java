package org.springblade.modules.iot.framework.apilog.core.enums;

/**
 * OperateTypeEnum adapter.
 */
public enum OperateTypeEnum {
    GET(1),
    CREATE(2),
    UPDATE(3),
    DELETE(4),
    EXPORT(5),
    IMPORT(6),
    OTHER(0);

    private final int type;

    OperateTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
