package org.springblade.core.condition.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件
 *
 * @author Chill
 */
@Data
public class Condition {

    /**
     * 条件列表
     */
    private List<ConditionItem> items = new ArrayList<>();

    /**
     * 排序列表
     */
    private List<SortItem> sorts = new ArrayList<>();

    /**
     * 添加等于条件
     */
    public Condition eq(String column, Object value) {
        items.add(new ConditionItem(column, value, Operator.EQ));
        return this;
    }

    /**
     * 添加不等于条件
     */
    public Condition ne(String column, Object value) {
        items.add(new ConditionItem(column, value, Operator.NE));
        return this;
    }

    /**
     * 添加大于条件
     */
    public Condition gt(String column, Object value) {
        items.add(new ConditionItem(column, value, Operator.GT));
        return this;
    }

    /**
     * 添加大于等于条件
     */
    public Condition ge(String column, Object value) {
        items.add(new ConditionItem(column, value, Operator.GE));
        return this;
    }

    /**
     * 添加小于条件
     */
    public Condition lt(String column, Object value) {
        items.add(new ConditionItem(column, value, Operator.LT));
        return this;
    }

    /**
     * 添加小于等于条件
     */
    public Condition le(String column, Object value) {
        items.add(new ConditionItem(column, value, Operator.LE));
        return this;
    }

    /**
     * 添加LIKE条件
     */
    public Condition like(String column, String value) {
        items.add(new ConditionItem(column, value, Operator.LIKE));
        return this;
    }

    /**
     * 添加左LIKE条件
     */
    public Condition likeLeft(String column, String value) {
        items.add(new ConditionItem(column, value, Operator.LIKE_LEFT));
        return this;
    }

    /**
     * 添加右LIKE条件
     */
    public Condition likeRight(String column, String value) {
        items.add(new ConditionItem(column, value, Operator.LIKE_RIGHT));
        return this;
    }

    /**
     * 添加IN条件
     */
    public Condition in(String column, List<?> values) {
        items.add(new ConditionItem(column, values, Operator.IN));
        return this;
    }

    /**
     * 添加NOT IN条件
     */
    public Condition notIn(String column, List<?> values) {
        items.add(new ConditionItem(column, values, Operator.NOT_IN));
        return this;
    }

    /**
     * 添加BETWEEN条件
     */
    public Condition between(String column, Object start, Object end) {
        items.add(new ConditionItem(column, new Object[]{start, end}, Operator.BETWEEN));
        return this;
    }

    /**
     * 添加IS NULL条件
     */
    public Condition isNull(String column) {
        items.add(new ConditionItem(column, null, Operator.IS_NULL));
        return this;
    }

    /**
     * 添加IS NOT NULL条件
     */
    public Condition isNotNull(String column) {
        items.add(new ConditionItem(column, null, Operator.IS_NOT_NULL));
        return this;
    }

    /**
     * 添加升序排序
     */
    public Condition orderByAsc(String column) {
        sorts.add(new SortItem(column, true));
        return this;
    }

    /**
     * 添加降序排序
     */
    public Condition orderByDesc(String column) {
        sorts.add(new SortItem(column, false));
        return this;
    }

    /**
     * 创建空条件
     */
    public static Condition create() {
        return new Condition();
    }

}
