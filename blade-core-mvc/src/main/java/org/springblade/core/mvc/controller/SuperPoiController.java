package org.springblade.core.mvc.controller;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.core.mvc.service.SuperCacheService;

import java.io.Serializable;

/**
 * SuperController
 * <p>
 * 继承该类，就拥有了如下方法：
 * 1，page 分页查询，并支持子类扩展4个方法：handlerQueryParams、query、handlerWrapper、handlerResult
 * 2，save 保存，并支持子类扩展方法：handlerSave
 * 3，update 修改，并支持子类扩展方法：handlerUpdate
 * 4，delete 删除，并支持子类扩展方法：handlerDelete
 * 5，get 单体查询， 根据ID直接查询DB
 * 5，detail 单体详情查询， 根据ID直接查询DB
 * 6，list 列表查询，根据参数条件，查询列表
 * 7，import 导入，并支持子类扩展方法：handlerImport
 * 8，export 导出，并支持子类扩展3个方法：handlerQueryParams、query、handlerResult
 * 9，preview 导出预览，并支持子类扩展3个方法：handlerQueryParams、query、handlerResult
 * <p>
 * 其中 page、export、preview 的查询条件一致，若子类重写了 handlerQueryParams、query、handlerResult 等任意方法，均衡受到影响
 * <p>
 * 若重写扩展方法无法满足，则可以重写page、save等方法，但切记不要修改 @RequestMapping 参数
 *
 * @param <S>         Service
 * @param <Id>        主键
 * @param <Entity>    实体
 * @param <PageQuery> 分页参数
 * @param <SaveVO>    保存参数
 * @param <UpdateVO>  修改参数
 * @param <ResultVO>  返回实体
 * @author mqttsnet
 * @date 2023年03月06日11:06:46
 */
public abstract class SuperPoiController<S extends SuperCacheService<Id, Entity>,
        Id extends Serializable, Entity extends SuperEntity<Id>, SaveVO, UpdateVO, PageQuery, ResultVO>
        extends SuperController<S, Id, Entity, SaveVO, UpdateVO, PageQuery, ResultVO> {

}
