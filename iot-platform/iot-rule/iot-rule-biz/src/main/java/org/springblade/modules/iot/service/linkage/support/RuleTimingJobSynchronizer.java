package org.springblade.modules.iot.service.linkage.support;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Map;
import java.util.Optional;

import cn.hutool.core.map.MapUtil;
import org.springblade.modules.iot.common.condition.AppointEffectiveTimeDTO;
import org.springblade.modules.iot.common.condition.ConditionCronUtil;
import org.springblade.core.secure.constant.SecureConstant;
import org.springblade.core.secure.utils.AuthUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.JsonUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.springblade.modules.iot.common.constant.JobConstant;
import org.springblade.modules.iot.entity.linkage.Rule;
import org.springblade.modules.iot.entity.linkage.RuleCondition;
import org.springblade.modules.iot.enumeration.linkage.ConditionStatusEnum;
import org.springblade.modules.iot.enumeration.linkage.ConditionTypeEnum;
import org.springblade.modules.iot.enumeration.linkage.RuleStatusEnum;
import org.springblade.modules.iot.job.dto.JobReturnT;
import org.springblade.modules.iot.job.dto.XxlJobInfoVO;
import org.springblade.modules.iot.job.dto.XxlJobTriggerStatusEnum;
import org.springblade.modules.iot.job.facade.JobFacade;
import org.springblade.modules.iot.manager.linkage.RuleConditionManager;
import org.springblade.modules.iot.manager.linkage.RuleManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 规则定时任务同步器 ── XXL-Job 只服务"定时触发"条件的规则。
 *
 * <p>事件驱动架构下,设备属性/动作触发的规则由 {@code RuleTriggerEventConsumer} 实时评估,
 * 不再依赖轮询;仅当规则存在<b>启用的定时触发条件</b>时才注册调度任务。规则或条件的任一
 * CUD 都应调用 {@link #sync}/{@link #syncByRuleId} 重新对账:有定时条件→按最新频率/状态重建任务,
 * 无→移除任务。重建采用"先删后建"(注册链路唯一,免去 update/start/pause 多分支),
 * 规则编辑是低频操作,job-admin 的任务重建成本可忽略。
 *
 * <p>依赖 manager 层(而非 service 层)读写规则与条件,避免与
 * {@code RuleServiceImpl}/{@code RuleConditionServiceImpl} 形成循环依赖。
 *
 * @author mqttsnet
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RuleTimingJobSynchronizer {

    private final JobFacade jobFacade;
    private final RuleManager ruleManager;
    private final RuleConditionManager ruleConditionManager;

    /**
     * 按规则 ID 同步(条件 CUD 入口用)。
     */
    public void syncByRuleId(Long ruleId) {
        if (ruleId == null) {
            return;
        }
        Rule rule = ruleManager.getById(ruleId);
        if (rule == null) {
            return;
        }
        sync(rule);
    }

    /**
     * 按规则当前条件集同步定时任务。
     *
     * @param rule 规则实体(appointContent 应为最新值)
     */
    public void sync(Rule rule) {
        AppointEffectiveTimeDTO appoint = Optional
                .ofNullable(JsonUtil.parse(rule.getAppointContent(), AppointEffectiveTimeDTO.class))
                .orElseGet(AppointEffectiveTimeDTO::new);

        boolean hasTimingCondition = ruleConditionManager.count(new LambdaQueryWrapper<RuleCondition>()
                .eq(RuleCondition::getRuleId, rule.getId())
                .eq(RuleCondition::getConditionType, ConditionTypeEnum.TIMING_TRIGGER.getValue())
                .eq(RuleCondition::getStatus, ConditionStatusEnum.ENABLED.getValue())) > 0;

        // 先删后建:老任务一律清掉,需要时按最新 frequency/状态重建
        removeTask(rule, appoint, true);
        if (hasTimingCondition) {
            registerTask(rule, appoint);
        }
    }

    /**
     * 删除规则时清理关联任务(规则行将删除,不回写 appointContent)。
     */
    public void removeOnDelete(Rule rule) {
        AppointEffectiveTimeDTO appoint = JsonUtil.parse(rule.getAppointContent(), AppointEffectiveTimeDTO.class);
        removeTask(rule, appoint, false);
    }

    private void registerTask(Rule rule, AppointEffectiveTimeDTO appoint) {
        // 定时触发规则必须有合法执行频率,缺失/非正值直接报错阻断保存(而非注册一个坏任务)
        ArgumentAssert.isTrue(appoint.getFrequency() != null && appoint.getFrequency() > 0,
                "Timing rule requires a positive frequency (seconds)");
        String cron = ConditionCronUtil.secondsToCron(appoint.getFrequency());
        ArgumentAssert.isTrue(ConditionCronUtil.validateCronExpression(cron), "Cron expression is not valid");

        XxlJobTriggerStatusEnum triggerStatus = RuleStatusEnum.ACTIVATED.getValue().equals(rule.getStatus())
                ? XxlJobTriggerStatusEnum.RUNNING : XxlJobTriggerStatusEnum.STOPPED;
        Map<String, String> param = MapUtil.builder(ContextConstants.TENANT_ID_HEADER, ContextUtil.getTenantId().toString())
                .put("ruleIdentification", rule.getRuleIdentification())
                .build();
        XxlJobInfoVO jobInfoVO = XxlJobInfoVO.createFromCronExpression(JobConstant.DEF_IOT_JOB_GROUP_NAME,
                "【Scene linkage rule】" + rule.getRuleIdentification(),
                cron,
                JobConstant.SCENE_LINKAGE_RULE_JOB_HANDLER,
                JsonUtil.toJson(param), triggerStatus);

        JobReturnT<String> saveResult = jobFacade.saveTimingTask(jobInfoVO);
        if (saveResult.getCode() != JobReturnT.SUCCESS_CODE) {
            log.error("[RuleTimingJob] register task failed ruleId={} msg={}", rule.getId(), saveResult.getMsg());
            throw new ServiceException("Failed to save timing task");
        }
        appoint.setTaskId(saveResult.getContent());
        persistAppoint(rule, appoint);
        log.info("[RuleTimingJob] task registered ruleId={} taskId={} cron={} status={}",
                rule.getId(), appoint.getTaskId(), cron, triggerStatus);
    }

    private void removeTask(Rule rule, AppointEffectiveTimeDTO appoint, boolean persist) {
        if (appoint == null || StringUtils.isBlank(appoint.getTaskId())) {
            return;
        }
        try {
            Integer taskId = Integer.valueOf(appoint.getTaskId().trim());
            JobReturnT<XxlJobInfoVO> load = jobFacade.loadById(taskId);
            if (load.getCode() == JobReturnT.SUCCESS_CODE) {
                JobReturnT<String> removeResult = jobFacade.removeJob(taskId);
                if (removeResult.getCode() != JobReturnT.SUCCESS_CODE) {
                    log.warn("[RuleTimingJob] remove task failed ruleId={} taskId={} msg={}",
                            rule.getId(), taskId, removeResult.getMsg());
                }
            }
        } catch (NumberFormatException e) {
            log.warn("[RuleTimingJob] invalid taskId ruleId={} taskId={}", rule.getId(), appoint.getTaskId());
        } catch (Exception e) {
            log.warn("[RuleTimingJob] remove task exception ruleId={} taskId={} err={}",
                    rule.getId(), appoint.getTaskId(), e.getMessage(), e);
        }
        appoint.setTaskId(null);
        if (persist) {
            persistAppoint(rule, appoint);
        }
    }

    private void persistAppoint(Rule rule, AppointEffectiveTimeDTO appoint) {
        rule.setAppointContent(JsonUtil.toJson(appoint));
        ruleManager.updateById(rule);
    }
}
