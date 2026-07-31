package org.springblade.core.groovy.annotation;

import org.springblade.core.groovy.annotation.condition.OnExistingPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 存在这个属性时才满足
 *
 * @author mqttsnet 2024/9/18 5:55 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnExistingPropertyCondition.class)
public @interface ConditionalOnExistingProperty {

    /**
     * 属性
     */
    String property();

    /**
     * 属性值
     */
    String value() default "";

}
