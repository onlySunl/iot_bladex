package org.springblade.modules.iot.ota.service.statemachine.condition;

import java.util.List;
import java.util.Objects;

import com.alibaba.cola.statemachine.Condition;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.extern.slf4j.Slf4j;

/**
 * OTA升级基础条件类
 * 提供通用的条件判断逻辑，其他条件类应继承此类并重写特定逻辑
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/11
 */
@Slf4j
public class BaseUpgradeCondition implements Condition<OtaUpgradeContext> {

    /**
     * 条件名称
     */
    protected final String conditionName;

    /**
     * 默认构造函数
     */
    public BaseUpgradeCondition() {
        this.conditionName = this.getClass().getSimpleName();
    }

    /**
     * 带名称的构造函数
     *
     * @param conditionName 条件名称
     */
    public BaseUpgradeCondition(String conditionName) {
        this.conditionName = conditionName;
    }

    @Override
    public boolean isSatisfied(OtaUpgradeContext context) {
        log.debug("开始评估条件: {} - 任务ID: {}", conditionName, context.getTaskId());

        // 基础条件检查
        if (!validateBasicRequirements(context)) {
            log.warn("基础条件检查失败 - 条件: {}, 任务ID: {}", conditionName, context.getTaskId());
            return false;
        }

        // 执行具体的条件评估逻辑
        boolean result = evaluateCondition(context);

        log.debug("条件评估完成: {} - 任务ID: {}, 结果: {}", conditionName, context.getTaskId(), result);
        return result;
    }

    /**
     * 验证基础要求
     * 子类可以重写此方法来添加额外的验证逻辑
     *
     * @param context 升级上下文
     * @return true 如果基础要求满足
     */
    protected boolean validateBasicRequirements(OtaUpgradeContext context) {
        // 检查上下文是否为空
        if (Objects.isNull(context)) {
            log.error("升级上下文为空");
            return false;
        }

        // 检查任务ID
        if (Objects.isNull(context.getTaskId())) {
            log.error("任务ID为空");
            return false;
        }

        // 检查升级任务
        if (Objects.isNull(context.getUpgradeTask())) {
            log.error("升级任务为空 - 任务ID: {}", context.getTaskId());
            return false;
        }

        // 检查任务状态是否允许升级
        OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();
        if (!isStatusAllowedForUpgrade(currentStatus)) {
            log.warn("当前状态不允许升级 - 任务ID: {}, 状态: {}", context.getTaskId(), currentStatus);
            return false;
        }

        // 检查是否达到最大重试次数
        if (context.isMaxRetryReached()) {
            log.warn("已达到最大重试次数 - 任务ID: {}, 当前重试: {}, 最大重试: {}",
                    context.getTaskId(), context.getRetryCount(), context.getMaxRetryCount());
            return false;
        }

        // 检查是否超时
        if (context.isTimeout()) {
            log.warn("升级任务已超时 - 任务ID: {}", context.getTaskId());
            return false;
        }

        // 检查是否有错误
        if (context.hasError()) {
            log.warn("升级任务存在错误 - 任务ID: {}, 错误信息: {}", context.getTaskId(), context.getAllDeviceErrors());
            return false;
        }

        return true;
    }

    /**
     * 评估具体条件
     * 子类必须重写此方法来实现具体的条件逻辑
     *
     * @param context 升级上下文
     * @return true 如果条件满足
     */
    protected boolean evaluateCondition(OtaUpgradeContext context) {
        // 默认实现，子类应该重写此方法
        log.warn("基础条件类未实现具体的条件评估逻辑 - 条件: {}, 任务ID: {}", conditionName, context.getTaskId());
        return false;
    }

    /**
     * 检查状态是否允许升级
     *
     * @param status 当前状态
     * @return true 如果状态允许升级
     */
    protected boolean isStatusAllowedForUpgrade(OtaUpgradeTaskStatusEnum status) {
        return List.of(OtaUpgradeTaskStatusEnum.PENDING, OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .contains(status);
    }

    /**
     * 获取条件名称
     *
     * @return 条件名称
     */
    @Override
    public String name() {
        return conditionName;
    }

    /**
     * 设置条件名称
     *
     * @param name 条件名称
     */
    public void setName(String name) {
        // 注意：这里不能直接设置final字段，但子类可以通过构造函数设置
        log.warn("条件名称是final字段，无法在运行时修改");
    }

    /**
     * 记录条件评估日志
     *
     * @param context 升级上下文
     * @param result  评估结果
     * @param message 附加信息
     */
    protected void logConditionEvaluation(OtaUpgradeContext context, boolean result, String message) {
        if (result) {
            log.info("条件评估通过 - 条件: {}, 任务ID: {}, 信息: {}", conditionName, context.getTaskId(), message);
        } else {
            log.warn("条件评估失败 - 条件: {}, 任务ID: {}, 信息: {}", conditionName, context.getTaskId(), message);
        }
    }

    /**
     * 获取条件描述
     *
     * @return 条件描述
     */
    public String getDescription() {
        return "OTA升级基础条件类，提供通用的条件判断逻辑";
    }
}