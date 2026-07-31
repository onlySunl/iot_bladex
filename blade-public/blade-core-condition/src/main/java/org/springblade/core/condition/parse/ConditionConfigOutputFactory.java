package org.springblade.core.condition.parse;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 条件配置输出工厂
 **/
@Component
public class ConditionConfigOutputFactory {

    private Map<String, ConditionConfigOutput> outputImpls = new ConcurrentHashMap<>();

    public ConditionConfigOutputFactory() {
        outputImpls.put(WrapperConditionConfigOutput.class.getName(), new WrapperConditionConfigOutput());
    }

    public <T extends ConditionConfigOutput> T getOutputImpl(Class<T> clazz) {
        return (T) outputImpls.get(clazz.getName());
    }
}
