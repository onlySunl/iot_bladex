package org.springblade.core.condition.convertor.datatype;

import org.springblade.core.condition.constant.DefaultValueConstant;
import com.taosdata.jdbc.enums.DataType;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

@Component
public class IntegerConvert implements ValueConvert {
    @Override
    public DataType getTargetDataType() {
        return DataType.INT;
    }

    @Override
    public Object convert(String originValue) {
        return NumberUtils.toInt(originValue, DefaultValueConstant.INTEGER);
    }
}
