package org.springblade.common.utils;

import org.springframework.beans.BeanUtils;

/**
 * Bean 转换工具类
 */
public class BeanPlusUtil {
    
    /**
     * 对象转换（忽略错误）
     */
    public static <T> T toBeanIgnoreError(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 对象转换
     */
    public static <T> T toBean(Object source, Class<T> targetClass) {
        return toBeanIgnoreError(source, targetClass);
    }
}
