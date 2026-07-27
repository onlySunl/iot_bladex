package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级完成Action类
 *
 * <p>处理静态升级成功完成事件</p>
 *
 * <p>该类负责处理静态升级任务的成功完成逻辑</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StaticCompleteUpgradeAction extends BaseOtaUpgradeAction {


    /**
     * 执行静态升级完成动作
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("StaticCompleteUpgradeAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);
        try {
            log.info("检查静态升级完成条件 - 任务ID: {}", context.getTaskId());
            // 检查是否符合升级完成条件
            if (!isUpgradeCompleteConditionMet(context)) {
                log.warn("静态升级完成条件未满足 - 任务ID: {}", context.getTaskId());
                return;
            }
            log.info("静态升级成功 - 任务ID: {}", context.getTaskId());
            // 更新任务状态为成功
            updateTaskStatus(context.getTaskId(), OtaUpgradeTaskStatusEnum.COMPLETED);
        } catch (Exception e) {
            throw new RuntimeException("处理静态升级成功事件异常: " + e.getMessage(), e);
        }
    }

    /**
     * 检查静态升级完成条件是否满足
     * <p>升级完成条件包括：</p>
     * <ul>
     *     <li>达到计划结束时间</li>
     *     <li>升级超时</li>
     *     <li>所有设备都已处理完成（可选条件）</li>
     * </ul>
     * <p>注意：静态升级基于触发时的设备范围执行，到达计划结束时间后自动完成</p>
     *
     * @param context 升级上下文
     * @return 是否满足升级完成条件
     */
    private boolean isUpgradeCompleteConditionMet(OtaUpgradeContext context) {
        // 检查是否超时
        boolean isTimeout = context.isTimeout();

        // 检查是否到达计划结束时间
        boolean isScheduledEndTimeReached = context.isScheduledEndTimeReached();

        log.info("静态升级完成条件检查 - 任务ID: {}, 是否超时: {}, 是否到达计划结束时间: {}", context.getTaskId(), isTimeout, isScheduledEndTimeReached);
        // 静态升级完成条件：超时 或 到达计划结束时间
        return isTimeout || isScheduledEndTimeReached;
    }


}