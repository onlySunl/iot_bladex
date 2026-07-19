package org.springblade.modules.iot.api.task.factory;

import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.service.RemoteIotTaskService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 任务接口熔断降级处理工厂
 */
@Component
public class RemoteIotTaskFallbackFactory implements FallbackFactory<RemoteIotTaskService> {

    @Override
    public RemoteIotTaskService create(Throwable cause) {
        return new RemoteIotTaskService() {
            @Override
            public void saveTask(TaskInfo task) {}

            @Override
            public void renewTask(TaskInfo task) {}

            @Override
            public void pauseTask(Long taskId, String reason) {}

            @Override
            public void deleteTask(Long taskId, String reason) {}

            @Override
            public void resumeTask(Long taskId, String reason) {}

            @Override
            public TaskInfo updateTaskState(Long taskId, String state, String reason) {
                return null;
            }
        };
    }
}