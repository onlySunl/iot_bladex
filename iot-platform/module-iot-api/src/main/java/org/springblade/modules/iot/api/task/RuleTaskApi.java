

package org.springblade.modules.iot.api.task;

import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.common.entity.PageResult;

public interface RuleTaskApi {

    TaskInfo getTask(Long id);

    void updateTask(TaskInfo req);

    PageResult<TaskInfo> selectTaskPage(TaskInfoPageReq request);

}
