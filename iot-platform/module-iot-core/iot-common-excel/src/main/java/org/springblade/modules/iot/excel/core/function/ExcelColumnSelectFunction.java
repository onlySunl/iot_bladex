package org.springblade.modules.iot.excel.core.function;

import java.util.List;

/**
 * Excel 列下拉选择数据获取函数接口
 */
public interface ExcelColumnSelectFunction {

    /**
     * 获取下拉选择数据
     */
    List<String> getOptions();
}
