package org.springblade.core.dinger.abstraction;

import org.springblade.basic.utils.ArgumentAssert;

import java.util.function.Supplier;

public abstract class NoticeAbstract {

    /**
     * 获取并验证属性
     *
     * @param supplier
     * @param errorMsg
     * @return
     */
    public String getRequiredProperty(Supplier<String> supplier, String errorMsg) {
        String value = supplier.get();
        ArgumentAssert.notNull(value, errorMsg);
        return value;
    }

}
