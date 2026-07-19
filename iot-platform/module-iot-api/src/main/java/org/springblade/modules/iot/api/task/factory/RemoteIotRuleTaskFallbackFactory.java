package org.springblade.modules.iot.api.task.factory;


import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.api.task.service.RemoteIotRuleTaskService;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 规则任务远程接口熔断降级工厂
 */
@Component
public class RemoteIotRuleTaskFallbackFactory implements FallbackFactory<RemoteIotRuleTaskService> {

    @Override
    public RemoteIotRuleTaskService create(Throwable cause) {
        return new RemoteIotRuleTaskService() {
            @Override
            public TaskInfo getTask(Long id) {
                return null;
            }

            @Override
            public void updateTask(TaskInfo req) {
                // 写操作熔断无返回，可在此抛业务异常
            }

            @Override
            public PageResult<TaskInfo> selectTaskPage(TaskInfoPageReq request) {
                return new PageResult<>();
            }
        };
    }
}
