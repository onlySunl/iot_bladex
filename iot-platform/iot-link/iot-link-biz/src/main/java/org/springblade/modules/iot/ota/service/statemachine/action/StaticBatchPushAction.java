package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级批量推送Action类
 *
 * <p>处理静态升级过程中批量推送升级包事件</p>
 *
 * <p>该类负责批量推送升级包到符合条件的设备</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@AllArgsConstructor
public class StaticBatchPushAction extends BaseOtaUpgradeAction {

    /**
     * 执行静态升级批量推送动作
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("StaticBatchPushAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);

        try {
            log.info("开始批量推送升级包 - 任务ID: {}", context.getTaskId());
        } catch (Exception e) {
            throw new RuntimeException("处理静态升级批量推送事件异常: " + e.getMessage(), e);
        }
    }

}