package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.controller.admin.channel.vo.Channel;
import org.springblade.modules.iot.controller.admin.channel.vo.ChannelPageReqVO;
import org.springblade.modules.iot.controller.admin.channel.vo.ChannelSaveReqVO;
import org.springblade.modules.iot.common.entity.PageResult;

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
    Long createChannel(ChannelSaveReqVO createReqVO);

    /**
     * 更新通道
     *
     * @param updateReqVO 更新信息
     */
    void updateChannel(ChannelSaveReqVO updateReqVO);

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
    List<Channel> getChannelList();

    /**
     * 获得通道分页
     *
     * @param pageReqVO 分页查询
     * @return 通道分页
     */
    PageResult<Channel> getChannelPage(ChannelPageReqVO pageReqVO);
}
