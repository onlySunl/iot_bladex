

package org.springblade.modules.iot.service.rule;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springblade.modules.iot.IRuleLogData;
import org.springblade.modules.iot.ITaskLogData;
import org.springblade.modules.iot.ruleengine.rule.RuleManager;
import org.springblade.modules.iot.framework.common.exception.ServiceException;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.security.core.util.SecurityFrameworkUtils;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.api.task.TaskService;
import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.springblade.modules.iot.controller.admin.rule.vo.*;
import org.springblade.modules.iot.convert.RuleInfoConvert;
import org.springblade.modules.iot.convert.RuleLogConvert;
import org.springblade.modules.iot.convert.TaskInfoConvert;
import org.springblade.modules.iot.entity.TaskInfoDO;
import org.springblade.modules.iot.entity.EiotRuleInfoDO;
import org.springblade.modules.iot.dal.mysql.TaskInfoMapper;
import org.springblade.modules.iot.dal.mysql.ruleinfo.EiotRuleInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.stream.Collectors;


/**
 * 规则引擎 Service 实现类
 *
 * @author EnjoyIot
 */
@Slf4j
@Service
public class EiotRuleInfoServiceImpl implements EiotRuleInfoService {

    @Resource
    private RuleManager ruleManager;

    @Resource
    private TaskService taskService;

    @Resource
    private IRuleLogData ruleLogData;

    @Autowired
    private ITaskLogData taskLogData;

    @Resource
    private TaskInfoMapper taskInfoMapper;

    @Resource
    private EiotRuleInfoMapper ruleInfoMapper;

    @Override
    public Long saveRule(EiotRuleInfoSaveReqVO createReqVO) {
        // 插入
        RuleInfo ruleInfo = RuleInfoConvert.INSTANCE.edit2Info(createReqVO);
        Long id = ruleInfo.getId();
        if (ObjectUtil.isNull(id)) {
            EiotRuleInfoDO obj = RuleInfoConvert.INSTANCE.toDo(ruleInfo);
            if(ObjectUtil.isNull(obj.getState())){
                obj.setState(RuleInfo.STATE_STOPPED);
            }
            ruleInfoMapper.insert(obj);
            Long newId = obj.getId();

            ruleInfo.setId(newId);
            ruleManager.add(ruleInfo);
            return newId;
        } else {
            validateRuleInfoExists(id);

            if (RuleInfo.STATE_RUNNING.equals(ruleInfo.getState())) {
                throw new ServiceException(400, "规则已运行");
            }
            EiotRuleInfoDO obj = RuleInfoConvert.INSTANCE.toDo(ruleInfo);
            ruleInfoMapper.updateById(obj);
            // 更新规则后，同步更新缓存（即使规则是停止状态，也要更新缓存）
            ruleManager.add(ruleInfo);
            return id;
        }
        // 返回
    }

    @Override
    public boolean clearRuleLogByRuleId(Long ruleId) {
        ruleLogData.deleteByRuleId(ruleId);
        return true;
    }

    @Override
    public PageResult<TaskInfoVo> selectTaskPageList(TaskInfoPageReq request) {
        PageResult<TaskInfoDO> pageResult = taskInfoMapper.selectPage(request);

        return new PageResult<>(pageResult.getList()
                .stream().map(TaskInfoConvert.INSTANCE::convertVo).collect(Collectors.toList()), pageResult.getTotal());
    }

    @Override
    public Long saveTask(TaskInfoSaveReqVo req) {
        TaskInfoDO taskInfo = TaskInfoConvert.INSTANCE.convert(req);
        if (ObjectUtil.isNull(taskInfo.getId())) {
            taskInfo.setState(TaskInfo.STATE_STOP);
        } else {
            validateTaskExists(taskInfo.getId());
//            dataOwnerService.checkOwner(taskInfo);
        }

        Long taskInfoId = taskInfo.getId();
        if (ObjectUtil.isNull(taskInfoId)) {
            taskInfoMapper.insert(taskInfo);
            return taskInfo.getId();
        } else {
            taskInfoMapper.updateById(taskInfo);
            return taskInfoId;
        }
    }

    @Override
    public void updateTask(TaskInfo taskInfo) {
        TaskInfoDO taskInfoDO = TaskInfoConvert.INSTANCE.convert(taskInfo);
        taskInfoMapper.updateById(taskInfoDO);
    }

    @Override
    public TaskInfo getTask(Long id) {
        return TaskInfoConvert.INSTANCE.convert(taskInfoMapper.selectById(id));
    }

    @Override
    public Boolean pauseTask(Long taskId) {
        validateTaskExists(taskId);
//        dataOwnerService.checkOwner(taskInfo);
        taskService.pauseTask(taskId, "stop by " + SecurityFrameworkUtils.getLoginUserId());
        return true;
    }

    @Override
    public Boolean resumeTask(Long taskId) {
        validateTaskExists(taskId);
//        dataOwnerService.checkOwner(taskInfo);
        taskService.resumeTask(taskId, "resume by " + SecurityFrameworkUtils.getLoginUserId());
        return true;
    }

    @Override
    public Boolean renewTask(Long taskId) {
        TaskInfoDO taskInfoDO = validateTaskExists(taskId);
        TaskInfo taskInfo = TaskInfoConvert.INSTANCE.convert(taskInfoDO);
        try {
            taskService.renewTask(taskInfo);
            taskService.updateTaskState(taskId, TaskInfo.STATE_RUNNING, "renew by " + SecurityFrameworkUtils.getLoginUserId());
        } catch (Exception e) {
            log.error("renew task error", e);
            throw new ServiceException(400, "任务重启异常");
        }
        return true;
    }

    @Override
    public Boolean deleteTask(Long taskId) {
        validateTaskExists(taskId);

        taskService.deleteTask(taskId, "delete by " + SecurityFrameworkUtils.getLoginUserId());
        taskInfoMapper.deleteById(taskId);
        try {
            taskLogData.deleteByTaskId(taskId);
        } catch (Throwable e) {
            log.error("delete task logs failed", e);
        }
        return true;
    }

    @Override
    public PageResult<TaskLog> selectTaskLogPageList(TaskLogPageReq request) {
        return taskLogData.findByTaskId(request.getTaskId(), request.getPageNo(), request.getPageSize());
    }

    @Override
    public Boolean clearTaskLogs(Long taskId) {
        taskLogData.deleteByTaskId(taskId);
        return true;
    }

    @Override
    public PageResult<RuleLogVo> selectRuleLogPage(RuleLogPageReq request) {
        return RuleLogConvert.INSTANCE.convertPage(ruleLogData.findByRuleId(request.getRuleId(), request.getPageNo(), request.getPageSize()));
    }

    @Override
    public Boolean resumeRule(Long ruleId) {
        validateRuleInfoExists(ruleId);

        //更新状态
        updateState(ruleId, RuleInfo.STATE_RUNNING);
        RuleInfo ruleInfo = getRuleInfo(ruleId);
        ruleManager.resume(ruleInfo);
        return true;
    }

    @Override
    public Boolean pauseRule(Long id) {
        validateRuleInfoExists(id);
        //更新状态
        updateState(id, RuleInfo.STATE_STOPPED);
        ruleManager.pause(id);
        return true;
    }

    private void updateState(Long ruleId, Integer state) {
        LambdaUpdateWrapper<EiotRuleInfoDO> up = new LambdaUpdateWrapper<>();
        up.set(EiotRuleInfoDO::getState, state);
        up.eq(EiotRuleInfoDO::getId, ruleId);
        ruleInfoMapper.update(null, up);
    }

    @Override
    public void deleteRuleInfo(Long ruleId) {
        try {
            validateRuleInfoExists(ruleId);
            ruleInfoMapper.deleteById(ruleId);
            ruleManager.remove(ruleId);
            ruleLogData.deleteByRuleId(ruleId);
        } catch (Throwable e) {
            log.warn("删除失败", e);
        }
    }

    @Override
    public RuleInfo getRuleInfo(Long id) {
        return RuleInfoConvert.INSTANCE.convert(ruleInfoMapper.selectById(id));
    }

    @Override
    public PageResult<RuleInfo> getRuleInfoPage(RuleInfoPageReqVO pageReqVO) {
        PageResult<EiotRuleInfoDO> result = ruleInfoMapper.selectPage(pageReqVO);
        return RuleInfoConvert.INSTANCE.convertPage(result);
    }

    private TaskInfoDO validateTaskExists(Long taskId) {
        TaskInfoDO oldTask = taskInfoMapper.selectById(taskId);
        if (oldTask == null) {
            throw new ServiceException(400, "任务不存在");
        }
        return oldTask;
    }

    private EiotRuleInfoDO validateRuleInfoExists(Long id) {
        EiotRuleInfoDO eiotRuleInfoDO = ruleInfoMapper.selectById(id);
        if (eiotRuleInfoDO == null) {
            throw new ServiceException(400, "规则不存在");
        }
        return eiotRuleInfoDO;
    }
}
