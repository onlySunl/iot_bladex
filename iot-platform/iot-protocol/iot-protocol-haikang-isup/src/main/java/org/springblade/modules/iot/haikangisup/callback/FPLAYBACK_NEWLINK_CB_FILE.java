package org.springblade.modules.iot.haikangisup.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.service.stream.HCISUPStream;
import org.springblade.modules.iot.haikangisup.service.stream.StreamService;
import org.springblade.modules.iot.haikangisup.handler.PlaybackStreamHandler;
import org.springblade.modules.iot.haikangisup.manager.StreamManager;
import com.sun.jna.Pointer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 回放数据回调
 */
@Slf4j
@RequiredArgsConstructor
public class FPLAYBACK_NEWLINK_CB_FILE implements HCISUPStream.PLAYBACK_NEWLINK_CB {

	private final Map<Integer, PlaybackStreamHandler> handlerMap = new ConcurrentHashMap<>();

	@Override
	public boolean invoke(int lPlayBackLinkHandle, HCISUPStream.NET_EHOME_PLAYBACK_NEWLINK_CB_INFO pNewLinkCBMsg, Pointer pUserData) {
		log.info("回放数据回调参数 lPlayBackLinkHandle: {}", lPlayBackLinkHandle);

		int iSessionID = pNewLinkCBMsg.lSessionID;
		log.info("pNewLinkCBMsg.lSessionID是和回放的时候的sessionID一致的 这个应该是全局唯一 通过sessionID可以确定是哪个摄像头 iSessionID: {}", iSessionID);

		StreamManager.playbackHandSAndSessionIDandMap.put(lPlayBackLinkHandle, iSessionID);
		StreamManager.sessionIDAndPlaybackHandleMap.put(iSessionID, lPlayBackLinkHandle);

		PlaybackStreamHandler playbackStreamHandler = handlerMap.computeIfAbsent(lPlayBackLinkHandle, handle -> {
			log.info("创建新的PlaybackStreamHandler实例，句柄: {}", handle);
			PlaybackStreamHandler playback = new PlaybackStreamHandler();
			StreamManager.sessionIDAndPlaybackStreamHandlerMap.put(iSessionID, playback);
			return playback;
		});

		HCISUPStream.NET_EHOME_PLAYBACK_DATA_CB_PARAM struDataCB = new HCISUPStream.NET_EHOME_PLAYBACK_DATA_CB_PARAM();
		struDataCB.fnPlayBackDataCB = playbackStreamHandler;
		struDataCB.byStreamFormat = 1;

		if (!StreamService.hCEhomeStream.NET_ESTREAM_SetPlayBackDataCB(lPlayBackLinkHandle, struDataCB)) {
			log.info("NET_ESTREAM_SetPlayBackDataCB 失败 err: {}", StreamService.hCEhomeStream.NET_ESTREAM_GetLastError());
			return false;
		}
		return true;
	}
}
