package org.springblade.modules.iot.service.impl;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.modules.iot.domain.MediaInfo;
import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springblade.modules.iot.config.UserSetting;
import org.springblade.modules.iot.constants.VideoManagerConstants;
import org.springblade.modules.iot.domain.StreamAuthorityInfo;
import org.springblade.modules.iot.service.IRedisCatchStorage;
import org.springblade.modules.iot.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @FileName RedisCatchStorageImpl
 * @Description
 * @Author fengcheng
 * @date 2026-04-01
 **/
@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class RedisCatchStorageImpl implements IRedisCatchStorage {

    @Autowired
    private UserSetting userSetting;

    @Resource
    private BladeRedis bladeRedis;


    @Override
    public void addStream(ZlmMediaServer mediaServerItem, String type, String app, String streamId, MediaInfo mediaInfo) {
        // 查找是否使用了callID
        StreamAuthorityInfo streamAuthorityInfo = getStreamAuthorityInfo(app, streamId);
        String key = VideoManagerConstants.ZLM_SERVER_STREAM_PREFIX + userSetting.getServerId() + "_" + type.toUpperCase() + "_" + app + "_" + streamId + "_" + mediaServerItem.getId();
        if (streamAuthorityInfo != null) {
            mediaInfo.setCallId(streamAuthorityInfo.getCallId());
        }
        bladeRedis.getRedisTemplate().opsForValue().set(key, JSON.toJSONString(mediaInfo));
    }

    @Override
    public void removeStream(Long mediaServerId, String type, String app, String streamId) {
        String key = VideoManagerConstants.ZLM_SERVER_STREAM_PREFIX + userSetting.getServerId() + "_" + type.toUpperCase() + "_"  + app + "_" + streamId + "_" + mediaServerId;
        bladeRedis.getRedisTemplate().delete(key);
    }

    /**
     * 获取流信息
     *
     * @param app
     * @param streamId
     * @param mediaServerId
     * @return
     */
    @Override
    public MediaInfo getStreamInfo(String app, String streamId, Long mediaServerId) {
        String scanKey = VideoManagerConstants.ZLM_SERVER_STREAM_PREFIX  + userSetting.getServerId() + "_*_" + app + "_" + streamId + "_" + mediaServerId;

        MediaInfo result = null;
        List<Object> keys = RedisUtil.scan(bladeRedis.getRedisTemplate(), scanKey);
        if (keys.size() > 0) {
            String key = (String) keys.get(0);
            String mediaInfoJson = (String)bladeRedis.getRedisTemplate().opsForValue().get(key);
            result = JSON.parseObject(mediaInfoJson, MediaInfo.class);
        }

        return result;
    }

    public StreamAuthorityInfo getStreamAuthorityInfo(String app, String stream) {
        String key = VideoManagerConstants.MEDIA_STREAM_AUTHORITY;
        String objectKey = app+ "_" + stream;
        return (StreamAuthorityInfo)bladeRedis.getRedisTemplate().opsForHash().get(key, objectKey);
    }

    /**
     * 存储推流的鉴权信息
     *
     * @param app 应用名
     * @param stream 流
     * @param streamAuthorityInfo 鉴权信息
     */
    @Override
    public void updateStreamAuthorityInfo(String app, String stream, StreamAuthorityInfo streamAuthorityInfo) {
        String key = VideoManagerConstants.MEDIA_STREAM_AUTHORITY;
        String objectKey = app+ "_" + stream;
        bladeRedis.getRedisTemplate().opsForHash().put(key, objectKey, streamAuthorityInfo);
    }

    /**
     * 添加推流列表信息到redis
     *
     * @param app
     * @param stream
     * @param mediaInfo
     */
    @Override
    public void addPushListItem(String app, String stream, MediaInfo mediaInfo) {
        String key = VideoManagerConstants.PUSH_STREAM_LIST + app + "_" + stream;
        bladeRedis.getRedisTemplate().opsForValue().set(key, mediaInfo);
    }

    /**
     * 获取推流列表信息从redis
     *
     * @param app
     * @param stream
     * @return
     */
    @Override
    public MediaInfo getPushListItem(String app, String stream) {
        String key = VideoManagerConstants.PUSH_STREAM_LIST + app + "_" + stream;
        return (MediaInfo)bladeRedis.getRedisTemplate().opsForValue().get(key);
    }

    /**
     * 移除推流的鉴权信息
     *
     * @param app
     * @param stream
     * @param id
     */
    @Override
    public void removePushListItem(String app, String stream, Long id) {
        String key = VideoManagerConstants.PUSH_STREAM_LIST + app + "_" + stream;
        MediaInfo param = (MediaInfo)bladeRedis.getRedisTemplate().opsForValue().get(key);
        if (param != null) {
            bladeRedis.getRedisTemplate().delete(key);
        }
    }
}
