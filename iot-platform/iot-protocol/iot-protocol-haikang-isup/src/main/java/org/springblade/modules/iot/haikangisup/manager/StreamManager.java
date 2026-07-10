package org.springblade.modules.iot.haikangisup.manager;


import org.springblade.modules.iot.common.domain.RtpServerParam;
import org.springblade.modules.iot.haikangisup.handler.PlaybackStreamHandler;
import org.springblade.modules.iot.haikangisup.handler.PreviewStreamHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class StreamManager {
    // 全部使用 ConcurrentHashMap 保证线程安全
    public static Map<Integer, RtpServerParam> luserIdAndRtpServerParamMap = new ConcurrentHashMap<>();
    public static Map<Integer, Integer> userIDandSessionMap = new ConcurrentHashMap<>();
    public static Map<Integer, Integer> previewHandSAndSessionIDandMap = new ConcurrentHashMap<>();
    public static Map<Integer, Integer> sessionIDAndPreviewHandleMap = new ConcurrentHashMap<>();
    public static Map<Integer, PreviewStreamHandler> sessionIDAndPreviewStreamHandlerMap = new ConcurrentHashMap<>();
    public static Map<String, RtpServerParam> streamKeyAndRtpServerParamMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> streamKeyAndSessionIDMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> streamKeyAndLuserIdMap = new ConcurrentHashMap<>();

    // 回放相关的映射
    public static Map<String, CountDownLatch> playbackKeyAndLatchMap = new ConcurrentHashMap<>();
    public static Map<String, RtpServerParam> playbackKeyAndRtpServerParamMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> playbackKeyAndSessionIDMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> playbackKeyAndPlaybackHandleMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> playbackKeyAndLuserIdMap = new ConcurrentHashMap<>();
    public static Map<Integer, PlaybackStreamHandler> sessionIDAndPlaybackStreamHandlerMap = new ConcurrentHashMap<>();
    public static Map<Integer, Integer> playbackHandSAndSessionIDandMap = new ConcurrentHashMap<>();
    public static Map<Integer, Integer> sessionIDAndPlaybackHandleMap = new ConcurrentHashMap<>();
    public static Map<Integer, RtpServerParam> luserIdAndPlaybackRtpServerParamMap = new ConcurrentHashMap<>();

    // 回放会话复用跟踪：记录每个 playbackKey 对应的会话时间信息
    /** playbackKey → 当前回放会话的开始时间 */
    public static Map<String, LocalDateTime> playbackKeyAndStartTimeMap = new ConcurrentHashMap<>();
    /** playbackKey → 当前回放会话的结束时间 */
    public static Map<String, LocalDateTime> playbackKeyAndEndTimeMap = new ConcurrentHashMap<>();
    /** playbackKey → 当前回放会话的播放日期（用于判断是否跨天） */
    public static Map<String, LocalDate> playbackKeyAndPlayDateMap = new ConcurrentHashMap<>();
    /** playbackKey → 当前回放会话的 lSessionID（用于 seek 操作） */
    public static Map<String, Integer> playbackKeyAndDeviceSessionIdMap = new ConcurrentHashMap<>();

    // 下载相关的映射
    public static Map<Integer, String> playbackUserIDandTypeMap = new ConcurrentHashMap<>();
    public static Map<Integer, String> playbackUserIDandFilePathMap = new ConcurrentHashMap<>();
    public static Map<Integer, String> sessionIDandDownloadKeyMap = new ConcurrentHashMap<>();
}
