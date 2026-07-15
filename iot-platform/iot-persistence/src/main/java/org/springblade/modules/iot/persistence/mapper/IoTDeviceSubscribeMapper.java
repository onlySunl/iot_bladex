

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceSubscribe;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IoTDeviceSubscribeMapper extends BladeMapper<IoTDeviceSubscribe> {

  List<IoTDeviceSubscribe> selectSubscribeBO(IoTDeviceSubscribe ioTDeviceSubscribe);

  List<IoTDeviceSubscribe> selectSubscribesBO(IoTDeviceSubscribe ioTDeviceSubscribe);

  List<IoTDeviceSubscribe> selectByMsgAndType(IoTDeviceSubscribe ioTDeviceSubscribe);
}
