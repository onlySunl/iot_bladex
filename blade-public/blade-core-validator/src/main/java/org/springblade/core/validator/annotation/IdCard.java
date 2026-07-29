package org.springblade.core.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springblade.core.validator.annotation.validator.IdCardValidator;

import java.lang.annotation.*;

/**
 * 身份证号验证注解
 *
 * @author Chill
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IdCardValidator.class)
public @interface IdCard {

    String message() default "身份证号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
