package org.springblade.modules.iot.controller.admin.rule;

import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.service.rule.EiotRuleInfoService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.*;
import org.springblade.modules.iot.controller.admin.rule.vo.*;

import static org.springblade.modules.iot.common.entity.CommonResult.success;

@Tag(name = "管理后台 - 规则引擎")
@RestController
@RequestMapping("/eiot/rule_engine")
@Validated
public class EiotRuleInfoController {

    @Resource
    private EiotRuleInfoService ruleEngineService;

    @PostMapping("/save")
    @Operation(summary = "保存规则引擎")
    public CommonResult<Long> createRuleInfo(@Valid @RequestBody EiotRuleInfoSaveReqVO createReqVO) {
        return success(ruleEngineService.saveRule(createReqVO));
    }

    @Operation(summary = "暂停规则")
    @PostMapping("/pause")
    public CommonResult<Boolean> pauseRule(@Validated @RequestBody IdReqVo req) {
        return success(ruleEngineService.pauseRule(req.getId()));
    }

    @Operation(summary = "恢复规则")
    @PostMapping("/resume")
    public CommonResult<Boolean> resumeRule(@Validated @RequestBody IdReqVo req) {
        return success(ruleEngineService.resumeRule(req.getId()));
    }

    @PostMapping("/delete")
    @Operation(summary = "删除规则引擎")
    public CommonResult<Boolean> deleteRuleInfo(@Validated @RequestBody IdReqVo req) {
        ruleEngineService.deleteRuleInfo(req.getId());
        return success(true);
    }

    @PostMapping("/get")
    @Operation(summary = "获得规则引擎")
    public CommonResult<EiotRuleInfoRespVO> getRuleInfo(@Validated @RequestBody IdReqVo req) {
        RuleInfo ruleInfo = ruleEngineService.getRuleInfo(req.getId());
        return success(BeanUtils.toBean(ruleInfo, EiotRuleInfoRespVO.class));
    }

    @PostMapping("/page")
    @Operation(summary = "获得规则引擎分页")
    public CommonResult<PageResult<EiotRuleInfoRespVO>> getRuleInfoPage(@Valid @RequestBody RuleInfoPageReqVO pageReqVO) {
        PageResult<RuleInfo> pageResult = ruleEngineService.getRuleInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EiotRuleInfoRespVO.class));
    }

    @Operation(summary = "规则日志")
    @PostMapping("/ruleLog/list")
    public CommonResult<PageResult<RuleLogVo>> getRuleLogs(
            @Validated @RequestBody RuleLogPageReq request
    ) {
        return success(ruleEngineService.selectRuleLogPage(request));
    }

    @Operation(summary = "清理日志")
    @PostMapping("/ruleLog/clear")
    public CommonResult<Boolean> clearRuleLogs(@Validated @RequestBody RuleIdReq request) {
        Long ruleId = request.getId();
        return success(ruleEngineService.clearRuleLogByRuleId(ruleId));
    }

    @Operation(summary = "定时任务列表")
    @PostMapping("/tasks/list")
    public CommonResult<PageResult<TaskInfoVo>> tasks(@Validated @RequestBody TaskInfoPageReq request) {
        return success(ruleEngineService.selectTaskPageList(request));
    }

    @Operation(summary = "保存定时任务")
    @PostMapping("/task/save")
    public CommonResult<Long> saveTask(@Validated @RequestBody TaskInfoSaveReqVo taskInfo) {
        return success(ruleEngineService.saveTask(taskInfo));
    }

    @Operation(summary = "停止定时任务")
    @PostMapping("/task/pause")
    public CommonResult<Boolean> pauseTask(@Validated @RequestBody IdReqVo reqVo) {

        return success(ruleEngineService.pauseTask(reqVo.getId()));
    }

    @Operation(summary = "恢复定时任务")
    @PostMapping("/task/resume")
    public CommonResult<Boolean> resumeTask(@Validated @RequestBody IdReqVo reqVo) {
        return success(ruleEngineService.resumeTask(reqVo.getId()));
    }

    @Operation(summary = "更新定时任务")
    @PostMapping("/task/renew")
    public CommonResult<Boolean> renewTask(@Validated @RequestBody IdReqVo reqVo) {
        return success(ruleEngineService.renewTask(reqVo.getId()));

    }

    @Operation(summary = "删除定时任务")
    @PostMapping("/task/delete")
    public CommonResult<Boolean> deleteTask(@Validated @RequestBody IdReqVo request) {

        return success(ruleEngineService.deleteTask(request.getId()));
    }

    @Operation(summary = "定时任务日志list")
    @PostMapping("/taskLogs/list")
    public CommonResult<PageResult<TaskLog>> getTaskLogs(
            @Validated @RequestBody TaskLogPageReq request
    ) {
        return success(ruleEngineService.selectTaskLogPageList(request));
    }

    @Operation(summary = "清除定时任务日志")
    @PostMapping("/taskLogs/clear")
    public CommonResult<Boolean> clearTaskLogs(@Validated @RequestBody TaskIdReq req) {
        return success(ruleEngineService.clearTaskLogs(req.getId()));
    }
}
