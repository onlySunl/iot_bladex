

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.persistence.consistent.DeviceShardingRouter;
import org.springblade.modules.iot.persistence.consistent.TableShard;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLog;
import org.springblade.modules.iot.persistence.entity.bo.IoTDeviceLogBO;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceLogVO;
import org.springblade.modules.iot.persistence.query.LogQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@TableShard(
    tableNamePrefix = "iot_device_log",
    value = "iotId",
    fieldFlag = true,
    shardStrategy = DeviceShardingRouter.class)
@Mapper
public interface IoTDeviceLogMapper extends BaseMapper<IoTDeviceLog> {

  /** 分页查询设备日志列表 */
  List<IoTDeviceLogVO> queryLogPageList(IoTDeviceLogBO bo);

  /** 分页查询设备日志列表 */
  List<IoTDeviceLogVO> queryLogPageV2List(LogQuery logQuery);

  /** 根据主键查询 */
  IoTDeviceLogVO queryLogById(LogQuery logQuery);

  /**
   * 获取设备事件的统计信息
   *
   * @param event 事件id
   * @param iotId 设备iotId
   */
  List<String> queryEventTotalByEventAndId(
      @Param("event") String event, @Param("iotId") String iotId);

  /** 查询最新包含坐标的第二条日志 */
  IoTDeviceLog queryCoordinatesLogByIotId(@Param("iotId") String iotId);

  /** 查询日志ID - 此方法不使用分表 */
  @TableShard(
      tableNamePrefix = "iot_device_log",
      value = "",
      fieldFlag = false,
      shardStrategy = DeviceShardingRouter.class)
  Long queryLogIdByTime(@Param("tablesIndex") int tablesIndex, @Param("time") Long time);

  @TableShard(
      tableNamePrefix = "iot_device_log",
      value = "",
      fieldFlag = false,
      shardStrategy = DeviceShardingRouter.class)
  int deleteLogById(@Param("tablesIndex") int tablesIndex, @Param("id") Long id);

  @TableShard(
      tableNamePrefix = "iot_device_log",
      value = "",
      fieldFlag = false,
      shardStrategy = DeviceShardingRouter.class)
  int deleteLogByTask(
      @Param("tablesIndex") int tablesIndex,
      @Param("productKey") String productKey,
      @Param("id") Long id);

  @TableShard(
      tableNamePrefix = "iot_device_log",
      value = "",
      fieldFlag = false,
      shardStrategy = DeviceShardingRouter.class)
  Long queryLogIdByProductKeyAndTime(
      @Param("tablesIndex") int tablesIndex,
      @Param("productKey") String productKey,
      @Param("time") Long time);
}
