package org.springblade.common.database.mybatis.conditions;

import org.springblade.common.database.mybatis.conditions.query.LbQueryWrap;

/**
 * Wrapper 工具类
 * 提供便捷的 Wrapper 创建方法
 */
public final class Wraps {

    private Wraps() {
    }

    /**
     * 创建 LambdaQueryWrapper
     *
     * @param <T> 实体类型
     * @return LbQueryWrap 实例
     */
    public static <T> LbQueryWrap<T> lbQ() {
        return LbQueryWrap.of();
    }

    /**
     * 创建 LambdaQueryWrapper
     *
     * @param entityClass 实体类
     * @param <T>         实体类型
     * @return LbQueryWrap 实例
     */
    public static <T> LbQueryWrap<T> lbQ(Class<T> entityClass) {
        return LbQueryWrap.of(entityClass);
    }

    /**
     * 创建 LambdaQueryWrapper
     *
     * @param entity 实体对象
     * @param <T>    实体类型
     * @return LbQueryWrap 实例
     */
    public static <T> LbQueryWrap<T> lbQ(T entity) {
        return LbQueryWrap.of(entity);
    }
}
