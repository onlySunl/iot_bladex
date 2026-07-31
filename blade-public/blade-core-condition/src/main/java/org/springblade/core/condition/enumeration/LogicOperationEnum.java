package org.springblade.core.condition.enumeration;

import com.google.common.collect.Lists;
import org.springblade.basic.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 逻辑符号
 */

public enum LogicOperationEnum {

    /**
     * 等于
     */
    AND(0, "与"),

    /**
     * 不等于
     */
    OR(1, "或");

    /**
     * 操作符值
     */
    private int code;
    /**
     * 显示名称
     */
    private String message;

    LogicOperationEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static List<LogicOperationEnum> getAllOperators() {
        return Lists.newArrayList(LogicOperationEnum.values());
    }

    public static LogicOperationEnum getByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        LogicOperationEnum[] values = LogicOperationEnum.values();
        Optional<LogicOperationEnum> opt = Arrays.stream(values).filter(operatorEnum -> operatorEnum.name().equals(name)).findFirst();
        return opt.orElse(null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

