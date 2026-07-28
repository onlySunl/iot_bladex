package org.springblade.core.database.mybatis;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 基础 Service 实现类
 *
 * @author Chill
 */
public class BladeServiceImpl<M extends BladeMapper<T>, T> extends ServiceImpl<M, T> implements BladeService<T> {

}
