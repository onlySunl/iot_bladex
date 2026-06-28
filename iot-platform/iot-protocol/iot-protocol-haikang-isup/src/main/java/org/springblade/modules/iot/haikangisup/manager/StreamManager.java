package org.springblade.modules.iot.haikangisup.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 流管理器
 */
public class StreamManager {
	public static Map<Integer, Object> luserIdAndRtpServerParamMap = new ConcurrentHashMap<>();
	public static Map<Integer, Integer> userIDandSessionMap = new ConcurrentHashMap<>();
	public static Map<Integer, Integer> previewHandSAndSessionIDandMap = new ConcurrentHashMap<>();
	public static Map<Integer, Integer> sessionIDAndPreviewHandleMap = new ConcurrentHashMap<>();
	public static Map<Integer, Object> sessionIDAndPreviewStreamHandlerMap = new ConcurrentHashMap<>();
	public static Map<String, Object> streamKeyAndRtpServerParamMap = new ConcurrentHashMap<>();
	public static Map<String, Integer> streamKeyAndSessionIDMap = new ConcurrentHashMap<>();
	public static Map<String, Integer> streamKeyAndLuserIdMap = new ConcurrentHashMap<>();

	// 回放相关
	public static Map<String, CountDownLatch> playbackKeyAndLatchMap = new ConcurrentHashMap<>();
	public static Map<String, Object> playbackKeyAndRtpServerParamMap = new ConcurrentHashMap<>();
	public static Map<String, Integer> playbackKeyAndSessionIDMap = new ConcurrentHashMap<>();
	public static Map<String, Integer> playbackKeyAndPlaybackHandleMap = new ConcurrentHashMap<>();
	public static Map<String, Integer> playbackKeyAndLuserIdMap = new ConcurrentHashMap<>();
	public static Map<Integer, Object> sessionIDAndPlaybackStreamHandlerMap = new ConcurrentHashMap<>();
	public static Map<Integer, Integer> playbackHandSAndSessionIDandMap = new ConcurrentHashMap<>();
	public static Map<Integer, Integer> sessionIDAndPlaybackHandleMap = new ConcurrentHashMap<>();
	public static Map<Integer, Object> luserIdAndPlaybackRtpServerParamMap = new ConcurrentHashMap<>();

	// 下载相关
	public static Map<Integer, String> playbackUserIDandTypeMap = new ConcurrentHashMap<>();
	public static Map<Integer, String> playbackUserIDandFilePathMap = new ConcurrentHashMap<>();
	public static Map<Integer, String> sessionIDandDownloadKeyMap = new ConcurrentHashMap<>();
}
