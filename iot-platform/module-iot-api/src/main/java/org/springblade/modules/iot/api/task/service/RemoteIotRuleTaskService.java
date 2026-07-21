package org.springblade.modules.iot.api.task.service;

import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.api.task.factory.RemoteIotRuleTaskFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IOT设备远程Feign调用接口，对齐 {@link RuleTaskApi} 全部能力
 *
 * @FileName RemoteIotRuleService
 * @Description 跨服务调用iot设备相关缓存/注册/鉴权/子设备/属性配置接口
 * @Author fengcheng
 * @date 2026-03-28
 **/
@FeignClient(contextId = "remoteIotRuleTaskService",
        value = IotServiceNameConstants.IOT_RULE_TASK,
        fallbackFactory = RemoteIotRuleTaskFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotRuleTaskService {

    /**
     * 根据任务ID获取任务详情
     */
    @GetMapping("/api/inner/ruleTaskApi/getTask")
    TaskInfo getTask(@RequestParam("id") Long id);

    /**
     * 更新任务信息
     */
    @PostMapping("/api/inner/ruleTaskApi/updateTask")
    void updateTask(@RequestBody TaskInfo req);

    /**
     * 分页查询任务列表
     */
    @PostMapping("/api/inner/ruleTaskApi/selectTaskPage")
    PageResult<TaskInfo> selectTaskPage(@RequestBody TaskInfoPageReq request);
}
