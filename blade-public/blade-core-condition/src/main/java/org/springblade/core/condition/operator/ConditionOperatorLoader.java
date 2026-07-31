package org.springblade.core.condition.operator;

import com.google.common.collect.Maps;
import org.springblade.core.condition.enumeration.LogicOperationEnum;
import org.springblade.core.condition.enumeration.OperatorEnum;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 条件操作符加载器
 **/
public class ConditionOperatorLoader {

    private static Map<String, ConditionOperator> functionOperatorMap;

    static {
        functionOperatorMap = Maps.newHashMap();
    }

    private ConditionOperatorLoader() {
    }

    /**
     * 获取操作符列表
     */
    public static Collection<ConditionOperator> getFunctionOperators() {
        return functionOperatorMap.values();
    }

    public static List<ConditionOperator> getAllOperators() {
        // 获取所有的操作符
        List<OperatorEnum> allOperators = OperatorEnum.getAllOperators();
        return allOperators.stream().map(opt -> {
            ConditionOperator conditionOperator = new ConditionOperator();
            conditionOperator.setName(opt.name());
            conditionOperator.setValue(opt.getValue());
            conditionOperator.setDesc(opt.getDesc());
            conditionOperator.setSupportedDataTypes(
                    opt.getApplicableDataTypes().stream()
                            .map(OperatorEnum.DataTypeEnum::getValue)
                            .collect(Collectors.toList())
            );
            return conditionOperator;
        }).collect(Collectors.toList());
    }


    public static List<ConditionOperator> getAllOperatorConnect() {
        // 获取所有的连接符
        List<LogicOperationEnum> allOperators = LogicOperationEnum.getAllOperators();
        return allOperators.stream().map(opt -> {
            ConditionOperator conditionOperator = new ConditionOperator();
            conditionOperator.setName(opt.name());
            conditionOperator.setValue(String.valueOf(opt.getCode()));
            conditionOperator.setDesc(opt.getMessage());
            return conditionOperator;
        }).collect(Collectors.toList());
    }


    /**
     * 根据操作符名称如isChange获取操作符
     */
    public static ConditionOperator getOperatorByName(String name) {
        return functionOperatorMap.get(name);
    }


}
