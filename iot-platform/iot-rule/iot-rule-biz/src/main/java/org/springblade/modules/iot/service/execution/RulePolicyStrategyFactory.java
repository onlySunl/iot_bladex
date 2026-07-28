package org.springblade.modules.iot.service.execution;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import org.springblade.modules.iot.dto.linkage.RuleConditionPolicyDTO;
import org.springblade.modules.iot.dto.linkage.execution.PolicyContext;
import org.springblade.modules.iot.dto.linkage.execution.PolicyPair;
import org.springblade.modules.iot.enumeration.linkage.ConditionTypeEnum;
import org.springblade.modules.iot.service.execution.policy.DeviceActionTriggerPolicy;
import org.springblade.modules.iot.service.execution.policy.DevicePropertiesPolicy;
import org.springblade.modules.iot.service.execution.policy.TimingTriggerPolicy;
import org.springblade.modules.iot.service.execution.service.RulePolicyStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 根据策略上下文获取适当的策略实例的工厂。
 *
 * @author xiaonan
 */
@Component
@Slf4j
public class RulePolicyStrategyFactory {

    private final DevicePropertiesPolicy devicePropertiesPolicy;
    private final TimingTriggerPolicy timingTriggerPolicy;
    private final DeviceActionTriggerPolicy deviceActionTriggerPolicy;


    @Autowired
    public RulePolicyStrategyFactory(
            DevicePropertiesPolicy devicePropertiesPolicy,
            TimingTriggerPolicy timingTriggerPolicy,
            DeviceActionTriggerPolicy deviceActionTriggerPolicy) {
        this.devicePropertiesPolicy = devicePropertiesPolicy;
        this.timingTriggerPolicy = timingTriggerPolicy;
        this.deviceActionTriggerPolicy = deviceActionTriggerPolicy;
    }


    public List<PolicyPair<RulePolicyStrategyService, RuleConditionPolicyDTO>> getPolicyStrategies(PolicyContext context) {
        if (context == null || context.getRulePolicyDTO() == null) {
            log.warn("PolicyContext or RulePolicyDTO cannot be null. context: {}", JSON.toJSONString(context));
            return Collections.emptyList();
        }

        if (context.getRulePolicyDTO().getRuleConditionPolicyDTOS() == null || CollUtil.isEmpty(context.getRulePolicyDTO().getRuleConditionPolicyDTOS())) {
            log.warn("RuleConditionPolicyDTO cannot be null. context: {}", JSON.toJSONString(context));
            return Collections.emptyList();
        }

        // 事件路径按触发类型过滤:只评估与事件匹配的条件组,混搭规则的定时条件仍归定时任务触发
        Integer triggerConditionType = context.getTriggerConditionType();

        return context.getRulePolicyDTO().getRuleConditionPolicyDTOS().stream()
                .filter(dto -> triggerConditionType == null
                        || Objects.equals(dto.getConditionType(), triggerConditionType))
                .map(conditionPolicyDTO -> {
                    ConditionTypeEnum conditionType = ConditionTypeEnum.fromValue(conditionPolicyDTO.getConditionType());
                    RulePolicyStrategyService policyService = switch (Objects.requireNonNull(conditionType)) {
                        case DEVICE_PROPERTIES_TRIGGER -> devicePropertiesPolicy;
                        case TIMING_TRIGGER -> timingTriggerPolicy;
                        case DEVICE_ACTION_TRIGGER -> deviceActionTriggerPolicy;
                        default -> {
                            log.error("Unknown condition type: {}", conditionType);
                            throw new IllegalStateException("Unknown condition type: " + conditionType);
                        }
                    };
                    return new PolicyPair<>(policyService, conditionPolicyDTO);
                })
                .collect(Collectors.toList());
    }


}