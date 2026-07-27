package org.springblade.modules.iot.ota.service.statemachine.event.publisher;

import java.util.List;

import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.service.statemachine.event.OtaTaskExecutionEvent;
import org.springblade.modules.iot.ota.service.statemachine.event.OtaTaskExecutionResultEvent;
import org.springblade.modules.iot.ota.service.statemachine.event.source.OtaTaskExecutionEventSource;
import org.springblade.modules.iot.ota.service.statemachine.event.source.OtaTaskExecutionResultEventSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * OTA事件发布器
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
@AllArgsConstructor
public class OtaEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    /**
     * 发布OTA任务升级执行事件
     *
     * @param upgradeTask              升级任务
     * @param upgradePackage           升级包
     * @param deviceIdentificationList 设备标识列表
     */
    public void publishOtaTaskUpgradeExecutionEvent(OtaUpgradeTasksResultDTO upgradeTask,
                                                    OtaUpgradesResultDTO upgradePackage,
                                                    List<String> deviceIdentificationList) {
        try {
            OtaTaskExecutionEventSource source = OtaTaskExecutionEventSource.builder()
                    .upgradeTask(upgradeTask)
                    .upgradePackage(upgradePackage)
                    .deviceIdentificationList(deviceIdentificationList)
                    .build();

            OtaTaskExecutionEvent event = new OtaTaskExecutionEvent(source);
            eventPublisher.publishEvent(event);
            log.info("发布OTA任务升级执行事件成功 - 任务ID: {}, 设备数量: {}", upgradeTask.getId(), deviceIdentificationList.size());
        } catch (Exception e) {
            log.error("发布OTA任务升级执行事件失败 - 任务ID: {}", upgradeTask.getId(), e);
        }
    }

    /**
     * 发布OTA任务升级执行结果事件
     *
     * @param taskId        任务ID
     * @param taskName      任务名称
     * @param success       是否成功
     * @param successCount  成功数量
     * @param failureCount  失败数量
     * @param totalCount    总数量
     * @param errorMessage  错误信息
     * @param executionTime 执行时间(毫秒)
     * @param failedDevices 失败设备列表
     */
    public void publishOtaTaskExecutionResultEvent(Long taskId,
                                                   String taskName,
                                                   boolean success,
                                                   int successCount,
                                                   int failureCount,
                                                   int totalCount,
                                                   String errorMessage,
                                                   long executionTime,
                                                   List<String> failedDevices) {
        try {
            OtaTaskExecutionResultEventSource source = OtaTaskExecutionResultEventSource.builder()
                    .taskId(taskId)
                    .taskName(taskName)
                    .success(success)
                    .successCount(successCount)
                    .failureCount(failureCount)
                    .totalCount(totalCount)
                    .errorMessage(errorMessage)
                    .executionTime(executionTime)
                    .failedDevices(failedDevices)
                    .build();
            OtaTaskExecutionResultEvent event = new OtaTaskExecutionResultEvent(source);
            eventPublisher.publishEvent(event);
            log.info("发布OTA任务执行结果事件成功 - 任务ID: {}, 成功: {}/{}", taskId, successCount, totalCount);
        } catch (Exception e) {
            log.error("发布OTA任务执行结果事件失败 - 任务ID: {}", taskId, e);
        }
    }

    /**
     * 发布成功结果事件
     *
     * @param taskId        任务ID
     * @param taskName      任务名称
     * @param successCount  成功数量
     * @param totalCount    总数量
     * @param executionTime 执行时间(毫秒)
     */
    public void publishSuccessResultEvent(Long taskId, String taskName,
                                          int successCount, int totalCount,
                                          long executionTime) {
        OtaTaskExecutionResultEventSource source = OtaTaskExecutionResultEventSource.success(
                taskId, taskName, successCount, totalCount, executionTime);
        OtaTaskExecutionResultEvent event = new OtaTaskExecutionResultEvent(source);
        eventPublisher.publishEvent(event);
        log.info("发布OTA任务成功结果事件 - 任务ID: {}, 成功率: {}/{}", taskId, successCount, totalCount);
    }

    /**
     * 发布失败结果事件
     *
     * @param taskId        任务ID
     * @param taskName      任务名称
     * @param errorMessage  错误信息
     * @param executionTime 执行时间(毫秒)
     */
    public void publishFailureResultEvent(Long taskId, String taskName,
                                          String errorMessage, long executionTime) {
        OtaTaskExecutionResultEventSource source = OtaTaskExecutionResultEventSource.failure(
                taskId, taskName, errorMessage, executionTime);
        OtaTaskExecutionResultEvent event = new OtaTaskExecutionResultEvent(source);
        eventPublisher.publishEvent(event);
        log.info("发布OTA任务失败结果事件 - 任务ID: {}, 错误: {}", taskId, errorMessage);
    }

}
