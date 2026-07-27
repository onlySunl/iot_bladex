package org.springblade.core.mp.ext;

import org.springblade.core.mp.base.BaseService;
import org.springblade.core.mp.base.BaseEntity;

/**
 * 扩展 BaseService，增加 saveBefore/updateBefore 等钩子方法。
 * 参照 thinglinks SuperService 设计。
 *
 * @param <T> 实体类型
 * @author EnjoyIot
 */
public interface ExtBaseService<T extends BaseEntity> extends BaseService<T> {

	/**
	 * 保存前的钩子方法，子类可覆盖实现：
	 * 1. 业务校验（如字段唯一性检查）
	 * 2. 字段填充（如默认值设置）
	 * 3. VO → Entity 转换
	 *
	 * @param entity 待保存的实体
	 * @return
	 */
	<SaveVO> T saveBefore(T entity);

	/**
     * 保存后的钩子方法，子类可覆盖实现：
     * 1. 缓存刷新
     * 2. 事件发布
     * 3. 关联数据保存
     *
     * @param entity 已保存的实体
     * @return
     */
	<SaveVO> T saveAfter(T entity);

	/**
	 * 更新前的钩子方法，子类可覆盖实现：
	 * 1. 业务校验
	 * 2. 字段填充
	 * 3. 数据存在性检查
	 *
	 * @param entity 待更新的实体
	 */
	<UpdateVO> T updateBefore(T entity);

	/**
	 * 更新后的钩子方法，子类可覆盖实现：
	 * 1. 缓存刷新
	 * 2. 事件发布
	 * 3. 关联数据更新
	 *
	 * @param entity 已更新的实体
	 */
	<UpdateVO> T updateAfter(T entity);

}
