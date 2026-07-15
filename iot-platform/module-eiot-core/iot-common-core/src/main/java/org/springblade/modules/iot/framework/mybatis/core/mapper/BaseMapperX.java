package org.springblade.modules.iot.framework.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springblade.core.boot.mapper.BladeMapper;

import java.util.Collection;
import java.util.List;

/**
 * BaseMapperX adapter - extends BladeMapper with enjoy-iot compatible query methods.
 */
public interface BaseMapperX<T> extends BladeMapper<T> {

    default IPage<T> selectPage(IPage<T> page, LambdaQueryWrapper<T> wrapper) {
        return selectPage(page, wrapper);
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(field, value);
        return selectList(wrapper);
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(field, value);        return selectOne(wrapper);
    }

    default Long selectCount() {
        return selectCount(new Wrapper<T>() {
            @Override
            public String getSqlSegment() {
                return "";
            }
        });
    }

    default List<T> selectBatchIds(Collection<?> ids) {
        return selectBatchIds(ids);
    }
}
