package org.springblade.modules.iot.framework.excel.core.util;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ExcelUtils adapter - placeholder for Excel export functionality.
 */
public class ExcelUtils {
    public static <T> void write(HttpServletResponse response, String fileName, List<T> list, Class<T> clazz) {
        // TODO: Implement with BladeX Excel util
        throw new UnsupportedOperationException("Excel export not yet implemented");
    }
}
