package org.springblade.core.condition.convertor;

import org.springblade.core.condition.convertor.datatype.ValueConvert;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RightParamValueConvertor {

    private final Map<String, ValueConvert> VALUE_CONVERT_MAP = new ConcurrentHashMap<>();


    public RightParamValueConvertor(Map<String, ValueConvert> valueConvertMap) {
        this.VALUE_CONVERT_MAP.putAll(valueConvertMap);
    }

    public Object convert(String dataType, String originValue) {
        ValueConvert convert = VALUE_CONVERT_MAP.get(dataType);
        if (convert == null) {
            return originValue;
        }
        return convert.convert(originValue);
    }
}
