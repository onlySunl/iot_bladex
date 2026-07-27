package org.springblade.modules.iot.ota.service.statemachine.action;

import java.util.List;
import java.util.Optional;

import cn.hutool.core.collection.CollUtil;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.UpgradeRecordDeduplicationStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 静态升级开始Action类
 *
 * <p>处理静态升级的开始事件，包括：</p>
 * <ul>
 *   <li>START_UPGRADE：开始升级</li>
 *   <li>RETRY_UPGRADE：重试升级</li>
 * </ul>
 *
 * <p>该类负责静态升级任务的启动和重试逻辑</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component
@AllArgsConstructor
public class StaticStartUpgradeAction extends BaseOtaUpgradeAction {
    private final UpgradeRecordDeduplicationStrategy upgradeRecordDeduplicationStrategy;

    /**
     * 执行静态升级开始动作
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("StaticStartUpgradeAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);

        try {
            // 根据事件类型执行不同的逻辑
            switch (event) {
                case RETRY_UPGRADE:
                case START_UPGRADE:
                    log.info("静态升级开始 - 任务ID: {}", context.getTaskId());
                    startStaticUpgrade(context);
                    break;
                default:
                    throw new RuntimeException("不支持的静态升级开始事件类型: " + event);
            }

        } catch (Exception e) {
            throw new RuntimeException("处理静态升级开始事件异常: " + e.getMessage(), e);
        }
    }

    /**
     * 开始静态升级的核心逻辑
     *
     * <p>将原StaticUpgradeProcessorImpl中的startStaticUpgrade逻辑迁移到Action中</p>
     *
     * @param context 升级上下文
     */
    private void startStaticUpgrade(OtaUpgradeContext context) {
        log.info("开始静态升级 - 任务ID: {}, 重试次数: {}", context.getTaskId(), context.getRetryCount());
        try {
            // 1. 更新任务状态为进行中
            updateTaskStatus(context.getTaskId(), OtaUpgradeTaskStatusEnum.IN_PROGRESS);
            context.setCurrentStatus(OtaUpgradeTaskStatusEnum.IN_PROGRESS);

            // 2. 获取触发时的目标设备列表（静态升级只处理触发时存在的设备）
            List<DeviceResultVO> targetDevices = getTargetDevicesAtTriggerTime(context);
            log.info("获取目标设备列表 - 任务ID: {}, 目标设备数量: {}", context.getTaskId(), targetDevices.size());

            // 3. 过滤符合条件的设备（版本过滤）
            List<DeviceResultVO> qualifiedDeviceList = filterQualifiedDevices(context, targetDevices);
            log.info("版本过滤后设备列表 - 任务ID: {}, 符合目标版本设备数量: {}", context.getTaskId(), qualifiedDeviceList.size());

            if (CollUtil.isEmpty(qualifiedDeviceList)) {
                log.info("没有符合目标版本的设备，任务ID: {}", context.getTaskId());
                return;
            }

            // 4. 处理每个设备的升级逻辑
            qualifiedDeviceList.forEach(qualifiedDevice -> {
                try {
                    handleDeviceUpgrade(context, qualifiedDevice);
                    log.info("设备标识: {} 升级处理成功 - 任务ID: {}", qualifiedDevice.getDeviceIdentification(), context.getTaskId());
                } catch (Exception e) {
                    log.error("处理设备标识: {} 升级失败 - 任务ID: {}", qualifiedDevice.getDeviceIdentification(), context.getTaskId(), e);
                    context.addDeviceError(qualifiedDevice.getDeviceIdentification(), e.getMessage());
                }
            });

            // 5. 根据错误信息判断是否需要重试
            if (context.hasError()) {
                // 有设备处理失败，检查是否达到最大重试次数
                if (context.isMaxRetryReached()) {
                    log.info("静态升级任务达到最大重试次数，标记为失败 - 任务ID: {}, 失败设备数量: {}", context.getTaskId(), context.getErrorDeviceCount());
                    // 达到最大重试次数，标记任务为失败
                    fireEvent(context, OtaUpgradeEventEnum.UPGRADE_FAILED);
                } else {
                    log.info("静态升级有设备处理失败，增加重试次数 - 任务ID: {}, 失败设备数量: {}, 当前重试次数: {}/{}",
                            context.getTaskId(), context.getErrorDeviceCount(), context.getRetryCount(), context.getMaxRetryCount());
                    // 增加重试次数
                    context.incrementRetryCount();
                    updateTaskRetryCount(context.getTaskId(), context.getRetryCount());
                    log.info("重试次数已更新，等待下一轮定时任务重新处理 - 任务ID: {}, 新的重试次数: {}/{}",
                            context.getTaskId(), context.getRetryCount(), context.getMaxRetryCount());
                    // 不触发任何事件，保持IN_PROGRESS状态，等待下一轮定时任务重新处理
                }
            } else {
                log.info("静态升级所有设备处理成功 - 任务ID: {}, 设备数量: {}", context.getTaskId(), qualifiedDeviceList.size());
                // 所有设备处理完成，触发升级完成事件
                fireEvent(context, OtaUpgradeEventEnum.UPGRADE_COMPLETED);
            }
        } catch (Exception e) {
            log.error("静态升级开始失败 - 任务ID: {}", context.getTaskId(), e);
        }
    }

    /**
     * 处理单个设备的升级逻辑
     *
     * @param context         升级上下文
     * @param qualifiedDevice 符合条件的设备
     */
    private void handleDeviceUpgrade(OtaUpgradeContext context, DeviceResultVO qualifiedDevice) {
        log.info("处理设备标识: {} 升级 - 任务ID: {}", qualifiedDevice.getDeviceIdentification(), context.getTaskId());
        Optional<OtaUpgradeRecordsResultDTO> upgradeRecordOptional = upgradeRecordDeduplicationStrategy.getUpgradeRecordByTaskIdAndDeviceIdentification(context.getTaskId(), qualifiedDevice.getDeviceIdentification());
        if (upgradeRecordOptional.isEmpty()) {
            log.info("任务ID: {} 设备标识: {} - 不存在升级记录，创建升级记录", context.getTaskId(), qualifiedDevice.getDeviceIdentification());
            upgradeRecordOptional = createUpgradeRecord(context, qualifiedDevice);
        }
        if (upgradeRecordOptional.isEmpty()) {
            log.error("任务ID: {} 设备标识: {} - 创建升级记录失败", context.getTaskId(), qualifiedDevice.getDeviceIdentification());
            return;
        }
        OtaUpgradeRecordsResultDTO otaUpgradeRecordsResultDTO = upgradeRecordOptional.get();

        // 检查升级记录是否超时，如果超时则标记为失败并跳过后续流程
        if (isUpgradeRecordTimeout(otaUpgradeRecordsResultDTO, context)) {
            log.info("设备标识: {} 升级已超时，标记为失败 - 任务ID: {}", qualifiedDevice.getDeviceIdentification(), context.getTaskId());
            handleTimeoutUpgradeRecord(otaUpgradeRecordsResultDTO, context);
            return;
        }

        // 判断设备是否已经下发升级指令且无需后续流程
        if (isCommandAlreadySentAndNoFurtherProcessNeeded(otaUpgradeRecordsResultDTO)) {
            log.info("设备标识: {} 已成功下发升级指令且APP确认状态为:{}，跳过后续流程", qualifiedDevice.getDeviceIdentification(), OtaTaskRecordAppConfirmStatusEnum.fromValue(otaUpgradeRecordsResultDTO.getAppConfirmationStatus()).orElse(OtaTaskRecordAppConfirmStatusEnum.PENDING).getDesc());
            return;
        }
        context.setUpgradeRecord(otaUpgradeRecordsResultDTO);
        OtaTaskRecordAppConfirmStatusEnum appConfirmStatusEnum = OtaTaskRecordAppConfirmStatusEnum.fromValue(otaUpgradeRecordsResultDTO.getAppConfirmationStatus()).orElse(OtaTaskRecordAppConfirmStatusEnum.PENDING);
        switch (appConfirmStatusEnum) {
            case PENDING:
                log.info("设备标识: {} 应用确认状态为 {}，等待应用确认后处理", qualifiedDevice.getDeviceIdentification(), appConfirmStatusEnum.getDesc());
                fireEvent(context, OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_PENDING);
                break;
            case NOT_REQUIRED:
                log.info("设备标识: {} 应用确认状态为 {}，开始处理升级", qualifiedDevice.getDeviceIdentification(), appConfirmStatusEnum.getDesc());
                fireEvent(context, OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_NO_REQUIRED);
                break;
            case CONFIRMED:
                log.info("设备标识: {} 应用确认状态为 {}，开始处理升级", qualifiedDevice.getDeviceIdentification(), appConfirmStatusEnum.getDesc());
                fireEvent(context, OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE);
                break;
            case REJECTED:
                log.info("设备标识: {} 应用确认状态为 {}，无需处理升级", qualifiedDevice.getDeviceIdentification(), appConfirmStatusEnum.getDesc());
                fireEvent(context, OtaUpgradeEventEnum.APP_REJECT_UPGRADE);
                break;
            default:
                log.warn("设备标识: {} 应用确认状态为 {}，无需处理升级", qualifiedDevice.getDeviceIdentification(), appConfirmStatusEnum.getDesc());
                break;
        }
    }


}