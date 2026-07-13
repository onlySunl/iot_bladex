

package org.springblade.modules.iot.databridge.core.mapper;

import org.springblade.modules.iot.pojo.entity.DataInputLog;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 数据输入日志Mapper
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
public interface DataInputLogMapper extends Mapper<DataInputLog> {

  /** 查询最近的日志 */
  List<DataInputLog> selectRecentLogs(@Param("configId") Long configId, @Param("limit") int limit);

  /** 计算成功率 */
  Double calculateSuccessRate(
      @Param("configId") Long configId,
      @Param("startTime") LocalDateTime startTime,
      @Param("endTime") LocalDateTime endTime);

  /** 删除过期日志 */
  int deleteExpiredLogs(@Param("expireTime") LocalDateTime expireTime);
}
