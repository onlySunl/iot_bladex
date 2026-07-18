package org.springblade.modules.iot.mybatis.core.query;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class MPJLambdaWrapperX<T> extends MPJLambdaWrapper<T> {

    public MPJLambdaWrapperX<T> likeIfPresent(String column, String val) {
        if (StringUtils.hasText(val)) {
            return (MPJLambdaWrapperX<T>) super.like(column, val);
        }
        return this;
    }

    public MPJLambdaWrapperX<T> inIfPresent(String column, Collection<?> values) {
        if (values != null && !values.isEmpty()) {
            return (MPJLambdaWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public MPJLambdaWrapperX<T> eqIfPresent(String column, Object val) {
        if (val != null) {
            return (MPJLambdaWrapperX<T>) super.eq(column, val);
        }
        return this;
    }
}
