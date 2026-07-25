package org.springblade.modules.iot.service.alert;

import jakarta.annotation.Resource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.CollectionUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplate;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplatePageReqVO;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplateSaveReqVO;
import org.springblade.modules.iot.convert.ChannelTemplateConvert;
import org.springblade.modules.iot.dal.mysql.alertconfig.AlertConfigMapper;
import org.springblade.modules.iot.dal.mysql.channelconfig.ChannelConfigMapper;
import org.springblade.modules.iot.dal.mysql.channelconfig.ChannelTemplateMapper;
import org.springblade.modules.iot.entity.ChannelConfigDO;
import org.springblade.modules.iot.entity.ChannelTemplateDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通道模板 Service 实现类
 */
@Service
@Validated
public class ChannelTemplateServiceImpl extends BaseServiceImpl<ChannelTemplateMapper, ChannelTemplateDO> implements IChannelTemplateService {

    @Resource
    private ChannelTemplateMapper channelTemplateMapper;

    @Resource
    private AlertConfigMapper alertConfigMapper;

    @Resource
    private IChannelConfigService channelConfigService;

    @Resource
    private ChannelConfigMapper channelConfigMapper;

    @Resource
    private ChannelSmsService ChannelSmsTemplateService;

    @Override
    public Long createChannelTemplate(ChannelTemplateSaveReqVO createReqVO) {
        ChannelTemplateDO channelTemplate = BeanUtils.toBean(createReqVO, ChannelTemplateDO.class);

        // 短信需要单独处理
        ChannelSmsTemplateService.createTemplate(createReqVO, channelTemplate);

        // 插入
        channelTemplateMapper.insert(channelTemplate);
        // 返回
        return channelTemplate.getId();
    }

    @Override
    public void updateChannelTemplate(ChannelTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateChannelTemplateExists(updateReqVO.getId());
        // 更新
        ChannelTemplateDO updateObj = BeanUtils.toBean(updateReqVO, ChannelTemplateDO.class);

        // 短信需要单独处理
        ChannelSmsTemplateService.updateTemplate(updateReqVO, updateObj);

        channelTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteById(Long id) {
        // 校验存在
        validateChannelTemplateExists(id);
        // 检测是否被使用
        if (alertConfigMapper.selectCountByChannelTemplateId(id) > 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CHANNEL_TEMPLATE_USED);
        }

        // 短信需要单独处理
        ChannelTemplateDO channelTemplateDO = channelTemplateMapper.selectById(id);
        ChannelSmsTemplateService.deleteTemplate(channelTemplateDO);

        // 删除
        channelTemplateMapper.deleteById(id);
    }

    @Override
    public ChannelTemplate getChannelTemplate(Long id) {
        return ChannelTemplateConvert.INSTANCE.convert(channelTemplateMapper.selectById(id));
    }

    @Override
    public PageResult<ChannelTemplate> getChannelTemplatePage(ChannelTemplatePageReqVO pageReqVO) {
        PageResult<ChannelTemplate> pageResult = ChannelTemplateConvert.INSTANCE.convertPage(PageResult.from(channelTemplateMapper.selectPage(new Page<ChannelTemplateDO>(pageReqVO.getPageNo(), pageReqVO.getPageSize()), pageReqVO)));
        if (CollectionUtil.isNotEmpty(pageResult.getList())) {
            Set<Long> channelConfigIds = pageResult.getList().stream().map(ChannelTemplate::getChannelConfigId).collect(Collectors.toSet());
            List<ChannelConfigDO> channelConfigDOList = channelConfigMapper.selectByIds(channelConfigIds);
            Map<Long, String> channelConfigCodeMap = channelConfigDOList.stream().collect(Collectors.toMap(ChannelConfigDO::getId, ChannelConfigDO::getCode));
            pageResult.getList().forEach(template -> template.setChannelCode(channelConfigCodeMap.get(template.getChannelConfigId())));
        }
        return pageResult;
    }

    private void validateChannelTemplateExists(Long id) {
        if (channelTemplateMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NOT_EXISTS);
        }
    }
}
