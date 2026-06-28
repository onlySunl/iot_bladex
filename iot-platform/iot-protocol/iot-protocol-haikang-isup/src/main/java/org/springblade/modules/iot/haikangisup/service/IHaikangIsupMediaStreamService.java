package org.springblade.modules.iot.haikangisup.service;

/**
 * 海康ISUP媒体流服务接口
 */
public interface IHaikangIsupMediaStreamService {

	/**
	 * 开始播放
	 *
	 * @param lUserID 用户ID
	 * @param streamKey 流键
	 * @param params 参数
	 */
	void startPlay(Integer lUserID, String streamKey, Object params);

	/**
	 * 停止播放
	 *
	 * @param lUserID 用户ID
	 * @param streamKey 流键
	 */
	void stopPlay(Integer lUserID, String streamKey);

	/**
	 * 开始回放
	 *
	 * @param lUserID 用户ID
	 * @param channelId 通道ID
	 * @param startTime 开始时间
	 * @param stopTime 结束时间
	 * @param streamKey 流键
	 */
	void startPlayback(Integer lUserID, Integer channelId, String startTime, String stopTime, String streamKey);

	/**
	 * 停止回放
	 *
	 * @param lUserID 用户ID
	 * @param streamKey 流键
	 */
	void stopPlayback(Integer lUserID, String streamKey);
}
