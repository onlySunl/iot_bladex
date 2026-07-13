

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceGeoFence;
import org.springblade.modules.iot.pojo.vo.IoTDeviceGeoFenceVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备围栏表 @Author gitee.com/NexIoT
 *
 * @since 2023/8/5 8:54
 */
@Mapper
public interface IoTDeviceGeoFenceMapper extends BaseMapper<IoTDeviceGeoFence> {

  List<IoTDeviceGeoFence> selectByIotId(
      @Param("iotId") String iotId, @Param("appUnionId") String appUnionId);

  List<IoTDeviceGeoFenceVO> selectList(
      @Param("ioTDeviceGeoFence") IoTDeviceGeoFence ioTDeviceGeoFence);

  int updateFence(@Param("ioTDeviceGeoFence") IoTDeviceGeoFence ioTDeviceGeoFence);
}
