package org.springblade.core.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springblade.core.validator.annotation.validator.EmailValidator;

import java.lang.annotation.*;

/**
 * 邮箱验证注解
 *
 * @author Chill
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    String message() default "邮箱格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
