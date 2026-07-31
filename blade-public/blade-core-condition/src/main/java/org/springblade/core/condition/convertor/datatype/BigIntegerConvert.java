package org.springblade.core.condition.convertor.datatype;

import org.springblade.core.condition.constant.DefaultValueConstant;
import com.taosdata.jdbc.enums.DataType;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;


@Component
public class BigIntegerConvert implements ValueConvert {
    @Override
    public DataType getTargetDataType() {
        return DataType.BIGINT;
    }

    @Override
    public Object convert(String originValue) {
        return NumberUtils.toLong(originValue, DefaultValueConstant.LONG);
    }
}
