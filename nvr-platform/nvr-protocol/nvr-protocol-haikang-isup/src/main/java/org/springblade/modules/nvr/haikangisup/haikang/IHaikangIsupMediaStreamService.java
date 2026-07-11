package org.springblade.modules.nvr.haikangisup.haikang;

import org.springblade.modules.nvr.common.domain.RtpServerParam;
import org.springblade.modules.nvr.domain.QsDevice;

import java.io.File;

/**
 * @FileName IHaikangIsupMediaStreamService
 * @Description
 * @Author fengcheng
 * @date 2026-04-08
 **/
public interface IHaikangIsupMediaStreamService {

    /**
     * 开始播放
     *
     * @param lUserID
     * @param device
     * @param streamKey
     * @param rtpServerParam
     */
    void startPlay(Integer lUserID, QsDevice device, String streamKey, RtpServerParam rtpServerParam);

    /**
     * 停止播放
     *
     * @param lUserID
     * @param id
     * @param channel
     * @param streamKey
     */
    void stopPlay(Integer lUserID, Long id, Integer channel, String streamKey);

    /**
     * 开始回放
     *
     * @param lUserID
     * @param device
     * @param playbackKey
     * @param rtpServerParam
     */
    void startPlayback(Integer lUserID, QsDevice device, String playbackKey, RtpServerParam rtpServerParam);

    /**
     * 停止回放
     *
     * @param lUserID
     * @param id
     * @param channel
     * @param playbackKey
     */
    void stopPlayback(Integer lUserID, Long id, Integer channel, String playbackKey);

    /**
     * 清理回放资源
     *
     * @param playbackKey
     * @param rtpServerParam
     */
    void cleanupPlaybackResources(String playbackKey, RtpServerParam rtpServerParam);

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
    File downloadRecordByTime(Integer lUserID, QsDevice device, Integer channelId, String startTime, String endTime, String filePath);

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
    boolean trySeekPlayback(String playbackKey, Integer lUserID, String newStartTime, String newEndTime);
}
