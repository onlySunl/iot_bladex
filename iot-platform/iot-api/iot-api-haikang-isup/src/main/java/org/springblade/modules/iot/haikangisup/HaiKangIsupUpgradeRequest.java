package org.springblade.modules.iot.haikangisup;

import lombok.Data;

import java.io.Serializable;

/**
 * 海康ISUP升级请求
 *
 * @author fengcheng
 */
@Data
public class HaiKangIsupUpgradeRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 设备IP
	 */
	private String deviceIp;

	/**
	 * 升级包路径
	 */
	private String filePath;

	/**
	 * 升级类型：0-设备本身，1-摄像机
	 */
	private int upgradeType;
}
