package org.springblade.modules.iot.ota.service.statemachine.action;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.cola.statemachine.Action;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordCommandSendStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeRecordStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.service.OtaUpgradeTasksService;
import org.springblade.modules.iot.ota.service.statemachine.OtaUpgradeStateMachine;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import org.springblade.modules.iot.ota.service.statemachine.event.publisher.OtaEventPublisher;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.DeviceVersionFilterStrategy;
import org.springblade.modules.iot.ota.service.statemachine.strategy.scope.UpgradeScopeStrategyFactory;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

/**
 * 基础OTA升级动作抽象类
 *
 * <p>提供状态机动作的通用基类，包含完整的日志记录、事务管理和异常处理机制</p>
 * <p>所有具体的OTA升级动作都应该继承此类，并实现execute方法</p>
 *
 * @author mqttsnet
 * @version 2.0.0
 * @since 2025/11/10
 */
@Slf4j
public abstract class BaseOtaUpgradeAction implements Action<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> {

    @Lazy
    @Autowired
    protected OtaUpgradeTasksService otaUpgradeTasksService;
    @Autowired
    protected UpgradeScopeStrategyFactory scopeStrategyFactory;
    @Autowired
    protected DeviceVersionFilterStrategy deviceVersionFilterStrategy;
    @Autowired
    protected OtaUpgradeRecordsService otaUpgradeRecordsService;
    @Lazy
    @Autowired
    @Qualifier("otaUpgradeStateMachine")
    protected OtaUpgradeStateMachine otaUpgradeStateMachine;
    @Autowired
    protected OtaEventPublisher otaEventPublisher;

    /**
     * 执行OTA升级动作
     *
     * <p>这是状态机调用的主要方法，包含完整的日志记录、事务管理和异常处理</p>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    @Override
    public void execute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context) {
        log.info("{} execute.params... from:{}, to:{}, event:{}, context:{}", getClass().getSimpleName(), from, to, event, context);
        try {
            // 验证上下文有效性
            if (!validateContext(context)) {
                String errorMsg = "升级上下文无效";
                log.error("{}-{} - taskId: {}", getClass().getSimpleName(), errorMsg, context.getTaskId());
                throw new RuntimeException(errorMsg);
            }
            // 执行具体的业务逻辑
            doExecute(from, to, event, context);
            log.info("{} 执行成功 - taskId: {}", getClass().getSimpleName(), context.getTaskId());

        } catch (Exception e) {
            String errorMsg = String.format("动作执行异常 [%s]: %s", getClass().getSimpleName(), e.getMessage());
            log.error("{}-{} - taskId: {}, 异常信息: {}", getClass().getSimpleName(), errorMsg, context.getTaskId(), e.getMessage(), e);
            throw new RuntimeException(errorMsg, e);
        }
    }

    /**
     * 具体的动作执行逻辑
     *
     * <p>子类需要实现此方法来完成具体的业务逻辑</p>
     *
     * @param from    当前状态
     * @param to      目标状态
     * @param event   触发事件
     * @param context 升级上下文
     */
    protected abstract void doExecute(OtaUpgradeTaskStatusEnum from, OtaUpgradeTaskStatusEnum to, OtaUpgradeEventEnum event, OtaUpgradeContext context);

    /**
     * 验证上下文是否有效
     *
     * <p>检查升级上下文中的必要字段是否完整有效</p>
     *
     * @param context 升级上下文
     * @return 上下文是否有效
     */
    protected boolean validateContext(OtaUpgradeContext context) {
        return Objects.nonNull(context) && context.getTaskId() != null && context.getUpgradeTask() != null;
    }

    /**
     * 记录动作执行日志
     *
     * <p>提供统一的日志记录格式</p>
     *
     * @param message 日志消息
     */
    protected void logAction(String message) {
        log.info("[OTA Action] {}: {}", getClass().getSimpleName(), message);
    }

    /**
     * 触发状态机事件
     *
     * <p>提供统一的事件触发方法，封装状态机的fireEvent调用</p>
     *
     * @param context 升级上下文
     * @param event   触发事件
     */
    protected void fireEvent(OtaUpgradeContext context, OtaUpgradeEventEnum event) {
        // 使用延迟注入的方式避免循环依赖
        if (Objects.nonNull(otaUpgradeStateMachine)) {
            otaUpgradeStateMachine.fireEvent(context.getUpgradeMethod(), context, event);
        } else {
            log.error("状态机未初始化，无法触发事件: {}", event);
        }
    }

    /**
     * 获取目标设备列表（触发时存在的设备）
     *
     * <p>通用方法，用于获取升级触发时的目标设备列表</p>
     *
     * @param context 升级上下文
     * @return 目标设备列表
     */
    protected List<DeviceResultVO> getTargetDevicesAtTriggerTime(OtaUpgradeContext context) {
        try {
            // 使用策略工厂获取对应升级范围的策略
            var strategy = scopeStrategyFactory.getStrategyRequired(context.getUpgradeTask().getUpgradeScope());
            // 调用策略获取设备列表
            Optional<List<DeviceResultVO>> devicesOpt = strategy.getScopeDevices(context.getUpgradeTask());
            if (devicesOpt.isPresent()) {
                List<DeviceResultVO> devices = devicesOpt.get();
                log.info("获取升级范围设备列表成功 - 任务ID: {}, 升级范围: {}, 设备数量: {}", context.getTaskId(), context.getUpgradeScope().getDesc(), devices.size());
                return devices;
            } else {
                log.info("获取升级范围设备列表为空 - 任务ID: {}, 升级范围: {}", context.getTaskId(), context.getUpgradeScope().getDesc());
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.warn("获取升级范围设备列表失败 - 任务ID: {}, 升级范围: {}", context.getTaskId(), context.getUpgradeScope().getDesc(), e);
            return Collections.emptyList();
        }
    }

    /**
     * 过滤符合条件的设备（设备版本过滤）
     *
     * <p>通用方法，用于根据版本信息过滤符合条件的设备</p>
     *
     * @param context       升级上下文
     * @param targetDevices 目标设备列表
     * @return 符合条件的设备列表
     */
    protected List<DeviceResultVO> filterQualifiedDevices(OtaUpgradeContext context, List<DeviceResultVO> targetDevices) {
        return targetDevices.stream()
                .filter(device -> deviceVersionFilterStrategy.isVersionMatch(context.getUpgradeTask(), device))
                .collect(Collectors.toList());
    }

    /**
     * 创建升级记录
     *
     * <p>通用方法，用于为指定设备创建升级记录</p>
     *
     * @param context 升级上下文
     * @param device  设备信息
     * @return {@link  Optional<OtaUpgradeRecordsResultDTO>} 创建的升级记录
     */
    protected Optional<OtaUpgradeRecordsResultDTO> createUpgradeRecord(OtaUpgradeContext context, DeviceResultVO device) {
        Optional<OtaUpgradeRecordsResultVO> existingRecordOpt = otaUpgradeRecordsService.getByTaskIdAndDeviceIdentification(context.getTaskId(), device.getDeviceIdentification());
        if (existingRecordOpt.isPresent()) {
            log.info("设备标识: {} 升级记录已存在 - 任务ID: {}", device.getDeviceIdentification(), context.getTaskId());
            return Optional.of(BeanUtil.toBeanIgnoreError(existingRecordOpt.get(), OtaUpgradeRecordsResultDTO.class));
        }
        OtaUpgradeRecords record = new OtaUpgradeRecords();
        record.setUpgradeId(context.getUpgradeTaskId());
        record.setTaskId(context.getTaskId());
        record.setDeviceIdentification(device.getDeviceIdentification());
        record.setUpgradeStatus(OtaUpgradeRecordStatusEnum.WAITING.getValue());
        record.setSourceVersion(deviceVersionFilterStrategy.getDeviceCurrentVersion(context.getUpgradeTask(), device));
        record.setTargetVersion(context.getTargetVersion());
        if (context.getUpgradeTask().getAppConfirmationRequired()) {
            record.setAppConfirmationStatus(OtaTaskRecordAppConfirmStatusEnum.PENDING.getValue());
        } else {
            record.setAppConfirmationStatus(OtaTaskRecordAppConfirmStatusEnum.NOT_REQUIRED.getValue());
        }
        OtaUpgradeRecords savedRecord = otaUpgradeRecordsService.save(record);
        log.info("创建升级记录成功 - 任务ID: {}, 设备标识: {}, 记录ID: {}", context.getTaskId(), device.getDeviceIdentification(), savedRecord.getId());
        return Optional.of(BeanUtil.toBeanIgnoreError(savedRecord, OtaUpgradeRecordsResultDTO.class));
    }

    /**
     * 发布OTA任务升级执行事件
     * <p>通用方法，用于异步发布OTA任务升级执行事件</p>
     * <p>指定设备进行OTA任务升级执行事件</p>
     *
     * @param otaUpgradeTasksResultDTO 任务结果VO
     * @param otaUpgradesResultDTO     升级包
     * @param deviceIdentification     设备标识
     */
    public void publishTaskExecutionEventAsync(OtaUpgradeTasksResultDTO otaUpgradeTasksResultDTO,
                                               OtaUpgradesResultDTO otaUpgradesResultDTO,
                                               String deviceIdentification) {
        try {
            otaEventPublisher.publishOtaTaskUpgradeExecutionEvent(otaUpgradeTasksResultDTO, otaUpgradesResultDTO, Collections.singletonList(deviceIdentification));
        } catch (Exception e) {
            log.error("异步发布任务执行事件失败 - 任务ID: {}", otaUpgradeTasksResultDTO.getId(), e);
        }
    }

    /**
     * 更新任务状态
     *
     * <p>通用方法，用于更新任务状态到数据库</p>
     *
     * @param taskId                   任务ID
     * @param otaUpgradeTaskStatusEnum 任务状态
     */
    protected void updateTaskStatus(Long taskId, OtaUpgradeTaskStatusEnum otaUpgradeTaskStatusEnum) {
        var taskOpt = Optional.ofNullable(otaUpgradeTasksService.getById(taskId));
        taskOpt.ifPresent(task -> {
            if (Objects.equals(task.getTaskStatus(), otaUpgradeTaskStatusEnum.getValue())) {
                log.info("任务ID: {}, 无需更新任务状态 -任务状态已为: {} ", taskId, otaUpgradeTaskStatusEnum.getDesc());
                return;
            }
            task.setTaskStatus(otaUpgradeTaskStatusEnum.getValue());
            otaUpgradeTasksService.updateById(task);
        });
    }

    /**
     * 更新任务重试次数
     *
     * <p>通用方法，用于更新任务重试次数到数据库</p>
     *
     * @param taskId     任务ID
     * @param retryCount 重试次数
     */
    protected void updateTaskRetryCount(Long taskId, Integer retryCount) {
        var taskOpt = Optional.ofNullable(otaUpgradeTasksService.getById(taskId));
        taskOpt.ifPresent(task -> {
            if (Objects.equals(task.getCurrentRetryCount(), retryCount)) {
                log.info("任务ID: {}, 无需更新重试次数 - 当前重试次数已为: {} ", taskId, retryCount);
                return;
            }
            task.setCurrentRetryCount(retryCount);
            otaUpgradeTasksService.updateById(task);
        });
    }

    /**
     * 判断设备是否已经下发升级指令且无需后续流程
     *
     * <p>通用方法，用于检查设备是否已经成功下发升级指令并且APP确认状态是无需确认或已确认的状态</p>
     * <p>满足条件的设备不需要走后续的升级流程</p>
     *
     * @param upgradeRecord 升级记录
     * @return 是否已经下发升级指令且无需后续流程
     */
    protected boolean isCommandAlreadySentAndNoFurtherProcessNeeded(OtaUpgradeRecordsResultDTO upgradeRecord) {
        if (Objects.isNull(upgradeRecord)) {
            return false;
        }

        try {
            // 检查命令下发状态是否为成功
            boolean isCommandSentSuccess = OtaTaskRecordCommandSendStatusEnum.fromValue(upgradeRecord.getCommandSendStatus())
                    .map(status -> status.equals(OtaTaskRecordCommandSendStatusEnum.SENT_SUCCESS))
                    .orElse(false);

            // 检查APP确认状态是否为无需确认或已确认
            boolean isAppConfirmed = OtaTaskRecordAppConfirmStatusEnum.fromValue(upgradeRecord.getAppConfirmationStatus())
                    .map(status -> status.equals(OtaTaskRecordAppConfirmStatusEnum.NOT_REQUIRED) ||
                            status.equals(OtaTaskRecordAppConfirmStatusEnum.CONFIRMED))
                    .orElse(false);

            boolean result = isCommandSentSuccess && isAppConfirmed;

            if (result) {
                log.info("设备标识: {} 已成功下发升级指令且APP确认状态为无需确认或已确认，跳过后续流程", upgradeRecord.getDeviceIdentification());
            }

            return result;
        } catch (Exception e) {
            log.warn("检查设备升级指令下发状态异常 - 设备标识: {}", upgradeRecord.getDeviceIdentification(), e);
            return false;
        }
    }

    /**
     * 检查升级记录是否已超时
     *
     * <p>设备级别的超时检查：从升级记录创建时间开始计算，超过配置的设备升级超时时间则判定为超时</p>
     * <p>注意：如果未配置设备升级超时时间，则永远不会超时</p>
     *
     * @param upgradeRecord 升级记录
     * @param context       升级上下文（用于获取超时配置）
     * @return 是否超时
     */
    protected boolean isUpgradeRecordTimeout(OtaUpgradeRecordsResultDTO upgradeRecord, OtaUpgradeContext context) {
        if (Objects.isNull(upgradeRecord)) {
            return false;
        }

        // 如果没有配置设备升级超时时间，则不检查超时
        Integer deviceUpgradeTimeout = context.getDeviceUpgradeTimeout();
        if (Objects.isNull(deviceUpgradeTimeout) || deviceUpgradeTimeout <= 0) {
            return false;
        }

        // 获取升级记录的开始时间（如果没有开始时间，使用创建时间）
        LocalDateTime recordStartTime = upgradeRecord.getStartTime();
        if (Objects.isNull(recordStartTime)) {
            recordStartTime = upgradeRecord.getCreatedTime();
        }

        // 如果还是没有时间，则无法判断超时
        if (Objects.isNull(recordStartTime)) {
            log.warn("升级记录ID: {} 没有开始时间和创建时间，无法判断是否超时", upgradeRecord.getId());
            return false;
        }

        // 计算超时截止时间
        LocalDateTime timeoutDeadline = recordStartTime.plusMinutes(deviceUpgradeTimeout);
        boolean isTimeout = LocalDateTime.now().isAfter(timeoutDeadline);
        if (isTimeout) {
            log.warn("升级记录ID: {} 已超时 - 设备标识: {}, 开始时间: {}, 超时时长: {}分钟, 截止时间: {}", upgradeRecord.getId(), upgradeRecord.getDeviceIdentification(), recordStartTime, deviceUpgradeTimeout, timeoutDeadline);
        }
        return isTimeout;
    }

    /**
     * 处理超时的升级记录，将其状态标记为失败
     *
     * <p>当设备升级超时后，更新升级记录状态为失败，并记录失败原因</p>
     *
     * @param upgradeRecord 超时的升级记录
     * @param context       升级上下文
     */
    protected void handleTimeoutUpgradeRecord(OtaUpgradeRecordsResultDTO upgradeRecord, OtaUpgradeContext context) {
        if (Objects.isNull(upgradeRecord)) {
            return;
        }

        try {
            // 检查当前状态，避免重复更新
            Optional<OtaUpgradeRecordStatusEnum> currentStatusOpt = OtaUpgradeRecordStatusEnum.fromValue(upgradeRecord.getUpgradeStatus());
            if (currentStatusOpt.isEmpty()) {
                log.warn("升级记录ID: {} 状态无效: {}, 无法处理超时", upgradeRecord.getId(), upgradeRecord.getUpgradeStatus());
                return;
            }

            OtaUpgradeRecordStatusEnum currentStatus = currentStatusOpt.get();
            // 如果已经是终态（成功、失败），则不需要再处理
            if (Objects.equals(currentStatus, OtaUpgradeRecordStatusEnum.SUCCESS) ||
                    Objects.equals(currentStatus, OtaUpgradeRecordStatusEnum.FAILURE)) {
                log.info("升级记录ID: {} 已处于终态: {}, 无需处理超时", upgradeRecord.getId(), currentStatus.getDesc());
                return;
            }

            // 更新升级记录状态为失败
            OtaUpgradeRecords record = otaUpgradeRecordsService.getById(upgradeRecord.getId());
            if (Objects.nonNull(record)) {
                record.setUpgradeStatus(OtaUpgradeRecordStatusEnum.FAILURE.getValue());
                record.setErrorMessage("设备升级超时，超时时长: " + context.getDeviceUpgradeTimeout() + "分钟");
                record.setEndTime(LocalDateTime.now());
                otaUpgradeRecordsService.updateById(record);
                log.info("处理超时升级记录成功 - 记录ID: {}, 设备标识: {}, 原状态: {}, 新状态: FAILURE", upgradeRecord.getId(), upgradeRecord.getDeviceIdentification(), currentStatus.getDesc());
            }
        } catch (Exception e) {
            log.error("处理超时升级记录异常 - 记录ID: {}, 设备标识: {}", upgradeRecord.getId(), upgradeRecord.getDeviceIdentification(), e);
        }
    }
}