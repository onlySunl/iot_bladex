package org.springblade.modules.iot.cache.device;

import org.springblade.modules.iot.cache.CacheSuperAbstract;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 设备缓存服务
 */
@Service
public class DeviceCacheService extends CacheSuperAbstract {
    
    public DeviceCacheVO getDevice(String deviceId) {
        // TODO: 从缓存获取设备信息
        return null;
    }
    
    public List<DeviceCacheVO> getDevices(List<String> deviceIds) {
        if (deviceIds == null || deviceIds.isEmpty()) {
            return Collections.emptyList();
        }
        // TODO: 从缓存获取设备列表
        return Collections.emptyList();
    }
    
    public void putDevice(DeviceCacheVO device) {
        // TODO: 缓存设备信息
    }
    
    public void evictDevice(String deviceId) {
        // TODO: 清除设备缓存
    }
}
