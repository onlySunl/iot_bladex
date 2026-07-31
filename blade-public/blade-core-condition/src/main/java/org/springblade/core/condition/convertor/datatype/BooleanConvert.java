package org.springblade.core.condition.convertor.datatype;

import com.taosdata.jdbc.enums.DataType;
import org.springframework.stereotype.Component;

@Component
public class BooleanConvert implements ValueConvert {
    @Override
    public DataType getTargetDataType() {
        return DataType.BOOL;
    }

    @Override
    public Object convert(String originValue) {
        return Boolean.valueOf(originValue);
    }
}
