package org.springblade.core.mp.ext;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.mp.base.BaseServiceImpl;

/**
 * 扩展 BaseServiceImpl，在 save/update 前后调用钩子方法。
 * 参照 thinglinks SuperServiceImpl 设计：
 * - saveBefore(entity)：保存前校验/填充
 * - saveAfter(entity)：保存后回调（缓存刷新、事件发布等）
 * - updateBefore(entity)：更新前校验/填充
 * - updateAfter(entity)：更新后回调
 *
 * @param <M> Mapper 类型
 * @param <T> 实体类型
 * @author EnjoyIot
 */
public class ExtBaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity>
        extends BaseServiceImpl<M, T> implements ExtBaseService<T> {


    @Override
    public <SaveVO> T saveBefore(T entity) {
        return BeanUtil.toBean(entity, getEntityClass());
    }

    @Override
    public <SaveVO> T saveAfter(T entity) {
        return BeanUtil.toBean(entity, getEntityClass());
    }

    @Override
    public <UpdateVO> T updateBefore(T entity) {
        return BeanUtil.toBean(entity, getEntityClass());
    }

    @Override
    public <UpdateVO> T updateAfter(T entity) {
        return BeanUtil.toBean(entity, getEntityClass());
    }
}
