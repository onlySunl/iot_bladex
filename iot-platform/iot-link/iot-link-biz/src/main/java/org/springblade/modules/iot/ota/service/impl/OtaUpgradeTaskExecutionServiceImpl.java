package org.springblade.modules.iot.ota.service.impl;

import java.time.Duration;
import org.springblade.core.log.exception.ServiceException;
import java.time.Instant;
import org.springblade.core.log.exception.ServiceException;
import java.time.LocalDateTime;
import org.springblade.core.log.exception.ServiceException;
import java.util.ArrayList;
import org.springblade.core.log.exception.ServiceException;
import java.util.Collections;
import org.springblade.core.log.exception.ServiceException;
import java.util.List;
import org.springblade.core.log.exception.ServiceException;
import java.util.Objects;
import org.springblade.core.log.exception.ServiceException;
import java.util.Optional;
import org.springblade.core.log.exception.ServiceException;

import cn.hutool.core.collection.CollUtil;
import org.springblade.core.log.exception.ServiceException;
import com.alibaba.cola.statemachine.StateMachine;
import org.springblade.core.log.exception.ServiceException;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.utils.BeanUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTasks;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeMethodEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.service.OtaUpgradeTaskExecutionService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.service.OtaUpgradeTasksService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.service.statemachine.config.OtaUpgradeStateMachineConfig;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.service.statemachine.strategy.executor.AppConfirmationStrategy;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.param.SendDeviceOtaUpgradeCommandRequestParam;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTasksPageQuery;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.result.DeviceOtaUpgradeAppConfirmationResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeTasksResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeRecordsUpdateVO;
import org.springblade.core.log.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springblade.core.log.exception.ServiceException;

/**
 * Description:
 * OTA任务执行服务
 * <p>
 * 负责协调OTA升级任务的执行流程
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Service
public class OtaUpgradeTaskExecutionServiceImpl implements OtaUpgradeTaskExecutionService {
    private final OtaUpgradeTasksService otaUpgradeTasksService;
    private final OtaUpgradeRecordsService otaUpgradeRecordsService;
    private final AppConfirmationStrategy appConfirmationStrategy;

    @Qualifier(OtaUpgradeStateMachineConfig.DYNAMIC_MACHINE_ID)
    private final StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> dynamicUpgradeStateMachine;
    @Qualifier(OtaUpgradeStateMachineConfig.STATIC_MACHINE_ID)
    private final StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> staticUpgradeStateMachine;

    public OtaUpgradeTaskExecutionServiceImpl(
            OtaUpgradeTasksService otaUpgradeTasksService,
            OtaUpgradeRecordsService otaUpgradeRecordsService,
            AppConfirmationStrategy appConfirmationStrategy,
            @Qualifier(OtaUpgradeStateMachineConfig.DYNAMIC_MACHINE_ID) StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> dynamicUpgradeStateMachine,
            @Qualifier(OtaUpgradeStateMachineConfig.STATIC_MACHINE_ID) StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> staticUpgradeStateMachine) {
        this.otaUpgradeTasksService = otaUpgradeTasksService;
        this.otaUpgradeRecordsService = otaUpgradeRecordsService;
        this.appConfirmationStrategy = appConfirmationStrategy;
        this.dynamicUpgradeStateMachine = dynamicUpgradeStateMachine;
        this.staticUpgradeStateMachine = staticUpgradeStateMachine;
    }

    @Override
    public void otaUpgradeTasksExecute(Long tenantId) {
        log.info("开始执行tenantId:{} OTA升级任务扫描并执行 - 开始执行时间: {}", tenantId, LocalDateTime.now());

        LocalDateTime currentTime = LocalDateTime.now();
        // 获取待执行的任务：
        // 1. 状态为 PENDING 或 IN_PROGRESS
        // 2. scheduledStartTime <= currentTime（计划开始时间已到达）
        OtaUpgradeTasksPageQuery query = new OtaUpgradeTasksPageQuery();
        query.setTaskStatusList(List.of(
                OtaUpgradeTaskStatusEnum.PENDING.getValue(),
                OtaUpgradeTaskStatusEnum.IN_PROGRESS.getValue()
        ));
        query.setScheduledStartTime(currentTime);
        List<OtaUpgradeTasksResultDTO> allTasks = otaUpgradeTasksService.getUpgradeTaskDetailsList(query);
        if (CollUtil.isEmpty(allTasks)) {
            log.info("tenantId:{} 未找到需要执行的OTA升级任务", tenantId);
            return;
        }
        log.info("tenantId:{} 开始执行OTA升级任务，共查询到 {} 个任务", tenantId, allTasks.size());
        allTasks.forEach(task -> {
            try {
                executeUpgradeTask(task);
            } catch (Exception e) {
                log.error("OTA升级任务处理异常 - 任务ID: {}, 名称: {}, 状态: {}", task.getId(), task.getTaskName(), task.getTaskStatus(), e);
            }
        });
        log.info("tenantId:{} OTA升级任务执行完成, 共执行 {} 个任务", tenantId, allTasks.size());
    }

    /**
     * 执行OTA升级任务
     * 完整的OTA升级流程，状态机驱动从开始到结束的自动流转
     *
     * <p><strong>完整的OTA升级流程：</strong></p>
     * <ol>
     *   <li><strong>任务初始化</strong>：检查任务状态，创建升级上下文</li>
     *   <li><strong>设备范围过滤</strong>：根据升级范围策略获取目标设备列表</li>
     *   <li><strong>APP确认检查</strong>：判断是否需要APP确认，设置确认状态</li>
     *   <li><strong>生成升级记录</strong>：为每个目标设备创建升级记录</li>
     *   <li><strong>命令下发</strong>：向设备发送升级指令</li>
     *   <li><strong>状态自动流转</strong>：状态机根据业务结果自动流转到下一状态</li>
     *   <li><strong>结果处理</strong>：处理升级成功、失败、部分成功等结果</li>
     * </ol>
     *
     * @param otaUpgradeTasksResultDTO 升级任务
     * @see OtaUpgradeTaskStatusEnum 任务状态枚举
     * @see OtaUpgradeEventEnum 升级事件枚举
     * @see #executeUpgradeTaskWithDevices(OtaUpgradeTasksResultDTO, List) 指定设备列表的执行方法
     */
    @Override
    public void executeUpgradeTask(OtaUpgradeTasksResultDTO otaUpgradeTasksResultDTO) {
        Instant startTime = Instant.now();
        log.info("开始执行OTA升级任务 - 任务ID: {}, 任务名称: {}", otaUpgradeTasksResultDTO.getId(), otaUpgradeTasksResultDTO.getTaskName());
        // 1. 检查任务是否可以执行
        if (isTaskExecutable(otaUpgradeTasksResultDTO)) {
            log.warn("任务不可执行 - 任务ID: {}, 任务状态: {}", otaUpgradeTasksResultDTO.getId(), OtaUpgradeTaskStatusEnum.fromValue(otaUpgradeTasksResultDTO.getTaskStatus()).get().getDesc());
            return;
        }
        // 2. 创建升级上下文
        OtaUpgradeContext upgradeContext = createUpgradeContext(otaUpgradeTasksResultDTO);
        // 3. 根据升级方式选择状态机
        StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                isDynamicUpgrade(otaUpgradeTasksResultDTO) ? dynamicUpgradeStateMachine : staticUpgradeStateMachine;
        // 4. 根据当前任务状态选择对应的事件，实现自动流转
        OtaUpgradeTaskStatusEnum currentStatus = upgradeContext.getCurrentStatus();
        OtaUpgradeEventEnum event = getEventForCurrentStatus(currentStatus, upgradeContext);
        log.info("状态机自动流转 - 任务ID: {}, 当前状态: {}, 触发事件: {}", otaUpgradeTasksResultDTO.getId(), currentStatus, event);
        // 5. 触发状态机执行，状态机会自动处理完整的业务流程
        OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, event, upgradeContext);
        long executionTime = Duration.between(startTime, Instant.now()).toMillis();
        log.info("OTA升级任务执行完成 - 任务ID: {}, 当前状态: {} -> 下一状态: {}, 耗时: {}ms", otaUpgradeTasksResultDTO.getId(), currentStatus, nextStatus, executionTime);
    }

    @Override
    public void sendDeviceOtaUpgradeCommand(SendDeviceOtaUpgradeCommandRequestParam param) {
        OtaUpgradeTasksResultVO otaUpgradeTasksResultVO = otaUpgradeTasksService.getUpgradeTaskDetails(param.getUpgradeTaskId());
        ArgumentAssert.notNull(otaUpgradeTasksResultVO, "Upgrade task not found");
        OtaUpgradeTasksResultDTO otaUpgradeTasksResultDTO = BeanUtil.toBeanIgnoreError(otaUpgradeTasksResultVO, OtaUpgradeTasksResultDTO.class);
        executeUpgradeTaskWithDevices(otaUpgradeTasksResultDTO, param.getDeviceIdentificationList());
    }

    /**
     * 根据当前状态获取对应的事件，实现状态自动流转
     *
     * <p><strong>职责说明：</strong>本方法负责根据当前状态和业务条件返回对应的事件，
     * 实现任务的自动流转和终结。</p>
     *
     * <p><strong>状态流转逻辑：</strong></p>
     * <ul>
     *   <li>PENDING状态：触发开始升级事件</li>
     *   <li>IN_PROGRESS状态：
     *       <ul>
     *           <li>如果达到计划结束时间或超时：触发升级完成事件</li>
     *           <li>否则：触发开始升级事件（处理中间失败的设备）</li>
     *       </ul>
     *   </li>
     *   <li>FAILED状态：触发重试升级事件</li>
     * </ul>
     *
     * @param currentStatus 当前任务状态
     * @param context       升级上下文
     * @return 对应的事件
     * @see OtaUpgradeStateMachineConfig 状态机配置中的转换条件处理
     */
    private OtaUpgradeEventEnum getEventForCurrentStatus(OtaUpgradeTaskStatusEnum currentStatus, OtaUpgradeContext context) {
        return switch (currentStatus) {
            case PENDING ->
                // 待发布状态：开始升级
                    OtaUpgradeEventEnum.START_UPGRADE;
            case IN_PROGRESS -> {
                // 进行中状态：检查是否应该完成任务
                // 如果达到计划结束时间或超时，触发完成事件
                if (context.isScheduledEndTimeReached() || context.isTimeout()) {
                    log.info("任务ID: {} 达到完成条件，触发UPGRADE_COMPLETED事件", context.getTaskId());
                    yield OtaUpgradeEventEnum.UPGRADE_COMPLETED;
                }
                // 否则继续处理中间失败的设备
                yield OtaUpgradeEventEnum.START_UPGRADE;
            }
            case FAILED ->
                // 失败状态：触发重试升级事件，由状态机判断是否可以重试
                    OtaUpgradeEventEnum.RETRY_UPGRADE;
            default ->
                // 其他状态：默认返回开始升级事件
                    OtaUpgradeEventEnum.START_UPGRADE;
        };
    }

    /**
     * 执行OTA升级任务（手动重试指定设备）
     *
     * <p>本方法用于手动重试指定设备的升级，是对常规升级流程的补充</p>
     * <p>主要用于以下场景：</p>
     * <ul>
     *   <li>设备升级失败后需要单独重试</li>
     *   <li>设备未收到升级指令需要重新下发</li>
     *   <li>升级过程中断需要恢复特定设备</li>
     * </ul>
     *
     * <p><strong>与常规升级流程的区别：</strong></p>
     * <table border="1">
     *   <tr>
     *     <th>对比项</th>
     *     <th>常规升级（{@link #executeUpgradeTask}）</th>
     *     <th>手动重试指定设备</th>
     *   </tr>
     *   <tr>
     *     <td>触发事件</td>
     *     <td>{@link OtaUpgradeEventEnum#START_UPGRADE} 或 {@link OtaUpgradeEventEnum#RETRY_UPGRADE}</td>
     *     <td>{@link OtaUpgradeEventEnum#MANUAL_RETRY_DEVICES}</td>
     *   </tr>
     *   <tr>
     *     <td>设备获取方式</td>
     *     <td>从升级范围策略获取</td>
     *     <td>直接使用指定的设备列表</td>
     *   </tr>
     *   <tr>
     *     <td>版本过滤</td>
     *     <td>进行版本过滤</td>
     *     <td>不进行版本过滤</td>
     *   </tr>
     *   <tr>
     *     <td>重复下发</td>
     *     <td>已下发成功的设备跳过</td>
     *     <td>允许重复下发</td>
     *   </tr>
     *   <tr>
     *     <td>任务状态影响</td>
     *     <td>影响任务整体状态和重试计数</td>
     *     <td>不影响任务整体状态和重试计数</td>
     *   </tr>
     * </table>
     *
     * <p><strong>执行流程：</strong></p>
     * <ol>
     *   <li>检查任务是否可以执行（必须为 PENDING 或 IN_PROGRESS 状态）</li>
     *   <li>验证设备标识列表非空</li>
     *   <li>创建升级上下文并设置指定设备列表</li>
     *   <li>根据升级方式选择对应的状态机</li>
     *   <li>触发 {@link OtaUpgradeEventEnum#MANUAL_RETRY_DEVICES} 事件</li>
     *   <li>状态机自动处理后续流程（检查APP确认、下发指令等）</li>
     * </ol>
     *
     * <p><strong>注意事项：</strong></p>
     * <ul>
     *   <li>允许在 PENDING 或 IN_PROGRESS 状态下执行</li>
     *   <li>设备列表中的设备标识将被逐一处理</li>
     *   <li>APP确认状态为 PENDING 或 REJECTED 的设备会被跳过</li>
     *   <li>允许对已下发成功的设备重新下发</li>
     *   <li>执行结果不影响任务的整体状态和重试计数</li>
     * </ul>
     *
     * @param otaUpgradeTasksResultDTO 升级任务
     * @param deviceIdentifications    设备标识列表，需要手动重试的设备
     * @see #executeUpgradeTask(OtaUpgradeTasksResultDTO) 常规升级流程
     * @see OtaUpgradeEventEnum#MANUAL_RETRY_DEVICES
     * @see org.springblade.modules.iot.ota.service.statemachine.action.ManualRetryDevicesAction
     * @see OtaUpgradeContext#getSpecifiedDeviceIdentifications()
     */
    @Override
    public void executeUpgradeTaskWithDevices(OtaUpgradeTasksResultDTO otaUpgradeTasksResultDTO, List<String> deviceIdentifications) {
        Instant startTime = Instant.now();
        log.info("开始执行OTA升级任务（手动重试指定设备） - 任务ID: {}, 设备数量: {}", otaUpgradeTasksResultDTO.getId(), CollUtil.size(deviceIdentifications));
        // 1. 检查任务是否可以执行（必须为 PENDING 或 IN_PROGRESS 状态）
        if (isTaskExecutable(otaUpgradeTasksResultDTO)) {
            log.warn("任务不可执行（手动重试） - 任务ID: {}, 任务状态: {}", otaUpgradeTasksResultDTO.getId(), otaUpgradeTasksResultDTO.getTaskStatus());
            throw new ServiceException("The task is not within the execution cycle and cannot be manually retried!");
        }
        // 2. 验证设备标识列表
        if (CollUtil.isEmpty(deviceIdentifications)) {
            log.warn("设备标识列表为空（手动重试） - 任务ID: {}", otaUpgradeTasksResultDTO.getId());
            throw new ServiceException("Please select the devices that need to be upgraded again!");
        }
        // 3. 创建升级上下文并设置指定设备列表
        OtaUpgradeContext upgradeContext = createUpgradeContext(otaUpgradeTasksResultDTO);
        upgradeContext.setSpecifiedDeviceIdentifications(deviceIdentifications);
        log.info("已设置指定设备列表到上下文 - 任务ID: {}, 设备数量: {}", upgradeContext.getTaskId(), deviceIdentifications.size());
        // 4. 根据升级方式选择对应的状态机
        StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                isDynamicUpgrade(otaUpgradeTasksResultDTO) ? dynamicUpgradeStateMachine : staticUpgradeStateMachine;
        // 5. 触发手动重试指定设备事件
        OtaUpgradeTaskStatusEnum currentStatus = upgradeContext.getCurrentStatus();
        OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(
                currentStatus,
                OtaUpgradeEventEnum.MANUAL_RETRY_DEVICES,
                upgradeContext
        );
        long executionTime = Duration.between(startTime, Instant.now()).toMillis();
        log.info("OTA升级任务执行完成（手动重试指定设备） - 任务ID: {}, 当前状态: {} -> 下一状态: {}, 耗时: {}ms", otaUpgradeTasksResultDTO.getId(), currentStatus, nextStatus, executionTime);
    }

    /**
     * 检查任务是否可以执行
     *
     * @param upgradeTask 升级任务
     * @return {@link Boolean} 任务是否可执行
     */
    private boolean isTaskExecutable(OtaUpgradeTasksResultDTO upgradeTask) {
        if (Objects.isNull(upgradeTask)) {
            return true;
        }

        // 检查任务状态是否为可执行状态
        OtaUpgradeTaskStatusEnum taskStatus = OtaUpgradeTaskStatusEnum.fromValue(upgradeTask.getTaskStatus()).orElse(OtaUpgradeTaskStatusEnum.PENDING);
        return !List.of(OtaUpgradeTaskStatusEnum.PENDING, OtaUpgradeTaskStatusEnum.IN_PROGRESS).contains(taskStatus);
    }

    /**
     * 是否为动态升级
     *
     * @param upgradeTask 升级任务
     * @return {@link Boolean} 是否为动态升级
     */
    private boolean isDynamicUpgrade(OtaUpgradeTasksResultDTO upgradeTask) {
        return Optional.ofNullable(upgradeTask.getUpgradeMethod())
                .flatMap(OtaUpgradeMethodEnum::fromValue)
                .map(OtaUpgradeMethodEnum.DYNAMIC::equals)
                .orElse(false);
    }

    /**
     * 创建升级上下文
     *
     * <p>使用新的构造函数创建上下文对象</p>
     *
     * @param upgradeTask 升级任务
     * @return {@link OtaUpgradeContext} 升级上下文
     * @see OtaUpgradeContext
     */
    private OtaUpgradeContext createUpgradeContext(OtaUpgradeTasksResultDTO upgradeTask) {
        return OtaUpgradeContext.builder()
                .tenantId(AuthUtil.getTenantId())
                .upgradeTask(upgradeTask)
                .build();
    }

    /**
     * 处理OTA升级任务APP确认操作
     *
     * @param taskId                   升级任务
     * @param deviceIdentificationList 设备标识列表
     * @param confirmStatusEnum        确认状态枚举
     * @return {@link DeviceOtaUpgradeAppConfirmationResultVO} 确认结果
     * @see org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum
     * @see org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum
     * @see OtaUpgradeStateMachineConfig
     */
    @Override
    public DeviceOtaUpgradeAppConfirmationResultVO otaUpgradeAppConfirmation(Long taskId, List<String> deviceIdentificationList, OtaTaskRecordAppConfirmStatusEnum confirmStatusEnum) {
        // 验证设备标识列表
        if (CollUtil.isEmpty(deviceIdentificationList)) {
            log.warn("任务ID: {}, 处理OTA升级任务APP确认操作, 设备标识列表为空", taskId);
            return new DeviceOtaUpgradeAppConfirmationResultVO()
                    .setTaskId(taskId)
                    .setDeviceIdentificationList(Collections.emptyList());
        }
        OtaUpgradeTasks otaUpgradeTasks = otaUpgradeTasksService.getById(taskId);
        if (Objects.isNull(otaUpgradeTasks)) {
            throw new ServiceException("OTA升级任务不存在");
        }
        //记录操作成功的设备标识列表
        List<String> successDeviceIdentificationList = new ArrayList<>();
        deviceIdentificationList.stream().distinct().forEach(deviceIdentification -> {
            Optional<OtaUpgradeRecordsResultVO> otaUpgradeRecordsResultVOOptional = otaUpgradeRecordsService.getByTaskIdAndDeviceIdentification(taskId, deviceIdentification);
            if (otaUpgradeRecordsResultVOOptional.isEmpty()) {
                log.warn("任务ID: {}, 设备标识: {}, 处理OTA升级任务APP确认操作, 未找到升级记录", taskId, deviceIdentification);
                return;
            }
            OtaUpgradeRecordsResultVO otaUpgradeRecordsResultVO = otaUpgradeRecordsResultVOOptional.get();
            Optional<OtaTaskRecordAppConfirmStatusEnum> currentStatusOptional = OtaTaskRecordAppConfirmStatusEnum.fromValue(otaUpgradeRecordsResultVO.getAppConfirmationStatus());
            if (currentStatusOptional.isEmpty()) {
                log.warn("任务ID: {}, 设备标识: {}, 处理OTA升级任务APP确认操作, 当前确认状态无效: {}", taskId, deviceIdentification, otaUpgradeRecordsResultVO.getAppConfirmationStatus());
                return;
            }

            OtaTaskRecordAppConfirmStatusEnum currentStatus = currentStatusOptional.get();

            // 无需确认状态，不需要处理
            if (currentStatus.equals(OtaTaskRecordAppConfirmStatusEnum.NOT_REQUIRED)) {
                log.info("任务ID: {}, 设备标识: {}, 当前状态为无需确认({}), 无需处理确认操作", taskId, deviceIdentification, currentStatus.getDesc());
                successDeviceIdentificationList.add(deviceIdentification);
                return;
            }

            // 已确认或已拒绝状态，不允许修改
            if (currentStatus.equals(OtaTaskRecordAppConfirmStatusEnum.CONFIRMED) || currentStatus.equals(OtaTaskRecordAppConfirmStatusEnum.REJECTED)) {
                log.warn("任务ID: {}, 设备标识: {}, 当前状态为{}(已确认或已拒绝), 不允许修改确认状态", taskId, deviceIdentification, currentStatus.getDesc());
                return;
            }

            // 状态相同，无需重复操作
            if (confirmStatusEnum.equals(currentStatus)) {
                log.info("任务ID: {}, 设备标识: {}, 状态已为:{}, 无需重复操作", taskId, deviceIdentification, confirmStatusEnum.getDesc());
                successDeviceIdentificationList.add(deviceIdentification);
                return;
            }

            OtaUpgradeRecordsUpdateVO otaUpgradeRecordsUpdateVO = new OtaUpgradeRecordsUpdateVO()
                    .setId(otaUpgradeRecordsResultVO.getId())
                    .setAppConfirmationStatus(confirmStatusEnum.getValue())
                    .setAppConfirmationTime(LocalDateTime.now());
            otaUpgradeRecordsService.updateOtaUpgradeRecord(otaUpgradeRecordsUpdateVO);
            log.info("任务ID: {}, 设备标识: {}, APP确认状态从{}成功修改为{}", taskId, deviceIdentification, currentStatus.getDesc(), confirmStatusEnum.getDesc());
            successDeviceIdentificationList.add(deviceIdentification);
        });

        return new DeviceOtaUpgradeAppConfirmationResultVO()
                .setTaskId(taskId)
                .setDeviceIdentificationList(successDeviceIdentificationList);
    }
}