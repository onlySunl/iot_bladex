package org.springblade.modules.iot.ota.service.statemachine.action;

import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 动态升级设备结果上报Action类
 *
 * <p>处理动态升级过程中设备结果上报事件</p>
 *
 * <p>该类负责处理设备升级结果上报，包括成功和失败的逻辑处理</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@AllArgsConstructor
public class DynamicDeviceResultAction extends BaseOtaUpgradeAction {

    /**
     * 执行动态升级设备结果上报动作
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("DynamicDeviceResultAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);

        try {
            log.info("动态升级设备结果上报 - 任务ID: {}", context.getTaskId());

        } catch (Exception e) {
            throw new RuntimeException("处理动态升级设备结果上报事件异常: " + e.getMessage(), e);
        }
    }

}