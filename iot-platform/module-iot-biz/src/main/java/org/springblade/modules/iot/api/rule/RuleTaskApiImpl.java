package org.springblade.modules.iot.api.rule;

import org.springblade.modules.iot.api.task.RuleTaskApi;
import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.rule.vo.TaskInfoVo;
import org.springblade.modules.iot.convert.TaskInfoConvert;
import org.springblade.modules.iot.service.rule.EiotRuleInfoService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.stream.Collectors;

@Service
public class RuleTaskApiImpl implements RuleTaskApi {

    @Resource
    private EiotRuleInfoService ruleInfoService;

    @Override
    public TaskInfo getTask(Long id) {
        return ruleInfoService.getTask(id);
    }

    @Override
    public void updateTask(TaskInfo req) {
        ruleInfoService.updateTask(req);
    }

    @Override
    public PageResult<TaskInfo> selectTaskPage(TaskInfoPageReq request) {
        PageResult<TaskInfoVo> pageResult = ruleInfoService.selectTaskPageList(request);
        return new PageResult<>(pageResult.getList().stream()
                .map(TaskInfoConvert.INSTANCE::convert).collect(Collectors.toList()),
                pageResult.getTotal());
    }

}
