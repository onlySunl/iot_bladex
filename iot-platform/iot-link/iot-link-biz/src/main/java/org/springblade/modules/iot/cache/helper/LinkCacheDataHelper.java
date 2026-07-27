package org.springblade.modules.iot.cache.helper;

import org.springblade.modules.iot.cache.vo.device.DeviceAclRuleCacheVO;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 链路缓存数据助手
 * 提供设备、产品等缓存数据的访问方法
 */
@Component
public class LinkCacheDataHelper {

    /**
     * 获取设备访问控制规则（按设备ID列表）
     *
     * @param deviceIds 设备ID列表
     * @return 设备访问控制规则列表
     */
    public List<DeviceAclRuleCacheVO> getDeviceAclRules(List<String> deviceIds) {
        if (deviceIds == null || deviceIds.isEmpty()) {
            return Collections.emptyList();
        }
        // TODO: 从缓存或数据库获取设备访问控制规则
        return Collections.emptyList();
    }

    /**
     * 获取设备访问控制规则（按产品ID和设备ID）
     *
     * @param productId 产品ID
     * @param deviceId 设备ID
     * @return 设备访问控制规则列表
     */
    public List<DeviceAclRuleCacheVO> getDeviceAclRules(String productId, String deviceId) {
        if (productId == null && deviceId == null) {
            return Collections.emptyList();
        }
        // TODO: 从缓存或数据库获取设备访问控制规则
        return Collections.emptyList();
    }

    /**
     * 获取设备缓存信息
     *
     * @param deviceIds 设备ID列表
     * @return 设备缓存信息列表
     */
    public List<DeviceCacheVO> getDevices(List<String> deviceIds) {
        if (deviceIds == null || deviceIds.isEmpty()) {
            return Collections.emptyList();
        }
        // TODO: 从缓存或数据库获取设备信息
        return Collections.emptyList();
    }

    /**
     * 获取单个设备缓存信息
     *
     * @param deviceId 设备ID
     * @return 设备缓存信息
     */
    public DeviceCacheVO getDevice(String deviceId) {
        if (deviceId == null || deviceId.isEmpty()) {
            return null;
        }
        // TODO: 从缓存或数据库获取设备信息
        return null;
    }

    /**
     * 获取设备缓存VO（按设备标识或客户端ID）
     *
     * @param deviceIdOrClientId 设备标识或客户端ID
     * @return 设备缓存VO
     */
    public DeviceCacheVO getDeviceCacheVO(String deviceIdOrClientId) {
        if (deviceIdOrClientId == null || deviceIdOrClientId.isEmpty()) {
            return null;
        }
        // TODO: 从缓存或数据库获取设备缓存VO
        return null;
    }

    /**
     * 获取产品下所有设备
     *
     * @param productIds 产品ID列表
     * @return 产品ID到设备列表的映射
     */
    public Map<String, List<DeviceCacheVO>> getDevicesByProductIds(List<String> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return Collections.emptyMap();
        }
        // TODO: 从缓存或数据库获取产品下的设备
        return Collections.emptyMap();
    }
}
