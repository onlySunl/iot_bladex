
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.dal.mysql;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.dal.dataobject.TaskInfoDO;
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
