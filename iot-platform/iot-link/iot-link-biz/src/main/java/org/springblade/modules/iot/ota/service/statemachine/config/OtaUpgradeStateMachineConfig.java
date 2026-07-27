package org.springblade.modules.iot.ota.service.statemachine.config;

import java.util.Objects;

import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.FailCallback;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.action.AppConfirmationConfirmedAction;
import org.springblade.modules.iot.ota.service.statemachine.action.AppConfirmationNoRequiredAction;
import org.springblade.modules.iot.ota.service.statemachine.action.AppConfirmationPendingAction;
import org.springblade.modules.iot.ota.service.statemachine.action.AppConfirmationRejectAction;
import org.springblade.modules.iot.ota.service.statemachine.action.DynamicCancelUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.DynamicCompleteUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.DynamicDeviceOfflineAction;
import org.springblade.modules.iot.ota.service.statemachine.action.DynamicDeviceOnlineAction;
import org.springblade.modules.iot.ota.service.statemachine.action.DynamicRetryUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.DynamicStartUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.DynamicTimeoutAction;
import org.springblade.modules.iot.ota.service.statemachine.action.ManualRetryDevicesAction;
import org.springblade.modules.iot.ota.service.statemachine.action.StaticCancelUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.StaticCompleteUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.StaticFailUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.StaticRetryUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.StaticStartUpgradeAction;
import org.springblade.modules.iot.ota.service.statemachine.action.StaticTimeoutAction;
import org.springblade.modules.iot.ota.service.statemachine.condition.DynamicUpgradeCondition;
import org.springblade.modules.iot.ota.service.statemachine.condition.StaticUpgradeCondition;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OTA升级状态机配置类
 * 重新设计状态机流转逻辑，确保流程连贯且与任务状态紧密对应
 *
 * <p><strong>设计原则：</strong></p>
 * <ul>
 *   <li><strong>静态升级</strong>：基于触发时的设备范围执行，执行完成后进入终结态</li>
 *   <li><strong>动态升级</strong>：任务持续处于进行中状态，动态维护范围内的设备</li>
 *   <li><strong>事件精简</strong>：保留核心事件，去除冗余事件类型</li>
 *   <li><strong>流转连贯</strong>：状态转换与实际业务逻辑紧密结合</li>
 * </ul>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @see OtaUpgradeEventEnum
 * @see OtaUpgradeTaskStatusEnum
 * @since 2025/11/10
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class OtaUpgradeStateMachineConfig {

    /**
     * 动态升级状态机ID
     */
    public final static String DYNAMIC_MACHINE_ID = "dynamicOtaUpgradeStateMachine";
    /**
     * 静态升级状态机ID
     */
    public final static String STATIC_MACHINE_ID = "staticOtaUpgradeStateMachine";

    // 动态升级条件类
    private final DynamicUpgradeCondition dynamicUpgradeCondition;
    // 静态升级条件类
    private final StaticUpgradeCondition staticUpgradeCondition;

    // APP确认Action类
    private final AppConfirmationPendingAction appConfirmationPendingAction;
    private final AppConfirmationNoRequiredAction appConfirmationNoRequiredAction;
    private final AppConfirmationConfirmedAction appConfirmationConfirmedAction;
    private final AppConfirmationRejectAction appConfirmationRejectAction;

    // 动态升级Action类
    private final DynamicStartUpgradeAction dynamicStartUpgradeAction;
    private final DynamicDeviceOnlineAction dynamicDeviceOnlineAction;
    private final DynamicDeviceOfflineAction dynamicDeviceOfflineAction;
    private final DynamicCancelUpgradeAction dynamicCancelUpgradeAction;
    private final DynamicCompleteUpgradeAction dynamicCompleteUpgradeAction;
    private final DynamicTimeoutAction dynamicTimeoutAction;
    private final DynamicRetryUpgradeAction dynamicRetryUpgradeAction;

    // 静态升级Action类
    private final StaticStartUpgradeAction staticStartUpgradeAction;
    private final StaticCancelUpgradeAction staticCancelUpgradeAction;
    private final StaticTimeoutAction staticTimeoutAction;
    private final StaticCompleteUpgradeAction staticCompleteUpgradeAction;
    private final StaticFailUpgradeAction staticFailUpgradeAction;
    private final StaticRetryUpgradeAction staticRetryUpgradeAction;

    // 手动重试指定设备Action类
    private final ManualRetryDevicesAction manualRetryDevicesAction;

    /**
     * 动态升级状态机
     * 特点：任务持续处于进行中状态，动态维护范围内的设备，到达计划结束时间后自动完成
     *
     * <p><strong>状态流转逻辑：</strong></p>
     * <ul>
     *   <li>PENDING → IN_PROGRESS：开始升级</li>
     *   <li>IN_PROGRESS → IN_PROGRESS：设备上线/离线/结果上报/APP确认等内联事件</li>
     *   <li>IN_PROGRESS → COMPLETED：达到计划结束时间或超时</li>
     *   <li>IN_PROGRESS → CANCELLED：手动取消</li>
     *   <li>IN_PROGRESS → FAILED：超时失败</li>
     *   <li>FAILED → IN_PROGRESS：重试升级</li>
     * </ul>
     *
     * @return 动态升级状态机实例
     * @see #configureDynamicUpgradeTransitions(StateMachineBuilder)
     */
    @Bean(DYNAMIC_MACHINE_ID)
    public StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> dynamicUpgradeStateMachine() {
        StateMachineBuilder<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> builder = StateMachineBuilderFactory.create();

        configureDynamicUpgradeTransitions(builder);
        builder.setFailCallback(failCallback());

        StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine = builder.build(DYNAMIC_MACHINE_ID);
        log.info("OTA动态升级状态机初始化完成");
        return stateMachine;
    }

    /**
     * 静态升级状态机
     * 特点：基于触发时的设备范围执行，执行完成后进入终结态
     *
     * <p><strong>状态流转逻辑：</strong></p>
     * <ul>
     *   <li>PENDING → IN_PROGRESS：开始升级</li>
     *   <li>IN_PROGRESS → IN_PROGRESS：设备结果上报/APP确认等内联事件</li>
     *   <li>IN_PROGRESS → COMPLETED：升级成功</li>
     *   <li>IN_PROGRESS → FAILED：升级失败/超时</li>
     *   <li>IN_PROGRESS → CANCELLED：手动取消</li>
     *   <li>FAILED → IN_PROGRESS：重试升级</li>
     * </ul>
     *
     * @return 静态升级状态机实例
     * @see #configureStaticUpgradeTransitions(StateMachineBuilder)
     */
    @Bean(STATIC_MACHINE_ID)
    public StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> staticUpgradeStateMachine() {
        StateMachineBuilder<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> builder = StateMachineBuilderFactory.create();

        configureStaticUpgradeTransitions(builder);
        builder.setFailCallback(failCallback());

        StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine = builder.build(STATIC_MACHINE_ID);
        log.info("OTA静态升级状态机初始化完成");
        return stateMachine;
    }

    /**
     * 配置动态升级状态转换
     *
     * @param builder 状态机构建器
     * @see DynamicUpgradeCondition
     */
    private void configureDynamicUpgradeTransitions(StateMachineBuilder<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> builder) {
        // 开始升级：PENDING → IN_PROGRESS
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.PENDING)
                .to(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.START_UPGRADE)
                .when(dynamicUpgradeCondition::canStartDynamicUpgrade)
                .perform(dynamicStartUpgradeAction);

        // 重新开始升级：IN_PROGRESS → IN_PROGRESS（内联）- 处理设备中间失败情况
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.START_UPGRADE)
                .when(dynamicUpgradeCondition::isDynamicUpgrade)
                .perform(dynamicStartUpgradeAction);

        // 设备上线：IN_PROGRESS → IN_PROGRESS（内联）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.DEVICE_ONLINE)
                .when(dynamicUpgradeCondition::canHandleDeviceEvent)
                .perform(dynamicDeviceOnlineAction);

        // 设备离线：IN_PROGRESS → IN_PROGRESS（内联）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.DEVICE_OFFLINE)
                .when(dynamicUpgradeCondition::canHandleDeviceEvent)
                .perform(dynamicDeviceOfflineAction);

        // 无需APP确认升级：IN_PROGRESS → IN_PROGRESS（使用单独Action类）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_NO_REQUIRED)
                .when(dynamicUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationNoRequiredAction);

        // APP确认升级：IN_PROGRESS → IN_PROGRESS（使用单独Action类）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE)
                .when(dynamicUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationConfirmedAction);

        // APP拒绝升级：IN_PROGRESS → IN_PROGRESS（使用单独Action类）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_REJECT_UPGRADE)
                .when(dynamicUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationRejectAction);

        // APP确认升级待处理：IN_PROGRESS → IN_PROGRESS（内联）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_PENDING)
                .when(dynamicUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationPendingAction);

        // 手动重试指定设备：PENDING → IN_PROGRESS（外部转换）
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.PENDING)
                .to(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.MANUAL_RETRY_DEVICES)
                .when(dynamicUpgradeCondition::isDynamicUpgrade)
                .perform(manualRetryDevicesAction);

        // 手动重试指定设备：IN_PROGRESS → IN_PROGRESS（内联转换）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.MANUAL_RETRY_DEVICES)
                .when(dynamicUpgradeCondition::isDynamicUpgrade)
                .perform(manualRetryDevicesAction);

        // 升级完成：IN_PROGRESS → COMPLETED（达到计划结束时间或超时）
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .to(OtaUpgradeTaskStatusEnum.COMPLETED)
                .on(OtaUpgradeEventEnum.UPGRADE_COMPLETED)
                .when(dynamicUpgradeCondition::isDynamicUpgrade)
                .perform(dynamicCompleteUpgradeAction);

        // 手动取消：IN_PROGRESS → CANCELLED
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .to(OtaUpgradeTaskStatusEnum.CANCELLED)
                .on(OtaUpgradeEventEnum.MANUAL_CANCEL)
                .when(dynamicUpgradeCondition::isDynamicUpgrade)
                .perform(dynamicCancelUpgradeAction);

        // 超时处理：IN_PROGRESS → FAILED
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .to(OtaUpgradeTaskStatusEnum.FAILED)
                .on(OtaUpgradeEventEnum.TIMEOUT)
                .when(dynamicUpgradeCondition::isDynamicUpgrade)
                .perform(dynamicTimeoutAction);

        // 重试升级：FAILED → IN_PROGRESS
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.FAILED)
                .to(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.RETRY_UPGRADE)
                .when(dynamicUpgradeCondition::canRetryDynamicUpgrade)
                .perform(dynamicRetryUpgradeAction);
    }

    /**
     * 配置静态升级状态转换
     *
     * @param builder 状态机构建器
     * @see StaticUpgradeCondition
     */
    private void configureStaticUpgradeTransitions(StateMachineBuilder<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> builder) {
        // 开始升级：PENDING → IN_PROGRESS
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.PENDING)
                .to(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.START_UPGRADE)
                .when(staticUpgradeCondition::canStartStaticUpgrade)
                .perform(staticStartUpgradeAction);

        // 重新开始升级：IN_PROGRESS → IN_PROGRESS（内联）- 处理设备中间失败情况
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.START_UPGRADE)
                .when(staticUpgradeCondition::isStaticUpgrade)
                .perform(staticStartUpgradeAction);

        // 无需APP确认升级：IN_PROGRESS → IN_PROGRESS（使用单独Action类）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_NO_REQUIRED)
                .when(staticUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationNoRequiredAction);

        // APP确认升级：IN_PROGRESS → IN_PROGRESS（使用单独Action类）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE)
                .when(staticUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationConfirmedAction);

        // APP拒绝升级：IN_PROGRESS → IN_PROGRESS（使用单独Action类）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_REJECT_UPGRADE)
                .when(staticUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationRejectAction);

        // APP确认升级待处理：IN_PROGRESS → IN_PROGRESS（内联）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.APP_CONFIRM_UPGRADE_PENDING)
                .when(staticUpgradeCondition::canHandleDeviceEvent)
                .perform(appConfirmationPendingAction);

        // 手动重试指定设备：PENDING → IN_PROGRESS（外部转换）
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.PENDING)
                .to(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.MANUAL_RETRY_DEVICES)
                .when(staticUpgradeCondition::isStaticUpgrade)
                .perform(manualRetryDevicesAction);

        // 手动重试指定设备：IN_PROGRESS → IN_PROGRESS（内联转换）
        builder.internalTransition()
                .within(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.MANUAL_RETRY_DEVICES)
                .when(staticUpgradeCondition::isStaticUpgrade)
                .perform(manualRetryDevicesAction);

        // 升级成功：IN_PROGRESS → COMPLETED
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .to(OtaUpgradeTaskStatusEnum.COMPLETED)
                .on(OtaUpgradeEventEnum.UPGRADE_COMPLETED)
                .when(staticUpgradeCondition::isStaticUpgrade)
                .perform(staticCompleteUpgradeAction);

        // 升级失败：IN_PROGRESS → FAILED
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .to(OtaUpgradeTaskStatusEnum.FAILED)
                .on(OtaUpgradeEventEnum.UPGRADE_FAILED)
                .when(staticUpgradeCondition::isStaticUpgrade)
                .perform(staticFailUpgradeAction);

        // 手动取消：IN_PROGRESS → CANCELLED
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .to(OtaUpgradeTaskStatusEnum.CANCELLED)
                .on(OtaUpgradeEventEnum.MANUAL_CANCEL)
                .when(staticUpgradeCondition::isStaticUpgrade)
                .perform(staticCancelUpgradeAction);

        // 超时处理：IN_PROGRESS → FAILED
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .to(OtaUpgradeTaskStatusEnum.FAILED)
                .on(OtaUpgradeEventEnum.TIMEOUT)
                .when(staticUpgradeCondition::isStaticUpgrade)
                .perform(staticTimeoutAction);

        // 重试升级：FAILED → IN_PROGRESS
        builder.externalTransition()
                .from(OtaUpgradeTaskStatusEnum.FAILED)
                .to(OtaUpgradeTaskStatusEnum.IN_PROGRESS)
                .on(OtaUpgradeEventEnum.RETRY_UPGRADE)
                .when(staticUpgradeCondition::canRetryStaticUpgrade)
                .perform(staticRetryUpgradeAction);
    }

    /**
     * 状态机失败回调方法
     * 当状态机流转失败时触发，记录详细的错误信息并抛出异常
     *
     * @return 失败回调函数
     */
    private FailCallback<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> failCallback() {
        return (statusEnum, event, context) -> {
            // 安全获取状态和事件信息
            String currentStatus = Objects.nonNull(statusEnum) ? statusEnum.getDesc() : "未知状态";
            String currentStatusCode = Objects.nonNull(statusEnum) ? statusEnum.name() : "UNKNOWN";
            String eventName = Objects.nonNull(event) ? event.name() : "未知事件";

            // 构建错误消息
            StringBuilder errorMsg = new StringBuilder("OTA升级状态机流转失败");
            errorMsg.append(" | 当前状态: ").append(currentStatus).append("(").append(currentStatusCode).append(")");
            errorMsg.append(" | 触发事件: ").append(eventName);

            // 安全获取上下文信息
            if (Objects.nonNull(context)) {
                Long taskId = context.getTaskId();
                errorMsg.append(" | 任务ID: ").append(Objects.nonNull(taskId) ? taskId : "未知");

                // 获取升级方式（枚举类型）
                try {
                    errorMsg.append(" | 升级方式: ").append(context.getUpgradeMethod());
                } catch (Exception e) {
                    errorMsg.append(" | 升级方式: 获取失败");
                }

                // 诊断可能的失败原因
                if (Objects.isNull(context.getUpgradeTask())) {
                    errorMsg.append(" | 失败原因: 升级任务对象为空");
                } else if (Objects.isNull(context.getUpgradeTask().getUpgradeMethod())) {
                    errorMsg.append(" | 失败原因: 升级方式未设置");
                } else {
                    errorMsg.append(" | 失败原因: 不满足状态流转条件或配置错误");
                }

                // 添加错误设备信息
                int errorDeviceCount = context.getErrorDeviceCount();
                if (errorDeviceCount > 0) {
                    errorMsg.append(" | 错误设备数: ").append(errorDeviceCount);
                }
            } else {
                errorMsg.append(" | 失败原因: 上下文对象为空");
            }
            // 记录详细的错误日志
            log.error("状态机流转异常: {}", errorMsg);
            // 抛出明确的状态异常
            throw new IllegalStateException(errorMsg.toString());
        };
    }
}