package org.springblade.modules.iot.haikangisup.utils;

/**
 * 公共方法工具类
 */
public class CommonUtil {

	/**
	 * 解析海康设备字符串
	 *
	 * @param data 字节数组
	 * @return 字符串
	 */
	public static String parseHikvisionString(byte[] data) {
		if (data == null || data.length == 0) {
			return "";
		}
		int endIndex = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == 0) {
				endIndex = i;
				break;
			}
		}
		if (endIndex == 0) {
			endIndex = data.length;
		}
		return new String(data, 0, endIndex).trim();
	}

	/**
	 * 格式化字节数组为十六进制字符串
	 *
	 * @param bytes 字节数组
	 * @return 十六进制字符串
	 */
	public static String bytesToHexString(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}

	/**
	 * 格式化时间
	 *
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @return 格式化后的时间字符串
	 */
	public static String formatTime(int year, int month, int day, int hour, int minute, int second) {
		return String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
	}
}
