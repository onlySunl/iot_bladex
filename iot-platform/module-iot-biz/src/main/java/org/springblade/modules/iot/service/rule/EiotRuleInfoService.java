

package org.springblade.modules.iot.service.rule;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.api.task.dto.TaskInfo;
import org.springblade.modules.iot.api.task.dto.TaskInfoPageReq;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.springblade.modules.iot.controller.admin.rule.vo.*;

/**
 * 规则引擎 Service 接口
 *
 * @author EnjoyIot
 */
public interface EiotRuleInfoService {

    /**
     * 删除规则引擎
     *
     * @param id 编号
     */
    void deleteRuleInfo(Long id);

    /**
     * 获得规则引擎
     *
     * @param id 编号
     * @return 规则引擎
     */
    RuleInfo getRuleInfo(Long id);

    /**
     * 获得规则引擎分页
     *
     * @param pageReqVO 分页查询
     * @return 规则引擎分页
     */
    PageResult<RuleInfo> getRuleInfoPage(RuleInfoPageReqVO pageReqVO);

    Long saveRule(EiotRuleInfoSaveReqVO createReqVO);

    boolean clearRuleLogByRuleId(Long ruleId);

    PageResult<TaskInfoVo> selectTaskPageList(TaskInfoPageReq request);

    Long saveTask(TaskInfoSaveReqVo taskInfo);

    void updateTask(TaskInfo taskInfo);

    TaskInfo getTask(Long id);

    Boolean pauseTask(Long id);

    Boolean resumeTask(Long id);

    Boolean renewTask(Long id);

    Boolean deleteTask(Long id);

    PageResult<TaskLog> selectTaskLogPageList(TaskLogPageReq request);

    Boolean clearTaskLogs(Long taskId);

    PageResult<RuleLogVo> selectRuleLogPage(RuleLogPageReq request);

    Boolean resumeRule(Long id);

    Boolean pauseRule(Long id);
}
