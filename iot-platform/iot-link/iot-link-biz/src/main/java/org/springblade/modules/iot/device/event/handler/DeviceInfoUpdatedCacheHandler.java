package org.springblade.modules.iot.device.event.handler;

import java.util.List;

import org.springblade.common.utils.ArgumentAssert;
import org.springblade.modules.iot.cache.device.DeviceCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description:
 * 设备信息更新缓存处理器
 *
 * @author Sun ShiHuan
 * @version 1.0.0
 * @since 2025/6/5
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceInfoUpdatedCacheHandler {

    private final DeviceCacheService deviceCacheService;


    /**
     * 处理设备信息更新缓存
     *
     * <p>刷新指定设备标识集合的缓存</p>
     *
     * @param deviceIdentificationList 设备标识集合
     * @return 是否成功处理
     * @see DeviceCacheService#refreshDeviceCache(String)
     */
    public boolean handleDeviceInfoUpdatedCache(List<String> deviceIdentificationList) {
        try {
            ArgumentAssert.notEmpty(deviceIdentificationList, "deviceIdentificationList is null");
            log.info("处理设备信息更新缓存...deviceIdentificationList:{}", deviceIdentificationList);
            // 刷新设备缓存
            deviceIdentificationList.forEach(deviceCacheService::refreshDeviceCache);
            return true;
        } catch (Exception e) {
            log.error("处理设备信息更新缓存失败...deviceIdentificationList:{}", deviceIdentificationList, e);
            return false;
        }
    }

}
