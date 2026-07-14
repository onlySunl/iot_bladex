

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceFenceRel;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceGeoFenceVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备和围栏中间表 @Author gitee.com/NexIoT
 *
 * @since 2023/8/5 8:54
 */
@Mapper
public interface IoTDeviceFenceRelMapper extends BaseMapper<IoTDeviceFenceRel> {

  int deleteFenceInstance(@Param("iotId") String iotId);

  int deleteDeviceIdAndFenceId(@Param("deviceId") String deviceId, @Param("fenceId") Long fenceId);

  List<IoTDeviceGeoFenceVO> selectFenceByIotId(@Param("iotId") String iotId);
}
