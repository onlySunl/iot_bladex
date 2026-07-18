

package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.framework.common.exception.util.ServiceExceptionUtil;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.common.util.object.BeanUtils;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigPageReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfigReqVO;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplate;
import org.springblade.modules.iot.convert.ChannelConfigConvert;
import org.springblade.modules.iot.entity.ChannelConfigDO;
import org.springblade.modules.iot.dal.mysql.channelconfig.ChannelConfigMapper;
import org.springblade.modules.iot.dal.mysql.channeltemplate.ChannelTemplateMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 通道配置 Service 实现类
 *
 * @author EnjoyIot
 */
@Service
@Validated
public class ChannelConfigServiceImpl implements ChannelConfigService {

    @Resource
    private ChannelConfigMapper channelConfigMapper;

    @Resource
    private ChannelTemplateMapper channelTemplateMapper;

    @Override
    public Long createChannelConfig(ChannelConfig createReqVO) {
        // 插入
        ChannelConfigDO channelConfig = BeanUtils.toBean(createReqVO, ChannelConfigDO.class);
        channelConfigMapper.insert(channelConfig);
        // 返回
        return channelConfig.getId();
    }

    @Override
    public boolean updateChannelConfig(ChannelConfig updateReqVO) {
        // 校验存在
        validateChannelConfigExists(updateReqVO.getId());
        // 更新
        ChannelConfigDO updateObj = BeanUtils.toBean(updateReqVO, ChannelConfigDO.class);
        return channelConfigMapper.updateById(updateObj) > 0;
    }

    @Override
    public void deleteChannelConfig(Long id) {
        // 校验存在
        validateChannelConfigExists(id);
        // 检测通道配置是否已经被使用
        if(channelTemplateMapper.selectCountByConfigId(id)>0){
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CHANNEL_CONFIG_USED);
        }
        // 删除
        channelConfigMapper.deleteById(id);
    }

    @Override
    public ChannelConfig getChannelConfig(Long id) {
        return ChannelConfigConvert.INSTANCE.convert(channelConfigMapper.selectById(id));
    }

    @Override
    public PageResult<ChannelConfig> getChannelConfigPage(ChannelConfigPageReqVO pageReqVO) {
        return ChannelConfigConvert.INSTANCE.convertPage(channelConfigMapper.selectPage(pageReqVO));
    }

    @Override
    public List<ChannelConfig> getChannelConfigAll(ChannelConfigReqVO reqVO) {
        LambdaQueryWrapperX<ChannelConfigDO> q = new LambdaQueryWrapperX<>();
        q.likeIfPresent(ChannelConfigDO::getTitle, reqVO.getTitle());

        return ChannelConfigConvert.INSTANCE.convertList(channelConfigMapper.selectList(q));
    }

    private void validateChannelConfigExists(Long id) {
        if (channelConfigMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CHANNEL_CONFIG_NOT_EXISTS);
        }
    }
}
