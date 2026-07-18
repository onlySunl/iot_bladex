

package org.springblade.modules.iot.service.device;


import org.springblade.modules.iot.api.device.dto.DeviceInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author: EnjoyIot
 * @Date: 2025/1/8 18:42
 * @Version: V1.0
 * @Description: 设备控制接口
 */
public interface DeviceCtrlService {
    void invokeService(Long deviceId, String service,
                         Map<String, Object> args);

    void invokeService(Long deviceId, String service,
                         Map<String, Object> args, boolean checkOwner);

    void otaUpgrade(Long deviceId, boolean checkOwner, Object data);

    void getProperty(Long deviceId, List<String> properties,
                       boolean checkOwner);

    void setProperty(Long deviceId, Map<String, Object> properties);

    void setProperty(Long deviceId, Map<String, Object> properties,
                       boolean checkOwner);

    void sendConfig(Long deviceId, boolean checkOwner);

    void sendConfig(Long deviceId);

    DeviceInfo getAndCheckDevice(Long deviceId, boolean checkOwner);

    void bindDevice(List<Long> subDeviceIds, Long parentId);

    void unbindDevice(List<Long> subDeviceIds);
}
