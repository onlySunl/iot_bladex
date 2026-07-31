package org.springblade.core.condition.convertor.datatype;

import com.taosdata.jdbc.enums.DataType;

/**
 * @author cheny
 * @date 2023/5/6 10:17
 */
public interface ValueConvert {
    /**
     * 获取目标数据类型
     *
     * @return 目标数据类型
     */
    DataType getTargetDataType();

    /**
     * 转换原始右值
     *
     */
    Object convert(String originValue);
}
