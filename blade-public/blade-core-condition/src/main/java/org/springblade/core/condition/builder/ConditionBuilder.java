package org.springblade.core.condition.builder;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springblade.core.condition.model.Condition;
import org.springblade.core.condition.model.ConditionItem;
import org.springblade.core.condition.model.Operator;
import org.springblade.core.condition.model.SortItem;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 条件构建器
 *
 * @author Chill
 */
public class ConditionBuilder {

    /**
     * 构建 LambdaQueryWrapper
     *
     * @param condition 条件
     * @param <T>       实体类型
     * @return LambdaQueryWrapper
     */
    public static <T> LambdaQueryWrapper<T> build(Condition condition) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        
        if (condition == null) {
            return wrapper;
        }

        // 构建条件
        for (ConditionItem item : condition.getItems()) {
            applyCondition(wrapper, item);
        }

        // 构建排序
        for (SortItem sort : condition.getSorts()) {
            if (sort.getAsc()) {
                wrapper.orderByAsc(getColumn(sort.getColumn()));
            } else {
                wrapper.orderByDesc(getColumn(sort.getColumn()));
            }
        }

        return wrapper;
    }

    /**
     * 应用条件
     */
    private static <T> void applyCondition(LambdaQueryWrapper<T> wrapper, ConditionItem item) {
        SFunction<T, ?> column = getColumn(item.getColumn());
        Object value = item.getValue();

        switch (item.getOperator()) {
            case EQ:
                wrapper.eq(column, value);
                break;
            case NE:
                wrapper.ne(column, value);
                break;
            case GT:
                wrapper.gt(column, value);
                break;
            case GE:
                wrapper.ge(column, value);
                break;
            case LT:
                wrapper.lt(column, value);
                break;
            case LE:
                wrapper.le(column, value);
                break;
            case LIKE:
                wrapper.like(column, value);
                break;
            case LIKE_LEFT:
                wrapper.likeLeft(column, value);
                break;
            case LIKE_RIGHT:
                wrapper.likeRight(column, value);
                break;
            case IN:
                if (value instanceof List) {
                    wrapper.in(column, (List<?>) value);
                }
                break;
            case NOT_IN:
                if (value instanceof List) {
                    wrapper.notIn(column, (List<?>) value);
                }
                break;
            case BETWEEN:
                if (value instanceof Object[]) {
                    Object[] arr = (Object[]) value;
                    if (arr.length == 2) {
                        wrapper.between(column, arr[0], arr[1]);
                    }
                }
                break;
            case IS_NULL:
                wrapper.isNull(column);
                break;
            case IS_NOT_NULL:
                wrapper.isNotNull(column);
                break;
        }
    }

    /**
     * 获取列（这里简化处理，实际应该使用反射或映射）
     */
    @SuppressWarnings("unchecked")
    private static <T> SFunction<T, ?> getColumn(String column) {
        // 这里需要根据实际情况实现列名到字段的映射
        // 暂时返回 null，实际使用时需要替换
        return null;
    }

}
