package org.springblade.modules.nvr.haikangisup.haikang.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.nvr.common.constants.SecurityConstants;
import org.springblade.modules.nvr.common.domain.RtpServerParam;
import org.springblade.modules.nvr.domain.QsDevice;
import org.springblade.modules.nvr.haikangisup.config.HaikangIsupConfig;
import org.springblade.modules.nvr.haikangisup.haikang.IHaikangIsupMediaStreamService;
import org.springblade.modules.nvr.haikangisup.haikang.cms.CmsService;
import org.springblade.modules.nvr.haikangisup.haikang.cms.HCISUPCMS;
import org.springblade.modules.nvr.haikangisup.haikang.stream.StreamService;
import org.springblade.modules.nvr.haikangisup.handler.PlaybackStreamHandler;
import org.springblade.modules.nvr.haikangisup.handler.PreviewStreamHandler;
import org.springblade.modules.nvr.haikangisup.manager.StreamManager;
import org.springblade.modules.nvr.service.RemoteZlmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @FileName HaikangIsupMediaStreamServiceImpl
 * @Description
 * @Author fengcheng
 * @date 2026-04-08
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class HaikangIsupMediaStreamServiceImpl implements IHaikangIsupMediaStreamService {

    @Autowired
    private RemoteZlmService remoteZlmService;

    private final Map<String, CountDownLatch> latchMap = new ConcurrentHashMap<>();

    private final HaikangIsupConfig haikangIsupConfig;
    
    // 下载相关的latch map
    private final Map<String, CountDownLatch> downloadLatchMap = new ConcurrentHashMap<>();

    /**
     * 开始播放
     *
     * @param lUserID
     * @param device
     * @param streamKey
     * @param rtpServerParam
     */
    @Async("taskExecutor")
    @Override
    public void startPlay(Integer lUserID, QsDevice device, String streamKey, RtpServerParam rtpServerParam) {
        if (latchMap.containsKey(streamKey)) {
            log.info("通道已在预览中，忽略重复开启: {}", streamKey);
            return;
        }

        CountDownLatch latch = new CountDownLatch(1);
        latchMap.put(streamKey, latch);
        StreamManager.streamKeyAndRtpServerParamMap.put(streamKey, rtpServerParam);
        boolean needCleanup = true;

        try {
            // 创建异步控制器
            RealPlay(lUserID, device, streamKey, rtpServerParam);
            // 阻塞，直到 stopPreview() 调用 latch.countDown()
            latch.await();
            needCleanup = false;
        } catch (Exception e) {
            log.error("海康设备预览异常，设备id: {}, 通道号: {}, streamKey: {}", device.getId(), device.getChannel(), streamKey, e);
            throw new RuntimeException(e);
        } finally {
            if (needCleanup) {
                cleanupResources(streamKey, rtpServerParam);
            }
            latchMap.remove(streamKey);
        }
    }

    /**
     * 停止播放
     *
     * @param luserId
     * @param id
     * @param channel
     * @param streamKey
     */
    @Override
    public void stopPlay(Integer luserId, Long id, Integer channel, String streamKey) {
        CountDownLatch latch = latchMap.get(streamKey);
        if (latch != null) {
            latch.countDown(); // 唤醒 preview
            log.info("结束预览实例: {}", streamKey);
        }

        RtpServerParam rtpServerParam = StreamManager.streamKeyAndRtpServerParamMap.get(streamKey);
        cleanupResources(streamKey, rtpServerParam);

        latchMap.remove(streamKey);
        log.info("停止预览，设备id: {}, 通道号: {}", id, channel);
    }

    /**
     * 统一资源清理方法
     */
    public void cleanupResources(String streamKey, RtpServerParam rtpServerParam) {
        Integer sessionId = StreamManager.streamKeyAndSessionIDMap.get(streamKey);
        Integer luserId = StreamManager.streamKeyAndLuserIdMap.get(streamKey);
        Integer previewHandleId = null;
        PreviewStreamHandler previewStreamHandler = null;

        if (sessionId != null) {
            previewHandleId = StreamManager.sessionIDAndPreviewHandleMap.get(sessionId);
            previewStreamHandler = StreamManager.sessionIDAndPreviewStreamHandlerMap.get(sessionId);
        }

        try {
            if (previewHandleId != null) {
                StreamService.hCEhomeStream.NET_ESTREAM_StopPreview(previewHandleId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 停止预览失败, streamKey: {}", streamKey, e);
        }

        try {
            if (luserId != null && sessionId != null) {
                CmsService.hCEhomeCMS.NET_ECMS_StopGetRealStream(luserId, sessionId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 停止获取实时流失败, streamKey: {}", streamKey, e);
        }

        try {
            if (previewStreamHandler != null && previewHandleId != null) {
                previewStreamHandler.close(previewHandleId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 关闭回调失败, streamKey: {}", streamKey, e);
        }

        // 清理所有 Map
        if (luserId != null) {
            StreamManager.userIDandSessionMap.remove(luserId);
        }
        if (sessionId != null) {
            StreamManager.sessionIDAndPreviewHandleMap.remove(sessionId);
            StreamManager.sessionIDAndPreviewStreamHandlerMap.remove(sessionId);
            StreamManager.luserIdAndRtpServerParamMap.remove(sessionId);
        }
        if (previewHandleId != null) {
            StreamManager.previewHandSAndSessionIDandMap.remove(previewHandleId);
        }

        StreamManager.streamKeyAndRtpServerParamMap.remove(streamKey);
        StreamManager.streamKeyAndSessionIDMap.remove(streamKey);
        StreamManager.streamKeyAndLuserIdMap.remove(streamKey);

        // 清理 zlm 资源
        if (rtpServerParam != null) {
            cleanupZlmResources(streamKey, rtpServerParam);
        }
    }

    /**
     * 清理 zlm 资源
     *
     * @param streamKey
     * @param rtpServerParam
     */
    private void cleanupZlmResources(String streamKey, RtpServerParam rtpServerParam) {
        try {
            log.info("[海康设备] 清理 zlm 资源, streamKey: {}, ssrc: {}", streamKey, rtpServerParam.getSsrc());
            remoteZlmService.releaseSsrc(rtpServerParam.getMediaServerId(), rtpServerParam.getSsrc());
            remoteZlmService.closeRTPServer(rtpServerParam.getMediaServerId(), rtpServerParam);
        } catch (Exception e) {
            log.error("[海康设备] 清理 zlm 资源失败, streamKey: {}", streamKey, e);
        }
    }

    private void RealPlay(Integer luserId, QsDevice device, String streamKey, RtpServerParam rtpServerParam) {

        HCISUPCMS.NET_EHOME_PREVIEWINFO_IN_V11 struPreviewInV11 = new HCISUPCMS.NET_EHOME_PREVIEWINFO_IN_V11();
        //通道号
        struPreviewInV11.iChannel = device.getChannel();

        if ("TCP".equals(device.getProtocol())) {
            //0- TCP方式，1- UDP方式
            struPreviewInV11.dwLinkMode = 0;
        } else {
            //0- TCP方式，1- UDP方式
            struPreviewInV11.dwLinkMode = 1;
        }

        if ("1".equals(device.getStreamType())) {
            //码流类型：0- 主码流，1- 子码流, 2- 第三码流
            struPreviewInV11.dwStreamType = 0;
        } else {
            //码流类型：0- 主码流，1- 子码流, 2- 第三码流
            struPreviewInV11.dwStreamType = 1;
        }


        log.info("ip: {}, port: {}", haikangIsupConfig.getSmsServer().getIp(), haikangIsupConfig.getSmsServer().getPort());
        //流媒体服务器IP地址,公网地址
        struPreviewInV11.struStreamSever.szIP = haikangIsupConfig.getSmsServer().getIp().getBytes();
        //流媒体服务器端口，需要跟服务器启动监听端口一致
        struPreviewInV11.struStreamSever.wPort = (short) haikangIsupConfig.getSmsServer().getPort();
        struPreviewInV11.write();

        //预览请求
        HCISUPCMS.NET_EHOME_PREVIEWINFO_OUT struPreviewOut = new HCISUPCMS.NET_EHOME_PREVIEWINFO_OUT();
        boolean getRS = CmsService.hCEhomeCMS.NET_ECMS_StartGetRealStreamV11(luserId, struPreviewInV11, struPreviewOut);
        log.info("NET_ECMS_StartGetRealStream 预览请求: {}", getRS);


        if (!getRS) {
            log.error("NET_ECMS_StartGetRealStream 失败, error code: {}", CmsService.hCEhomeCMS.NET_ECMS_GetLastError());
            throw new RuntimeException("海康设备预览失败");
        } else {
            struPreviewOut.read();
            log.info("NET_ECMS_StartGetRealStream succeed, sessionID: {}", struPreviewOut.lSessionID);
            StreamManager.userIDandSessionMap.put(luserId, struPreviewOut.lSessionID);
            StreamManager.streamKeyAndLuserIdMap.put(streamKey, luserId);
            StreamManager.streamKeyAndSessionIDMap.put(streamKey, struPreviewOut.lSessionID);
            HCISUPCMS.NET_EHOME_PUSHSTREAM_IN struPushInfoIn = new HCISUPCMS.NET_EHOME_PUSHSTREAM_IN();
            struPushInfoIn.read();
            struPushInfoIn.dwSize = struPushInfoIn.size();
            struPushInfoIn.lSessionID = struPreviewOut.lSessionID;
            struPushInfoIn.write();
            HCISUPCMS.NET_EHOME_PUSHSTREAM_OUT struPushInfoOut = new HCISUPCMS.NET_EHOME_PUSHSTREAM_OUT();
            struPushInfoOut.read();
            struPushInfoOut.dwSize = struPushInfoOut.size();
            struPushInfoOut.write();

            if (!CmsService.hCEhomeCMS.NET_ECMS_StartPushRealStream(luserId, struPushInfoIn, struPushInfoOut)) {
                log.error("NET_ECMS_StartPushRealStream 失败, error code: {}", CmsService.hCEhomeCMS.NET_ECMS_GetLastError());
                throw new RuntimeException("海康设备推流失败");
            } else {
                log.info("NET_ECMS_StartPushRealStream 成功, sessionID: {}", struPreviewOut.lSessionID);

                StreamManager.luserIdAndRtpServerParamMap.put(struPushInfoIn.lSessionID, rtpServerParam);
            }
        }
    }

    /**
     * 开始回放
     *
     * @param lUserID
     * @param device
     * @param playbackKey
     * @param rtpServerParam
     */
    @Async("taskExecutor")
    @Override
    public void startPlayback(Integer lUserID, QsDevice device, String playbackKey, RtpServerParam rtpServerParam) {
        if (StreamManager.playbackKeyAndLatchMap.containsKey(playbackKey)) {
            log.info("回放通道已在运行，忽略重复开启: {}", playbackKey);
            return;
        }

        CountDownLatch latch = new CountDownLatch(1);
        StreamManager.playbackKeyAndLatchMap.put(playbackKey, latch);
        StreamManager.playbackKeyAndRtpServerParamMap.put(playbackKey, rtpServerParam);
        boolean needCleanup = true;

        try {
            // 创建异步控制器
            RealPlayback(lUserID, device, playbackKey, rtpServerParam);
            
            // 记录会话时间信息（用于后续seek判断）
            LocalDateTime startTime = parseDateTime(rtpServerParam.getStartTime());
            LocalDateTime endTime = parseDateTime(rtpServerParam.getEndTime());
            if (startTime != null) {
                StreamManager.playbackKeyAndStartTimeMap.put(playbackKey, startTime);
                StreamManager.playbackKeyAndPlayDateMap.put(playbackKey, startTime.toLocalDate());
            }
            if (endTime != null) {
                StreamManager.playbackKeyAndEndTimeMap.put(playbackKey, endTime);
            }
            
            // 阻塞，直到 stopPlayback() 调用 latch.countDown()
            latch.await();
            needCleanup = false;
        } catch (Exception e) {
            log.error("海康设备回放异常，设备id: {}, 通道号: {}, playbackKey: {}", device.getId(), device.getChannel(), playbackKey, e);
            throw new RuntimeException(e);
        } finally {
            if (needCleanup) {
                cleanupPlaybackResources(playbackKey, rtpServerParam);
            }
            // 清理会话时间跟踪信息
            StreamManager.playbackKeyAndStartTimeMap.remove(playbackKey);
            StreamManager.playbackKeyAndEndTimeMap.remove(playbackKey);
            StreamManager.playbackKeyAndPlayDateMap.remove(playbackKey);
            StreamManager.playbackKeyAndLatchMap.remove(playbackKey);
        }
    }

    /**
     * 尝试复用现有回放会话并跳转时间
     * 如果现有会话有效（同一日期、时间跨度<=30分钟、会话未失效），则执行seek跳转
     *
     * @param playbackKey 回放标识
     * @param lUserID 用户ID
     * @param newStartTime 新的开始时间
     * @param newEndTime 新的结束时间
     * @return true=seek成功，false=需要新建会话
     */
    @Override
    public boolean trySeekPlayback(String playbackKey, Integer lUserID, String newStartTime, String newEndTime) {
        // 检查是否存在活跃的回放会话
        Integer existingSessionId = StreamManager.playbackKeyAndSessionIDMap.get(playbackKey);
        if (existingSessionId == null || existingSessionId <= 0) {
            log.info("[回放seek] 无现有会话，需要新建: playbackKey={}", playbackKey);
            return false;
        }

        // 检查会话是否仍然有效（latch是否存在）
        CountDownLatch latch = StreamManager.playbackKeyAndLatchMap.get(playbackKey);
        if (latch == null) {
            log.info("[回放seek] 会话latch不存在，需要新建: playbackKey={}", playbackKey);
            return false;
        }

        // 解析新的开始时间
        LocalDateTime newStart = parseDateTime(newStartTime);
        if (newStart == null) {
            log.warn("[回放seek] 无法解析开始时间: {}", newStartTime);
            return false;
        }
        LocalDate newPlayDate = newStart.toLocalDate();

        // 条件1：播放日期改变
        LocalDate existingPlayDate = StreamManager.playbackKeyAndPlayDateMap.get(playbackKey);
        if (existingPlayDate != null && !existingPlayDate.equals(newPlayDate)) {
            log.info("[回放seek] 播放日期改变，需要新建: {} -> {}", existingPlayDate, newPlayDate);
            return false;
        }

        // 条件2：时间跨度超过30分钟
        LocalDateTime existingStartTime = StreamManager.playbackKeyAndStartTimeMap.get(playbackKey);
        if (existingStartTime != null) {
            long spanMinutes = Math.abs(java.time.Duration.between(existingStartTime, newStart).toMinutes());
            if (spanMinutes > 30) {
                log.info("[回放seek] 时间跨度超过30分钟({}分钟)，需要新建", spanMinutes);
                return false;
            }
        }

        // 所有条件满足，执行seek跳转
        log.info("[回放seek] 复用现有会话，执行seek跳转: playbackKey={}, sessionId={}, 新时间={}", 
            playbackKey, existingSessionId, newStartTime);

        boolean seekSuccess = seekPlayback(lUserID, playbackKey, existingSessionId, newStart);
        if (seekSuccess) {
            // seek成功，更新会话时间信息
            StreamManager.playbackKeyAndStartTimeMap.put(playbackKey, newStart);
            LocalDateTime newEnd = parseDateTime(newEndTime);
            if (newEnd != null) {
                StreamManager.playbackKeyAndEndTimeMap.put(playbackKey, newEnd);
            }
            StreamManager.playbackKeyAndPlayDateMap.put(playbackKey, newPlayDate);
            log.info("[回放seek] seek跳转成功: playbackKey={}", playbackKey);
            return true;
        } else {
            log.warn("[回放seek] seek跳转失败，需要新建会话: playbackKey={}", playbackKey);
            return false;
        }
    }

    /**
     * 解析时间字符串
     */
    private LocalDateTime parseDateTime(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            log.warn("时间解析失败: {}", timeStr);
            return null;
        }
    }

    /**
     * 回放seek跳转 - 复用现有会话跳转到新的时间点
     * 使用 NET_ECMS_PlayBackOperate 接口，enumMode=SEEK(3)
     */
    private boolean seekPlayback(Integer luserId, String playbackKey, int lSessionID, LocalDateTime seekTime) {
        try {
            if (seekTime == null) {
                log.warn("seek时间为空，无法跳转");
                return false;
            }

            // 构造seek参数
            HCISUPCMS.NET_EHOME_PLAYBACK_SEEK_PARAM seekParam = new HCISUPCMS.NET_EHOME_PLAYBACK_SEEK_PARAM();
            seekParam.lSessionID = lSessionID;

            // 获取回放句柄
            Integer playbackHandle = StreamManager.playbackKeyAndPlaybackHandleMap.get(playbackKey);
            if (playbackHandle != null) {
                seekParam.lHandle = playbackHandle;
            }

            // 设置跳转时间
            seekParam.struSeekTime.wYear = (short) seekTime.getYear();
            seekParam.struSeekTime.byMonth = (byte) seekTime.getMonthValue();
            seekParam.struSeekTime.byDay = (byte) seekTime.getDayOfMonth();
            seekParam.struSeekTime.byHour = (byte) seekTime.getHour();
            seekParam.struSeekTime.byMinute = (byte) seekTime.getMinute();
            seekParam.struSeekTime.bySecond = (byte) seekTime.getSecond();
            seekParam.struSeekTime.wMSecond = (short) (seekTime.getNano() / 1_000_000);
            seekParam.write();

            log.info("调用NET_ECMS_PlayBackOperate SEEK: luserId={}, sessionId={}, seekTime={}", 
                luserId, lSessionID, seekTime);

            boolean b = CmsService.hCEhomeCMS.NET_ECMS_PlayBackOperate(
                luserId, 
                HCISUPCMS.PLAYBACK_OPERATE_SEEK, 
                seekParam.getPointer()
            );

            if (!b) {
                int errorCode = CmsService.hCEhomeCMS.NET_ECMS_GetLastError();
                log.error("NET_ECMS_PlayBackOperate SEEK失败，错误代码:{}", errorCode);
                return false;
            }

            log.info("NET_ECMS_PlayBackOperate SEEK成功: sessionId={}, seekTime={}", lSessionID, seekTime);

            // 重置PlaybackStreamHandler的状态，准备接收新时间的数据
            PlaybackStreamHandler handler = StreamManager.sessionIDAndPlaybackStreamHandlerMap.get(lSessionID);
            if (handler != null) {
                handler.reset();
                log.info("已重置PlaybackStreamHandler: sessionId={}", lSessionID);
            }

            return true;
        } catch (Exception e) {
            log.error("seekPlayback异常: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 停止回放
     *
     * @param lUserID
     * @param id
     * @param channel
     * @param playbackKey
     */
    @Override
    public void stopPlayback(Integer lUserID, Long id, Integer channel, String playbackKey) {
        CountDownLatch latch = StreamManager.playbackKeyAndLatchMap.get(playbackKey);
        if (latch != null) {
            latch.countDown();
            log.info("结束回放实例: {}", playbackKey);
        }

        RtpServerParam rtpServerParam = StreamManager.playbackKeyAndRtpServerParamMap.get(playbackKey);
        cleanupPlaybackResources(playbackKey, rtpServerParam);

        StreamManager.playbackKeyAndLatchMap.remove(playbackKey);
        log.info("停止回放，设备id: {}, 通道号: {}", id, channel);
    }

    /**
     * 统一回放资源清理方法
     *
     * @param playbackKey
     * @param rtpServerParam
     */
    @Override
    public void cleanupPlaybackResources(String playbackKey, RtpServerParam rtpServerParam) {
        Integer sessionId = StreamManager.playbackKeyAndSessionIDMap.get(playbackKey);
        Integer luserId = StreamManager.playbackKeyAndLuserIdMap.get(playbackKey);
        Integer playbackHandleId = null;
        
        // 通过 sessionId 获取 playbackHandleId
        if (sessionId != null) {
            playbackHandleId = StreamManager.sessionIDAndPlaybackHandleMap.get(sessionId);
        }
        
        PlaybackStreamHandler playbackStreamHandler = null;

        if (sessionId != null) {
            playbackStreamHandler = StreamManager.sessionIDAndPlaybackStreamHandlerMap.get(sessionId);
        }

        try {
            if (playbackHandleId != null) {
                StreamService.hCEhomeStream.NET_ESTREAM_StopPlayBack(playbackHandleId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 停止回放失败，playbackKey: {}", playbackKey, e);
        }

        try {
            if (luserId != null && sessionId != null) {
                CmsService.hCEhomeCMS.NET_ECMS_StopPlayBack(luserId, sessionId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 停止获取回放流失败，playbackKey: {}", playbackKey, e);
        }

        try {
            if (playbackStreamHandler != null && playbackHandleId != null) {
                playbackStreamHandler.close(playbackHandleId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 关闭回放回调失败，playbackKey: {}", playbackKey, e);
        }

        // 清理所有 Map
        if (luserId != null) {
            StreamManager.playbackKeyAndLuserIdMap.remove(playbackKey);
        }
        if (sessionId != null) {
            StreamManager.sessionIDAndPlaybackStreamHandlerMap.remove(sessionId);
            StreamManager.sessionIDAndPlaybackHandleMap.remove(sessionId);
            StreamManager.luserIdAndPlaybackRtpServerParamMap.remove(sessionId);
        }
        if (playbackHandleId != null) {
            StreamManager.playbackHandSAndSessionIDandMap.remove(playbackHandleId);
        }

        StreamManager.playbackKeyAndPlaybackHandleMap.remove(playbackKey);
        StreamManager.playbackKeyAndRtpServerParamMap.remove(playbackKey);
        StreamManager.playbackKeyAndSessionIDMap.remove(playbackKey);
        
        // 清理会话时间跟踪信息
        StreamManager.playbackKeyAndStartTimeMap.remove(playbackKey);
        StreamManager.playbackKeyAndEndTimeMap.remove(playbackKey);
        StreamManager.playbackKeyAndPlayDateMap.remove(playbackKey);

        // 清理 zlm 资源
        if (rtpServerParam != null) {
            cleanupPlaybackZlmResources(playbackKey, rtpServerParam);
        }
    }

    /**
     * 清理回放 zlm 资源
     *
     * @param playbackKey
     * @param rtpServerParam
     */
    private void cleanupPlaybackZlmResources(String playbackKey, RtpServerParam rtpServerParam) {
        try {
            log.info("[海康设备] 清理回放 zlm 资源，playbackKey: {}, ssrc: {}", playbackKey, rtpServerParam.getSsrc());
            remoteZlmService.releaseSsrc(rtpServerParam.getMediaServerId(), rtpServerParam.getSsrc());
            remoteZlmService.closeRTPServer(rtpServerParam.getMediaServerId(), rtpServerParam);
        } catch (Exception e) {
            log.error("[海康设备] 清理回放 zlm 资源失败，playbackKey: {}", playbackKey, e);
        }
    }

    private void RealPlayback(Integer luserId, QsDevice device, String playbackKey, RtpServerParam rtpServerParam) {
        log.info("开始回放海康设备, deviceId: {}, ip: {}, lUserID: {}, channel: {}, startTime: {}, endTime: {}",
                device.getId(), device.getIpAddress(), luserId,
                device.getChannel(), rtpServerParam.getStartTime(), rtpServerParam.getEndTime());

        // 验证登录句柄
        if (luserId == null) {
            log.error("登录句柄无效, deviceId: {}", device.getId());
            throw new RuntimeException("登录句柄无效");
        }

        int channelToUse = device.getChannel();

        // 构造回放参数
        HCISUPCMS.NET_EHOME_PLAYBACK_INFO_IN m_struPlayBackInfoIn = new HCISUPCMS.NET_EHOME_PLAYBACK_INFO_IN();
        m_struPlayBackInfoIn.read();
        m_struPlayBackInfoIn.dwSize = m_struPlayBackInfoIn.size();
        m_struPlayBackInfoIn.dwChannel = channelToUse; //通道号
        m_struPlayBackInfoIn.byPlayBackMode = 1;//0- 按文件名回放，1- 按时间回放
        m_struPlayBackInfoIn.byStreamPackage = 0;//回放码流类型，设备端发出的码流格式 0－PS（默认） 1－RTP
        m_struPlayBackInfoIn.unionPlayBackMode.setType(HCISUPCMS.NET_EHOME_PLAYBACKBYTIME.class);

        // 解析开始时间
        String startTime = rtpServerParam.getStartTime();
        String endTime = rtpServerParam.getEndTime();
        String[] dateStartByFile = startTime.split(" ");
        String[] dateStart1 = dateStartByFile[0].split("-");
        String[] dateStart2 = dateStartByFile[1].split(":");

        // 填充开始时间
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime = new HCISUPCMS.NET_EHOME_TIME();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.wYear = (short) Integer.parseInt(dateStart1[0]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byMonth = (byte) Integer.parseInt(dateStart1[1]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byDay = (byte) Integer.parseInt(dateStart1[2]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byHour = (byte) Integer.parseInt(dateStart2[0]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byMinute = (byte) Integer.parseInt(dateStart2[1]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.bySecond = (byte) Integer.parseInt(dateStart2[2]);

        // 解析结束时间
        String[] dateEndByFile = endTime.split(" ");
        String[] dateEnd1 = dateEndByFile[0].split("-");
        String[] dateEnd2 = dateEndByFile[1].split(":");

        // 填充结束时间
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime = new HCISUPCMS.NET_EHOME_TIME();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.wYear = (short) Integer.parseInt(dateEnd1[0]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byMonth = (byte) Integer.parseInt(dateEnd1[1]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byDay = (byte) Integer.parseInt(dateEnd1[2]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byHour = (byte) Integer.parseInt(dateEnd2[0]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byMinute = (byte) Integer.parseInt(dateEnd2[1]);
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.bySecond = (byte) Integer.parseInt(dateEnd2[2]);

        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.byLocalOrUTC = 0; //0-设备本地时间
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.byDuplicateSegment = 0; //0-重复时间段的前段

        System.arraycopy(haikangIsupConfig.getSmsBackServer().getIp().getBytes(), 0, m_struPlayBackInfoIn.struStreamSever.szIP,
                0, haikangIsupConfig.getSmsBackServer().getIp().length());
        m_struPlayBackInfoIn.struStreamSever.wPort = (short) haikangIsupConfig.getSmsBackServer().getPort();
        m_struPlayBackInfoIn.write();

        HCISUPCMS.NET_EHOME_PLAYBACK_INFO_OUT m_struPlayBackInfoOut = new HCISUPCMS.NET_EHOME_PLAYBACK_INFO_OUT();
        m_struPlayBackInfoOut.write();

        if (!CmsService.hCEhomeCMS.NET_ECMS_StartPlayBack(luserId, m_struPlayBackInfoIn, m_struPlayBackInfoOut)) {
            log.error("NET_ECMS_StartPlayBack失败，错误代码:" + CmsService.hCEhomeCMS.NET_ECMS_GetLastError());
            throw new RuntimeException("NET_ECMS_StartPlayBack失败");
        } else {
            m_struPlayBackInfoOut.read();
            log.info("NET_ECMS_StartPlayBack成功，lSessionID:" + m_struPlayBackInfoOut.lSessionID);
        }

        int lSessionID = m_struPlayBackInfoOut.lSessionID;

        StreamManager.playbackKeyAndSessionIDMap.put(playbackKey, lSessionID);
        StreamManager.playbackKeyAndLuserIdMap.put(playbackKey, luserId);
        StreamManager.playbackKeyAndPlaybackHandleMap.put(playbackKey, lSessionID);

        // 设置 sessionID 与 rtpServerParam 的映射（供回调函数使用）
        StreamManager.luserIdAndPlaybackRtpServerParamMap.put(lSessionID, rtpServerParam);

        HCISUPCMS.NET_EHOME_PUSHPLAYBACK_IN m_struPushPlayBackIn = new HCISUPCMS.NET_EHOME_PUSHPLAYBACK_IN();
        m_struPushPlayBackIn.read();
        m_struPushPlayBackIn.dwSize = m_struPushPlayBackIn.size();
        m_struPushPlayBackIn.lSessionID = lSessionID;
        m_struPushPlayBackIn.write();

        HCISUPCMS.NET_EHOME_PUSHPLAYBACK_OUT m_struPushPlayBackOut = new HCISUPCMS.NET_EHOME_PUSHPLAYBACK_OUT();
        m_struPushPlayBackOut.read();
        m_struPushPlayBackOut.dwSize = m_struPushPlayBackOut.size();
        m_struPushPlayBackOut.write();

        if (!CmsService.hCEhomeCMS.NET_ECMS_StartPushPlayBack(luserId, m_struPushPlayBackIn, m_struPushPlayBackOut)) {
            int errorCode = CmsService.hCEhomeCMS.NET_ECMS_GetLastError();
            log.error("NET_ECMS_StartPushPlayBack失败，错误代码:" + errorCode);
            throw new RuntimeException("NET_ECMS_StartPushPlayBack失败，错误代码:" + errorCode);
        }
        log.info("NET_ECMS_StartPushPlayBack成功，lSessionID:" + lSessionID);
    }

    /**
     * 按时间下载录像
     *
     * @param lUserID
     * @param device
     * @param channelId
     * @param startTime
     * @param endTime
     * @param filePath
     * @return
     */
    @Override
    public File downloadRecordByTime(Integer lUserID, QsDevice device, Integer channelId, String startTime, String endTime, String filePath) {
        String downloadKey = "download_" + device.getId() + "_" + channelId + "_" + System.currentTimeMillis();
        
        log.info("开始下载海康设备录像, deviceId: {}, channelId: {}, startTime: {}, endTime: {}, filePath: {}",
                device.getId(), channelId, startTime, endTime, filePath);
        
        CountDownLatch latch = new CountDownLatch(1);
        downloadLatchMap.put(downloadKey, latch);
        // 同时也设置到PlaybackStreamHandler的静态map中
        PlaybackStreamHandler.downloadLatchMap.put(downloadKey, latch);
        
        try {
            // 开始下载
            startDownloadInternal(lUserID, device, channelId, startTime, endTime, filePath, downloadKey);
            
            // 等待下载完成（最多等待10分钟）
            boolean completed = latch.await(10, TimeUnit.MINUTES);
            if (!completed) {
                log.error("下载超时，downloadKey: {}", downloadKey);
                throw new RuntimeException("下载超时");
            }
            
            // 检查文件是否存在
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                log.error("下载的文件不存在或为空，filePath: {}", filePath);
                throw new RuntimeException("下载失败：文件不存在或为空");
            }
            
            log.info("海康设备录像下载成功, deviceId: {}, filePath: {}, fileSize: {} bytes",
                    device.getId(), filePath, file.length());
            
            return file;
        } catch (InterruptedException e) {
            log.error("下载被中断, downloadKey: {}", downloadKey, e);
            Thread.currentThread().interrupt();
            throw new RuntimeException("下载被中断", e);
        } catch (Exception e) {
            log.error("海康设备录像下载失败, deviceId: {}, error: {}", device.getId(), e.getMessage(), e);
            throw new RuntimeException("下载失败：" + e.getMessage(), e);
        } finally {
            downloadLatchMap.remove(downloadKey);
            cleanupDownloadResources(downloadKey, lUserID);
        }
    }

    /**
     * 内部开始下载方法
     */
    private void startDownloadInternal(Integer luserId, QsDevice device, Integer channelId, String startTime, 
                                        String endTime, String filePath, String downloadKey) {
        log.info("开始下载内部方法, deviceId: {}, ip: {}, lUserID: {}, channel: {}, startTime: {}, endTime: {}",
                device.getId(), device.getIpAddress(), luserId, channelId, startTime, endTime);

        // 验证登录句柄
        if (luserId == null) {
            log.error("登录句柄无效, deviceId: {}", device.getId());
            throw new RuntimeException("登录句柄无效");
        }

        // 构造回放参数
        HCISUPCMS.NET_EHOME_PLAYBACK_INFO_IN m_struPlayBackInfoIn = new HCISUPCMS.NET_EHOME_PLAYBACK_INFO_IN();
        m_struPlayBackInfoIn.read();
        m_struPlayBackInfoIn.dwSize = m_struPlayBackInfoIn.size();
        m_struPlayBackInfoIn.dwChannel = channelId; //通道号
        m_struPlayBackInfoIn.byPlayBackMode = 1;//0- 按文件名回放，1- 按时间回放
        m_struPlayBackInfoIn.byStreamPackage = 0;//回放码流类型，设备端发出的码流格式 0－PS（默认） 1－RTP
        m_struPlayBackInfoIn.unionPlayBackMode.setType(HCISUPCMS.NET_EHOME_PLAYBACKBYTIME.class);

        // 解析时间
        DateTimeFormatter formatter;
        if (startTime.contains("T")) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }

        LocalDateTime start = LocalDateTime.parse(startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endTime, formatter);

        // 填充开始时间
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime = new HCISUPCMS.NET_EHOME_TIME();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.wYear = (short) start.getYear();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byMonth = (byte) start.getMonthValue();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byDay = (byte) start.getDayOfMonth();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byHour = (byte) start.getHour();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.byMinute = (byte) start.getMinute();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStartTime.bySecond = (byte) start.getSecond();

        // 填充结束时间
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime = new HCISUPCMS.NET_EHOME_TIME();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.wYear = (short) end.getYear();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byMonth = (byte) end.getMonthValue();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byDay = (byte) end.getDayOfMonth();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byHour = (byte) end.getHour();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.byMinute = (byte) end.getMinute();
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.struStopTime.bySecond = (byte) end.getSecond();

        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.byLocalOrUTC = 0; //0-设备本地时间
        m_struPlayBackInfoIn.unionPlayBackMode.struPlayBackbyTime.byDuplicateSegment = 0; //0-重复时间段的前段

        System.arraycopy(haikangIsupConfig.getSmsBackServer().getIp().getBytes(), 0, m_struPlayBackInfoIn.struStreamSever.szIP,
                0, haikangIsupConfig.getSmsBackServer().getIp().length());
        m_struPlayBackInfoIn.struStreamSever.wPort = (short) haikangIsupConfig.getSmsBackServer().getPort();
        m_struPlayBackInfoIn.write();

        HCISUPCMS.NET_EHOME_PLAYBACK_INFO_OUT m_struPlayBackInfoOut = new HCISUPCMS.NET_EHOME_PLAYBACK_INFO_OUT();
        m_struPlayBackInfoOut.write();

        if (!CmsService.hCEhomeCMS.NET_ECMS_StartPlayBack(luserId, m_struPlayBackInfoIn, m_struPlayBackInfoOut)) {
            log.error("NET_ECMS_StartPlayBack失败，错误代码:" + CmsService.hCEhomeCMS.NET_ECMS_GetLastError());
            throw new RuntimeException("NET_ECMS_StartPlayBack失败");
        } else {
            m_struPlayBackInfoOut.read();
            log.info("NET_ECMS_StartPlayBack成功，lSessionID:" + m_struPlayBackInfoOut.lSessionID);
        }

        int lSessionID = m_struPlayBackInfoOut.lSessionID;

        // 设置映射
        StreamManager.playbackKeyAndSessionIDMap.put(downloadKey, lSessionID);
        StreamManager.playbackKeyAndLuserIdMap.put(downloadKey, luserId);
        StreamManager.playbackKeyAndPlaybackHandleMap.put(downloadKey, lSessionID);
        StreamManager.playbackUserIDandTypeMap.put(lSessionID, "download");
        StreamManager.playbackUserIDandFilePathMap.put(lSessionID, filePath);
        StreamManager.sessionIDandDownloadKeyMap.put(lSessionID, downloadKey);

        HCISUPCMS.NET_EHOME_PUSHPLAYBACK_IN m_struPushPlayBackIn = new HCISUPCMS.NET_EHOME_PUSHPLAYBACK_IN();
        m_struPushPlayBackIn.read();
        m_struPushPlayBackIn.dwSize = m_struPushPlayBackIn.size();
        m_struPushPlayBackIn.lSessionID = lSessionID;
        m_struPushPlayBackIn.write();

        HCISUPCMS.NET_EHOME_PUSHPLAYBACK_OUT m_struPushPlayBackOut = new HCISUPCMS.NET_EHOME_PUSHPLAYBACK_OUT();
        m_struPushPlayBackOut.read();
        m_struPushPlayBackOut.dwSize = m_struPushPlayBackOut.size();
        m_struPushPlayBackOut.write();

        if (!CmsService.hCEhomeCMS.NET_ECMS_StartPushPlayBack(luserId, m_struPushPlayBackIn, m_struPushPlayBackOut)) {
            log.error("NET_ECMS_StartPushPlayBack失败，错误代码:" + CmsService.hCEhomeCMS.NET_ECMS_GetLastError());
            throw new RuntimeException("NET_ECMS_StartPushPlayBack失败");
        } else {
            log.info("NET_ECMS_StartPushPlayBack成功，lSessionID:" + lSessionID);
        }
    }

    /**
     * 清理下载资源
     */
    private void cleanupDownloadResources(String downloadKey, Integer luserId) {
        Integer sessionId = StreamManager.playbackKeyAndSessionIDMap.get(downloadKey);
        Integer playbackHandleId = null;

        if (sessionId != null) {
            playbackHandleId = StreamManager.sessionIDAndPlaybackHandleMap.get(sessionId);
        }

        try {
            if (playbackHandleId != null) {
                StreamService.hCEhomeStream.NET_ESTREAM_StopPlayBack(playbackHandleId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 停止下载回放失败, downloadKey: {}", downloadKey, e);
        }

        try {
            if (luserId != null && sessionId != null) {
                CmsService.hCEhomeCMS.NET_ECMS_StopPlayBack(luserId, sessionId);
            }
        } catch (Exception e) {
            log.error("[海康设备] 停止获取下载回放流失败, downloadKey: {}", downloadKey, e);
        }

        // 清理所有 Map
        if (luserId != null) {
            StreamManager.playbackKeyAndLuserIdMap.remove(downloadKey);
        }
        if (sessionId != null) {
            StreamManager.sessionIDAndPlaybackHandleMap.remove(sessionId);
            StreamManager.playbackUserIDandTypeMap.remove(sessionId);
            StreamManager.playbackUserIDandFilePathMap.remove(sessionId);
        }
        if (playbackHandleId != null) {
            StreamManager.playbackHandSAndSessionIDandMap.remove(playbackHandleId);
        }

        StreamManager.playbackKeyAndPlaybackHandleMap.remove(downloadKey);
        StreamManager.playbackKeyAndSessionIDMap.remove(downloadKey);
        if (sessionId != null) {
            StreamManager.sessionIDandDownloadKeyMap.remove(sessionId);
        }
        
        // 清理PlaybackStreamHandler中的latch
        PlaybackStreamHandler.downloadLatchMap.remove(downloadKey);
        
        // 唤醒等待的线程
        CountDownLatch latch = downloadLatchMap.get(downloadKey);
        if (latch != null) {
            latch.countDown();
        }
    }
}
