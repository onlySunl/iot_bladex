package org.springblade.modules.iot.common.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.entity.SortablePageParam;

/**
 * 在 MyBatis Plus 的 BaseMapper 的基础上拓展，提供更多的能力
 * <p>
 * 1. {@link BaseMapper} 为 MyBatis Plus 的基础接口，提供基础的 CRUD 能力
 * 2. {@link MPJBaseMapper} 为 MyBatis Plus Join 的基础接口，提供连表 Join 能力
 */
public interface BaseMapperX<T> extends BladeMapper<T> {

    default PageResult<T> selectPage(SortablePageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        Query query = new Query();
        query.setCurrent(pageParam.getPageNo());
        query.setSize(pageParam.getPageSize());
        IPage iPage = selectPage(Condition.getPage(query)
                , queryWrapper);
        return new PageResult(iPage.getRecords(), iPage.getTotal());
    }

    default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        Query query = new Query();
        query.setCurrent(pageParam.getPageNo());
        query.setSize(pageParam.getPageSize());
        IPage iPage = selectPage(Condition.getPage(query)
                , queryWrapper);
        return new PageResult(iPage.getRecords(), iPage.getTotal());
    }

}