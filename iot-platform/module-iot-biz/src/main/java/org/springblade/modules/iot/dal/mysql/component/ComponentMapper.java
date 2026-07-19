

package org.springblade.modules.iot.dal.mysql.component;

import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentPageReqVO;
import org.springblade.modules.iot.entity.ComponentDO;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;

@Mapper
public interface ComponentMapper extends BaseMapperX<ComponentDO> {

    default ComponentDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapperX<ComponentDO>()
                .eq(ComponentDO::getName, name));
    }

    default PageResult<ComponentDO> selectPage(ComponentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ComponentDO>()
                .likeIfPresent(ComponentDO::getName, reqVO.getName())
                .eqIfPresent(ComponentDO::getType, reqVO.getType())
                .eqIfPresent(ComponentDO::getStatus, reqVO.getStatus())
                .orderByDesc(ComponentDO::getId));
    }

}