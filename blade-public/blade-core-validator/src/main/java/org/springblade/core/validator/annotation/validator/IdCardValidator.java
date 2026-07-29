package org.springblade.core.validator.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springblade.core.validator.annotation.IdCard;

import java.util.regex.Pattern;

/**
 * 身份证号验证器
 *
 * @author Chill
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    private static final Pattern ID_CARD_PATTERN = Pattern.compile("^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[\\dXx]$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        if (value.length() != 18) {
            return false;
        }
        return ID_CARD_PATTERN.matcher(value).matches();
    }

}
