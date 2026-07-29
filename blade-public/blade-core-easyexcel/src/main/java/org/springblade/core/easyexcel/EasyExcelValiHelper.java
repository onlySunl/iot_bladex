package org.springblade.core.easyexcel;

import cn.idev.excel.annotation.ExcelProperty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * -----------------------------------------------------------------------------
 * File Name: EasyExcelValiHelper
 * -----------------------------------------------------------------------------
 * Description: Utility class for validating entities with Excel properties.
 * <p>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/19       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/19 19:08
 */
@Slf4j
public class EasyExcelValiHelper {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Validates an entity and returns a map of validation errors.
     *
     * @param obj the entity to validate
     * @param <T> the type of the entity
     * @return a map where the key is the column index and the value is the validation error message
     * @throws NoSuchFieldException if a field specified in the validation error does not exist
     */
    public static <T> Map<Integer, String> validateEntity(T obj) {
        Map<Integer, String> resultMap = new HashMap<>();
        Set<ConstraintViolation<T>> violations = validator.validate(obj, Default.class);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> violation : violations) {
                try {
                    Field declaredField = obj.getClass().getDeclaredField(violation.getPropertyPath().toString());
                    declaredField.setAccessible(true);
                    ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);

                    int index = Optional.ofNullable(annotation)
                            .map(ExcelProperty::index)
                            .filter(idx -> idx != -1)
                            .orElseThrow(() -> new NoSuchFieldException("Field not found or no index specified: " + violation.getPropertyPath().toString()));

                    resultMap.put(index, violation.getMessage());
                } catch (NoSuchFieldException | SecurityException e) {
                    log.error("Validation error on field: {}", violation.getPropertyPath().toString(), e);
                }
            }
        }

        return resultMap.isEmpty() ? null : resultMap;
    }
}
