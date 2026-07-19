package org.springblade.modules.iot.service.alert;

import jakarta.annotation.Resource;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigPageReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigSaveReqVO;
import org.springblade.modules.iot.convert.ChannelConfigConvert;
import org.springblade.modules.iot.entity.ChannelConfigDO;
import org.springblade.modules.iot.dal.mysql.channelconfig.ChannelConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 通道配置 Service 实现类
 */
@Service
@Validated
public class ChannelConfigServiceImpl implements ChannelConfigService {

    @Resource
    private ChannelConfigMapper channelConfigMapper;

    @Override
    public Long createChannelConfig(ChannelConfigSaveReqVO createReqVO) {
        // 插入
        ChannelConfigDO channelConfig = BeanUtils.toBean(createReqVO, ChannelConfigDO.class);
        channelConfigMapper.insert(channelConfig);
        // 返回
        return channelConfig.getId();
    }

    @Override
    public void updateChannelConfig(ChannelConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateChannelConfigExists(updateReqVO.getId());
        // 更新
        ChannelConfigDO updateObj = BeanUtils.toBean(updateReqVO, ChannelConfigDO.class);
        channelConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteChannelConfig(Long id) {
        // 校验存在
        validateChannelConfigExists(id);
        // 删除
        channelConfigMapper.deleteById(id);
    }

    @Override
    public ChannelConfig getChannelConfig(Long id) {
        return ChannelConfigConvert.INSTANCE.convert(channelConfigMapper.selectById(id));
    }

    @Override
    public List<ChannelConfig> getChannelConfigList() {
        return ChannelConfigConvert.INSTANCE.convertList(channelConfigMapper.selectList());
    }

    @Override
    public PageResult<ChannelConfig> getChannelConfigPage(ChannelConfigPageReqVO pageReqVO) {
        return ChannelConfigConvert.INSTANCE.convertPage(channelConfigMapper.selectPage(pageReqVO));
    }

    private void validateChannelConfigExists(Long id) {
        if (channelConfigMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NOT_EXISTS);
        }
    }
}
