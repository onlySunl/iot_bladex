package org.springblade.modules.iot.gb28181.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.Gb28181PlatformChannel;
import org.springblade.modules.iot.gb28181.mapper.Gb28181PlatformChannelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 国标GB28181通道配置Service
 *
 * @author BladeX
 */
@Service
public class Gb28181PlatformChannelService extends ServiceImpl<Gb28181PlatformChannelMapper, Gb28181PlatformChannel>
    implements IService<Gb28181PlatformChannel> {

    /**
     * 根据平台ID查询通道列表
     */
    public List<Gb28181PlatformChannel> listByPlatformId(Long platformId) {
        return list(new LambdaQueryWrapper<Gb28181PlatformChannel>()
            .eq(Gb28181PlatformChannel::getPlatformId, platformId));
    }

    /**
     * 根据通道ID查询
     */
    public Gb28181PlatformChannel getByChannelId(String channelId) {
        return getOne(new LambdaQueryWrapper<Gb28181PlatformChannel>()
            .eq(Gb28181PlatformChannel::getChannelId, channelId));
    }

    /**
     * 分页查询通道列表
     */
    public IPage<Gb28181PlatformChannel> pageChannels(Page<Gb28181PlatformChannel> page, Gb28181PlatformChannel channel) {
        LambdaQueryWrapper<Gb28181PlatformChannel> wrapper = new LambdaQueryWrapper<>();
        if (channel.getPlatformId() != null) {
            wrapper.eq(Gb28181PlatformChannel::getPlatformId, channel.getPlatformId());
        }
        if (channel.getChannelId() != null) {
            wrapper.like(Gb28181PlatformChannel::getChannelId, channel.getChannelId());
        }
        if (channel.getName() != null) {
            wrapper.like(Gb28181PlatformChannel::getName, channel.getName());
        }
        wrapper.orderByDesc(Gb28181PlatformChannel::getCreateTime);
        return page(page, wrapper);
    }

    /**
     * 删除平台下所有通道
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByPlatformId(Long platformId) {
        return remove(new LambdaQueryWrapper<Gb28181PlatformChannel>()
            .eq(Gb28181PlatformChannel::getPlatformId, platformId));
    }

}
