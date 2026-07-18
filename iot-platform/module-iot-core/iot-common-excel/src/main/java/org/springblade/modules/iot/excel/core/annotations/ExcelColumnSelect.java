package org.springblade.modules.iot.excel.core.annotations;

import java.lang.annotation.*;

/**
 * Excel 列下拉选择注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelColumnSelect {

    /**
     * 下拉选择数据获取函数
     */
    Class<? extends org.springblade.modules.iot.excel.core.function.ExcelColumnSelectFunction> dictFunction()
            default org.springblade.modules.iot.excel.core.function.ExcelColumnSelectFunction.class;

    /**
     * 下拉选择数据，直接从 dictType 中获取
     */
    String dictType() default "";
}
