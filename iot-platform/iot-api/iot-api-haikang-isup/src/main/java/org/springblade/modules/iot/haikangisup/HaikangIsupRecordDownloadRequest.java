package org.springblade.modules.iot.haikangisup;

import lombok.Data;

import java.io.Serializable;

/**
 * 海康ISUP录像下载请求
 *
 * @author fengcheng
 */
@Data
public class HaikangIsupRecordDownloadRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 设备IP
	 */
	private String deviceIp;

	/**
	 * 通道号
	 */
	private int channelId;

	/**
	 * 开始时间 (yyyy-MM-dd HH:mm:ss)
	 */
	private String startTime;

	/**
	 * 结束时间 (yyyy-MM-dd HH:mm:ss)
	 */
	private String endTime;

	/**
	 * 录像类型：0-全部，1-定时录像，2-移动侦测，3-报警录像
	 */
	private int recordType;

	/**
	 * 下载保存路径
	 */
	private String savePath;
}
