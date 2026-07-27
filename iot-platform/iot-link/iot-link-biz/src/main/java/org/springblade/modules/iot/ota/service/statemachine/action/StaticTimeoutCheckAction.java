package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级超时检查动作
 *
 * <p>处理静态升级任务的超时检查逻辑</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
public class StaticTimeoutCheckAction extends BaseOtaUpgradeAction {


    /**
     * 执行超时检查动作
     *
     * <p>检查升级任务是否超时，并根据检查结果触发相应事件</p>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to,
                             OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("开始执行静态升级超时检查 - 任务ID: {}", context.getTaskId());

        try {
            // 调用处理器进行超时检查

        } catch (Exception e) {
            log.error("静态升级超时检查失败 - 任务ID: {}", context.getTaskId(), e);
            throw new RuntimeException("静态升级超时检查失败", e);
        }
    }

}