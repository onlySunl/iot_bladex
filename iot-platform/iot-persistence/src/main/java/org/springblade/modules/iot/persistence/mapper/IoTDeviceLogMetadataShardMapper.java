

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.persistence.consistent.DeviceMetaShardingRouter;
import org.springblade.modules.iot.persistence.consistent.TableShard;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLogMetadata;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogMetadataVO;
import org.springblade.modules.iot.persistence.query.LogQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@TableShard(
    tableNamePrefix = "iot_device_log_metadata",
    value = "iotId",
    fieldFlag = true,
    shardStrategy = DeviceMetaShardingRouter.class)
@Mapper
public interface IoTDeviceLogMetadataShardMapper extends BaseMapper<IoTDeviceLogMetadata> {

  /**
   * 获取属性最大的条数
   *
   * @param iotId
   * @param maxStorage
   * @param property
   * @return
   */
  Integer getTopPropertiesRecord(
      @Param("iotId") String iotId,
      @Param("maxStorage") int maxStorage,
      @Param("property") String property);

  /**
   * 删除属性最大的条数
   *
   * @param iotId iotId
   * @param topId 待删除的起始id
   * @return 影响条数
   */
  int deleteTopPropertiesRecord(
      @Param("iotId") String iotId,
      @Param("topId") Integer topId,
      @Param("property") String property);

  /**
   * 获取事件最大的条数
   *
   * @param iotId
   * @param maxStorage
   * @param event
   * @return
   */
  Integer getTopEventRecord(
      @Param("iotId") String iotId,
      @Param("maxStorage") int maxStorage,
      @Param("event") String event);

  /**
   * 删除属性最大的条数
   *
   * @param iotId iotId
   * @param topId 待删除的起始id
   * @return 影响条数
   */
  int deleteTopEventRecord(
      @Param("iotId") String iotId, @Param("topId") Integer topId, @Param("event") String event);

  List<IoTDeviceLogMetadataVO> selectLogMetaList(LogQuery logQuery);

  /**
   * 获取设备事件的统计信息
   *
   * @param event 事件id
   * @param iotId 设备iotId
   */
  List<String> queryEventTotalByEventAndId(
      @Param("event") String event, @Param("iotId") String iotId);
}
