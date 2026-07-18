

package org.springblade.modules.iot.api.device;


import org.springblade.modules.iot.api.device.dto.*;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.thing.ThingService;

import java.util.List;
import java.util.Map;

public interface DeviceApi {

    DeviceInfo getDeviceByPkDnByCache(String pk, String dn);

    DeviceInfo getDeviceInfoFromCache(Long deviceId);

    DeviceInfo registerDevice(RegisterDevice registerDevice);

    CommonResult<DeviceInfo> auth(DeviceAuth deviceAuth);

    Map<String, DevicePropertyCache> getPropertiesFromCache(Long deviceId);

    void updateDeviceLastTimeCache(Long deviceId, long lastTime);

    Boolean updateDeviceState(Long deviceId, boolean online);

    void savePropertiesCache(Long deviceId, Map<String, DevicePropertyCache> properties);

    void clearPropertiesCache(String productKey);

    DeviceConfig getDeviceConfig(Long deviceId);

    DeviceConfig getDeviceConfig(String productKey, String dn);

    /**
     * 调用设备服务
     *
     * @param service 服务
     */
    void invoke(ThingService<?> service);

    List<DeviceInfo> getSubDevicesByProductKeAndDeviceName(String pk, String dn);

    Boolean deregisterSubDevice(String pk, String dn, String model, String subPkDeregister, String subDnDeregister);
}
