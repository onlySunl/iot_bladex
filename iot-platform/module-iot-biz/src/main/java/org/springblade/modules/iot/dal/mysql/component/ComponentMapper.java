package org.springblade.modules.iot.dal.mysql.component;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.ComponentDO;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentPageReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 组件 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ComponentMapper extends BladeMapper<ComponentDO> {

    ComponentDO selectByName(@Param("name") String name);

    IPage<ComponentDO> selectPage(IPage<ComponentDO> page, @Param("reqVO") ComponentPageReqVO reqVO);

}
