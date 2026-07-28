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
 * APP拒绝升级事件Action类
 *
 * <p>处理静态和动态升级中的APP拒绝升级事件，主要功能包括：</p>
 * <ul>
 *   <li>验证升级任务和设备信息</li>
 *   <li>验证当前APP确认状态为待确认</li>
 *   <li>更新升级记录的APP确认状态为已拒绝</li>
 * </ul>
 *
 * <p>该类统一处理静态和动态升级模式下，设备APP拒绝升级的场景</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppConfirmationRejectAction extends BaseOtaUpgradeAction {

    private final AppConfirmationStrategy appConfirmationStrategy;
    private final OtaUpgradeRecordsService otaUpgradeRecordsService;

    /**
     * 执行APP拒绝升级事件
     *
     * <p>处理设备升级过程中APP拒绝升级的场景，主要逻辑包括：</p>
     * <ul>
     *   <li>验证升级任务和设备信息</li>
     *   <li>检查任务是否需要APP确认</li>
     *   <li>验证当前确认状态为待确认</li>
     *   <li>更新确认状态为已拒绝</li>
     * </ul>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("AppConfirmationRejectAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);
        try {
            // 获取升级记录
            OtaUpgradeRecordsResultDTO upgradeRecord = context.getUpgradeRecord();
            if (Objects.isNull(upgradeRecord)) {
                throw new RuntimeException("升级记录不存在");
            }
            String deviceIdentification = upgradeRecord.getDeviceIdentification();

            // 处理APP拒绝升级逻辑
            handleRejectUpgrade(from, to, event, context, deviceIdentification);

        } catch (Exception e) {
            throw new RuntimeException("处理APP拒绝升级事件过程中发生异常: " + e.getMessage(), e);
        }
    }

    /**
     * 处理APP拒绝升级逻辑
     *
     * <p>统一处理静态和动态升级中设备APP拒绝升级的场景</p>
     *
     * @param from                 当前状态
     * @param to                   目标状态
     * @param event                触发事件
     * @param context              升级上下文
     * @param deviceIdentification 设备标识
     */
    private void handleRejectUpgrade(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to,
                                     OtaUpgradeEventEnum event, OtaUpgradeContext context,
                                     String deviceIdentification) {
        log.info("处理APP拒绝升级事件 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);

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

            // 检查当前确认状态是否为待确认
            Integer currentStatus = otaUpgradeRecordsService
                    .getByTaskIdAndDeviceIdentification(context.getTaskId(), deviceIdentification)
                    .map(OtaUpgradeRecordsResultVO::getAppConfirmationStatus)
                    .orElse(null);

            if (Objects.isNull(currentStatus) || !currentStatus.equals(OtaTaskRecordAppConfirmStatusEnum.PENDING.getValue())) {
                log.info("当前APP确认状态不是待确认，跳过处理 - 任务ID: {}, 设备: {}, 当前状态: {}", context.getTaskId(), deviceIdentification, currentStatus);
                return;
            }

            // 更新升级记录的APP确认状态为已拒绝
            otaUpgradeRecordsService.updateAppConfirmationStatus(context.getTaskId(), deviceIdentification, OtaTaskRecordAppConfirmStatusEnum.REJECTED);
            
            log.info("APP拒绝升级事件处理完成 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
        } catch (Exception e) {
            throw new RuntimeException("处理APP拒绝升级事件异常: " + e.getMessage(), e);
        }
    }
}