

package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigPageReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigReqVO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 通道配置 Service 接口
 *
 * @author EnjoyIot
 */
public interface ChannelConfigService {

    /**
     * 创建通道配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChannelConfig(@Valid ChannelConfig createReqVO);

    /**
     * 更新通道配置
     *
     * @param updateReqVO 更新信息
     */
    boolean updateChannelConfig(@Valid ChannelConfig updateReqVO);

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
     * 获得通道配置分页
     *
     * @param pageReqVO 分页查询
     * @return 通道配置分页
     */
    PageResult<ChannelConfig> getChannelConfigPage(ChannelConfigPageReqVO pageReqVO);

    List<ChannelConfig> getChannelConfigAll(ChannelConfigReqVO reqVO);
}
