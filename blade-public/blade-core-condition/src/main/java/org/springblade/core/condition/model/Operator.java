package org.springblade.core.condition.model;

/**
 * 操作符枚举
 *
 * @author Chill
 */
public enum Operator {

    /**
     * 等于
     */
    EQ,

    /**
     * 不等于
     */
    NE,

    /**
     * 大于
     */
    GT,

    /**
     * 大于等于
     */
    GE,

    /**
     * 小于
     */
    LT,

    /**
     * 小于等于
     */
    LE,

    /**
     * 模糊匹配
     */
    LIKE,

    /**
     * 左模糊匹配
     */
    LIKE_LEFT,

    /**
     * 右模糊匹配
     */
    LIKE_RIGHT,

    /**
     * IN
     */
    IN,

    /**
     * NOT IN
     */
    NOT_IN,

    /**
     * BETWEEN
     */
    BETWEEN,

    /**
     * IS NULL
     */
    IS_NULL,

    /**
     * IS NOT NULL
     */
    IS_NOT_NULL

}
