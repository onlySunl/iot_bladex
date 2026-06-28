package org.springblade.modules.iot.dahua.manager;

import org.springblade.modules.iot.common.core.domain.RtpServerParam;
import org.springblade.modules.iot.dahua.callback.FPlayBackDataCallBack;
import org.springblade.modules.iot.dahua.callback.FRealDatarTPCallback;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StreamManager {
    public static Map<String, LLong> streamKeyAndRealHandleMap = new ConcurrentHashMap<>();
    public static Map<String, FRealDatarTPCallback> streamKeyAndFRealDatarTPCallbackMap = new ConcurrentHashMap<>();
    public static Map<String, RtpServerParam> streamKeyAndRtpServerParamMap = new ConcurrentHashMap<>();
    
    // 回放相关
    public static Map<String, LLong> playbackKeyAndPlaybackHandleMap = new ConcurrentHashMap<>();
    public static Map<String, FPlayBackDataCallBack> playbackKeyAndFPlayBackDataCallBackMap = new ConcurrentHashMap<>();
    public static Map<String, RtpServerParam> playbackKeyAndRtpServerParamMap = new ConcurrentHashMap<>();
    public static Map<String, java.util.concurrent.CountDownLatch> playbackKeyAndLatchMap = new ConcurrentHashMap<>();
}
