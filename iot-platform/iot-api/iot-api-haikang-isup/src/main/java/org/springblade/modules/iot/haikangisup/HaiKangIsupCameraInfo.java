package org.springblade.modules.iot.haikangisup;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 海康ISUP摄像头信息
 *
 * @author fengcheng
 */
@Data
public class HaiKangIsupCameraInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Data
	public static class CameraInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		/**
		 * 通道号
		 */
		private int channelId;

		/**
		 * 通道名称
		 */
		private String channelName;

		private String cameraName;

		private String cameraType;

		private String statusDesc;

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
	/**
	 * 摄像头数量
	 */
	private Integer cameraCount;

	/**
	 * 摄像头信息列表
	 */
	private List<CameraInfo> cameraList = new ArrayList<>();

	/**
	 * 是否获取成功
	 */
	private Boolean success;

	/**
	 * 错误信息
	 */
	private String errorMessage;
}
