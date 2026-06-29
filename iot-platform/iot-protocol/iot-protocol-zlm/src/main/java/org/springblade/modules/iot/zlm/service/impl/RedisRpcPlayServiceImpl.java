package org.springblade.modules.iot.zlm.service.impl;

import com.alibaba.fastjson2.JSON;
import org.springblade.modules.iot.domain.DownloadFileInfo;
import org.springblade.modules.iot.zlm.config.RedisRpcConfig;
import org.springblade.modules.iot.zlm.config.UserSetting;
import org.springblade.modules.iot.zlm.domain.redis.RedisRpcRequest;
import org.springblade.modules.iot.zlm.domain.redis.RedisRpcResponse;
import org.springblade.modules.iot.zlm.service.IRedisRpcPlayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @FileName RedisRpcPlayServiceImpl
 * @Description
 * @Author fengcheng
 * @date 2026-04-11
 **/
@Slf4j
@Service
public class RedisRpcPlayServiceImpl implements IRedisRpcPlayService {

    @Autowired
    private RedisRpcConfig redisRpcConfig;

    @Autowired
    private UserSetting userSetting;

    private RedisRpcRequest buildRequest(String uri, Object param) {
        RedisRpcRequest request = new RedisRpcRequest();
        request.setFromId(userSetting.getServerId());
        request.setParam(param);
        request.setUri(uri);
        return request;
    }

    @Override
    public DownloadFileInfo getRecordPlayUrl(String serverId, Long recordId) {
        RedisRpcRequest request = buildRequest("cloudRecord/play", recordId);
        RedisRpcResponse response = redisRpcConfig.request(request, userSetting.getPlayTimeout(), TimeUnit.SECONDS);
        if (response != null && response.getStatusCode() == HttpStatus.OK.value()) {
            return JSON.parseObject(response.getBody().toString(), DownloadFileInfo.class);
        }
        return null;
    }
}
