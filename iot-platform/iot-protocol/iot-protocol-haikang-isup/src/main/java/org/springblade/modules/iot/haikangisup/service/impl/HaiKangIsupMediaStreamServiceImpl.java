package org.springblade.modules.iot.haikangisup.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springblade.modules.iot.haikangisup.service.IHaikangIsupMediaStreamService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 海康ISUP媒体流服务实现
 */
@Slf4j
@Service
public class HaiKangIsupMediaStreamServiceImpl implements IHaikangIsupMediaStreamService {

	/**
	 * 播放会话管理
	 */
	private final ConcurrentHashMap<String, Object> playSessions = new ConcurrentHashMap<>();

	@Override
	public void startPlay(Integer lUserID, String streamKey, Object params) {
		log.info("开始播放 - lUserID:{}, streamKey:{}", lUserID, streamKey);
		playSessions.put(streamKey, params);
	}

	@Override
	public void stopPlay(Integer lUserID, String streamKey) {
		log.info("停止播放 - lUserID:{}, streamKey:{}", lUserID, streamKey);
		playSessions.remove(streamKey);
	}

	@Override
	public void startPlayback(Integer lUserID, Integer channelId, String startTime, String stopTime, String streamKey) {
		log.info("开始回放 - lUserID:{}, channelId:{}, startTime:{}, stopTime:{}, streamKey:{}",
			lUserID, channelId, startTime, stopTime, streamKey);
	}

	@Override
	public void stopPlayback(Integer lUserID, String streamKey) {
		log.info("停止回放 - lUserID:{}, streamKey:{}", lUserID, streamKey);
		playSessions.remove(streamKey);
	}
}
