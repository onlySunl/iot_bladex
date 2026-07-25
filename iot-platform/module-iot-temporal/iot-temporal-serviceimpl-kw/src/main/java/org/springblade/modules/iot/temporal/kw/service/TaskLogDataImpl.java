package org.springblade.modules.iot.temporal.kw.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaiwudb.util.KWTimestamp;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.ITaskLogData;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.temporal.kw.dao.KwTaskLogMapper;
import org.springblade.modules.iot.temporal.kw.model.KwTaskLog;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TaskLogDataImpl implements ITaskLogData {

    @Autowired
    private KwTaskLogMapper taskLogMapper;

    @Override
    public void deleteByTaskId(Long taskId) {
        taskLogMapper.delete(Wrappers.lambdaQuery(KwTaskLog.class).eq(KwTaskLog::getTaskId, taskId));
    }

    @Override
    public PageResult<TaskLog> findByTaskId(Long taskId, int page, int size) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(page);
        pageParam.setPageSize(size);
        IPage<KwTaskLog> iPage = taskLogMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                Wrappers.lambdaQuery(KwTaskLog.class).eq(KwTaskLog::getTaskId, taskId).orderByDesc(KwTaskLog::getTime)
        );
        return new PageResult<>(iPage.getRecords().stream().map(r ->
                        new TaskLog(r.getTime().toString(), taskId,
                                r.getContent(), r.getSuccess(), r.getTime().getTime()))
                .collect(Collectors.toList()), iPage.getTotal());
    }

    @Override
    public void add(TaskLog log) {
        KwTaskLog taskLog = BeanUtil.copy(log, KwTaskLog.class);
        taskLog.setTime(new KWTimestamp(System.currentTimeMillis()));
        taskLogMapper.insert(taskLog);
    }

}
