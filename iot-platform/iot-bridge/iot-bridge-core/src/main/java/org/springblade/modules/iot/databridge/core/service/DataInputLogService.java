

package org.springblade.modules.iot.databridge.core.service;

import org.springblade.modules.iot.pojo.bridge.entity.DataInputLog;
import org.springblade.modules.iot.pojo.entity.DataInputLog;
import org.springblade.modules.iot.databridge.core.mapper.DataInputLogMapper;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据输入日志服务
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Service
@Slf4j
public class DataInputLogService {

  @Resource private DataInputLogMapper dataInputLogMapper;

  /** 保存日志 */
  @Transactional(rollbackFor = Exception.class)
  public void save(DataInputLog log) {
    dataInputLogMapper.insert(log);
  }

  /** 根据配置ID查询日志 */
  public List<DataInputLog> getByConfigId(Long configId) {
    DataInputLog condition = new DataInputLog();
    condition.setConfigId(configId);
    return dataInputLogMapper.selectList(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<>(condition));
  }

  /** 查询最近的日志 */
  public List<DataInputLog> getRecentLogs(Long configId, int limit) {
    return dataInputLogMapper.selectRecentLogs(configId, limit);
  }

  /** 统计成功率 */
  public Double getSuccessRate(Long configId, LocalDateTime startTime, LocalDateTime endTime) {
    return dataInputLogMapper.calculateSuccessRate(configId, startTime, endTime);
  }

  /** 删除过期日志 */
  @Transactional(rollbackFor = Exception.class)
  public int deleteExpiredLogs(LocalDateTime expireTime) {
    return dataInputLogMapper.deleteExpiredLogs(expireTime);
  }
}
