package org.springblade.core.annotation.constraints;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 表单字段验证：要求纯文本格式
 * <p>
 * 跟其他验证注解的区别在于：传递过来的参数为null或者""时，不会进行加密格式验证
 * 只有在字段有值时才会验证是否为加密格式
 *
 * @author mqttsnet
 * @date 2025/11/07
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(PlainTextRequired.List.class)
@Documented
@Constraint(validatedBy = {})
public @interface PlainTextRequired {

    /**
     * @return the error message template
     */
    String message() default "必须是纯文本格式，不能包含加密数据";

    /**
     * @return the groups the constraint belongs to
     */
    Class<?>[] groups() default {};

    /**
     * @return the payload associated to the constraint
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * @return 是否允许空值，默认允许（null或空字符串不验证）
     */
    boolean allowEmpty() default true;

    /**
     * @return 字段名称（用于错误消息中显示）
     */
    String fieldName() default "";

    /**
     * Defines several {@link PlainTextRequired} annotations on the same element.
     *
     * @see PlainTextRequired
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PlainTextRequired[] value();
    }
}