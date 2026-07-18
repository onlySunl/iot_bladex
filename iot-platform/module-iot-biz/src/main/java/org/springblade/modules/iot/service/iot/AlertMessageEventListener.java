

package org.springblade.modules.iot.service.iot;


import org.springblade.modules.iot.message.event.MessageEvent;
import org.springblade.modules.iot.message.listener.MessageEventListener;
import org.springblade.modules.iot.framework.tenant.core.context.TenantContextHolder;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springblade.modules.iot.service.alert.AlertConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * @author sjg
 */
@Slf4j
@Component
public class AlertMessageEventListener implements MessageEventListener {

    @Resource
    private AlertConfigService alertConfigService;

    @Override
    @EventListener(classes = MessageEvent.class)
    public void doEvent(MessageEvent event) {
        Message message = event.getMessage();
        TenantContextHolder.setTenantId(message.getTenantId());
        AlertConfig alertConfig = alertConfigService.getAlertConfig(message.getAlertConfigId());
        alertConfigService.addAlertRecord(alertConfig, message.getFormatContent());
    }
}
