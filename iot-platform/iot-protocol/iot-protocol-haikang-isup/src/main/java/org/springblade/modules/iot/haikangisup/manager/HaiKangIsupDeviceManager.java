package org.springblade.modules.iot.haikangisup.manager;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.callback.FRegisterCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 海康ISUP设备管理器
 */
@Slf4j
@Component
public class HaiKangIsupDeviceManager {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 设备连接池
	 */
	public static final ConcurrentHashMap<String, Integer> devicePool = new ConcurrentHashMap<>();

	/**
	 * 注册设备
	 *
	 * @param deviceId 设备ID
	 * @param lUserID 用户ID
	 */
	public void registerDevice(String deviceId, Integer lUserID) {
		devicePool.put(deviceId, lUserID);
		log.info("注册设备成功 - deviceId:{}, lUserID:{}", deviceId, lUserID);
	}

	/**
	 * 注销设备
	 *
	 * @param deviceId 设备ID
	 */
	public void unregisterDevice(String deviceId) {
		devicePool.remove(deviceId);
		log.info("注销设备 - deviceId:{}", deviceId);
	}

	/**
	 * 获取设备用户ID
	 *
	 * @param deviceId 设备ID
	 * @return 用户ID
	 */
	public Integer getUserId(String deviceId) {
		return devicePool.get(deviceId);
	}

	/**
	 * 设备是否在线
	 *
	 * @param deviceId 设备ID
	 * @return 是否在线
	 */
	public boolean isOnline(String deviceId) {
		return devicePool.containsKey(deviceId);
	}

	/**
	 * 获取在线设备数量
	 *
	 * @return 在线设备数量
	 */
	public int getOnlineCount() {
		return devicePool.size();
	}
}
