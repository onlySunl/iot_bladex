package org.springblade.core.condition.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 条件项
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionItem {

    /**
     * 列名
     */
    private String column;

    /**
     * 值
     */
    private Object value;

    /**
     * 操作符
     */
    private Operator operator;

}
