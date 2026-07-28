package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 动态升级超时Action类
 *
 * <p>处理动态升级过程中的超时事件</p>
 *
 * <p>该类负责处理动态升级任务的超时逻辑</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicTimeoutAction extends BaseOtaUpgradeAction {


    /**
     * 执行动态升级超时动作
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.warn("DynamicTimeoutAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);

        try {
            log.warn("动态升级超时 - 任务ID: {}", context.getTaskId());


        } catch (Exception e) {
            throw new RuntimeException("处理动态升级超时事件异常: " + e.getMessage(), e);
        }
    }

}