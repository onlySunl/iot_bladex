package org.springblade.modules.iot.haikangisup;

import lombok.Data;

import java.io.Serializable;

/**
 * 海康ISUP摄像头信息
 *
 * @author fengcheng
 */
@Data
public class HaiKangIsupCameraInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 通道号
	 */
	private int channelId;

	/**
	 * 通道名称
	 */
	private String channelName;

	/**
	 * 设备ID
	 */
	private Long deviceId;

	/**
	 * 是否在线
	 */
	private boolean online;

	/**
	 * 制造商
	 */
	private String manufacturer;

	/**
	 * 型号
	 */
	private String model;

	/**
	 * IP地址
	 */
	private String ipAddress;
}
