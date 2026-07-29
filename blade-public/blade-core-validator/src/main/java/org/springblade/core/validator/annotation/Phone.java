package org.springblade.core.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springblade.core.validator.annotation.validator.PhoneValidator;

import java.lang.annotation.*;

/**
 * 手机号验证注解
 *
 * @author Chill
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    String message() default "手机号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
