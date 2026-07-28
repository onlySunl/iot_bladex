package org.springblade.modules.iot.device.manager;

import org.springblade.core.database.mybatis.BladeService;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.vo.query.DeviceCommandPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 设备命令下发及响应表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-10-20 17:27:25
 * @create [2023-10-20 17:27:25] [mqttsnet]
 */
public interface DeviceCommandManager extends BladeService<DeviceCommand> {
    /**
     * Fetch a list of device command result VOs.
     *
     * @param query the query parameters
     * @return a list of DeviceCommandResultVOs
     */
    List<DeviceCommand> getDeviceCommandResultVOList(DeviceCommandPageQuery query);
}


