package org.springblade.modules.iot.controller.admin.rule;

import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.service.rule.IEiotRuleInfoService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.*;
import org.springblade.modules.iot.controller.admin.rule.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;



@Tag(name = "管理后台 - 规则引擎")
@RestController
@RequestMapping("/eiot/rule_engine")
@Validated
public class EiotRuleInfoController extends BladeController {

    @Resource
    private IEiotRuleInfoService ruleEngineService;

    @PostMapping("/save")
    @Operation(summary = "保存规则引擎")
    public R<Long> createRuleInfo(@Valid @RequestBody EiotRuleInfoSaveReqVO createReqVO) {
        return data(ruleEngineService.saveRule(createReqVO));
    }

    @Operation(summary = "暂停规则")
    @PostMapping("/pause")
    public R<Boolean> pauseRule(@Validated @RequestBody IdReqVo req) {
        return data(ruleEngineService.pauseRule(req.getId()));
    }

    @Operation(summary = "恢复规则")
    @PostMapping("/resume")
    public R<Boolean> resumeRule(@Validated @RequestBody IdReqVo req) {
        return data(ruleEngineService.resumeRule(req.getId()));
    }

    @PostMapping("/delete")
    @Operation(summary = "删除规则引擎")
    public R<Boolean> deleteRuleInfo(@Validated @RequestBody IdReqVo req) {
        ruleEngineService.deleteRuleInfo(req.getId());
        return data(true);
    }

    @PostMapping("/get")
    @Operation(summary = "获得规则引擎")
    public R<EiotRuleInfoRespVO> getRuleInfo(@Validated @RequestBody IdReqVo req) {
        RuleInfo ruleInfo = ruleEngineService.getRuleInfo(req.getId());
        return data(BeanUtils.toBean(ruleInfo, EiotRuleInfoRespVO.class));
    }

    @PostMapping("/page")
    @Operation(summary = "获得规则引擎分页")
    public R<PageResult<EiotRuleInfoRespVO>> getRuleInfoPage(@Valid @RequestBody RuleInfoPageReqVO pageReqVO) {
        PageResult<RuleInfo> pageResult = ruleEngineService.getRuleInfoPage(pageReqVO);
        return data(BeanUtils.toBean(pageResult, EiotRuleInfoRespVO.class));
    }

    @Operation(summary = "规则日志")
    @PostMapping("/ruleLog/list")
    public R<PageResult<RuleLogVo>> getRuleLogs(
            @Validated @RequestBody RuleLogPageReq request
    ) {
        return data(ruleEngineService.selectRuleLogPage(request));
    }

    @Operation(summary = "清理日志")
    @PostMapping("/ruleLog/clear")
    public R<Boolean> clearRuleLogs(@Validated @RequestBody RuleIdReq request) {
        Long ruleId = request.getId();
        return data(ruleEngineService.clearRuleLogByRuleId(ruleId));
    }

    @Operation(summary = "定时任务列表")
    @PostMapping("/tasks/list")
    public R<PageResult<TaskInfoVo>> tasks(@Validated @RequestBody TaskInfoPageReq request) {
        return data(ruleEngineService.selectTaskPageList(request));
    }

    @Operation(summary = "保存定时任务")
    @PostMapping("/task/save")
    public R<Long> saveTask(@Validated @RequestBody TaskInfoSaveReqVo taskInfo) {
        return data(ruleEngineService.saveTask(taskInfo));
    }

    @Operation(summary = "停止定时任务")
    @PostMapping("/task/pause")
    public R<Boolean> pauseTask(@Validated @RequestBody IdReqVo reqVo) {

        return data(ruleEngineService.pauseTask(reqVo.getId()));
    }

    @Operation(summary = "恢复定时任务")
    @PostMapping("/task/resume")
    public R<Boolean> resumeTask(@Validated @RequestBody IdReqVo reqVo) {
        return data(ruleEngineService.resumeTask(reqVo.getId()));
    }

    @Operation(summary = "更新定时任务")
    @PostMapping("/task/renew")
    public R<Boolean> renewTask(@Validated @RequestBody IdReqVo reqVo) {
        return data(ruleEngineService.renewTask(reqVo.getId()));

    }

    @Operation(summary = "删除定时任务")
    @PostMapping("/task/delete")
    public R<Boolean> deleteTask(@Validated @RequestBody IdReqVo request) {

        return data(ruleEngineService.deleteTask(request.getId()));
    }

    @Operation(summary = "定时任务日志list")
    @PostMapping("/taskLogs/list")
    public R<PageResult<TaskLog>> getTaskLogs(
            @Validated @RequestBody TaskLogPageReq request
    ) {
        return data(ruleEngineService.selectTaskLogPageList(request));
    }

    @Operation(summary = "清除定时任务日志")
    @PostMapping("/taskLogs/clear")
    public R<Boolean> clearTaskLogs(@Validated @RequestBody TaskIdReq req) {
        return data(ruleEngineService.clearTaskLogs(req.getId()));
    }
}
