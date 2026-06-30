package org.springblade.modules.iot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 海康ISUP设备信息
 *
 * @author fengcheng
 */
@Data
public class HaiKangIsupDeviceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 模拟通道个数
	 */
	private int dwChannelNumber;

	/**
	 * 通道总数（包括模拟通道和数字通道）
	 */
	private int dwChannelAmount;

	/**
	 * 设备类型：1-数字视频录像机，3-数字视频服务器，30-网络摄像机，40-网络球机
	 */
	private int dwDevType;

	/**
	 * 设备中的硬盘数量
	 */
	private int dwDiskNumber;

	/**
	 * 设备序列号
	 */
	private String sSerialNumber;

	/**
	 * 模拟通道关联的报警输入个数
	 */
	private int dwAlarmInPortNum;

	/**
	 * 报警输入总数
	 */
	private int dwAlarmInAmount;

	/**
	 * 模拟通道关联的报警输出个数
	 */
	private int dwAlarmOutPortNum;

	/**
	 * 报警输出总数
	 */
	private int dwAlarmOutAmount;

	/**
	 * 起始视频通道号
	 */
	private int dwStartChannel;

	/**
	 * 语音对讲通道个数
	 */
	private int dwAudioChanNum;

	/**
	 * 设备支持的最大数字通道个数
	 */
	private int dwMaxDigitChannelNum;

	/**
	 * 语音对讲的音频格式：0-G.722，1-G.711U，2-G.711A，3-G.726，4-AAC，5-MP2L2
	 */
	private int dwAudioEncType;

	/**
	 * SIM卡序列号（车载设备扩展）
	 */
	private String sSIMCardSN;

	/**
	 * SIM卡手机号码（车载设备扩展）
	 */
	private String sSIMCardPhoneNum;

	/**
	 * 支持的零通道个数
	 */
	private int dwSupportZeroChan;

	/**
	 * 零通道的起始编号，默认为10000
	 */
	private int dwStartZeroChan;

	/**
	 * 0-智能（默认），1-专业智能
	 */
	private int dwSmartType;
}
