package org.springblade.modules.iot.service.alert;

import jakarta.annotation.Resource;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.alert.vo.ChannelReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.Channel;
import org.springblade.modules.iot.convert.ChannelConvert;
import org.springblade.modules.iot.dal.mysql.channelconfig.ChannelMapper;
import org.springblade.modules.iot.entity.ChannelDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.ChannelDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * 通道 Service 实现类
 */
@Service
@Validated
public class ChannelServiceImpl extends BaseServiceImpl<ChannelMapper, ChannelDO> implements IChannelService {

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public Long createChannel(ChannelReqVO createReqVO) {
        // 插入
        ChannelDO channel = BeanUtils.toBean(createReqVO, ChannelDO.class);
        channelMapper.insert(channel);
        // 返回
        return channel.getId();
    }

    @Override
    public void updateChannel(ChannelReqVO updateReqVO) {
        // 校验存在
        validateChannelExists(updateReqVO.getId());
        // 更新
        ChannelDO updateObj = BeanUtils.toBean(updateReqVO, ChannelDO.class);
        channelMapper.updateById(updateObj);
    }

    @Override
    public void deleteChannel(Long id) {
        // 校验存在
        validateChannelExists(id);
        // 删除
        channelMapper.deleteById(id);
    }

    @Override
    public Channel getChannel(Long id) {
        return ChannelConvert.INSTANCE.convert(channelMapper.selectById(id));
    }

    @Override
    public List<Channel> getChannelList(ChannelReqVO reqVO) {
        LambdaQueryWrapper<ChannelDO> reqVOX = new LambdaQueryWrapper<>();

        return ChannelConvert.INSTANCE.convertList(channelMapper.selectList(reqVOX));
    }

    private void validateChannelExists(Long id) {
        if (channelMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NOT_EXISTS);
        }
    }
}
