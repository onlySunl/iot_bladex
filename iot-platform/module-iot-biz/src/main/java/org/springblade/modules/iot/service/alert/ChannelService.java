package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.alert.vo.ChannelReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.Channel;

import java.util.List;

/**
 * 通道 Service 接口
 */
public interface ChannelService {

    /**
     * 创建通道
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChannel(ChannelReqVO createReqVO);

    /**
     * 更新通道
     *
     * @param updateReqVO 更新信息
     */
    void updateChannel(ChannelReqVO updateReqVO);

    /**
     * 删除通道
     *
     * @param id 编号
     */
    void deleteChannel(Long id);

    /**
     * 获得通道
     *
     * @param id 编号
     * @return 通道
     */
    Channel getChannel(Long id);

    /**
     * 获得通道列表
     *
     * @return 通道列表
     */
    List<Channel> getChannelList(ChannelReqVO reqVO);


}
