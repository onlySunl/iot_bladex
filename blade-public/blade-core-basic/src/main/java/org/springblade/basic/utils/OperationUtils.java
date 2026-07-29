package org.springblade.basic.utils;

public class OperationUtils {

    public static <T> T ternaryOperation(boolean condition, T trueValue, T falseValue) {
        return condition ? trueValue : falseValue;
    }


}
