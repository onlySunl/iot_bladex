package org.springblade.modules.iot.service.alert;

import jakarta.validation.Valid;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigSaveReqVO;
import org.springblade.modules.iot.common.entity.PageResult;

import java.util.List;

/**
 * 通道配置 Service 接口
 */
public interface ChannelConfigService {

    /**
     * 创建通道配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChannelConfig(@Valid ChannelConfigSaveReqVO createReqVO);

    /**
     * 更新通道配置
     *
     * @param updateReqVO 更新信息
     */
    void updateChannelConfig(@Valid ChannelConfigSaveReqVO updateReqVO);

    /**
     * 删除通道配置
     *
     * @param id 编号
     */
    void deleteChannelConfig(Long id);

    /**
     * 获得通道配置
     *
     * @param id 编号
     * @return 通道配置
     */
    ChannelConfig getChannelConfig(Long id);

    /**
     * 获得通道配置列表
     *
     * @return 通道配置列表
     */
    List<ChannelConfig> getChannelConfigList();

    /**
     * 获得通道配置分页
     *
     * @param pageReqVO 分页查询
     * @return 通道配置分页
     */
    PageResult<ChannelConfig> getChannelConfigPage(org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigPageReqVO pageReqVO);
}
