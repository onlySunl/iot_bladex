package org.springblade.modules.iot.service.alert;

import jakarta.annotation.Resource;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.channel.vo.Channel;
import org.springblade.modules.iot.controller.admin.channel.vo.ChannelPageReqVO;
import org.springblade.modules.iot.controller.admin.channel.vo.ChannelSaveReqVO;
import org.springblade.modules.iot.convert.ChannelConvert;
import org.springblade.modules.iot.entity.ChannelDO;
import org.springblade.modules.iot.dal.mysql.ChannelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 通道 Service 实现类
 */
@Service
@Validated
public class ChannelServiceImpl implements ChannelService {

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public Long createChannel(ChannelSaveReqVO createReqVO) {
        // 插入
        ChannelDO channel = BeanUtils.toBean(createReqVO, ChannelDO.class);
        channelMapper.insert(channel);
        // 返回
        return channel.getId();
    }

    @Override
    public void updateChannel(ChannelSaveReqVO updateReqVO) {
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
    public List<Channel> getChannelList() {
        return ChannelConvert.INSTANCE.convertList(channelMapper.selectList());
    }

    @Override
    public PageResult<Channel> getChannelPage(ChannelPageReqVO pageReqVO) {
        return ChannelConvert.INSTANCE.convertPage(channelMapper.selectPage(pageReqVO));
    }

    private void validateChannelExists(Long id) {
        if (channelMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NOT_EXISTS);
        }
    }
}
