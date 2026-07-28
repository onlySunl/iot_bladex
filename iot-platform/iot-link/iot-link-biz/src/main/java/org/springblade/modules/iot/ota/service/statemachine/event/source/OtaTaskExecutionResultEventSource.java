package org.springblade.modules.iot.ota.service.statemachine.event.source;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import org.springblade.basic.base.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Description:
 * OTA任务执行结果事件源
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OtaTaskExecutionResultEventSource extends Entity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     * <p>
     * 唯一标识一个OTA升级任务
     * </p>
     */
    private Long taskId;

    /**
     * 任务名称
     * <p>
     * OTA升级任务的描述性名称
     * </p>
     */
    private String taskName;

    /**
     * 执行是否成功
     * <p>
     * true: 任务整体执行成功（可能部分设备失败）
     * false: 任务执行失败（如系统异常、参数错误等）
     * </p>
     */
    private boolean success;

    /**
     * 成功设备数量
     * <p>
     * 成功完成OTA升级的设备数量
     * </p>
     */
    private int successCount;

    /**
     * 失败设备数量
     * <p>
     * OTA升级失败的设备数量
     * </p>
     */
    private int failureCount;

    /**
     * 总设备数量
     * <p>
     * 本次任务涉及的所有设备总数
     * </p>
     */
    private int totalCount;

    /**
     * 错误信息
     * <p>
     * 当任务执行失败时的详细错误描述
     * </p>
     */
    private String errorMessage;

    /**
     * 执行耗时（毫秒）
     * <p>
     * 任务从开始执行到结束的总耗时
     * </p>
     */
    private long executionTime;

    /**
     * 失败设备列表
     * <p>
     * 记录升级失败的设备标识列表，用于后续重试或排查问题
     * </p>
     */
    private List<String> failedDevices;

    /**
     * 计算成功率
     *
     * @return 成功率（0.0 - 1.0）
     */
    public double getSuccessRate() {
        return totalCount > 0 ? (double) successCount / totalCount : 0.0;
    }

    /**
     * 计算失败率
     *
     * @return 失败率（0.0 - 1.0）
     */
    public double getFailureRate() {
        return totalCount > 0 ? (double) failureCount / totalCount : 0.0;
    }

    /**
     * 检查是否所有设备都成功
     *
     * @return true: 所有设备都成功升级
     */
    public boolean isAllSuccess() {
        return successCount == totalCount && totalCount > 0;
    }

    /**
     * 检查是否所有设备都失败
     *
     * @return true: 所有设备都升级失败
     */
    public boolean isAllFailure() {
        return failureCount == totalCount && totalCount > 0;
    }

    /**
     * 获取执行状态描述
     *
     * @return 状态描述字符串
     */
    public String getStatusDescription() {
        if (!success) {
            return "任务执行失败: " + errorMessage;
        }
        if (isAllSuccess()) {
            return "全部设备升级成功";
        }
        if (isAllFailure()) {
            return "全部设备升级失败";
        }
        return String.format("部分成功: %d/%d 设备升级成功", successCount, totalCount);
    }

    /**
     * 构建器方法 - 创建成功结果
     *
     * @param taskId        任务ID
     * @param taskName      任务名称
     * @param successCount  成功数量
     * @param totalCount    总数量
     * @param executionTime 执行时间
     * @return 事件源对象
     */
    public static OtaTaskExecutionResultEventSource success(Long taskId, String taskName,
                                                            int successCount, int totalCount,
                                                            long executionTime) {
        return OtaTaskExecutionResultEventSource.builder()
                .taskId(taskId)
                .taskName(taskName)
                .success(true)
                .successCount(successCount)
                .failureCount(totalCount - successCount)
                .totalCount(totalCount)
                .executionTime(executionTime)
                .build();
    }

    /**
     * 构建器方法 - 创建失败结果
     *
     * @param taskId        任务ID
     * @param taskName      任务名称
     * @param errorMessage  错误信息
     * @param executionTime 执行时间
     * @return 事件源对象
     */
    public static OtaTaskExecutionResultEventSource failure(Long taskId, String taskName,
                                                            String errorMessage, long executionTime) {
        return OtaTaskExecutionResultEventSource.builder()
                .taskId(taskId)
                .taskName(taskName)
                .success(false)
                .errorMessage(errorMessage)
                .executionTime(executionTime)
                .build();
    }

    /**
     * 构建器方法 - 创建详细结果
     *
     * @param taskId        任务ID
     * @param taskName      任务名称
     * @param success       是否成功
     * @param successCount  成功数量
     * @param failureCount  失败数量
     * @param totalCount    总数量
     * @param errorMessage  错误信息
     * @param executionTime 执行时间
     * @param failedDevices 失败设备列表
     * @return 事件源对象
     */
    public static OtaTaskExecutionResultEventSource detailed(Long taskId, String taskName,
                                                             boolean success, int successCount,
                                                             int failureCount, int totalCount,
                                                             String errorMessage, long executionTime,
                                                             List<String> failedDevices) {
        return OtaTaskExecutionResultEventSource.builder()
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
    }
}