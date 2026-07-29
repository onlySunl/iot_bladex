package org.springblade.core.validator.constraintvalidators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.secure.EncryptDecryptUtils;
import org.springblade.basic.utils.StringUtils;
import org.springblade.core.annotation.constraints.PlainTextRequired;

/**
 * Description:
 * PlainTextRequired 验证器实现
 * 只有在字段有值时才会进行加密格式验证
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/07
 */
@Slf4j
public class PlainTextRequiredConstraintValidator implements ConstraintValidator<PlainTextRequired, String> {

    private boolean allowEmpty;
    private String fieldName;

    @Override
    public void initialize(PlainTextRequired constraintAnnotation) {
        this.allowEmpty = constraintAnnotation.allowEmpty();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果值为null，直接通过
        if (value == null) {
            return true;
        }

        // 如果允许空值且值为空字符串，直接通过
        if (allowEmpty && !StringUtils.hasText(value)) {
            return true;
        }

        // 只有有值时才进行加密格式验证
        boolean isEncrypted = EncryptDecryptUtils.isEncrypted(value);

        if (isEncrypted) {
            // 构建自定义错误消息
            String errorMessage = buildErrorMessage();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();

            log.warn("检测到加密格式输入 - 字段: {}, 输入: {}", StringUtils.hasText(fieldName) ? fieldName : "未知字段", maskSensitiveData(value));
            return false;
        }

        return true;
    }

    /**
     * 构建错误消息
     */
    private String buildErrorMessage() {
        if (StringUtils.hasText(fieldName)) {
            return fieldName + "必须是纯文本格式，不能包含加密数据";
        }
        return "必须是纯文本格式，不能包含加密数据";
    }

    /**
     * 敏感数据脱敏
     */
    private String maskSensitiveData(String data) {
        if (data == null || data.length() <= 4) {
            return "****";
        }
        return data.substring(0, 2) + "****" + data.substring(data.length() - 2);
    }
}
