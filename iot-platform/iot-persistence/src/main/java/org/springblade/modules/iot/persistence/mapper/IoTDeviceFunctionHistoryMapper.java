

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceFunctionHistory;
import org.springblade.modules.iot.persistence.entity.bo.IoTDeviceFunctionTaskBO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备功能下发任务Mapper接口
 *
 * @since 2025-12-30
 */
@Mapper
public interface IoTDeviceFunctionHistoryMapper extends BladeMapper<IoTDeviceFunctionHistory> {

  int retryHistory(@Param("bo") IoTDeviceFunctionTaskBO ioTDeviceFunctionTaskBO);

  void batchInsert(@Param("list") List<IoTDeviceFunctionHistory> histories);
}
