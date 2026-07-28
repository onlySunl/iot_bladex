package org.springblade.modules.iot.ota.service.statemachine.action;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import cn.hutool.core.collection.CollUtil;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.UpgradeRecordDeduplicationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 手动重试指定设备Action类
 *
 * <p>处理手动重试指定设备列表的升级，主要功能包括：</p>
 * <ul>
 *   <li>直接使用上下文中指定的设备标识列表，不获取升级范围</li>
 *   <li>不进行版本过滤，直接处理指定设备</li>
 *   <li>允许对已经下发过指令的设备重新下发（手动重试场景）</li>
 *   <li>检查或创建升级记录</li>
 *   <li>验证设备升级记录是否超时</li>
 *   <li>检查APP确认状态并触发相应事件</li>
 * </ul>
 *
 * <p><strong>与常规升级流程的区别：</strong></p>
 * <ul>
 *   <li>不更新任务状态（除非从 PENDING 转换到 IN_PROGRESS）</li>
 *   <li>不从升级范围策略获取设备列表</li>
 *   <li>不进行版本过滤检查</li>
 *   <li>允许重复下发指令（不检查 isCommandAlreadySentAndNoFurtherProcessNeeded）</li>
 *   <li>不影响任务的整体重试计数</li>
 * </ul>
 *
 * <p><strong>使用场景：</strong></p>
 * <ul>
 *   <li>设备升级失败后需要单独重试</li>
 *   <li>设备未收到升级指令需要重新下发</li>
 *   <li>升级过程中断需要恢复</li>
 * </ul>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @see OtaUpgradeEventEnum#MANUAL_RETRY_DEVICES
 * @see OtaUpgradeContext#getSpecifiedDeviceIdentifications()
 * @see DynamicStartUpgradeAction
 * @see StaticStartUpgradeAction
 * @since 2025/01/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ManualRetryDevicesAction extends BaseOtaUpgradeAction {

    private final UpgradeRecordDeduplicationStrategy upgradeRecordDeduplicationStrategy;
    private final DeviceService deviceService;

    /**
     * 执行手动重试指定设备事件
     *
     * <p>处理手动重试指定设备的升级，主要流程：</p>
     * <ol>
     *   <li>验证上下文中的指定设备标识列表</li>
     *   <li>更新任务状态为进行中（如果是 PENDING 状态）</li>
     *   <li>遍历每个设备标识，查询设备信息</li>
     *   <li>调用 handleDeviceUpgrade 处理设备升级</li>
     *   <li>记录处理结果和错误信息</li>
     * </ol>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件（{@link OtaUpgradeEventEnum#MANUAL_RETRY_DEVICES}）
     * @param context 升级上下文，需包含 {@link OtaUpgradeContext#getSpecifiedDeviceIdentifications()}
     * @throws RuntimeException 当处理过程中发生异常时抛出
     * @see #handleDeviceUpgrade(OtaUpgradeContext, DeviceResultVO)
     */
    @Override
    protected void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("ManualRetryDevicesAction execute.params... from:{}, to:{}, event:{}, context:{}", from, to, event, context);
        try {
            // 验证指定设备标识列表
            List<String> specifiedDeviceIdentifications = context.getSpecifiedDeviceIdentifications();
            if (CollUtil.isEmpty(specifiedDeviceIdentifications)) {
                log.warn("手动重试指定设备列表为空 - 任务ID: {}", context.getTaskId());
                return;
            }

            log.info("开始手动重试指定设备 - 任务ID: {}, 设备数量: {}", context.getTaskId(), specifiedDeviceIdentifications.size());

            // 处理每个设备的手动重试
            specifiedDeviceIdentifications.forEach(deviceIdentification -> {
                try {
                    // 查询设备信息
                    DeviceResultVO deviceInfo = deviceService.findByDeviceIdentification(deviceIdentification);
                    if (Objects.isNull(deviceInfo)) {
                        log.error("设备信息不存在 - 任务ID: {}, 设备标识: {}", context.getTaskId(), deviceIdentification);
                        context.addDeviceError(deviceIdentification, "设备信息不存在");
                        return;
                    }
                    // 调用 handleDeviceUpgrade 处理设备升级
                    handleDeviceUpgrade(context, deviceInfo);
                    log.info("设备手动重试处理成功 - 任务ID: {}, 设备标识: {}", context.getTaskId(), deviceIdentification);

                } catch (Exception e) {
                    log.error("设备手动重试处理失败 - 任务ID: {}, 设备标识: {}", context.getTaskId(), deviceIdentification, e);
                    context.addDeviceError(deviceIdentification, e.getMessage());
                }
            });
            log.info("手动重试指定设备完成 - 任务ID: {}, 设备数量: {}, 错误设备数: {}", context.getTaskId(), specifiedDeviceIdentifications.size(), context.getErrorDeviceCount());
        } catch (Exception e) {
            throw new RuntimeException("处理手动重试指定设备事件过程中发生异常: " + e.getMessage(), e);
        }
    }

    /**
     * 处理单个设备的升级逻辑
     *
     * <p>核心处理流程：</p>
     * <ol>
     *   <li>查询或创建设备的升级记录</li>
     *   <li>跳过 isCommandAlreadySentAndNoFurtherProcessNeeded 检查（允许重复下发）</li>
     *   <li>检查APP确认状态</li>
     *   <li>根据APP确认状态触发相应事件：
     *     <ul>
     *       <li>{@link OtaTaskRecordAppConfirmStatusEnum#PENDING}: 触发 {@link OtaUpgradeEventEnum#APP_CONFIRM_UPGRADE_PENDING}</li>
     *       <li>{@link OtaTaskRecordAppConfirmStatusEnum#NOT_REQUIRED}: 触发 {@link OtaUpgradeEventEnum#APP_CONFIRM_UPGRADE_NO_REQUIRED}</li>
     *       <li>{@link OtaTaskRecordAppConfirmStatusEnum#CONFIRMED}: 触发 {@link OtaUpgradeEventEnum#APP_CONFIRM_UPGRADE}</li>
     *       <li>{@link OtaTaskRecordAppConfirmStatusEnum#REJECTED}: 触发 {@link OtaUpgradeEventEnum#APP_REJECT_UPGRADE}</li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * <p><strong>与常规流程的区别：</strong></p>
     * <ul>
     *   <li>允许对已下发成功的设备重新下发</li>
     *   <li>不检查 {@link BaseOtaUpgradeAction#isCommandAlreadySentAndNoFurtherProcessNeeded}</li>
     * </ul>
     *
     * @param context    升级上下文
     * @param deviceInfo 设备信息
     * @throws RuntimeException 当处理过程中发生异常时抛出
     * @see BaseOtaUpgradeAction#createUpgradeRecord(OtaUpgradeContext, DeviceResultVO)
     * @see BaseOtaUpgradeAction#isUpgradeRecordTimeout(OtaUpgradeRecordsResultDTO, OtaUpgradeContext)
     * @see BaseOtaUpgradeAction#handleTimeoutUpgradeRecord(OtaUpgradeRecordsResultDTO, OtaUpgradeContext)
     * @see BaseOtaUpgradeAction#fireEvent(OtaUpgradeContext, OtaUpgradeEventEnum)
     */
    private void handleDeviceUpgrade(OtaUpgradeContext context, DeviceResultVO deviceInfo) {
        String deviceIdentification = deviceInfo.getDeviceIdentification();
        log.info("处理设备升级 - 任务ID: {}, 设备标识: {}", context.getTaskId(), deviceIdentification);
        //获取或创建升级记录
        Optional<OtaUpgradeRecordsResultDTO> upgradeRecordOptional = upgradeRecordDeduplicationStrategy
                .getUpgradeRecordByTaskIdAndDeviceIdentification(context.getTaskId(), deviceIdentification);
        if (upgradeRecordOptional.isEmpty()) {
            log.info("设备升级记录不存在，创建升级记录 - 任务ID: {}, 设备标识: {}", context.getTaskId(), deviceIdentification);
            upgradeRecordOptional = createUpgradeRecord(context, deviceInfo);
        }
        if (upgradeRecordOptional.isEmpty()) {
            log.error("创建升级记录失败 - 任务ID: {}, 设备标识: {}", context.getTaskId(), deviceIdentification);
            return;
        }
        OtaUpgradeRecordsResultDTO upgradeRecord = upgradeRecordOptional.get();
        //设置升级记录到上下文
        context.setUpgradeRecord(upgradeRecord);
        //获取APP确认状态
        OtaTaskRecordAppConfirmStatusEnum appConfirmStatus = OtaTaskRecordAppConfirmStatusEnum
                .fromValue(upgradeRecord.getAppConfirmationStatus())
                .orElse(OtaTaskRecordAppConfirmStatusEnum.PENDING);
        //根据APP确认状态触发相应事件
        switch (appConfirmStatus) {
            case PENDING:
                log.info("设备APP确认状态为 {}，等待应用确认后处理 - 设备标识: {}", appConfirmStatus.getDesc(), deviceIdentification);
                fireEvent(context, OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_PENDING);
                break;

            case NOT_REQUIRED:
                log.info("设备APP确认状态为 {}，开始处理升级 - 设备标识: {}", appConfirmStatus.getDesc(), deviceIdentification);
                fireEvent(context, OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_NO_REQUIRED);
                break;

            case CONFIRMED:
                log.info("设备APP确认状态为 {}，开始处理升级 - 设备标识: {}", appConfirmStatus.getDesc(), deviceIdentification);
                fireEvent(context, OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE);
                break;

            case REJECTED:
                log.info("设备APP确认状态为 {}，无需处理升级 - 设备标识: {}", appConfirmStatus.getDesc(), deviceIdentification);
                fireEvent(context, OtaUpgradeEventEnum.APP_REJECT_UPGRADE);
                break;

            default:
                log.warn("设备APP确认状态未知 - 设备标识: {}, 状态: {}", deviceIdentification, appConfirmStatus);
                break;
        }
    }
}
