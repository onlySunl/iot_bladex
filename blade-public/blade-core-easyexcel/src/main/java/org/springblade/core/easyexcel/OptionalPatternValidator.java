package org.springblade.core.easyexcel;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springblade.core.easyexcel.annotation.OptionalPattern;

import java.util.regex.Pattern;

/**
 * -----------------------------------------------------------------------------
 * File Name: OptionalPatternValidator
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
 * @date 2024/6/20 22:45
 */
public class OptionalPatternValidator implements ConstraintValidator<OptionalPattern, String> {

    private Pattern pattern;

    @Override
    public void initialize(OptionalPattern constraintAnnotation) {
        this.pattern = Pattern.compile(constraintAnnotation.regexp());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // 如果值为空，则认为是有效的
        }
        return pattern.matcher(value).matches(); // 如果值不为空，则必须匹配正则表达式
    }
}
