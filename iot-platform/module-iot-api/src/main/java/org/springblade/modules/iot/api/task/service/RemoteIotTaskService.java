package org.springblade.modules.iot.api.task.service;

import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.factory.RemoteIotTaskFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 任务基础操作远程Feign接口
 * 对齐 {@link org.springblade.modules.iot.api.task.TaskService}
 */
@FeignClient(contextId = "remoteIotTaskService",
        value = IotServiceNameConstants.IOT_TASK,
        fallbackFactory = RemoteIotTaskFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotTaskService {

    /** 新增任务 */
    @PostMapping("/api/inner/taskApi/saveTask")
    void saveTask(@RequestBody TaskInfo task);

    /** 续期任务 */
    @PostMapping("/api/inner/taskApi/renewTask")
    void renewTask(@RequestBody TaskInfo task);

    /** 暂停任务 */
    @PostMapping("/api/inner/taskApi/pauseTask")
    void pauseTask(@RequestParam("taskId") Long taskId, @RequestParam("reason") String reason);

    /** 删除任务 */
    @PostMapping("/api/inner/taskApi/deleteTask")
    void deleteTask(@RequestParam("taskId") Long taskId, @RequestParam("reason") String reason);

    /** 恢复任务 */
    @PostMapping("/api/inner/taskApi/resumeTask")
    void resumeTask(@RequestParam("taskId") Long taskId, @RequestParam("reason") String reason);

    /** 更新任务状态 */
    @PostMapping("/api/inner/taskApi/updateTaskState")
    TaskInfo updateTaskState(
            @RequestParam("taskId") Long taskId,
            @RequestParam("state") String state,
            @RequestParam("reason") String reason
    );
}