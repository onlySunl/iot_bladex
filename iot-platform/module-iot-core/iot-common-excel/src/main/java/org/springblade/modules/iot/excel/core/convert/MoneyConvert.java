package org.springblade.modules.iot.excel.core.convert;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额转换器
 */
public class MoneyConvert implements Converter<Object> {

    @Override
    public Class<?> supportJavaTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (value == null) {
            return new WriteCellData<>("");
        }
        BigDecimal bigDecimal;
        if (value instanceof BigDecimal) {
            bigDecimal = (BigDecimal) value;
        } else {
            bigDecimal = new BigDecimal(value.toString());
        }
        return new WriteCellData<>(bigDecimal.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }
}
