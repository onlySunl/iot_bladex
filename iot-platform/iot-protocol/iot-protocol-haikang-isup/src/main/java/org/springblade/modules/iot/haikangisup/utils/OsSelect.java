package org.springblade.modules.iot.haikangisup.utils;

/**
 * 操作系统判断工具类
 */
public class OsSelect {

	public static boolean isLinux() {
		return System.getProperty("os.name").toLowerCase().contains("linux");
	}

	public static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("windows");
	}
}
