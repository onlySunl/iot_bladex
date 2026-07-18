package org.springblade.modules.iot.excel.core.convert;

import cn.hutool.core.convert.Convert;
import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import org.springblade.modules.iot.dict.core.DictFrameworkUtils;
import org.springblade.modules.iot.excel.core.annotations.DictFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * Excel 数据字典转换器
 */
@Slf4j
public class DictConvert implements Converter<Object> {

    @Override
    public Class<?> supportJavaTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public Object convertToJavaData(ReadCellData readCellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        String type = getType(contentProperty);
        String label = readCellData.getStringValue();
        String value = DictFrameworkUtils.parseDictDataValue(type, label);
        if (value == null) {
            log.error("[convertToJavaData][type({}) 解析不掉 label({})]", type, label);
            return null;
        }
        Class<?> fieldClazz = contentProperty.getField().getType();
        return Convert.convert(fieldClazz, value);
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object object, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (object == null) {
            return new WriteCellData<>("");
        }
        String type = getType(contentProperty);
        String value = String.valueOf(object);
        String label = DictFrameworkUtils.getDictDataLabel(type, value);
        if (label == null) {
            log.error("[convertToExcelData][type({}) 转换不了 label({})]", type, value);
            return new WriteCellData<>("");
        }
        return new WriteCellData<>(label);
    }

    private static String getType(ExcelContentProperty contentProperty) {
        return contentProperty.getField().getAnnotation(DictFormat.class).value();
    }
}
