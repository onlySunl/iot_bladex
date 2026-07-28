package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级重试Action类
 * 处理静态升级重试事件（FAILED → IN_PROGRESS）
 *
 * @author thinglinks
 * @date 2024/12/19
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StaticRetryUpgradeAction extends BaseOtaUpgradeAction {

    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("StaticRetryUpgradeAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);

        try {
            log.info("静态升级重试 - 任务ID: {}", context.getTaskId());
            // 增加重试次数
            context.incrementRetryCount();
            log.info("重试次数已增加 - 当前重试次数: {}/{}", context.getRetryCount(), context.getMaxRetryCount());
            //更新任务的重试次数
            updateTaskRetryCount(context.getTaskId(), context.getRetryCount());
        } catch (Exception e) {
            throw new RuntimeException("处理静态升级重试事件异常: " + e.getMessage(), e);
        }
    }

}