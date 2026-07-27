package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级失败Action类
 *
 * <p>处理静态升级失败事件</p>
 *
 * <p>该类负责处理静态升级任务的失败逻辑</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@AllArgsConstructor
public class StaticFailUpgradeAction extends BaseOtaUpgradeAction {

    /**
     * 执行静态升级失败动作
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("StaticFailUpgradeAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);

        try {
            log.info("静态升级失败 - 任务ID: {}", context.getTaskId());
        } catch (Exception e) {
            throw new RuntimeException("处理静态升级失败事件异常: " + e.getMessage(), e);
        }
    }

}