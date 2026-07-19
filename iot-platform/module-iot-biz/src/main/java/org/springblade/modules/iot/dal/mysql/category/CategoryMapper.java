

package org.springblade.modules.iot.dal.mysql.category;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.category.vo.CategoryListReqVO;
import org.springblade.modules.iot.entity.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;

import java.util.List;

/**
 * IOT产品分类 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

    default List<CategoryDO> selectList(CategoryListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getSort, reqVO.getSort())
                .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus())
                //.betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CategoryDO::getIsSys, reqVO.getIsSys())
                .orderByDesc(CategoryDO::getId));
    }

	default CategoryDO selectByParentIdAndName(Long parentId, String name) {
        LambdaQueryWrapper lambdaQueryWrapper =  new LambdaQueryWrapperX<CategoryDO>()
                .eq(CategoryDO::getParentId,parentId)
                .eq(CategoryDO::getName,name);
	    return selectOne(lambdaQueryWrapper);
	}

    default Long selectCountByParentId(Long parentId) {
        LambdaQueryWrapper lambdaQueryWrapper =  new LambdaQueryWrapperX<CategoryDO>()
                .eq(CategoryDO::getParentId,parentId);
        return selectCount(lambdaQueryWrapper);
    }

}
