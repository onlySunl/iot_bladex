package org.springblade.modules.iot.service.impl;


import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.modules.iot.common.SystemAllInfo;
import org.springblade.modules.iot.common.VideoManagerConstants;
import org.springblade.modules.iot.service.IRedisCatchStorageService;
import org.springblade.modules.iot.utils.SystemInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName RedisCatchStorageSericeImpl
 * @Description
 * @Author fengcheng
 * @date 2026-04-15
 **/
@Slf4j
@Component
public class RedisCatchStorageServiceImpl implements IRedisCatchStorageService {
    @Resource
    private  BladeRedis bladeRedis;

    /**
     * 获取系统信息
     *
     * @return
     */
    @Override
    public SystemAllInfo getSystemInfo() {
        String cpuKey = VideoManagerConstants.SYSTEM_INFO_CPU_PREFIX;
        String memKey = VideoManagerConstants.SYSTEM_INFO_MEM_PREFIX;
        String netKey = VideoManagerConstants.SYSTEM_INFO_NET_PREFIX;
        String diskKey = VideoManagerConstants.SYSTEM_INFO_DISK_PREFIX;
        SystemAllInfo systemAllInfo = new SystemAllInfo();
        systemAllInfo.setCpu(bladeRedis.getRedisTemplate().opsForList().range(cpuKey, 0, -1));
        systemAllInfo.setMem(bladeRedis.getRedisTemplate().opsForList().range(memKey, 0, -1));
        systemAllInfo.setNet(bladeRedis.getRedisTemplate().opsForList().range(netKey, 0, -1));

        systemAllInfo.setDisk(bladeRedis.getRedisTemplate().opsForValue().get(diskKey));
        systemAllInfo.setNetTotal(SystemInfoUtils.getNetworkTotal());
        return systemAllInfo;
    }

    @Override
    public void addCpuInfo(double cpuInfo) {
        String key = VideoManagerConstants.SYSTEM_INFO_CPU_PREFIX;
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("time", DateUtil.now());
        infoMap.put("data", String.valueOf(cpuInfo));
        bladeRedis.getRedisTemplate().opsForList().rightPush(key, infoMap);
        // 每秒一个，最多只存30个
        Long size = bladeRedis.getRedisTemplate().opsForList().size(key);
        if (size != null && size >= 30) {
            for (int i = 0; i < size - 30; i++) {
                bladeRedis.getRedisTemplate().opsForList().leftPop(key);
            }
        }
    }

    @Override
    public void addMemInfo(double memInfo) {
        String key = VideoManagerConstants.SYSTEM_INFO_MEM_PREFIX;
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("time", DateUtil.now());
        infoMap.put("data", String.valueOf(memInfo));
        bladeRedis.getRedisTemplate().opsForList().rightPush(key, infoMap);
        // 每秒一个，最多只存30个
        Long size = bladeRedis.getRedisTemplate().opsForList().size(key);
        if (size != null && size >= 30) {
            for (int i = 0; i < size - 30; i++) {
                bladeRedis.getRedisTemplate().opsForList().leftPop(key);
            }
        }
    }

    @Override
    public void addNetInfo(Map<String, Double> networkInterfaces) {
        String key = VideoManagerConstants.SYSTEM_INFO_NET_PREFIX;
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("time", DateUtil.now());
        for (String netKey : networkInterfaces.keySet()) {
            infoMap.put(netKey, networkInterfaces.get(netKey));
        }
        bladeRedis.getRedisTemplate().opsForList().rightPush(key, infoMap);
        // 每秒一个，最多只存30个
        Long size = bladeRedis.getRedisTemplate().opsForList().size(key);
        if (size != null && size >= 30) {
            for (int i = 0; i < size - 30; i++) {
                bladeRedis.getRedisTemplate().opsForList().leftPop(key);
            }
        }
    }

    @Override
    public void addDiskInfo(List<Map<String, Object>> diskInfo) {
        String key = VideoManagerConstants.SYSTEM_INFO_DISK_PREFIX;
        bladeRedis.getRedisTemplate().opsForValue().set(key, diskInfo);
    }
}
