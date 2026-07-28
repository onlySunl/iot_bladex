package org.springblade.core.mp.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 基础 Service 实现类
 *
 * @author Chill
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

}
