

package org.springblade.modules.iot.api.task;

import org.springblade.modules.iot.api.task.dto.TaskInfo;

public interface TaskService {

    void saveTask(TaskInfo task);

    void renewTask(TaskInfo task);

    void pauseTask(Long taskId, String reason);

    void deleteTask(Long taskId, String reason);

    void resumeTask(Long taskId, String reason);

    TaskInfo updateTaskState(Long taskId, String state, String reason);

}
