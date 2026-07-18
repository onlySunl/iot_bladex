

package org.springblade.modules.iot.dal.redis.no;

import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.dal.redis.RedisKeyConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/23 14:49
 * @Version: V1.0
 */
@Repository
public class EiotRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setLastTime(Long deviceId, Long lastTime) {
        String key = RedisKeyConstants.DEVICE_LAST + deviceId;
        stringRedisTemplate.opsForValue().set(key, String.valueOf(lastTime));
    }

    public long getLastTime(Long deviceId) {
        String key = RedisKeyConstants.DEVICE_LAST + deviceId;

        String last = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(last)) {
            return 0L;
        }
        return Long.parseLong(last);
    }

    private String getPropertyCacheKey(Long deviceId) {
        return RedisKeyConstants.DEVICE_PROPERTY + deviceId;
    }

    public Map<String, DevicePropertyCache> getProperties(Long deviceId) {
        return getPropertyCacheInfo(deviceId).getProperties();
    }


    public void saveProperties(Long deviceId, Map<String, DevicePropertyCache> properties) {
        Map<String, DevicePropertyCache> old = getProperties(deviceId);
        old.putAll(properties);
        stringRedisTemplate.opsForValue().set(getPropertyCacheKey(deviceId),
                JsonUtils.toJsonString(new PropertyCacheInfo(old))
        );
    }

    private PropertyCacheInfo getPropertyCacheInfo(Long deviceId) {
        String json = stringRedisTemplate.opsForValue().get(getPropertyCacheKey(deviceId));
        if (StringUtils.isBlank(json)) {
            return new PropertyCacheInfo(new HashMap<>());
        }
        return JsonUtils.parseObject(json, new TypeReference<PropertyCacheInfo>() {
        });
    }

    public void clearProperties(List<Long> deviceIds) {
        if (deviceIds != null && !deviceIds.isEmpty()) {
            Set<String> keys = deviceIds.stream().map(this::getPropertyCacheKey).collect(Collectors.toSet());
            stringRedisTemplate.delete(keys);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class PropertyCacheInfo {

        private Map<String, DevicePropertyCache> properties;

    }


}
