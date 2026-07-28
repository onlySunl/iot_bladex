package org.springblade.modules.iot.device.mapper;

import org.springblade.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 设备命令下发及响应表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-10-20 17:27:25
 * @create [2023-10-20 17:27:25] [mqttsnet]
 */
@Repository
public interface DeviceCommandMapper extends SuperMapper<DeviceCommand> {

}


