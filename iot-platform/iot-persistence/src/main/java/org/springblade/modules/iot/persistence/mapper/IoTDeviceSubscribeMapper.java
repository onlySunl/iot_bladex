

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceSubscribe;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IoTDeviceSubscribeMapper extends BaseMapper<IoTDeviceSubscribe> {

  List<IoTDeviceSubscribe> selectSubscribeBO(IoTDeviceSubscribe ioTDeviceSubscribe);

  List<IoTDeviceSubscribe> selectSubscribesBO(IoTDeviceSubscribe ioTDeviceSubscribe);

  List<IoTDeviceSubscribe> selectByMsgAndType(IoTDeviceSubscribe ioTDeviceSubscribe);
}
