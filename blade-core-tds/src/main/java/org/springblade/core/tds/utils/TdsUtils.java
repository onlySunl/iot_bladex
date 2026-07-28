package org.springblade.core.tds.utils;

/**
 * TDS 工具类
 *
 * @author Chill
 */
public final class TdsUtils {

    private TdsUtils() {}

    /**
     * 构建表名
     */
    public static String buildTableName(String productId) {
        return "device_" + productId.replaceAll("-", "_");
    }

    /**
     * 构建子表名
     */
    public static String buildSubTableName(String productId, String deviceId) {
        return "device_" + productId.replaceAll("-", "_") + "_" + deviceId.replaceAll("-", "_");
    }

    /**
     * 构建标签名
     */
    public static String buildTagName(String tagName) {
        return TdsConstants.TAG_PREFIX + tagName;
    }
}
