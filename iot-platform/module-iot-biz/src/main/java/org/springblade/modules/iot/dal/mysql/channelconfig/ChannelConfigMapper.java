

package org.springblade.modules.iot.dal.mysql.channelconfig;

import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigPageReqVO;
import org.springblade.modules.iot.entity.ChannelConfigDO;

/**
 * 通道配置 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ChannelConfigMapper extends BaseMapperX<ChannelConfigDO> {

    default PageResult<ChannelConfigDO> selectPage(ChannelConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChannelConfigDO>()
                .likeIfPresent(ChannelConfigDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ChannelConfigDO::getCode, reqVO.getCode())
                .betweenIfPresent(ChannelConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ChannelConfigDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(ChannelConfigDO::getId));
    }

}
