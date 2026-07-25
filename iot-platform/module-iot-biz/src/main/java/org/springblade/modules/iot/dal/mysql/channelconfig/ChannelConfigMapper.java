package org.springblade.modules.iot.dal.mysql.channelconfig;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.ChannelConfigDO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigPageReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 通道配置 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ChannelConfigMapper extends BladeMapper<ChannelConfigDO> {

    IPage<ChannelConfigDO> selectPage(IPage<ChannelConfigDO> page, @Param("reqVO") ChannelConfigPageReqVO reqVO);

}
