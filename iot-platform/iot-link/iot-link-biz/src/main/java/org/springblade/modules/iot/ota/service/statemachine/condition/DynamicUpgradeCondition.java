package org.springblade.modules.iot.ota.service.statemachine.condition;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeMethodEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 动态升级条件判断类
 * <p>
 * 负责判断是否满足动态升级的条件
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/15
 */
@Slf4j
@Component
public class DynamicUpgradeCondition extends BaseUpgradeCondition {

    @Override
    public String name() {
        return "动态升级模式条件";
    }

    /**
     * 判断是否为动态升级
     *
     * @param context 升级上下文
     * @return 是否为动态升级
     */
    public boolean isDynamicUpgrade(OtaUpgradeContext context) {
        return OtaUpgradeMethodEnum.DYNAMIC.equals(context.getUpgradeMethod());
    }

    /**
     * 判断是否在重试限制内
     *
     * @param context 升级上下文
     * @return 是否在重试限制内
     */
    public boolean isWithinRetryLimit(OtaUpgradeContext context) {
        return context.getRetryCount() < context.getMaxRetryCount();
    }

    /**
     * 判断是否可以开始动态升级
     *
     * @param context 升级上下文
     * @return 是否可以开始
     */
    public boolean canStartDynamicUpgrade(OtaUpgradeContext context) {
        return isDynamicUpgrade(context);
    }

    /**
     * 判断是否可以重试动态升级
     *
     * @param context 升级上下文
     * @return 是否可以重试
     */
    public boolean canRetryDynamicUpgrade(OtaUpgradeContext context) {
        return isDynamicUpgrade(context) && isWithinRetryLimit(context);
    }

    /**
     * 判断是否可以处理设备事件
     *
     * @param context 升级上下文
     * @return 是否可以处理
     */
    public boolean canHandleDeviceEvent(OtaUpgradeContext context) {
        return isDynamicUpgrade(context);
    }
}