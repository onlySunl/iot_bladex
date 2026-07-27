package org.springblade.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.CollectionUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean 工具类
 * 补充 BladeX BeanUtil 缺失的方法
 */
public class BeanUtil {

    /**
     * 对象转换
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param <T>         目标类型
     * @return 目标对象
     */
    public static <T> T toBean(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        return org.springblade.core.tool.utils.BeanUtil.toBean(JsonUtil.toMap(source), targetClass);
    }

    /**
     * 对象转换（忽略错误）
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param <T>         目标类型
     * @return 目标对象
     */
    public static <T> T toBeanIgnoreError(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            return org.springblade.core.tool.utils.BeanUtil.toBean(JsonUtil.toMap(source), targetClass);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 列表对象转换
     *
     * @param sourceList  源列表
     * @param targetClass 目标类型
     * @param <T>         目标类型
     * @return 目标列表
     */
    public static <T> List<T> toBeanList(List<?> sourceList, Class<T> targetClass) {
        if (CollectionUtil.isEmpty(sourceList)) {
            return Collections.emptyList();
        }
        return sourceList.stream()
                .map(source -> org.springblade.core.tool.utils.BeanUtil.toBean(JsonUtil.toMap(source), targetClass))
                .collect(Collectors.toList());
    }

    /**
     * 分页对象转换
     *
     * @param sourcePage  源分页对象
     * @param targetClass 目标类型
     * @param <T>         目标类型
     * @return 目标分页对象
     */
    public static <T> IPage<T> toBeanPage(IPage<?> sourcePage, Class<T> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        Page<T> targetPage = new Page<>(sourcePage.getCurrent(), sourcePage.getSize(), sourcePage.getTotal());
        List<T> records = sourcePage.getRecords().stream()
                .map(record -> org.springblade.core.tool.utils.BeanUtil.toBean(JsonUtil.toMap(record), targetClass))
                .collect(Collectors.toList());
        targetPage.setRecords(records);
        return targetPage;
    }

    /**
     * 对象转换（带忽略空值参数）
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param ignoreNull  是否忽略空值
     * @param <T>         目标类型
     * @return 目标对象
     */
    public static <T> T toBean(Object source, Class<T> targetClass, boolean ignoreNull) {
        T target = org.springblade.core.tool.utils.BeanUtil.toBean(JsonUtil.toMap(source), targetClass);
        if (ignoreNull && target != null) {
            // 忽略空值的逻辑可以在这里实现
        }
        return target;
    }
}
