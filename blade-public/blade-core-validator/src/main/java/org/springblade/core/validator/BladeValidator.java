package org.springblade.core.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springblade.core.basic.utils.Func;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * BladeX 数据验证器
 *
 * @author Chill
 */
@Component
public class BladeValidator {

    private final Validator validator;

    public BladeValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * 验证对象
     *
     * @param obj 待验证对象
     * @param groups 验证组
     * @throws ConstraintViolationException 验证失败时抛出
     */
    public void validate(Object obj, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(obj, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    /**
     * 验证对象属性
     *
     * @param obj 对象
     * @param propertyName 属性名
     * @param groups 验证组
     * @throws ConstraintViolationException 验证失败时抛出
     */
    public void validateProperty(Object obj, String propertyName, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validateProperty(obj, propertyName, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    /**
     * 验证对象值
     *
     * @param beanType Bean 类型
     * @param propertyName 属性名
     * @param value 值
     * @param groups 验证组
     * @throws ConstraintViolationException 验证失败时抛出
     */
    public <T> void validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validator.validateValue(beanType, propertyName, value, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    /**
     * 获取第一个验证错误消息
     *
     * @param obj 待验证对象
     * @param groups 验证组
     * @return 错误消息，验证通过返回 null
     */
    public String getFirstMessage(Object obj, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(obj, groups);
        if (violations.isEmpty()) {
            return null;
        }
        return violations.iterator().next().getMessage();
    }

}
