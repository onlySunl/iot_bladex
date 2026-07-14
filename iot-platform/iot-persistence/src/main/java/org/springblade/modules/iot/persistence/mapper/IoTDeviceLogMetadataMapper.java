

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLogMetadata;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceLogMetadataVO;
import org.springblade.modules.iot.persistence.query.LogQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IoTDeviceLogMetadataMapper extends BaseMapper<IoTDeviceLogMetadata> {

  /**
   * 删除属性最大的条数
   *
   * @param iotId iotId
   * @param maxStorage 最大存储条数
   * @return 影响条数
   */
  int deleteTopPropertiesRecord(
      @Param("iotId") String iotId,
      @Param("maxStorage") int maxStorage,
      @Param("property") String property);

  /**
   * 删除属性最大的条数
   *
   * @param iotId iotId
   * @param maxStorage 最大存储条数
   * @return 影响条数
   */
  int deleteTopEventRecord(
      @Param("iotId") String iotId,
      @Param("maxStorage") int maxStorage,
      @Param("event") String event);

  List<IoTDeviceLogMetadataVO> selectLogMetaList(LogQuery logQuery);

  /**
   * 获取设备事件的统计信息
   *
   * @param event 事件id
   * @param iotId 设备iotId
   */
  List<String> queryEventTotalByEventAndId(
      @Param("event") String event, @Param("iotId") String iotId);

  Long queryLogMetaIdByTime(@Param("tablesIndex") int tablesIndex, @Param("time") Long time);

  int deleteLogMetaById(@Param("tablesIndex") int tablesIndex, @Param("id") Long id);

  int deleteLogMetaByTask(
      @Param("tablesIndex") int tablesIndex,
      @Param("productKey") String productKey,
      @Param("id") Long id);

  Long queryLogMetaIdByProductKeyAndTime(
      @Param("tablesIndex") int tablesIndex,
      @Param("productKey") String productKey,
      @Param("time") Long time);
}
