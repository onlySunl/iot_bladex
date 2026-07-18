

package org.springblade.modules.iot.service.device;


import org.springblade.modules.iot.api.device.dto.DeviceConfig;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.entity.DeviceConfigDO;
import org.springblade.modules.iot.dal.mysql.deviceconfig.DeviceConfigMapper;
import org.springblade.modules.iot.framework.common.exception.ServiceException;
import org.springblade.modules.iot.framework.common.util.object.BeanUtils;
import org.springblade.modules.iot.dal.redis.RedisKeyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.time.ZoneId;


@Service
@RequiredArgsConstructor
public class DeviceConfigServiceImpl implements DeviceConfigService {

    private final DeviceConfigMapper deviceConfigMapper;
    private final DeviceInfoService deviceInfoService;

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.DEVICE_ID_CONFIG, key = "#deviceId", unless = "#result == null")
    public DeviceConfig findByDeviceId(Long deviceId) {
        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(deviceId);
        if (deviceInfo == null) {
            return null;
        }
        DeviceConfigDO configDO = deviceConfigMapper.selectByPkDn(deviceInfo.getProductKey(), deviceInfo.getDn());
        if (configDO == null) {
            return null;
        }
        DeviceConfig dto = BeanUtils.toBean(configDO, DeviceConfig.class);
        dto.setDeviceId(deviceId);
        dto.setDeviceName(deviceInfo.getName());
        return dto;
    }

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.DEVICE_CONFIG, key = "#productKey+':'+#dn", unless = "#result == null")
    public DeviceConfig findByPkDn(String productKey, String dn) {
        DeviceInfo deviceInfo = deviceInfoService.getDeviceByPkDnByCache(productKey, dn);
        if (deviceInfo == null) {
            return null;
        }
        return findByDeviceId(deviceInfo.getId());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = RedisKeyConstants.DEVICE_ID_CONFIG, key = "#config.deviceId", beforeInvocation = true),
            @CacheEvict(cacheNames = RedisKeyConstants.DEVICE_CONFIG, key = "#config.productKey+':'+#config.deviceName", beforeInvocation = true)
    })
    public Boolean saveConfig(DeviceConfig config) {
        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(config.getDeviceId());
        if (deviceInfo == null) {
            throw new ServiceException(400, "设备不存在");
        }
        // 确保产品key/dn写入
        config.setProductKey(deviceInfo.getProductKey());
        config.setDeviceName(deviceInfo.getName());

        DeviceConfigDO exist = deviceConfigMapper.selectByPkDn(deviceInfo.getProductKey(), deviceInfo.getDn());
        DeviceConfigDO target = BeanUtils.toBean(config, DeviceConfigDO.class);
        target.setDn(deviceInfo.getDn());
        target.setProductKey(deviceInfo.getProductKey());
        target.setTenantId(deviceInfo.getTenantId());
        if (exist == null) {
            deviceConfigMapper.insert(target);
        } else {
            target.setId(exist.getId());
            deviceConfigMapper.updateById(target);
        }
        return true;
    }
}
