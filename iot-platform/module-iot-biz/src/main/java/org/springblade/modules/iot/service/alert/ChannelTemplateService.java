package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplate;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplatePageReqVO;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplateSaveReqVO;
import org.springblade.modules.iot.common.entity.PageResult;

/**
 * 通道模板 Service 接口
 */
public interface ChannelTemplateService {

    /**
     * 创建通道模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChannelTemplate(ChannelTemplateSaveReqVO createReqVO);

    /**
     * 更新通道模板
     *
     * @param updateReqVO 更新信息
     */
    void updateChannelTemplate(ChannelTemplateSaveReqVO updateReqVO);

    /**
     * 删除通道模板
     *
     * @param id 编号
     */
    void deleteById(Long id);

    /**
     * 获得通道模板
     *
     * @param id 编号
     * @return 通道模板
     */
    ChannelTemplate getChannelTemplate(Long id);

    /**
     * 获得通道模板分页
     *
     * @param pageReqVO 分页查询
     * @return 通道模板分页
     */
    PageResult<ChannelTemplate> getChannelTemplatePage(ChannelTemplatePageReqVO pageReqVO);
}
