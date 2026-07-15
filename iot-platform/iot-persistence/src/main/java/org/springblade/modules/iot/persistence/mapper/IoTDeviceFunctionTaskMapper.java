

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceFunctionTask;
import org.springblade.modules.iot.persistence.entity.bo.IoTDeviceFunctionHistoryBO;
import org.springblade.modules.iot.persistence.entity.bo.IoTDeviceFunctionTaskBO;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceFunctionHistoryVO;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceFunctionTaskVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备功能下发任务Mapper接口
 *
 * @since 2025-12-30
 */
@Mapper
public interface IoTDeviceFunctionTaskMapper extends BladeMapper<IoTDeviceFunctionTask> {

  List<IoTDeviceFunctionTaskVO> selectTaskList(
      @Param("bo") IoTDeviceFunctionTaskBO bo, @Param("unionId") String unionId);

  List<IoTDeviceFunctionHistoryVO> queryFunctionListByTaskId(
      @Param("bo") IoTDeviceFunctionHistoryBO bo, @Param("unionId") String unionId);

  int retryTask(@Param("id") Long taskId);

  IoTDeviceFunctionTask selectOneTask(@Param("bo") IoTDeviceFunctionTask bo);
}
