package org.springblade.common.utils;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 参数断言工具类
 * 提供常用的参数校验方法
 */
public class ArgumentAssert {

    /**
     * 断言对象不为 null
     *
     * @param object  目标对象
     * @param message 异常消息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言字符串不为空白
     *
     * @param text    目标字符串
     * @param message 异常消息
     */
    public static void notBlank(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言集合不为空
     *
     * @param collection 目标集合
     * @param message    异常消息
     */
    public static void notEmpty(java.util.Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言表达式为 true
     *
     * @param expression 表达式
     * @param message    异常消息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言表达式为 false
     *
     * @param expression 表达式
     * @param message    异常消息
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
