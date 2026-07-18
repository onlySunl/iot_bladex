

package org.springblade.modules.iot.dal.mysql.alertconfig;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.core.mp.mapper.BladeMapper;

import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.entity.AlertConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报警配置 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface AlertConfigMapper extends BladeMapper<AlertConfigDO> {

    default PageResult<AlertConfigDO> selectPage(AlertConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlertConfigDO>()
                .likeIfPresent(AlertConfigDO::getName, reqVO.getName())
                .eqIfPresent(AlertConfigDO::getRuleInfoId, reqVO.getRuleInfoId())
                .eqIfPresent(AlertConfigDO::getLevel, reqVO.getLevel())
                .eqIfPresent(AlertConfigDO::getStatus, reqVO.getStatus())
                .orderByDesc(AlertConfigDO::getId));
    }

    default Long selectCountByChannelTemplateId(Long templateId){
        return selectCount(new LambdaQueryWrapperX<AlertConfigDO>()
                .eq(AlertConfigDO::getMessageTemplateId, templateId));
    };
}
