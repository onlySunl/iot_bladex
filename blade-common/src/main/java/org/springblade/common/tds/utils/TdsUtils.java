package org.springblade.common.tds.utils;

/**
 * TDengine 工具类
 */
public class TdsUtils {
    public static String buildTableName(String deviceId) {
        return "device_" + deviceId.replaceAll("-", "_");
    }
    
    public static String buildDatabaseName(String tenantId) {
        return "iot_" + tenantId;
    }
}
