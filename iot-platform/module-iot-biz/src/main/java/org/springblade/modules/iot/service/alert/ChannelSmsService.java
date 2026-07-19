package org.springblade.modules.iot.service.alert;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.alert.dto.SmsConfig;
import org.springblade.modules.iot.api.alert.service.RemoteIotChannelSmsService;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.ChannelConfig;
import org.springblade.modules.iot.controller.admin.channeltemplate.vo.ChannelTemplateSaveReqVO;
import org.springblade.modules.iot.entity.ChannelConfigDO;
import org.springblade.modules.iot.entity.ChannelTemplateDO;
import org.springblade.modules.iot.dal.mysql.channelconfig.ChannelConfigMapper;
import org.springblade.modules.iot.dal.mysql.channeltemplate.ChannelTemplateMapper;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 短信模板状态同步服务
 * 定时查询服务商短信模板状态并更新本地记录
 */
@Slf4j
@Service
public class ChannelSmsService {
    private static final String SMS_CONFIG_CODE = "SMS";

    @Resource
    private RemoteIotChannelSmsService channelSmsStrategy;

    @Resource
    private ChannelTemplateMapper channelTemplateMapper;

    @Resource
    private ChannelConfigMapper channelConfigMapper;

    @Resource
    private ChannelConfigService channelConfigService;

    /**
     * 创建短信模板
     */
    public void createTemplate(ChannelTemplateSaveReqVO createReqVO, ChannelTemplateDO channelTemplate) {
        ChannelConfig channelConfig = channelConfigService.getChannelConfig(createReqVO.getChannelConfigId());
        if (!Objects.equals(channelConfig.getCode(), SMS_CONFIG_CODE)) {
            return;
        }

        SmsConfig smsConfig = JsonUtils.parseObject(channelConfig.getParam(), SmsConfig.class);
        String templateCode = channelSmsStrategy.createSmsTemplate(createReqVO.getContent(), createReqVO.getId(), smsConfig);
        channelTemplate.setTemplateCode(templateCode);
        channelTemplate.setStatus(0); // 待审核状态
    }

    /**
     * 修改短信模板
     */
    public void updateTemplate(ChannelTemplateSaveReqVO updateReqVO, ChannelTemplateDO updateObj) {
        ChannelConfig channelConfig = channelConfigService.getChannelConfig(updateReqVO.getChannelConfigId());
        if (!Objects.equals(channelConfig.getCode(), SMS_CONFIG_CODE)) {
            return;
        }

        // 如果模板内容有变化，则需要更新服务商短信模板
        ChannelTemplateDO originalTemplate = channelTemplateMapper.selectById(updateReqVO.getId());
        if (originalTemplate != null && !Objects.equals(originalTemplate.getContent(), updateReqVO.getContent())) {
            // 待审核状态无法更新短信模板
            if (originalTemplate.getStatus() == 0) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.SMS_TEMPLATE_UPDATE_ERROR_IN_AUDIT);
            }
            // 更新服务商短信模板
            SmsConfig smsConfig = JsonUtils.parseObject(channelConfig.getParam(), SmsConfig.class);
            String templateCode = channelSmsStrategy.updateSmsTemplate(updateReqVO.getContent(), originalTemplate.getTemplateCode(), smsConfig);
            updateObj.setTemplateCode(templateCode);
            updateObj.setStatus(0); // 重置为待审核状态
        }
    }

    /**
     * 删除短信模板
     */
    public void deleteTemplate(ChannelTemplateDO originalTemplate) {
        ChannelConfig channelConfig = channelConfigService.getChannelConfig(originalTemplate.getChannelConfigId());
        if (!Objects.equals(channelConfig.getCode(), SMS_CONFIG_CODE)) {
            return;
        }

        // 待审核状态无法更新短信模板
        if (originalTemplate.getStatus() == 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SMS_TEMPLATE_DELETE_ERROR_IN_AUDIT);
        }

        // 删除服务商短信模板
        SmsConfig smsConfig = JsonUtils.parseObject(channelConfig.getParam(), SmsConfig.class);
        channelSmsStrategy.deleteSmsTemplate(originalTemplate.getTemplateCode(), smsConfig);
    }

    /**
     * 定时同步短信模板状态
     * 每小时执行一次
     */
    @Scheduled(fixedRate = 3600000)
    public void syncSmsTemplateStatus() {
        log.info("开始同步短信模板状态");

        try {
            // 查询所有SMS类型的通道配置
            LambdaQueryWrapper<ChannelConfigDO> channelConfigQuery = new LambdaQueryWrapperX<ChannelConfigDO>()
                    .eq(ChannelConfigDO::getCode, SMS_CONFIG_CODE);
            List<ChannelConfigDO> smsChannelConfigs = channelConfigMapper.selectList(channelConfigQuery);

            Map<Long, SmsConfig> smsConfigMap = smsChannelConfigs.stream().collect(Collectors.toMap(
                    ChannelConfigDO::getId,
                    channelConfig -> JsonUtils.parseObject(channelConfig.getParam(), SmsConfig.class)));

            if (smsChannelConfigs.isEmpty()) {
                log.info("没有找到SMS类型的通道配置");
                return;
            }

            // 提取通道配置ID列表
            List<Long> channelConfigIds = smsChannelConfigs.stream()
                    .map(ChannelConfigDO::getId)
                    .collect(Collectors.toList());

            // 查询所有待审核且属于SMS通道的短信模板
            LambdaQueryWrapper<ChannelTemplateDO> templateQuery = new LambdaQueryWrapperX<ChannelTemplateDO>()
                    .eq(ChannelTemplateDO::getStatus, 0) // 0-待审核状态
                    .in(ChannelTemplateDO::getChannelConfigId, channelConfigIds);

            List<ChannelTemplateDO> templateList = channelTemplateMapper.selectList(templateQuery);
            log.info("找到 {} 个待审核的短信模板", templateList.size());

            // 构建通道配置映射
            Map<Long, ChannelConfigDO> channelConfigMap = smsChannelConfigs.stream()
                    .collect(Collectors.toMap(ChannelConfigDO::getId, config -> config));

            for (ChannelTemplateDO template : templateList) {
                try {
                    ChannelConfigDO channelConfigDO = channelConfigMap.get(template.getChannelConfigId());
                    if (channelConfigDO == null) {
                        log.warn("短信模板 {} 对应的通道配置不存在", template.getId());
                        continue;
                    }

                    // 查询服务商短信模板状态
                    Integer templateStatus = channelSmsStrategy.querySmsTemplateStatus(
                            smsConfigMap.get(channelConfigDO.getId()), template.getTemplateCode());
                    if (templateStatus != null && !templateStatus.equals(template.getStatus())) {
                        // 更新本地模板状态
                        channelTemplateMapper.update(null, new LambdaUpdateWrapper<ChannelTemplateDO>()
                                .eq(ChannelTemplateDO::getId, template.getId())
                                .set(ChannelTemplateDO::getStatus, templateStatus));
                        log.info("更新短信模板 {} 状态为 {}", template.getId(), templateStatus);
                    }
                } catch (Exception e) {
                    log.error("处理短信模板 {} 状态时发生错误", template.getId(), e);
                }
            }
        } catch (Exception e) {
            log.error("同步短信模板状态时发生错误", e);
        }

        log.info("短信模板状态同步完成");
    }
}
