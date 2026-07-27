package org.springblade.common.condition.enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperatorEnum {
    EQ("=="), NEQ("!="), GT(">"), GTE(">="), LT("<"), LTE("<="),
    CONTAINS("contains"), NOT_CONTAINS("not_contains"),
    IN("in"), NOT_IN("not_in"), BETWEEN("between"),
    IS_NULL("is_null"), IS_NOT_NULL("is_not_null");
    
    private final String operator;
}
