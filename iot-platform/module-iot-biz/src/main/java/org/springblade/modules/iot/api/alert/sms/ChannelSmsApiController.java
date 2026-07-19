package org.springblade.modules.iot.api.alert.sms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.alert.sms.ChannelSmsStrategy;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 短信渠道对外API控制器，与RemoteIotChannelSmsService一一对应
 */
@RestController
@RequestMapping("/channelSmsApi")
@Tag(name = "短信渠道API", description = "短信发送、短信模板增删改查接口")
public class ChannelSmsApiController extends BladeController {

    @Resource
    private ChannelSmsStrategy channelSmsStrategy;

    @PostMapping("/sendSms")
    @Operation(summary = "发送短信通知")
    public void sendSms(@RequestBody Map<String, Object> templateParam,
                        @Parameter(description = "模板ID") @RequestParam String templateId,
                        @RequestBody SmsConfig smsConfig) {
        channelSmsStrategy.sendSms(templateParam, templateId, smsConfig);
    }

    @PostMapping("/createSmsTemplate")
    @Operation(summary = "创建短信模板")
    public String createSmsTemplate(@Parameter(description = "模板内容") @RequestParam String templateContent,
                                    @Parameter(description = "模板主键ID") @RequestParam Long templateId,
                                    @RequestBody SmsConfig smsConfig) {
        return channelSmsStrategy.createSmsTemplate(templateContent, templateId, smsConfig);
    }

    @PostMapping("/updateSmsTemplate")
    @Operation(summary = "更新短信模板")
    public String updateSmsTemplate(@Parameter(description = "模板内容") @RequestParam String templateContent,
                                    @Parameter(description = "外部模板编码") @RequestParam String templateCode,
                                    @RequestBody SmsConfig smsConfig) {
        return channelSmsStrategy.updateSmsTemplate(templateContent, templateCode, smsConfig);
    }

    @PostMapping("/querySmsTemplateStatus")
    @Operation(summary = "查询短信模板审核状态")
    public Integer querySmsTemplateStatus(@RequestBody SmsConfig smsConfig,
                                          @Parameter(description = "外部模板编码") @RequestParam String templateCode) {
        return channelSmsStrategy.querySmsTemplateStatus(smsConfig, templateCode);
    }

    @PostMapping("/deleteSmsTemplate")
    @Operation(summary = "删除短信模板")
    public void deleteSmsTemplate(@Parameter(description = "外部模板编码") @RequestParam String templateCode,
                                  @RequestBody SmsConfig smsConfig) {
        channelSmsStrategy.deleteSmsTemplate(templateCode, smsConfig);
    }
}