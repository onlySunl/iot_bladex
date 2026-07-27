package org.springblade.common.database.mybatis.conditions.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * Lambda Query Wrapper 扩展
 * 提供链式调用和便捷方法
 *
 * @param <T> 实体类型
 */
public class LbQueryWrap<T> extends LambdaQueryWrapper<T> {

    private static final long serialVersionUID = 1L;

    public LbQueryWrap() {
        super();
    }

    public LbQueryWrap(T entity) {
        super(entity);
    }

    public LbQueryWrap(Class<T> entityClass) {
        super();
        if (entityClass != null) {
            this.setEntityClass(entityClass);
        }
    }

    /**
     * 静态工厂方法
     */
    public static <T> LbQueryWrap<T> of() {
        return new LbQueryWrap<>();
    }

    /**
     * 静态工厂方法
     */
    public static <T> LbQueryWrap<T> of(Class<T> entityClass) {
        return new LbQueryWrap<>(entityClass);
    }

    /**
     * 静态工厂方法
     */
    public static <T> LbQueryWrap<T> of(T entity) {
        return new LbQueryWrap<>(entity);
    }

    // 重写 eq 方法返回 LbQueryWrap 以支持链式调用
    @Override
    public LbQueryWrap<T> eq(SFunction<T, ?> column, Object val) {
        super.eq(column, val);
        return this;
    }

    // 重写 ne 方法
    @Override
    public LbQueryWrap<T> ne(SFunction<T, ?> column, Object val) {
        super.ne(column, val);
        return this;
    }

    // 重写 gt 方法
    @Override
    public LbQueryWrap<T> gt(SFunction<T, ?> column, Object val) {
        super.gt(column, val);
        return this;
    }

    // 重写 ge 方法
    @Override
    public LbQueryWrap<T> ge(SFunction<T, ?> column, Object val) {
        super.ge(column, val);
        return this;
    }

    // 重写 lt 方法
    @Override
    public LbQueryWrap<T> lt(SFunction<T, ?> column, Object val) {
        super.lt(column, val);
        return this;
    }

    // 重写 le 方法
    @Override
    public LbQueryWrap<T> le(SFunction<T, ?> column, Object val) {
        super.le(column, val);
        return this;
    }

    // 重写 like 方法
    @Override
    public LbQueryWrap<T> like(SFunction<T, ?> column, Object val) {
        super.like(column, val);
        return this;
    }

    // 重写 likeLeft 方法
    @Override
    public LbQueryWrap<T> likeLeft(SFunction<T, ?> column, Object val) {
        super.likeLeft(column, val);
        return this;
    }

    // 重写 likeRight 方法
    @Override
    public LbQueryWrap<T> likeRight(SFunction<T, ?> column, Object val) {
        super.likeRight(column, val);
        return this;
    }

    // 重写 in 方法
    @Override
    public LbQueryWrap<T> in(SFunction<T, ?> column, java.util.Collection<?> coll) {
        super.in(column, coll);
        return this;
    }

    // 重写 notIn 方法
    @Override
    public LbQueryWrap<T> notIn(SFunction<T, ?> column, java.util.Collection<?> coll) {
        super.notIn(column, coll);
        return this;
    }

    // 重写 isNull 方法
    @Override
    public LbQueryWrap<T> isNull(SFunction<T, ?> column) {
        super.isNull(column);
        return this;
    }

    // 重写 isNotNull 方法
    @Override
    public LbQueryWrap<T> isNotNull(SFunction<T, ?> column) {
        super.isNotNull(column);
        return this;
    }

    // 重写 orderByAsc 方法
    @Override
    public LbQueryWrap<T> orderByAsc(SFunction<T, ?>... columns) {
        super.orderByAsc(columns);
        return this;
    }

    // 重写 orderByDesc 方法
    @Override
    public LbQueryWrap<T> orderByDesc(SFunction<T, ?>... columns) {
        super.orderByDesc(columns);
        return this;
    }

    // 重写 and 方法
    @Override
    public LbQueryWrap<T> and(java.util.function.Consumer<LambdaQueryWrapper<T>> consumer) {
        super.and(consumer);
        return this;
    }

    // 重写 or 方法
    @Override
    public LbQueryWrap<T> or(java.util.function.Consumer<LambdaQueryWrapper<T>> consumer) {
        super.or(consumer);
        return this;
    }

    // 带条件的 eq
    @Override
    public LbQueryWrap<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        super.eq(condition, column, val);
        return this;
    }

    // 带条件的 in
    @Override
    public LbQueryWrap<T> in(boolean condition, SFunction<T, ?> column, java.util.Collection<?> coll) {
        super.in(condition, column, coll);
        return this;
    }

    // 带条件的 like
    @Override
    public LbQueryWrap<T> like(boolean condition, SFunction<T, ?> column, Object val) {
        super.like(condition, column, val);
        return this;
    }

    // 带条件的 and
    @Override
    public LbQueryWrap<T> and(boolean condition, java.util.function.Consumer<LambdaQueryWrapper<T>> consumer) {
        super.and(condition, consumer);
        return this;
    }
}
