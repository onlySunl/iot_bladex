package org.springblade.modules.iot.ota.service.statemachine;

import java.util.List;
import java.util.Optional;

import com.alibaba.cola.statemachine.StateMachine;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeEventEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeMethodEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.statemachine.config.OtaUpgradeStateMachineConfig;
import org.springblade.modules.iot.ota.service.statemachine.context.OtaUpgradeContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * OTA升级状态机封装类
 * <p>
 * 封装状态机的操作，提供统一的升级任务处理接口
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/10
 */
@Slf4j
@Component("otaUpgradeStateMachine")
public class OtaUpgradeStateMachine {

    private final StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> dynamicUpgradeStateMachine;
    private final StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> staticUpgradeStateMachine;

    public OtaUpgradeStateMachine(@Qualifier(OtaUpgradeStateMachineConfig.DYNAMIC_MACHINE_ID) StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> dynamicUpgradeStateMachine,
                                  @Qualifier(OtaUpgradeStateMachineConfig.STATIC_MACHINE_ID) StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> staticUpgradeStateMachine) {
        this.dynamicUpgradeStateMachine = dynamicUpgradeStateMachine;
        this.staticUpgradeStateMachine = staticUpgradeStateMachine;
    }

    /**
     * 检查升级任务是否可以执行
     * <p>
     * 只有待发布、进行中、部分完成的任务可以执行
     * </p>
     *
     * @param upgradeTask 升级任务
     * @return {@link Boolean} 是否可以执行
     */
    public Boolean canExecuteUpgrade(OtaUpgradeTasksResultDTO upgradeTask) {
        try {
            // 检查任务状态是否为待发布或进行中
            Optional<OtaUpgradeTaskStatusEnum> otaUpgradeTaskStatusEnumOptional = OtaUpgradeTaskStatusEnum.fromValue(upgradeTask.getTaskStatus());

            if (otaUpgradeTaskStatusEnumOptional.isEmpty()) {
                log.warn("任务状态无效，无法执行 - 任务ID: {}", upgradeTask.getId());
                return false;
            }
            OtaUpgradeTaskStatusEnum currentStatus = otaUpgradeTaskStatusEnumOptional.get();
            // 只有待发布、进行中的任务可以执行
            return List.of(OtaUpgradeTaskStatusEnum.PENDING, OtaUpgradeTaskStatusEnum.IN_PROGRESS).contains(currentStatus);
        } catch (Exception e) {
            log.error("检查任务执行条件失败 - 任务ID: {}", upgradeTask.getId(), e);
            return false;
        }
    }

    /**
     * 处理升级任务
     *
     * @param upgradeTask 升级任务
     * @return 处理是否成功
     */
    public boolean processUpgradeTask(OtaUpgradeTasksResultDTO upgradeTask) {
        try {
            // 创建升级上下文
            OtaUpgradeContext context = createUpgradeContext(upgradeTask);

            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                    isDynamicUpgrade(context.getUpgradeMethod()) ? dynamicUpgradeStateMachine : staticUpgradeStateMachine;

            // 获取当前状态
            OtaUpgradeTaskStatusEnum currentStatus = OtaUpgradeTaskStatusEnum.fromValue(upgradeTask.getTaskStatus())
                    .orElse(OtaUpgradeTaskStatusEnum.PENDING);

            // 触发状态转换
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, OtaUpgradeEventEnum.START_UPGRADE, context);
            log.info("状态机处理完成 - 任务ID: {}, 当前状态: {}, 下一状态: {}", upgradeTask.getId(), currentStatus, nextStatus);
            // 根据状态转换结果判断处理是否成功
            // 只有当状态发生变化且新状态是成功的状态时才返回true
            return nextStatus != currentStatus && nextStatus.isSuccessfulStatus();
        } catch (Exception e) {
            log.error("处理升级任务失败 - 任务ID: {}", upgradeTask.getId(), e);
            return false;
        }
    }

    /**
     * 开始升级
     *
     * @param upgradeMethod 升级模式
     * @param context       升级上下文
     * @return 下一状态
     */
    public OtaUpgradeTaskStatusEnum startUpgrade(OtaUpgradeMethodEnum upgradeMethod, OtaUpgradeContext context) {
        try {
            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                    isDynamicUpgrade(upgradeMethod) ? dynamicUpgradeStateMachine : staticUpgradeStateMachine;
            // 获取当前状态
            OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();
            // 触发状态转换
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, OtaUpgradeEventEnum.START_UPGRADE, context);
            log.info("开始升级处理完成 - 任务ID: {}, 当前状态: {}, 下一状态: {}", context.getTaskId(), currentStatus, nextStatus);
            return nextStatus;
        } catch (Exception e) {
            log.error("开始升级失败 - 任务ID: {}", context.getTaskId(), e);
            return context.getCurrentStatus();
        }
    }

    /**
     * 创建升级上下文
     *
     * @param upgradeTask 升级任务
     * @return 升级上下文
     */
    private OtaUpgradeContext createUpgradeContext(OtaUpgradeTasksResultDTO upgradeTask) {
        return OtaUpgradeContext.builder()
                .tenantId(AuthUtil.getTenantId())
                .upgradeTask(upgradeTask)
                .build();
    }

    /**
     * 判断是否为动态升级
     *
     * @param upgradeMethod 升级模式枚举
     * @return 是否为动态升级
     */
    private boolean isDynamicUpgrade(OtaUpgradeMethodEnum upgradeMethod) {
        return OtaUpgradeMethodEnum.DYNAMIC.equals(upgradeMethod);
    }

    /**
     * 处理超时
     *
     * @param upgradeMethod 升级模式
     * @param context       升级上下文
     * @return 下一状态
     */
    public OtaUpgradeTaskStatusEnum handleTimeout(OtaUpgradeMethodEnum upgradeMethod, OtaUpgradeContext context) {
        try {
            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                    isDynamicUpgrade(upgradeMethod) ?
                            dynamicUpgradeStateMachine : staticUpgradeStateMachine;

            // 获取当前状态
            OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();

            // 触发状态转换 - 超时对应升级失败事件
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, OtaUpgradeEventEnum.UPGRADE_FAILED, context);

            log.info("超时处理完成 - 任务ID: {}, 当前状态: {}, 下一状态: {}", context.getTaskId(), currentStatus, nextStatus);

            return nextStatus;
        } catch (Exception e) {
            log.error("处理超时失败 - 任务ID: {}", context.getTaskId(), e);
            return context.getCurrentStatus();
        }
    }

    /**
     * 处理重试升级
     *
     * @param upgradeMethod 升级模式
     * @param context       升级上下文
     * @return 下一状态
     */
    public OtaUpgradeTaskStatusEnum retryUpgrade(OtaUpgradeMethodEnum upgradeMethod, OtaUpgradeContext context) {
        try {
            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                    isDynamicUpgrade(upgradeMethod) ? dynamicUpgradeStateMachine : staticUpgradeStateMachine;

            // 获取当前状态
            OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();

            // 触发状态转换
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, OtaUpgradeEventEnum.RETRY_UPGRADE, context);

            log.info("重试升级处理完成 - 任务ID: {}, 当前状态: {}, 下一状态: {}", context.getTaskId(), currentStatus, nextStatus);
            return nextStatus;
        } catch (Exception e) {
            log.error("处理重试升级失败 - 任务ID: {}", context.getTaskId(), e);
            return context.getCurrentStatus();
        }
    }

    /**
     * 处理升级失败
     *
     * @param upgradeMethod 升级模式
     * @param context       升级上下文
     * @return 下一状态
     */
    public OtaUpgradeTaskStatusEnum failUpgrade(OtaUpgradeMethodEnum upgradeMethod, OtaUpgradeContext context) {
        try {
            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                    isDynamicUpgrade(upgradeMethod) ?
                            dynamicUpgradeStateMachine : staticUpgradeStateMachine;

            // 获取当前状态
            OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();

            // 触发状态转换
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, OtaUpgradeEventEnum.UPGRADE_FAILED, context);

            log.info("升级失败处理完成 - 任务ID: {}, 当前状态: {}, 下一状态: {}", context.getTaskId(), currentStatus, nextStatus);
            return nextStatus;
        } catch (Exception e) {
            log.error("处理升级失败失败 - 任务ID: {}", context.getTaskId(), e);
            return context.getCurrentStatus();
        }
    }

    /**
     * 处理部分成功
     *
     * @param upgradeMethod 升级模式
     * @param context       升级上下文
     * @return 下一状态
     */
    public OtaUpgradeTaskStatusEnum partialSuccess(OtaUpgradeMethodEnum upgradeMethod, OtaUpgradeContext context) {
        try {
            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                    isDynamicUpgrade(upgradeMethod) ?
                            dynamicUpgradeStateMachine : staticUpgradeStateMachine;

            // 获取当前状态
            OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();

            // 触发状态转换
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, OtaUpgradeEventEnum.UPGRADE_COMPLETED, context);

            log.info("部分成功处理完成 - 任务ID: {}, 当前状态: {}, 下一状态: {}", context.getTaskId(), currentStatus, nextStatus);
            return nextStatus;
        } catch (Exception e) {
            log.error("处理部分成功失败 - 任务ID: {}", context.getTaskId(), e);
            return context.getCurrentStatus();
        }
    }

    /**
     * 处理升级完成
     *
     * @param upgradeMethod 升级模式
     * @param context       升级上下文
     * @return 下一状态
     */
    public OtaUpgradeTaskStatusEnum completeUpgrade(OtaUpgradeMethodEnum upgradeMethod, OtaUpgradeContext context) {
        try {
            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine =
                    isDynamicUpgrade(upgradeMethod) ? dynamicUpgradeStateMachine : staticUpgradeStateMachine;
            // 获取当前状态
            OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();
            // 触发状态转换
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, OtaUpgradeEventEnum.UPGRADE_COMPLETED, context);
            log.info("升级完成处理完成 - 任务ID: {}, 当前状态: {}, 下一状态: {}", context.getTaskId(), currentStatus, nextStatus);
            return nextStatus;
        } catch (Exception e) {
            log.error("处理升级完成失败 - 任务ID: {}", context.getTaskId(), e);
            return context.getCurrentStatus();
        }
    }

    /**
     * 触发状态机事件
     *
     * @param upgradeMethod 升级模式
     * @param context       升级上下文
     * @param event         事件枚举
     * @return 下一状态
     */
    public OtaUpgradeTaskStatusEnum fireEvent(OtaUpgradeMethodEnum upgradeMethod, OtaUpgradeContext context, OtaUpgradeEventEnum event) {
        try {
            // 根据升级模式选择对应的状态机
            StateMachine<OtaUpgradeTaskStatusEnum, OtaUpgradeEventEnum, OtaUpgradeContext> stateMachine = isDynamicUpgrade(upgradeMethod) ?
                    dynamicUpgradeStateMachine : staticUpgradeStateMachine;

            // 获取当前任务最新状态
            OtaUpgradeTaskStatusEnum currentStatus = context.getCurrentStatus();

            // 触发状态转换
            OtaUpgradeTaskStatusEnum nextStatus = stateMachine.fireEvent(currentStatus, event, context);
            log.info("状态机事件触发完成 - 任务ID: {}, 事件: {}, 当前状态: {}, 下一状态: {}", context.getTaskId(), event, currentStatus, nextStatus);
            return nextStatus;
        } catch (Exception e) {
            log.error("触发状态机事件失败 - 任务ID: {}, 事件: {}", context.getTaskId(), event, e);
            return context.getCurrentStatus();
        }
    }
}