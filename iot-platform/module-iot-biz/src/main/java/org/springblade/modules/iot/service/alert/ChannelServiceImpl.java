

package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.controller.admin.alert.vo.ChannelReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.Channel;
import org.springblade.modules.iot.convert.ChannelConvert;
import org.springblade.modules.iot.entity.ChannelDO;
import org.springblade.modules.iot.dal.mysql.ChannelMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 通道配置 Service 接口
 *
 * @author EnjoyIot
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Resource
    private ChannelMapper channelMapper;

    public List<Channel> getChannelList(ChannelReqVO reqVO) {
        LambdaQueryWrapperX<ChannelDO> reqVOX = new LambdaQueryWrapperX<>();

        return ChannelConvert.INSTANCE.convertList(channelMapper.selectList(reqVOX));
    }

}
