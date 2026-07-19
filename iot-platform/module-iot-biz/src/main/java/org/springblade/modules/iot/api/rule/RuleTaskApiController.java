package org.springblade.modules.iot.api.rule;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 规则任务对外API控制器，与RemoteIotRuleTaskService接口一一对应
 */
@RestController
@RequestMapping("/ruleTaskApi")
@Tag(name = "规则任务API", description = "规则任务查询、更新、分页接口")
public class RuleTaskApiController extends BladeController {

    @Resource
    private RuleTaskApi ruleTaskApi;

    @GetMapping("/getTask")
    @Operation(summary = "根据任务ID查询任务详情")
    public TaskInfo getTask(@Parameter(description = "任务主键ID") @RequestParam Long id) {
        return ruleTaskApi.getTask(id);
    }

    @PostMapping("/updateTask")
    @Operation(summary = "更新任务信息")
    public void updateTask(@RequestBody TaskInfo req) {
        ruleTaskApi.updateTask(req);
    }

    @PostMapping("/selectTaskPage")
    @Operation(summary = "分页查询规则任务列表")
    public PageResult<TaskInfo> selectTaskPage(@RequestBody TaskInfoPageReq request) {
        return ruleTaskApi.selectTaskPage(request);
    }
}