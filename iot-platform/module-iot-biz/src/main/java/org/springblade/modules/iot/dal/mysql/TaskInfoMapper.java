

package org.springblade.modules.iot.dal.mysql;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.entity.TaskInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * iot任务 Mapper
 *
 * @author EnjoyIotEnjoyIot
 */
@Mapper
public interface TaskInfoMapper extends BaseMapperX<TaskInfoDO> {

    default PageResult<TaskInfoDO> selectPage(TaskInfoPageReq reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskInfoDO>()
                .likeIfPresent(TaskInfoDO::getName, reqVO.getName())
                .eqIfPresent(TaskInfoDO::getState, reqVO.getState())
                .eqIfPresent(TaskInfoDO::getType, reqVO.getType())
                .orderByDesc(TaskInfoDO::getId));
    }

}
