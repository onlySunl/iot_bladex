package org.springblade.modules.iot.databridge.core.service;

import java.time.LocalDateTime;
import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.pojo.bridge.entity.DataInputLog;

/**
 * DataInputLog 服务接口
 */
public interface DataInputLogService extends BladeService<DataInputLog> {

    void save(DataInputLog log);
    List<DataInputLog> getByConfigId(Long configId);
    List<DataInputLog> getRecentLogs(Long configId, int limit);
    Double getSuccessRate(Long configId, LocalDateTime startTime, LocalDateTime endTime);
    int deleteExpiredLogs(LocalDateTime expireTime);
}
