package org.springblade.core.condition.enumeration;

import java.io.Serializable;


/**
 * 参数类型
 **/
public enum ParamTypeEnum implements Serializable {

    /**
     * 常量(直接输入，或从选人、组织机构、枚举组件内选择常量-属于确定的值)
     */
    CONSTANT("CONSTANT", "常量"),

    /**
     * 变量(从可选值范围内选择的或系统提供的可选变量-属于设计态不确定的值)
     */
    CONTEXT_VARIABLE("CONTEXT_VARIABLE", "上下文变量"),

    /**
     * 系统变量
     */
    SYSTEM_VARIABLE("SYSTEM_VARIABLE", "系统变量"),

    /**
     * 表达式
     */
    EXPRESSION("EXPRESSION", "表达式");

    private String code;

    private String message;

    ParamTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
