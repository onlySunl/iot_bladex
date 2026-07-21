package org.springblade.modules.iot.api.alert.service;

import org.springblade.modules.iot.api.alert.dto.SendSmsRequest;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springblade.modules.iot.api.alert.factory.RemoteIotChannelSmsFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 短信渠道远程Feign接口，对齐 {@link ChannelSmsStrategy}
 */
@FeignClient(contextId = "remoteIotChannelSmsService",
        value = IotServiceNameConstants.IOT_ALERT_SMS,
        fallbackFactory = RemoteIotChannelSmsFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotChannelSmsService {

    /** 发送短信 */
    @PostMapping("/api/channelSms/sendSms")
    void sendSms(@RequestBody SendSmsRequest request);

    /** 创建短信模板 */
    @PostMapping("/api/channelSms/createSmsTemplate")
    String createSmsTemplate(@RequestParam("templateContent") String templateContent,
                             @RequestParam("templateId") Long templateId,
                             @RequestBody SmsConfig smsConfig);

    /** 更新短信模板 */
    @PostMapping("/api/channelSms/updateSmsTemplate")
    String updateSmsTemplate(@RequestParam("templateContent") String templateContent,
                             @RequestParam("templateCode") String templateCode,
                             @RequestBody SmsConfig smsConfig);

    /** 查询模板审核状态 */
    @PostMapping("/api/channelSms/querySmsTemplateStatus")
    Integer querySmsTemplateStatus(@RequestBody SmsConfig smsConfig,
                                   @RequestParam("templateCode") String templateCode);

    /** 删除短信模板 */
    @PostMapping("/api/channelSms/deleteSmsTemplate")
    void deleteSmsTemplate(@RequestParam("templateCode") String templateCode,
                           @RequestBody SmsConfig smsConfig);
}