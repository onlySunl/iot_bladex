package org.springblade.core.easyexcel.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springblade.core.easyexcel.OptionalPatternValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * -----------------------------------------------------------------------------
 * File Name: OptionalPattern
 * -----------------------------------------------------------------------------
 * Description:
 * OptionalPatternValidator
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/20       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/20 22:43
 */
@Documented
@Constraint(validatedBy = OptionalPatternValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalPattern {
    String message() default "Invalid format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp();
}
