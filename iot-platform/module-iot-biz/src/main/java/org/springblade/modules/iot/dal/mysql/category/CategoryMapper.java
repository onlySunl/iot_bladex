package org.springblade.modules.iot.dal.mysql.category;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.CategoryDO;

/**
 * 分类 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface CategoryMapper extends BladeMapper<CategoryDO> {

    CategoryDO selectByParentIdAndName(@Param("parentId") Long parentId, @Param("name") String name);

    Long selectCountByParentId(@Param("parentId") Long parentId);

}
