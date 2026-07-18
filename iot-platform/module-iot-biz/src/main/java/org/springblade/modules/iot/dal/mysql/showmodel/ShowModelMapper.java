

package org.springblade.modules.iot.dal.mysql.showmodel;

import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.entity.ShowModelDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品显示模型 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ShowModelMapper extends BaseMapperX<ShowModelDO> {

//    default PageResult<ShowModelDO> selectPage(ShowModelPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<ShowModelDO>()
//                .eqIfPresent(ShowModelDO::getTyp, reqVO.getTyp())
//                .likeIfPresent(ShowModelDO::getName, reqVO.getName())
//                .eqIfPresent(ShowModelDO::getModelCode, reqVO.getModelCode())
//                .eqIfPresent(ShowModelDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(ShowModelDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(ShowModelDO::getId));
//    }

}
