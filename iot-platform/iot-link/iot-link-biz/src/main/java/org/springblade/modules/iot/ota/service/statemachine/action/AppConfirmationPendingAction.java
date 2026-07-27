package org.springblade.modules.iot.ota.service.statemachine.action;

import java.util.Objects;

import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.AppConfirmationStrategy;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 通用APP确认待处理事件Action类
 *
 * <p>处理静态和动态升级中的APP确认待处理事件，主要功能包括：</p>
 * <ul>
 *   <li>验证升级任务是否需要APP确认</li>
 *   <li>检查设备是否在升级范围内</li>
 *   <li>更新升级记录的APP确认状态为待确认</li>
 *   <li>触发后续的APP确认流程</li>
 * </ul>
 *
 * <p>该类统一处理静态和动态升级模式下，设备需要等待APP确认的场景</p>
 *
 * @author mqttsnet
 * @version 2.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppConfirmationPendingAction extends BaseOtaUpgradeAction {

    private final AppConfirmationStrategy appConfirmationStrategy;
    private final OtaUpgradeRecordsService otaUpgradeRecordsService;

    /**
     * 执行APP确认待处理事件
     *
     * <p>处理设备升级过程中需要APP确认的场景，主要逻辑包括：</p>
     * <ul>
     *   <li>验证升级任务和设备信息</li>
     *   <li>检查是否需要APP确认</li>
     *   <li>根据升级模式检查设备范围</li>
     *   <li>更新升级记录的APP确认状态为待确认</li>
     *   <li>触发APP确认通知</li>
     * </ul>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("AppConfirmationPendingAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);
        try {
            // 获取升级记录
            OtaUpgradeRecordsResultDTO upgradeRecord = context.getUpgradeRecord();
            if (Objects.isNull(upgradeRecord)) {
                throw new RuntimeException("升级记录不存在");
            }
            String deviceIdentification = upgradeRecord.getDeviceIdentification();

            // 处理APP确认待处理逻辑
            handleAppConfirmationPending(from, to, event, context, deviceIdentification);

        } catch (Exception e) {
            throw new RuntimeException("处理APP确认待处理事件过程中发生异常: " + e.getMessage(), e);
        }
    }

    /**
     * 处理APP确认待处理逻辑
     *
     * <p>统一处理静态和动态升级中设备需要APP确认的场景</p>
     *
     * @param from                 当前状态
     * @param to                   目标状态
     * @param event                触发事件
     * @param context              升级上下文
     * @param deviceIdentification 设备标识
     */
    private void handleAppConfirmationPending(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to,
                                              OtaUpgradeEventEnum event, OtaUpgradeContext context,
                                              String deviceIdentification) {
        log.info("处理APP确认待处理事件 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
        try {
            OtaUpgradeTasksResultDTO task = context.getUpgradeTask();
            if (Objects.isNull(task)) {
                throw new RuntimeException("升级任务为空");
            }
            // 检查任务是否需要APP确认
            if (!appConfirmationStrategy.needConfirmation(task, deviceIdentification)) {
                log.info("任务无需APP确认，跳过处理 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
                return;
            }
            // 检查当前确认状态，避免重复设置
            Integer currentStatus = otaUpgradeRecordsService
                    .getByTaskIdAndDeviceIdentification(context.getTaskId(), deviceIdentification)
                    .map(OtaUpgradeRecordsResultVO::getAppConfirmationStatus)
                    .orElse(null);
            // 如果当前状态不是待确认，则更新为待确认状态
            if (Objects.isNull(currentStatus) || !currentStatus.equals(OtaTaskRecordAppConfirmStatusEnum.PENDING.getValue())) {
                // 更新升级记录的APP确认状态为待确认
                otaUpgradeRecordsService.updateAppConfirmationStatus(context.getTaskId(), deviceIdentification, OtaTaskRecordAppConfirmStatusEnum.PENDING);
                log.info("设置APP确认状态为待确认 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
                // 触发APP确认通知（这里使用方可以集成消息推送等机制）
                triggerAppConfirmationNotification(context, deviceIdentification);
            } else {
                log.info("APP确认状态已是待确认，无需重复设置 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
            }
            log.info("APP确认待处理事件处理完成 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
        } catch (Exception e) {
            throw new RuntimeException("处理APP确认待处理事件异常: " + e.getMessage(), e);
        }
    }

    /**
     * 检查设备是否在升级范围内
     *
     * <p>根据升级模式动态检查设备范围：</p>
     * <ul>
     *   <li>静态升级：验证设备是否在升级任务触发时的初始范围内</li>
     *   <li>动态升级：验证设备是否在当前升级任务的范围内</li>
     * </ul>
     *
     * @param context              升级上下文
     * @param deviceIdentification 设备标识
     * @return 设备是否在升级范围内
     */
    private boolean isDeviceInUpgradeScope(OtaUpgradeContext context, String deviceIdentification) {
        try {
            // 获取升级任务触发时的目标设备列表
            var targetDevices = getTargetDevicesAtTriggerTime(context);
            // 检查设备是否在目标设备列表中
            boolean isInScope = targetDevices.stream().anyMatch(device -> deviceIdentification.equals(device.getDeviceIdentification()));
            log.info("设备范围检查结果 - 任务ID: {}, 设备: {}, 是否在范围内: {}", context.getTaskId(), deviceIdentification, isInScope);
            return isInScope;
        } catch (Exception e) {
            log.error("检查设备升级范围异常 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification, e);
            return false;
        }
    }

    /**
     * 触发APP确认通知
     *
     * <p>触发APP端的确认通知，可以集成消息推送、WebSocket通知等机制</p>
     *
     * @param context              升级上下文
     * @param deviceIdentification 设备标识
     */
    private void triggerAppConfirmationNotification(OtaUpgradeContext context, String deviceIdentification) {
        try {
            log.info("触发APP确认通知 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);

            // TODO: 这里可以集成具体的APP通知机制
            // 例如：
            // 1. 发送WebSocket消息给APP端
            // 2. 调用消息推送服务
            // 3. 记录通知日志

            log.debug("APP确认通知已触发 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);

        } catch (Exception e) {
            log.error("触发APP确认通知异常 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification, e);
        }
    }
}