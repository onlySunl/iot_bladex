package org.springblade.modules.iot.task;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 任务基础操作对外API控制器
 * 与 {@link org.springblade.modules.iot.api.task.service.RemoteIotTaskService} 一一对应
 */
@RestController
@RequestMapping("/taskApi")
@Tag(name = "任务基础操作API", description = "新增/续期/暂停/删除/恢复/状态更新任务")
public class TaskApiController extends BladeController {

    @Resource
    private TaskService taskService;

    @PostMapping("/saveTask")
    @Operation(summary = "新增任务")
    public void saveTask(@RequestBody TaskInfo task) {
        taskService.saveTask(task);
    }

    @PostMapping("/renewTask")
    @Operation(summary = "续期任务")
    public void renewTask(@RequestBody TaskInfo task) {
        taskService.renewTask(task);
    }

    @PostMapping("/pauseTask")
    @Operation(summary = "暂停任务")
    public void pauseTask(
            @Parameter(description = "任务ID") @RequestParam Long taskId,
            @Parameter(description = "暂停原因") @RequestParam String reason
    ) {
        taskService.pauseTask(taskId, reason);
    }

    @PostMapping("/deleteTask")
    @Operation(summary = "删除任务")
    public void deleteTask(
            @Parameter(description = "任务ID") @RequestParam Long taskId,
            @Parameter(description = "删除原因") @RequestParam String reason
    ) {
        taskService.deleteTask(taskId, reason);
    }

    @PostMapping("/resumeTask")
    @Operation(summary = "恢复已暂停任务")
    public void resumeTask(
            @Parameter(description = "任务ID") @RequestParam Long taskId,
            @Parameter(description = "恢复原因") @RequestParam String reason
    ) {
        taskService.resumeTask(taskId, reason);
    }

    @PostMapping("/updateTaskState")
    @Operation(summary = "更新任务状态并返回任务信息")
    public TaskInfo updateTaskState(
            @Parameter(description = "任务ID") @RequestParam Long taskId,
            @Parameter(description = "目标状态") @RequestParam String state,
            @Parameter(description = "变更原因") @RequestParam String reason
    ) {
        return taskService.updateTaskState(taskId, state, reason);
    }
}