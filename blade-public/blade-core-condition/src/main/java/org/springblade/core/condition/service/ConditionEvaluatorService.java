package org.springblade.core.condition.service;

import org.springblade.core.condition.enumeration.OperatorEnum;
import org.springblade.core.condition.model.dto.BaseConditionDTO;
import org.springblade.core.condition.model.dto.ConditionInfoDTO;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * -----------------------------------------------------------------------------
 * File Name: ConditionEvaluatorService
 * -----------------------------------------------------------------------------
 * Description: 用于评估条件的服务类
 * <p>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2023/12/17       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2023/12/17 00:17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConditionEvaluatorService {


    /**
     * 根据提供的条件信息评估条件是否满足。
     *
     * @param condition 条件信息对象
     * @return 如果条件满足则返回 true，否则返回 false
     */
    private boolean evaluateCondition(ConditionInfoDTO condition) {
        if (condition.getType() == BaseConditionDTO.ConditionExpTypeEnum.GROUP) {
            return evaluateConditionGroup(condition);
        } else {
            return evaluateSingleCondition(condition);
        }
    }

    /**
     * 对条件组进行评估。
     *
     * @param groupCondition 条件组
     * @return 如果条件组满足则返回 true，否则返回 false
     */
    private boolean evaluateConditionGroup(ConditionInfoDTO groupCondition) {
        List<Boolean> results = groupCondition.getConditions().stream()
                .map(this::evaluateCondition)
                .collect(Collectors.toList());

        return groupCondition.getLogicalOperator() == BaseConditionDTO.LogicalOperator.AND
                ? results.stream().allMatch(Boolean::booleanValue)
                : results.stream().anyMatch(Boolean::booleanValue);
    }

    /**
     * 对单个条件进行评估。
     *
     * @param condition 单个条件
     * @return 如果条件满足则返回 true，否则返回 false
     */
    private boolean evaluateSingleCondition(ConditionInfoDTO condition) {
        try {
            Object leftValue = convertValue(condition.getLeftParam().getValue(), condition.getLeftParam().getDataType());
            OperatorEnum operator = OperatorEnum.valueOf(condition.getOperator().getValue().toUpperCase());
            List<Object> rightValues = condition.getRightParams().stream()
                    .map(rp -> convertValue(rp.getValue(), rp.getDataType()))
                    .collect(Collectors.toList());

            return compare(leftValue, operator, rightValues);
        } catch (Exception e) {
            // 可以在这里记录日志或处理特定的异常
            return false;
        }
    }

    /**
     * 根据操作符和数据类型比较左右值。
     *
     * @param leftValue   左值
     * @param operator    操作符
     * @param rightValues 右值列表
     * @return 如果比较满足操作符定义则返回 true，否则返回 false
     */
    public boolean compare(Object leftValue, OperatorEnum operator, List<Object> rightValues) {
        try {
            switch (operator) {
                case EQ:
                    return rightValues.stream().anyMatch(rightValue -> Objects.equals(leftValue, rightValue));
                case NEQ:
                    return rightValues.stream().noneMatch(rightValue -> Objects.equals(leftValue, rightValue));
                case GT:
                    return rightValues.stream().anyMatch(rightValue -> compareGreaterThan(leftValue, rightValue));
                case LT:
                    return rightValues.stream().anyMatch(rightValue -> compareLessThan(leftValue, rightValue));
                case GE:
                    return rightValues.stream().anyMatch(rightValue -> compareGreaterThanOrEqual(leftValue, rightValue));
                case LE:
                    return rightValues.stream().anyMatch(rightValue -> compareLessThanOrEqual(leftValue, rightValue));
                case RLIKE:
                    return rightValues.stream().anyMatch(rightValue -> rightLikeMatch(leftValue, rightValue));
                case LLIKE:
                    return rightValues.stream().anyMatch(rightValue -> leftLikeMatch(leftValue, rightValue));
                case LIKE:
                    return rightValues.stream().anyMatch(rightValue -> fullLikeMatch(leftValue, rightValue));
                case NOTLIKE:
                    return rightValues.stream().noneMatch(rightValue -> fullLikeMatch(leftValue, rightValue));
                case IN:
                    return rightValues.contains(leftValue);
                case NOTIN:
                    return !rightValues.contains(leftValue);
                case NULL:
                    return leftValue == null;
                case NOTNULL:
                    return leftValue != null;
                case ZERO:
                    return compareEqualToZero(leftValue);
                case NOTZERO:
                    return !compareEqualToZero(leftValue);
                case BETWEEN:
                    return compareBetween(leftValue, rightValues);
                case NOTBETWEEN:
                    return !compareBetween(leftValue, rightValues);
                case INCLUDEALL:
                    return rightValues.containsAll((List) leftValue);
                case INCLUDEANY:
                    return ((List) leftValue).stream().anyMatch(rightValues::contains);
                case NOTINCLUDEALL:
                    return !rightValues.containsAll((List) leftValue);
                case NOTINCLUDEANY:
                    return ((List) leftValue).stream().noneMatch(rightValues::contains);
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operator);
            }
        } catch (Exception e) {
            log.error("Error during comparison: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将值转换为相应的数据类型。
     *
     * @param value    原始值
     * @param dataType 数据类型
     * @return 转换后的值
     */
    private Object convertValue(Object value, String dataType) {
        if (value == null) {
            return null;
        }
        try {
            switch (OperatorEnum.DataTypeEnum.valueOf(dataType.toUpperCase())) {
                case STRING:
                    return Objects.toString(value, null);
                case INT:
                    return Integer.parseInt(value.toString());
                case BIGINT:
                    return Long.parseLong(value.toString());
                case DECIMAL:
                    return new BigDecimal(value.toString());
                case DATETIME:
                    return LocalDateTime.parse(value.toString(), DateTimeFormatter.ISO_DATE_TIME);
                case BOOL:
                    return Boolean.parseBoolean(value.toString());
                // ... 添加其他数据类型的转换 ...
                default:
                    throw new IllegalArgumentException("Unsupported data type: " + dataType);
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid value for data type " + dataType + ": " + value, e);
        }
    }

    private boolean compareGreaterThan(Object leftValue, Object rightValue) {
        if (leftValue == null || rightValue == null) {
            return false;
        }
        if (leftValue instanceof Comparable && rightValue instanceof Comparable) {
            return ((Comparable) leftValue).compareTo(rightValue) > 0;
        }
        throw new IllegalArgumentException("Cannot compare non-comparable types or null values");
    }

    private boolean compareLessThan(Object leftValue, Object rightValue) {
        // 实现小于逻辑
        return ((Comparable) leftValue).compareTo(rightValue) < 0;
    }

    private boolean compareGreaterThanOrEqual(Object leftValue, Object rightValue) {
        // 实现大于等于逻辑
        return ((Comparable) leftValue).compareTo(rightValue) >= 0;
    }

    private boolean compareLessThanOrEqual(Object leftValue, Object rightValue) {
        // 实现小于等于逻辑
        return ((Comparable) leftValue).compareTo(rightValue) <= 0;
    }

    private boolean rightLikeMatch(Object leftValue, Object rightValue) {
        // 实现字符串右匹配逻辑
        return leftValue.toString().endsWith(rightValue.toString());
    }

    private boolean leftLikeMatch(Object leftValue, Object rightValue) {
        // 实现字符串左匹配逻辑
        return leftValue.toString().startsWith(rightValue.toString());
    }

    private boolean fullLikeMatch(Object leftValue, Object rightValue) {
        // 实现字符串全匹配逻辑
        return leftValue.toString().contains(rightValue.toString());
    }

    private boolean compareEqualToZero(Object leftValue) {
        if (leftValue == null) {
            return false;
        }
        if (leftValue instanceof Number) {
            return ((Number) leftValue).doubleValue() == 0;
        }
        throw new IllegalArgumentException("Non-numeric value for a numeric comparison operator");
    }

    private boolean compareBetween(Object leftValue, List<Object> rightValues) {
        if (leftValue == null || rightValues.size() < 2) {
            return false;
        }
        if (leftValue instanceof Comparable && rightValues.get(0) instanceof Comparable && rightValues.get(1) instanceof Comparable) {
            return ((Comparable) leftValue).compareTo(rightValues.get(0)) >= 0
                   && ((Comparable) leftValue).compareTo(rightValues.get(1)) <= 0;
        }
        throw new IllegalArgumentException("Cannot compare non-comparable types or null values");
    }


    /**
     * 从左参数 DTO 中获取值。
     *
     * @param leftParam 左参数 DTO
     * @return 左参数的值
     */
    private Object getLeftValue(SingleConditionDTO.LeftParamDTO leftParam) {
        return leftParam.getValue();
    }

}
