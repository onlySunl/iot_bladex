

package org.springblade.modules.iot.dal.mysql.channeltemplate;

import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplatePageReqVO;
import org.springblade.modules.iot.entity.ChannelTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通道模板 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ChannelTemplateMapper extends BaseMapperX<ChannelTemplateDO> {

    default PageResult<ChannelTemplateDO> selectPage(ChannelTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChannelTemplateDO>()
                .likeIfPresent(ChannelTemplateDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ChannelTemplateDO::getChannelConfigId, reqVO.getChannelConfigId())
                .eqIfPresent(ChannelTemplateDO::getContent, reqVO.getContent())
                .betweenIfPresent(ChannelTemplateDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ChannelTemplateDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(ChannelTemplateDO::getId));
    }

    default Long selectCountByConfigId(Long id){
        return selectCount(new LambdaQueryWrapperX<ChannelTemplateDO>()
                .eq(ChannelTemplateDO::getChannelConfigId,id));
    }
}
