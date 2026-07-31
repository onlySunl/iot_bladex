package org.springblade.core.condition.operator;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 条件操作符
 **/
@Getter
@Setter
public class ConditionOperator implements Serializable {

    private static final long serialVersionUID = 3952726168571378969L;

    /**
     * 操作符名称
     */
    private String name;

    /**
     * 操作符值
     */
    private String value;

    /**
     * 操作符描述
     */
    private String desc;

    /**
     * 操作符支持的数据类型列表
     */
    private List<String> supportedDataTypes;

}
