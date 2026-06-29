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
	 * 设备ID
	 */
	private Long deviceId;

	/**
	 * FTP服务器IP地址
	 */
	private String ftpServerIp;

	/**
	 * FTP服务器端口号，默认21
	 */
	private Integer ftpServerPort = 21;

	/**
	 * FTP用户名
	 */
	private String ftpAccount;

	/**
	 * FTP密码
	 */
	private String ftpPassword;

	/**
	 * 升级文件名
	 */
	private String fileName;

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

	/**
	 * 待升级设备关联的通道号
	 */
	private Integer channel = 1;
}
