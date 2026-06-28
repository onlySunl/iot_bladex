package org.springblade.modules.iot.haikangisup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公共方法工具类2
 */
public class CommonMethod {

	/**
	 * 获取当前时间字符串
	 *
	 * @return 时间字符串
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 格式化日期
	 *
	 * @param date 日期对象
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 格式化日期
	 *
	 * @param timestamp 时间戳
	 * @return 格式化后的字符串
	 */
	public static String formatDate(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(timestamp));
	}

	/**
	 * 字符串是否为空
	 *
	 * @param str 字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * 字符串是否不为空
	 *
	 * @param str 字符串
	 * @return 是否不为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
