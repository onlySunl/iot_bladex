package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级手动取消动作
 *
 * <p>处理静态升级任务的手动取消逻辑</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
public class StaticManualCancelAction extends BaseOtaUpgradeAction {

    /**
     * 执行手动取消动作
     *
     * <p>处理用户手动取消升级任务的逻辑</p>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to,
                             OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("开始执行静态升级手动取消 - 任务ID: {}", context.getTaskId());

        try {
            // 调用处理器处理手动取消
            log.info("静态升级手动取消处理完成 - 任务ID: {}", context.getTaskId());

        } catch (Exception e) {
            log.error("静态升级手动取消失败 - 任务ID: {}", context.getTaskId(), e);
            throw new RuntimeException("静态升级手动取消失败", e);
        }
    }

}