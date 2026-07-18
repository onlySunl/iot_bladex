

package org.springblade.modules.iot.api.alert;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplate;
import org.springblade.modules.iot.service.alert.AlertConfigService;
import org.springblade.modules.iot.service.alert.ChannelConfigService;
import org.springblade.modules.iot.service.alert.ChannelTemplateService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class AlertApiImpl implements AlertApi {

    @Resource
    private AlertConfigService alertConfigService;

    @Resource
    private ChannelTemplateService channelTemplateService;

    @Resource
    private ChannelConfigService channelConfigService;

    @Override
    public PageResult<AlertConfig> getAlertConfigPage(AlertConfigPageReqVO reqVO) {
        return alertConfigService.getAlertConfigPage(reqVO);
    }

    @Override
    public Message getNotifyMessage(AlertConfig alertConfig) {
        ChannelTemplate channelTemplate = channelTemplateService.getChannelTemplate(alertConfig.getMessageTemplateId());
        if (Objects.isNull(channelTemplate)) {
            return null;
        }
        Long channelConfigId = channelTemplate.getChannelConfigId();

        Message message = Message.builder()
                .content(channelTemplate.getContent())
                .templateCode(channelTemplate.getTemplateCode())
                .alertConfigId(alertConfig.getId())
                .build();

        if (channelConfigId != null) {
            ChannelConfig channelConfig = channelConfigService.getChannelConfig(channelTemplate.getChannelConfigId());
            if(Objects.isNull(channelConfig)){
                log.warn("告警通道配置丢失");
                return null;
            }
            message.setChannelCode(channelConfig.getCode());
            message.setChannelConfig(channelConfig.getParam());
        }
        return message;
    }
}
