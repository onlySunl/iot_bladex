package org.springblade.modules.iot.ota.service.statemachine.condition;

import java.util.Objects;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeMethodEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级条件判断类
 * <p>
 * 负责判断是否满足静态升级的条件
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/15
 */
@Slf4j
@Component
public class StaticUpgradeCondition extends BaseUpgradeCondition {

    @Override
    public String name() {
        return "静态升级模式条件";
    }

    /**
     * 判断是否为静态升级
     *
     * @param context 升级上下文
     * @return 是否为静态升级
     */
    public boolean isStaticUpgrade(OtaUpgradeContext context) {
        if (Objects.isNull(context)) {
            log.error("升级上下文为空");
            return false;
        }

        OtaUpgradeMethodEnum upgradeMethod = context.getUpgradeMethod();
        boolean result = OtaUpgradeMethodEnum.STATIC.equals(upgradeMethod);
        log.info("静态升级条件检查 - 任务ID: {}, 升级方式: {}, 结果: {}", context.getTaskId(), upgradeMethod, result);
        return result;
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
     * 判断是否可以开始静态升级
     *
     * @param context 升级上下文
     * @return 是否可以开始
     */
    public boolean canStartStaticUpgrade(OtaUpgradeContext context) {
        return isStaticUpgrade(context);
    }

    /**
     * 判断是否可以重试静态升级
     *
     * @param context 升级上下文
     * @return 是否可以重试
     */
    public boolean canRetryStaticUpgrade(OtaUpgradeContext context) {
        return isStaticUpgrade(context) && isWithinRetryLimit(context);
    }

    /**
     * 判断是否可以处理设备事件
     *
     * @param context 升级上下文
     * @return 是否可以处理
     */
    public boolean canHandleDeviceEvent(OtaUpgradeContext context) {
        return isStaticUpgrade(context);
    }

    /**
     * 判断是否可以批量完成检查
     *
     * @param context 升级上下文
     * @return 是否可以检查
     */
    public boolean canCheckBatchCompletion(OtaUpgradeContext context) {
        return isStaticUpgrade(context);
    }

    /**
     * 判断是否可以完成静态升级
     *
     * @param context 升级上下文
     * @return 是否可以完成
     */
    public boolean canCompleteStaticUpgrade(OtaUpgradeContext context) {
        return isStaticUpgrade(context);
    }
}