package org.springblade.modules.iot.service.alert;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.AlertApi;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报警 Service 实现类
 */
@Slf4j
@Service
public class AlertServiceImpl implements AlertService {

    @Resource
    private AlertApi alertApi;

    @Resource
    private AlertConfigService alertConfigService;

    @Override
    public void triggerAlert(AlertConfig config, String content) {
        // 记录报警
        alertConfigService.addAlertRecord(config, content);

        // 获取通知消息
        Message message = alertApi.getNotifyMessage(config);
        if (message == null) {
            log.warn("未找到报警通知配置, alertConfigId: {}", config.getId());
            return;
        }

        // TODO: 发送通知（邮件/短信/语音等）
        log.info("触发报警通知, alertConfigId: {}, channelCode: {}", config.getId(), message.getChannelCode());
    }

    @Override
    public List<AlertConfig> getDeviceAlertConfigs(Long productId) {
        // TODO: 根据产品ID获取报警配置列表
        return List.of();
    }
}
