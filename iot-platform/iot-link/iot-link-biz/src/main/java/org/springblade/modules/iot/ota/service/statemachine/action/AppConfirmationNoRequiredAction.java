package org.springblade.modules.iot.ota.service.statemachine.action;

import java.util.Objects;

import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 无需APP确认升级事件Action类
 *
 * <p>处理静态和动态升级中的无需APP确认升级事件，主要功能包括：</p>
 * <ul>
 *   <li>验证升级任务和设备信息</li>
 *   <li>更新升级记录的APP确认状态为无需确认</li>
 *   <li>触发后续的升级执行流程</li>
 * </ul>
 *
 * <p>该类统一处理静态和动态升级模式下，设备无需APP确认即可直接升级的场景</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@AllArgsConstructor
public class AppConfirmationNoRequiredAction extends BaseOtaUpgradeAction {

    private final OtaUpgradeRecordsService otaUpgradeRecordsService;

    /**
     * 执行无需APP确认升级事件
     *
     * <p>处理设备升级过程中无需APP确认的场景，主要逻辑包括：</p>
     * <ul>
     *   <li>验证升级任务和设备信息</li>
     *   <li>更新升级记录的APP确认状态为无需确认</li>
     *   <li>触发升级执行事件</li>
     * </ul>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("AppConfirmationNoRequiredAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);
        try {
            // 获取升级记录
            OtaUpgradeRecordsResultDTO upgradeRecord = context.getUpgradeRecord();
            if (Objects.isNull(upgradeRecord)) {
                throw new RuntimeException("升级记录不存在");
            }
            String deviceIdentification = upgradeRecord.getDeviceIdentification();

            // 处理无需APP确认升级逻辑
            handleNoRequiredConfirmation(from, to, event, context, deviceIdentification);

        } catch (Exception e) {
            throw new RuntimeException("处理无需APP确认升级事件过程中发生异常: " + e.getMessage(), e);
        }
    }

    /**
     * 处理无需APP确认升级逻辑
     *
     * <p>统一处理静态和动态升级中设备无需APP确认的场景</p>
     *
     * @param from                 当前状态
     * @param to                   目标状态
     * @param event                触发事件
     * @param context              升级上下文
     * @param deviceIdentification 设备标识
     */
    private void handleNoRequiredConfirmation(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, 
                                              OtaUpgradeEventEnum event, OtaUpgradeContext context,
                                              String deviceIdentification) {
        log.info("处理无需APP确认升级事件 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
        try {
            OtaUpgradeTasksResultDTO task = context.getUpgradeTask();
            if (Objects.isNull(task)) {
                throw new RuntimeException("升级任务为空");
            }

            // 更新升级记录的APP确认状态为无需确认
            otaUpgradeRecordsService.updateAppConfirmationStatus(context.getTaskId(), deviceIdentification, OtaTaskRecordAppConfirmStatusEnum.NOT_REQUIRED);
            
            // 发布OTA任务升级执行事件
            publishTaskExecutionEventAsync(context.getUpgradeTask(), context.getUpgradeTask().getOtaUpgradesResult(), deviceIdentification);
            
            log.info("无需APP确认升级事件处理完成 - 任务ID: {}, 设备: {}", context.getTaskId(), deviceIdentification);
        } catch (Exception e) {
            throw new RuntimeException("处理无需APP确认升级事件异常: " + e.getMessage(), e);
        }
    }
}