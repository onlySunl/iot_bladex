package org.springblade.core.validator.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springblade.core.validator.annotation.Email;

import java.util.regex.Pattern;

/**
 * 邮箱验证器
 *
 * @author Chill
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return EMAIL_PATTERN.matcher(value).matches();
    }

}
